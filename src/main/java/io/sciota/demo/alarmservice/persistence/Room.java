package io.sciota.demo.alarmservice.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    public Room(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format(
                "Room[id=%d, name='%s']",
                id, name);
    }

}