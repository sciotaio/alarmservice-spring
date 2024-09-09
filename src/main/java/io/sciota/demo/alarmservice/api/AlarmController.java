package io.sciota.demo.alarmservice.api;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.sciota.demo.alarmservice.dtos.EventDto;
import io.sciota.demo.alarmservice.mapper.DateUtils;
import io.sciota.demo.alarmservice.mapper.DtoMapper;
import io.sciota.demo.alarmservice.mapper.DtoValidation;
import io.sciota.demo.alarmservice.persistence.Alarm;
import io.sciota.demo.alarmservice.persistence.AlarmRepository;
import io.sciota.demo.alarmservice.persistence.RoomRepository;
import io.sciota.demo.alarmservice.persistence.ScheduleRepository;
import jakarta.annotation.Resource;

@RestController
public class AlarmController {

	private static final Logger log = LoggerFactory.getLogger(AlarmController.class);

	private final AtomicLong counter = new AtomicLong();

	@Resource
	AlarmRepository alarmRepository;

	@Resource
	ScheduleRepository scheduleRepository;

	@Resource
	RoomRepository roomRepository;

	@GetMapping("/hello")
	public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return "Ok #" + counter.getAndIncrement() + " from '" + name + "'";
	}

	@GetMapping("/room")
	public List<RoomDto> getRooms() {
		return StreamSupport.stream(roomRepository.findAll().spliterator(), false)
				.map(DtoMapper::from)
				.collect(Collectors.toList());
	}

	@GetMapping("/schedule")
	public List<ScheduleDto> getSchedules(@RequestParam(value = "roomId", required = true) long roomId) {
		if (!scheduleRepository.existsById(roomId)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					String.format("room with id '%d' does not exist.", roomId));
		}

		// query db
		var scheds = scheduleRepository.findByRoomId(roomId);

		// map to dto
		var res = scheds
				.stream()
				.map(DtoMapper::from)
				.collect(Collectors.toList());

		return res;
	}

	@PostMapping("/schedule")
	public ResponseEntity<String> postSchedule(@RequestBody ScheduleDto dto) {
		var room = roomRepository.findById(dto.roomId);
		if (!room.isPresent()) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(String.format("room with id '%d' does not exist.", dto.roomId));
		}

		// validate
		try {
			DtoValidation.validate(dto);
		} catch (IllegalArgumentException e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(String.format("Validation failed: %s", e.getMessage()));
		}

		// insert in DB
		scheduleRepository.save(DtoMapper.from(dto, room.get()));

		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

	@GetMapping("/alarm")
	public List<AlarmDto> getAlarms(@RequestParam(value = "roomId", required = true) long roomId) {
		if (!roomRepository.existsById(roomId)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					String.format("room with id '%d' does not exist.", roomId));
		}

		return alarmRepository.findByRoomId(roomId)
				.stream()
				.map(DtoMapper::from)
				.toList();
	}

	@PostMapping("/event")
	public ResponseEntity<String> postEvent(@RequestBody EventDto event) {
		var room = roomRepository.findById(event.getRoomId());
		if (!room.isPresent()) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(String.format("room with id '%d' does not exist.", event.getRoomId()));
		}

		if (event.getTimestamp() == null) {
			event.setTimestamp(OffsetDateTime.now());
		}

		var date = event.getTimestamp();
		var mins = date.getHour() * 60 + date.getMinute();
		var dayOfWeek = event.getTimestamp().getDayOfWeek();

		var schedules = scheduleRepository.findByRoomId(event.getRoomId());
		var isAlert = StreamSupport.stream(schedules.spliterator(), false).anyMatch((schd) -> {
			if (DateUtils.isToday(dayOfWeek, schd.getActiveDaysOfWeekMask())) {
				var begin = schd.getBeginMinsOfDay();
				var end = schd.getEndMinsOfDay();
				if (end > begin) {
					return mins >= begin && mins < end;
				} else {
					log.error(String.format("Malformed schedule with id '%d'", schd.getId()));
				}
			}
			return false;
		});

		if (isAlert) {
			log.warn(String.format("Event '%s' for room '%d' caused an alert! Saving alert to DB ...",
					event.getEventType(),
					event.getRoomId()));

			Alarm alarm = DtoMapper.from(event, room.get());
			alarmRepository.save(alarm);
		} else {
			log.info(String.format("Event '%s' for room '%d' didn't cause an alert.", event.getEventType(),
					event.getRoomId()));
		}

		// the event won't be stored anywhere (yet?)

		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

	@PostMapping("/alarm/{id}/acknowledge")
	public ResponseEntity<String> postAlarm(@PathVariable Long id) {
		var alarm = alarmRepository.findById(id);
		if (!alarm.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					String.format("alarm with id '%d' does not exist.", id));
		}

		var _alarm = alarm.get();
		_alarm.setAcknowledged(true);
		alarmRepository.save(_alarm);

		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

}