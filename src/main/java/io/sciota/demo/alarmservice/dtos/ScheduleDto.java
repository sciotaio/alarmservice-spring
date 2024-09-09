package io.sciota.demo.alarmservice.dtos;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * ScheduleDto
 */

@JsonTypeName("Schedule")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-09-09T12:31:25.209519+02:00[Europe/Berlin]")
public class ScheduleDto {

  private Integer begin;

  private Integer end;

  private Integer daysOfWeekMask;

  private Long roomId;

  public ScheduleDto begin(Integer begin) {
    this.begin = begin;
    return this;
  }

  /**
   * Get begin
   * minimum: 0
   * maximum: 1439
   * @return begin
  */
  @Min(0) @Max(1439) 
  @Schema(name = "begin", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("begin")
  public Integer getBegin() {
    return begin;
  }

  public void setBegin(Integer begin) {
    this.begin = begin;
  }

  public ScheduleDto end(Integer end) {
    this.end = end;
    return this;
  }

  /**
   * Get end
   * minimum: 0
   * maximum: 1439
   * @return end
  */
  @Min(0) @Max(1439) 
  @Schema(name = "end", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("end")
  public Integer getEnd() {
    return end;
  }

  public void setEnd(Integer end) {
    this.end = end;
  }

  public ScheduleDto daysOfWeekMask(Integer daysOfWeekMask) {
    this.daysOfWeekMask = daysOfWeekMask;
    return this;
  }

  /**
   * Get daysOfWeekMask
   * @return daysOfWeekMask
  */
  
  @Schema(name = "days_of_week_mask", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("days_of_week_mask")
  public Integer getDaysOfWeekMask() {
    return daysOfWeekMask;
  }

  public void setDaysOfWeekMask(Integer daysOfWeekMask) {
    this.daysOfWeekMask = daysOfWeekMask;
  }

  public ScheduleDto roomId(Long roomId) {
    this.roomId = roomId;
    return this;
  }

  /**
   * Get roomId
   * @return roomId
  */
  
  @Schema(name = "roomId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("roomId")
  public Long getRoomId() {
    return roomId;
  }

  public void setRoomId(Long roomId) {
    this.roomId = roomId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ScheduleDto schedule = (ScheduleDto) o;
    return Objects.equals(this.begin, schedule.begin) &&
        Objects.equals(this.end, schedule.end) &&
        Objects.equals(this.daysOfWeekMask, schedule.daysOfWeekMask) &&
        Objects.equals(this.roomId, schedule.roomId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(begin, end, daysOfWeekMask, roomId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ScheduleDto {\n");
    sb.append("    begin: ").append(toIndentedString(begin)).append("\n");
    sb.append("    end: ").append(toIndentedString(end)).append("\n");
    sb.append("    daysOfWeekMask: ").append(toIndentedString(daysOfWeekMask)).append("\n");
    sb.append("    roomId: ").append(toIndentedString(roomId)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

