package io.sciota.demo.alarmservice.persistence;

import org.springframework.lang.NonNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Table(name = "alarm")
@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Alarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NonNull
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
    
    @NonNull
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date timestamp;

    @NonNull
    private String reason;

    @NonNull
    @Column(columnDefinition = "boolean default false")
    private boolean acknowledged;

    @Override
    public String toString() {
        return String.format(
                "Alarm[id=%d, reason='%s', room='%s']",
                id, reason, room);
    }

}