package io.sciota.demo.alarmservice.api;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.sciota.demo.alarmservice.persistence.Alarm;
import io.sciota.demo.alarmservice.persistence.AlarmRepository;
import io.sciota.demo.alarmservice.persistence.Room;
import io.sciota.demo.alarmservice.persistence.RoomRepository;
import jakarta.annotation.Resource;

@RestController
public class AlarmController {

	private final AtomicLong counter = new AtomicLong();

	@Resource
	AlarmRepository alarmRepository;

	@Resource
	RoomRepository roomRepository;

	@GetMapping("/hello")
	public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return "Ok #" + counter.getAndIncrement() + " from '" + name + "'";
	}

	@GetMapping("/rooms")
	public List<Room> getRooms() {
		return StreamSupport.stream(roomRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
	}

	@GetMapping("/alarms")
	public List<Alarm> getAlarms(@RequestParam(value = "roomId", required = true) long roomId) {
		if (!roomRepository.existsById(roomId)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					String.format("room with id '%d' does not exist.", roomId));
		}
		return alarmRepository.findByRoom("" + roomId);
	}

	@PostMapping("/alarms")
	public String postAlarm(@RequestBody Alarm alarm) {
		return "todo!";
	}

}