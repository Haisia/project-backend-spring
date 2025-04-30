package com.haisia.backend.blog.projectlog.controller;

import com.haisia.backend.blog.projectlog.dto.CreateProjectLogCategoryRequest;
import com.haisia.backend.blog.projectlog.dto.CreateProjectLogPostRequest;
import com.haisia.backend.blog.projectlog.dto.CreateProjectLogRequest;
import com.haisia.backend.blog.projectlog.dto.GetAllProjectLogResponse;
import com.haisia.backend.blog.projectlog.dto.GetProjectLogPostResponse;
import com.haisia.backend.blog.projectlog.service.ProjectLogService;
import com.haisia.backend.common.dto.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/blog/projectlog")
@RestController
public class ProjectLogController {

  private final ProjectLogService projectLogService;

  @Operation(
    summary = "Project Log 등록",
    description = "새로운 Project Log 를 등록합니다.",
    responses = {
      @ApiResponse(responseCode = "200", description = "Project Log 등록 성공"),
      @ApiResponse(responseCode = "500", description = "Project Log 등록 실패"),
    }
  )
  @PostMapping
  public ResponseEntity<ResponseData<Long>> createProjectLog(@RequestBody CreateProjectLogRequest request) {
    Long result = projectLogService.createProjectLog(request);
    return ResponseEntity.ok(ResponseData.success(result));
  }

  @Operation(
    summary = "Project Log Category 등록",
    description = "새로운 Project Log Category 를 등록합니다.",
    responses = {
      @ApiResponse(responseCode = "200", description = "Project Log Category 등록 성공"),
      @ApiResponse(responseCode = "500", description = "Project Log Category 등록 실패"),
    }
  )
  @PostMapping("/category")
  public ResponseEntity<ResponseData<Long>> createProjectLogCategory(@RequestBody CreateProjectLogCategoryRequest request) {
    Long result = projectLogService.createProjectLogCategory(request);
    return ResponseEntity.ok(ResponseData.success(result));
  }

  @Operation(
    summary = "Project Log Post 등록",
    description = "새로운 Project Log Post 를 등록합니다.",
    responses = {
      @ApiResponse(responseCode = "200", description = "Project Log Post 등록 성공"),
      @ApiResponse(responseCode = "500", description = "Project Log Post 등록 실패"),
    }
  )
  @PostMapping("/post")
  public ResponseEntity<ResponseData<Long>> createProjectLogPost(@RequestBody CreateProjectLogPostRequest request) {
    Long result = projectLogService.createProjectLogPost(request);
    return ResponseEntity.ok(ResponseData.success(result));
  }

  @Operation(
    summary = "Project Log 개요 조회",
    description = "Id를 기반으로 Project Log 의 개요를 조회합니다.",
    responses = {
      @ApiResponse(responseCode = "200", description = "Project Log 조회 성공"),
      @ApiResponse(responseCode = "500", description = "Project Log 조회 실패"),
    }
  )
  @GetMapping
  public ResponseEntity<ResponseData<GetProjectLogPostResponse>> getProjectLog(@RequestParam Long id) {
    GetProjectLogPostResponse result = projectLogService.getProjectLog(id);
    return ResponseEntity.ok(ResponseData.success(result));
  }

  @Operation(
    summary = "Project Log Post 조회",
    description = "Id를 기반으로 Project Log Post 의 개요를 조회합니다.",
    responses = {
      @ApiResponse(responseCode = "200", description = "Project Log Post 조회 성공"),
      @ApiResponse(responseCode = "500", description = "Project Log Post 조회 실패"),
    }
  )
  @GetMapping("/{id}")
  public ResponseEntity<ResponseData<GetProjectLogPostResponse>> getProjectLogPost(@PathVariable Long id) {
    GetProjectLogPostResponse result = projectLogService.getProjectLogPost(id);
    return ResponseEntity.ok(ResponseData.success(result));
  }

  @Operation(
    summary = "모든 Project Log Post 조회",
    description = "모든 Project Log Post 를 조회합니다.",
    responses = {
      @ApiResponse(responseCode = "200", description = "Project Log Post 조회 성공"),
      @ApiResponse(responseCode = "500", description = "Project Log Post 조회 실패"),
    }
  )
  @GetMapping("/all")
  public ResponseEntity<ResponseData<GetAllProjectLogResponse>> getAllProjectLogPosts() {
    GetAllProjectLogResponse result = projectLogService.getAllProjectLogPosts();
    return ResponseEntity.ok(ResponseData.success(result));
  }
}
