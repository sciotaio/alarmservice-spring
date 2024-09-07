package io.sciota.demo.alarmservice.api;

import java.util.Date;

public class AlarmDto {
    public Long alarmId;
    public Long roomId;
    public String reason;
    public Boolean acknowledged;
    public Date timestamp;
}