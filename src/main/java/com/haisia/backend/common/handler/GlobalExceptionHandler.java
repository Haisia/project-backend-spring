package com.haisia.backend.common.handler;

import com.haisia.backend.common.dto.ResponseData;
import com.haisia.backend.common.enums.ResponseCode;
import com.haisia.backend.common.exception.ApplicationException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ApplicationException.class)
  public ResponseEntity<ResponseData<Object>> handleApplicationException(ApplicationException exception) {
    StackTraceElement stackTrace = exception.getStackTrace()[0];
    String location = String.format("%s#%s:%d", stackTrace.getClassName(), stackTrace.getMethodName(), stackTrace.getLineNumber());
    log.warn("[ApplicationException] at {} - {}", location, exception.getMessage());

    return ResponseEntity
      .status(exception.getCode().getHttpStatus())
      .body(ResponseData.fail(exception.getCode(), exception.getMessage()));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ResponseData<Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
    log.warn("[ValidationException] : {}", exception.getMessage());

    List<String> errorMessages = exception.getBindingResult()
      .getFieldErrors()
      .stream()
      .map(fieldError -> String.format("%s : %s", fieldError.getField(), fieldError.getDefaultMessage()))
      .collect(Collectors.toList());

    String combinedMessage = String.join("; ", errorMessages);

    return ResponseEntity
      .status(ResponseCode.VALIDATION_ERROR.getHttpStatus())
      .body(ResponseData.fail(ResponseCode.VALIDATION_ERROR, combinedMessage));
  }

  @ExceptionHandler(NoResourceFoundException.class)
  public ResponseEntity<ResponseData<Object>> handleNoResourceFoundException(
    NoResourceFoundException exception,
    HttpServletRequest request
  ) {
    String requestUrl = request.getRequestURI();
    String clientIp = request.getRemoteAddr();

    log.warn("[NoResourceFoundException] 요청 URL: {}, 요청 IP: {}, 메시지: {}",
      requestUrl, clientIp, exception.getMessage());

    return ResponseEntity
      .status(ResponseCode.RESOURCE_NOT_FOUND_ERROR.getHttpStatus())
      .body(ResponseData.fail(ResponseCode.RESOURCE_NOT_FOUND_ERROR, ResponseCode.RESOURCE_NOT_FOUND_ERROR.getMessage()));
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ResponseData<Object>> handleRuntimeException(RuntimeException exception) {
    log.error("[RuntimeException] : ", exception);
    return ResponseEntity
      .status(ResponseCode.INTERNAL_SERVER_ERROR.getHttpStatus())
      .body(ResponseData.fail(ResponseCode.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다."));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ResponseData<Object>> handleException(Exception exception) {
    log.error("[Exception] : ", exception);
    return ResponseEntity
      .status(ResponseCode.INTERNAL_SERVER_ERROR.getHttpStatus())
      .body(ResponseData.fail(ResponseCode.INTERNAL_SERVER_ERROR, "예상치 못한 오류가 발생했습니다."));
  }
}