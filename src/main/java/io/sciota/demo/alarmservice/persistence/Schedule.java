package io.sciota.demo.alarmservice.persistence;

import org.springframework.lang.NonNull;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NonNull
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
    
    @NonNull
    private int beginMinsOfDay;

    @NonNull
    private int endMinsOfDay;

    @NonNull
    private int activeDaysOfWeekMask;

    @Override
    public String toString() {
        return String.format(
                "ArmSchedule[id=%d, begin='%s', end='%s', room='%s']",
                id, beginMinsOfDay, endMinsOfDay, activeDaysOfWeekMask, room);
    }

}