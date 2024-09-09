package io.sciota.demo.alarmservice.dtos;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * AlarmDto
 */

@JsonTypeName("Alarm")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-09-09T12:23:03.034484500+02:00[Europe/Berlin]")
public class AlarmDto {

  private String reason;

  private Boolean acknowledged;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime timestamp;

  private Long alarmId;

  private Long roomId;

  public AlarmDto reason(String reason) {
    this.reason = reason;
    return this;
  }

  /**
   * Get reason
   * @return reason
  */
  
  @Schema(name = "reason", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("reason")
  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public AlarmDto acknowledged(Boolean acknowledged) {
    this.acknowledged = acknowledged;
    return this;
  }

  /**
   * Get acknowledged
   * @return acknowledged
  */
  
  @Schema(name = "acknowledged", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("acknowledged")
  public Boolean getAcknowledged() {
    return acknowledged;
  }

  public void setAcknowledged(Boolean acknowledged) {
    this.acknowledged = acknowledged;
  }

  public AlarmDto timestamp(OffsetDateTime timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  /**
   * Get timestamp
   * @return timestamp
  */
  @Valid 
  @Schema(name = "timestamp", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("timestamp")
  public OffsetDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(OffsetDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public AlarmDto alarmId(Long alarmId) {
    this.alarmId = alarmId;
    return this;
  }

  /**
   * Get alarmId
   * @return alarmId
  */
  
  @Schema(name = "alarmId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("alarmId")
  public Long getAlarmId() {
    return alarmId;
  }

  public void setAlarmId(Long alarmId) {
    this.alarmId = alarmId;
  }

  public AlarmDto roomId(Long roomId) {
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
    AlarmDto alarm = (AlarmDto) o;
    return Objects.equals(this.reason, alarm.reason) &&
        Objects.equals(this.acknowledged, alarm.acknowledged) &&
        Objects.equals(this.timestamp, alarm.timestamp) &&
        Objects.equals(this.alarmId, alarm.alarmId) &&
        Objects.equals(this.roomId, alarm.roomId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(reason, acknowledged, timestamp, alarmId, roomId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AlarmDto {\n");
    sb.append("    reason: ").append(toIndentedString(reason)).append("\n");
    sb.append("    acknowledged: ").append(toIndentedString(acknowledged)).append("\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
    sb.append("    alarmId: ").append(toIndentedString(alarmId)).append("\n");
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

