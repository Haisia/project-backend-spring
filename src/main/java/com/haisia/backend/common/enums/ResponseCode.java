package com.haisia.backend.common.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseCode {
  SUCCESS("SUCCESS", "요청이 성공적으로 처리되었습니다.", HttpStatus.OK),
  DATABASE_ERROR("DB_ERROR", "데이터베이스 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
  AUTHENTICATION_ERROR("AUTH_ERROR", "인증 실패", HttpStatus.UNAUTHORIZED),
  AUTHORIZATION_ERROR("ACCESS_DENIED", "권한 없음", HttpStatus.FORBIDDEN),
  BUSINESS_ERROR("BUSINESS_ERROR", "비즈니스 로직 오류", HttpStatus.BAD_REQUEST),
  VALIDATION_ERROR("VALIDATION_ERROR", "유효성 검증 실패", HttpStatus.BAD_REQUEST),
  RESOURCE_NOT_FOUND_ERROR("RESOURCE_NOT_FOUND_ERROR", "잘못된 리소스 요청", HttpStatus.BAD_REQUEST),
  INTERNAL_SERVER_ERROR("INTERNAL_ERROR", "서버 내부 오류", HttpStatus.INTERNAL_SERVER_ERROR),
  EXTERNAL_API_ERROR("EXTERNAL_API_ERROR", "외부 API 오류", HttpStatus.BAD_GATEWAY);

  private final String code;
  private final String message;
  private final HttpStatus httpStatus;

  ResponseCode(String code, String message, HttpStatus httpStatus) {
    this.code = code;
    this.message = message;
    this.httpStatus = httpStatus;
  }
}
