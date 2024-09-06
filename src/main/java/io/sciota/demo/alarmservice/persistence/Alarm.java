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
public class Alarm {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String room;
    private String reason;

    // protected Alarm() {}

    public Alarm(String room, String reason) {
        this.room = room;
        this.reason = reason;
    }

    @Override
    public String toString() {
        return String.format(
                "Alarm[id=%d, firstName='%s', lastName='%s']",
                id, room, reason);
    }

}