package io.sciota.demo.alarmservice.dtos;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets properties
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-09-09T10:14:03.955164600+02:00[Europe/Berlin]")
public enum PropertiesDto {
  
  MOTION_DETECTED("MOTION_DETECTED"),
  
  WINDOW_OPENED("WINDOW_OPENED");

  private String value;

  PropertiesDto(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static PropertiesDto fromValue(String value) {
    for (PropertiesDto b : PropertiesDto.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

