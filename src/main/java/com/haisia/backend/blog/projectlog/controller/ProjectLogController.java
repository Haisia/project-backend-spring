package com.haisia.backend.blog.projectlog.controller;

import com.haisia.backend.blog.projectlog.dto.CreateProjectLogCategoryRequest;
import com.haisia.backend.blog.projectlog.dto.CreateProjectLogPostRequest;
import com.haisia.backend.blog.projectlog.dto.CreateProjectLogRequest;
import com.haisia.backend.blog.projectlog.dto.GetAllProjectLogResponse;
import com.haisia.backend.blog.projectlog.dto.GetProjectLogPostResponse;
import com.haisia.backend.blog.projectlog.service.ProjectLogService;
import com.haisia.backend.common.dto.ResponseData;
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

  @PostMapping
  public ResponseEntity<ResponseData<Long>> createProjectLog(@RequestBody CreateProjectLogRequest request) {
    Long result = projectLogService.createProjectLog(request);
    return ResponseEntity.ok(ResponseData.success(result));
  }

  @PostMapping("/category")
  public ResponseEntity<ResponseData<Long>> createProjectLogCategory(@RequestBody CreateProjectLogCategoryRequest request) {
    Long result = projectLogService.createProjectLogCategory(request);
    return ResponseEntity.ok(ResponseData.success(result));
  }

  @PostMapping("/post")
  public ResponseEntity<ResponseData<Long>> createProjectLogPost(@RequestBody CreateProjectLogPostRequest request) {
    Long result = projectLogService.createProjectLogPost(request);
    return ResponseEntity.ok(ResponseData.success(result));
  }

  @GetMapping
  public ResponseEntity<ResponseData<GetProjectLogPostResponse>> getProjectLog(@RequestParam Long projectId) {
    GetProjectLogPostResponse result = projectLogService.getProjectLog(projectId);
    return ResponseEntity.ok(ResponseData.success(result));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseData<GetProjectLogPostResponse>> getProjectLogPost(@PathVariable Long id) {
    GetProjectLogPostResponse result = projectLogService.getProjectLogPost(id);
    return ResponseEntity.ok(ResponseData.success(result));
  }

  @GetMapping("/all")
  public ResponseEntity<ResponseData<GetAllProjectLogResponse>> getAllProjectLogPosts() {
    GetAllProjectLogResponse result = projectLogService.getAllProjectLogPosts();
    return ResponseEntity.ok(ResponseData.success(result));
  }
}
