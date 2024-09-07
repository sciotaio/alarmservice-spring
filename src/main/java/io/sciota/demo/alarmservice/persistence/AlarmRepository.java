package io.sciota.demo.alarmservice.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AlarmRepository extends CrudRepository<Alarm, Long> {

    List<Alarm> findByReason(String reason);

    List<Alarm> findByRoomName(String roomName);
    
    List<Alarm> findByRoomId(Long roomId);

    Alarm findById(long id);
}