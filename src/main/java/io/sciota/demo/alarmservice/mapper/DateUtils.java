package io.sciota.demo.alarmservice.mapper;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils {

  public static Date asDate(OffsetDateTime localDate) {
    return Date.from(localDate.toInstant());
  }

  public static Date asDate(LocalDate localDate) {
    return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
  }

  public static Date asDate(LocalDateTime localDateTime) {
    return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
  }

  public static LocalDate asLocalDate(Date date) {
    return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
  }

  public static LocalDateTime asLocalDateTime(Date date) {
    return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
  }

  public static boolean isToday(DayOfWeek day, int dayOfWeekMask) {
    var ord = DayOfWeek.MONDAY.getValue();
    if (ord != 1) {
      throw new RuntimeException("Expecting MONDAY to start have value of '1'");
    }
    return (dayOfWeekMask & (0b00000001 << (ord - 1))) > 0;
  }
}