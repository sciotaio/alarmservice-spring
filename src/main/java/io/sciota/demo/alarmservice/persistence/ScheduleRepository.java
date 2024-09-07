package io.sciota.demo.alarmservice.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ScheduleRepository extends CrudRepository<Schedule, Long> {

    List<Schedule> findByRoomId(Long roomId);

    Schedule findById(long id);
    
}