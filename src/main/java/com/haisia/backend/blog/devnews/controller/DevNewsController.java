package com.haisia.backend.blog.devnews.controller;

import com.haisia.backend.blog.devnews.dto.DevNewsRequest;
import com.haisia.backend.blog.devnews.dto.DevNewsResponse;
import com.haisia.backend.blog.devnews.service.DevNewsService;
import com.haisia.backend.common.dto.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Tag(name = "Dev News API", description = "Dev News 관련 기능을 제공합니다.")
@RequestMapping("/api/blog/devnews")
@RestController
public class DevNewsController {

  private final DevNewsService devNewsService;

  @Operation(
    summary = "Dev News 등록",
    description = "새로운 Dev News 를 등록합니다.",
    responses = {
      @ApiResponse(responseCode = "200", description = "Dev News 등록 성공"),
      @ApiResponse(responseCode = "500", description = "Dev News 등록 실패"),
    }
  )
  @PostMapping
  public ResponseEntity<ResponseData<?>> postDevNews(@RequestBody DevNewsRequest request) {
    devNewsService.postDevNews(request);
    return ResponseEntity.ok(ResponseData.success(null));
  }

  @Operation(
    summary = "Dev News 단건 조회",
    description = "Id를 기반으로 Dev News 를 조회합니다.",
    responses = {
      @ApiResponse(responseCode = "200", description = "Dev News 조회 성공"),
      @ApiResponse(responseCode = "500", description = "Dev News 조회 실패"),
    }
  )
  @GetMapping("/{id}")
  public ResponseEntity<ResponseData<DevNewsResponse>> getDevNews(@PathVariable Long id) {
    DevNewsResponse result = devNewsService.getDevNews(id);
    return ResponseEntity.ok(ResponseData.success(result));
  }

  @Operation(
    summary = "모든 Dev News 조회",
    description = "모든 Dev News 를 조회합니다.",
    responses = {
      @ApiResponse(responseCode = "200", description = "Dev News 조회 성공"),
      @ApiResponse(responseCode = "500", description = "Dev News 조회 실패"),
    }
  )
  @GetMapping("/all")
  public ResponseEntity<ResponseData<DevNewsResponse>> getAllDevNews() {
    DevNewsResponse result = devNewsService.getAllDevNews();
    return ResponseEntity.ok(ResponseData.success(result));
  }
}
