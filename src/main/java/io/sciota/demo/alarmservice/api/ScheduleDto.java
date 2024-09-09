package io.sciota.demo.alarmservice.api;

public class ScheduleDto {
    public Long scheduleId;
    public Long roomId;
    public Integer begin;
    public Integer end;
    public Integer days_of_week_mask;
}