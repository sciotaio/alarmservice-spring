package io.sciota.demo.alarmservice.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository<Room, Long> {

    List<Room> findByName(String name);

    Room findById(long id);
}