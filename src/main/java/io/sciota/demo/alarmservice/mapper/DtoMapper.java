package io.sciota.demo.alarmservice.mapper;

import io.sciota.demo.alarmservice.api.AlarmDto;
import io.sciota.demo.alarmservice.api.RoomDto;
import io.sciota.demo.alarmservice.api.ScheduleDto;
import io.sciota.demo.alarmservice.persistence.Alarm;
import io.sciota.demo.alarmservice.persistence.Room;
import io.sciota.demo.alarmservice.persistence.Schedule;

public class DtoMapper {
    public static ScheduleDto from(Schedule dbSched) {
        ScheduleDto sched = new ScheduleDto();
        sched.roomId = dbSched.getRoom().getId();
        sched.begin = dbSched.getBeginMinsOfDay();
        sched.end = dbSched.getEndMindOfDay();
        sched.days_of_week_mask = dbSched.getActiveDaysOfWeekMask();
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
        alarm.setAcknowledged(dto.acknowledged);
        return alarm;
    }

    public static RoomDto from(Room dbRoom) {
        var room = new RoomDto();
        room.id = dbRoom.getId();
        room.name = dbRoom.getName();
        return room;
    }

}
