package io.sciota.demo.alarmservice.mapper;

import io.sciota.demo.alarmservice.api.ScheduleDto;

public class DtoValidation {
    public static void validate(ScheduleDto sched) {
        if (sched.begin >= sched.end) {
            throw new IllegalArgumentException("'begin' must be before 'end'");
        }
    }

}
