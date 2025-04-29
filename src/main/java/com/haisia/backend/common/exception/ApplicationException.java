package com.haisia.backend.common.exception;

import com.haisia.backend.common.enums.ResponseCode;
import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {
  private final ResponseCode code;

  public ApplicationException(ResponseCode code, String message) {
    super(message);
    this.code = code;
  }
}
