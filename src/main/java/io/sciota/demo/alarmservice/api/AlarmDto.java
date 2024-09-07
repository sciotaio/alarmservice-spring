package io.sciota.demo.alarmservice.api;

public class AlarmDto {
    public Long alarmId;
    public Long roomId;
    public String reason;
    public boolean acknowledged;
    public java.util.Date timestamp;
}