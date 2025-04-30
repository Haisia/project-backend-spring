package com.haisia.backend.blog.troubleshooting.controller;

import com.haisia.backend.blog.troubleshooting.dto.*;
import com.haisia.backend.blog.troubleshooting.service.TroubleshootingService;
import com.haisia.backend.common.dto.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/blog/troubleshooting")
@RestController
public class TroubleshootingController {

  private final TroubleshootingService troubleshootingService;

  @Operation(
    summary = "Troubleshooting 등록",
    description = "새로운 Troubleshooting 를 등록합니다.",
    responses = {
      @ApiResponse(responseCode = "200", description = "Troubleshooting 등록 성공"),
      @ApiResponse(responseCode = "500", description = "Troubleshooting 등록 실패"),
    }
  )
  @PostMapping
  public ResponseEntity<ResponseData<Long>> createTroubleshooting(@RequestBody CreateTroubleshootingRequest request) {
    Long result = troubleshootingService.createTroubleshooting(request);
    return ResponseEntity.ok(ResponseData.success(result));
  }

  @Operation(
    summary = "Troubleshooting Category 등록",
    description = "새로운 Troubleshooting Category 를 등록합니다.",
    responses = {
      @ApiResponse(responseCode = "200", description = "Troubleshooting Category 등록 성공"),
      @ApiResponse(responseCode = "500", description = "Troubleshooting Category 등록 실패"),
    }
  )
  @PostMapping("/category")
  public ResponseEntity<ResponseData<Long>> createTroubleshootingCategory(@RequestBody CreateTroubleshootingCategoryRequest request) {
    Long result = troubleshootingService.createCategory(request);
    return ResponseEntity.ok(ResponseData.success(result));
  }

  @Operation(
    summary = "Troubleshooting Post 등록",
    description = "새로운 Troubleshooting Post 를 등록합니다.",
    responses = {
      @ApiResponse(responseCode = "200", description = "Troubleshooting Post 등록 성공"),
      @ApiResponse(responseCode = "500", description = "Troubleshooting Post 등록 실패"),
    }
  )
  @PostMapping("/post")
  public ResponseEntity<ResponseData<Long>> createTroubleshootingPost(@RequestBody CreateTroubleshootingPostRequest request) {
    Long result = troubleshootingService.createPost(request);
    return ResponseEntity.ok(ResponseData.success(result));
  }

  @Operation(
    summary = "Troubleshooting Post 단건 조회",
    description = "Id를 기반으로 Troubleshooting Post 를 조회합니다.",
    responses = {
      @ApiResponse(responseCode = "200", description = "Troubleshooting Post 조회 성공"),
      @ApiResponse(responseCode = "500", description = "Troubleshooting Post 조회 실패"),
    }
  )
  @GetMapping("/{id}")
  public ResponseEntity<ResponseData<GetTroubleshootingPostResponse>> getTroubleshootingPost(@PathVariable Long id) {
    GetTroubleshootingPostResponse result = troubleshootingService.getPost(id);
    return ResponseEntity.ok(ResponseData.success(result));
  }

  @Operation(
    summary = "모든 Troubleshooting Post 조회",
    description = "모든 Troubleshooting Post 를 조회합니다.",
    responses = {
      @ApiResponse(responseCode = "200", description = "Troubleshooting Post 조회 성공"),
      @ApiResponse(responseCode = "500", description = "Troubleshooting Post 조회 실패"),
    }
  )
  @GetMapping("/all")
  public ResponseEntity<ResponseData<GetAllTroubleshootingPostsResponse>> getAllTroubleshootingPosts() {
    GetAllTroubleshootingPostsResponse result = troubleshootingService.getAllPosts();
    return ResponseEntity.ok(ResponseData.success(result));
  }
}
