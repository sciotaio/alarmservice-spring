package io.sciota.demo.alarmservice.mapper;

import java.time.Instant;
import java.util.Date;

import io.sciota.demo.alarmservice.api.AlarmDto;
import io.sciota.demo.alarmservice.api.RoomDto;
import io.sciota.demo.alarmservice.api.ScheduleDto;
import io.sciota.demo.alarmservice.dtos.EventDto;
import io.sciota.demo.alarmservice.persistence.Alarm;
import io.sciota.demo.alarmservice.persistence.Room;
import io.sciota.demo.alarmservice.persistence.Schedule;

public class DtoMapper {
    public static ScheduleDto from(Schedule dbSched) {
        ScheduleDto sched = new ScheduleDto();
        sched.scheduleId = dbSched.getId();
        sched.roomId = dbSched.getRoom().getId();
        sched.begin = dbSched.getBeginMinsOfDay();
        sched.end = dbSched.getEndMinsOfDay();
        sched.days_of_week_mask = dbSched.getActiveDaysOfWeekMask();
        return sched;
    }

    public static Schedule from(ScheduleDto dto, Room dbRoom) {
        Schedule sched = new Schedule();
        sched.setRoom(dbRoom);
        sched.setBeginMinsOfDay(dto.begin);
        sched.setEndMinsOfDay(dto.end);
        sched.setActiveDaysOfWeekMask(dto.days_of_week_mask);
        return sched;
    }

    public static AlarmDto from(Alarm dbAlarm) {
        var alarm = new AlarmDto();
        alarm.alarmId = dbAlarm.getId();
        alarm.roomId = dbAlarm.getRoom().getId();
        alarm.reason = dbAlarm.getReason();
        alarm.timestamp = dbAlarm.getTimestamp();
        alarm.acknowledged = dbAlarm.isAcknowledged();
        return alarm;
    }

    public static Alarm from(AlarmDto dto, Room room) {
        var alarm = new Alarm();
        alarm.setRoom(room);
        alarm.setReason(dto.reason);
        alarm.setTimestamp(dto.timestamp);
        alarm.setAcknowledged(dto.acknowledged == null ? false : dto.acknowledged);
        return alarm;
    }

    public static RoomDto from(Room dbRoom) {
        var room = new RoomDto();
        room.id = dbRoom.getId();
        room.name = dbRoom.getName();
        return room;
    }

    public static Alarm from(EventDto event, Room dbRoom) {
        Alarm alarm = new Alarm();
        alarm.setAcknowledged(false);
        alarm.setRoom(dbRoom);
        alarm.setReason(event.getEventType());
        // alarm.setTimestamp(DateUtils.asDate(event.getTimestamp()));  // use event timestamp
        alarm.setTimestamp(Date.from(Instant.now()));  // overwrite timestamp with new alarm creation timestamp
        return alarm;
    }

}
