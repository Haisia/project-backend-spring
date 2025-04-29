package com.haisia.backend.common.dto;

import com.haisia.backend.common.enums.ResponseCode;
import lombok.*;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ResponseData<T> {
  private T data;
  private ResponseCode code;
  private String message;
  private LocalDateTime timestamp;

  public static <T> ResponseData<T> success(T data) {
    return ResponseData.<T>builder()
      .data(data)
      .code(ResponseCode.SUCCESS)
      .message(ResponseCode.SUCCESS.getMessage())
      .timestamp(LocalDateTime.now())
      .build();
  }

  public static ResponseData<Object> fail(ResponseCode code, String message) {
    return ResponseData.builder()
      .code(code)
      .message(message)
      .timestamp(LocalDateTime.now())
      .build();
  }
}