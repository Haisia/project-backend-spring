package com.haisia.backend.blog.controller;

import com.haisia.backend.blog.dto.projectlog.CreateProjectLogCategoryRequest;
import com.haisia.backend.blog.dto.projectlog.CreateProjectLogPostRequest;
import com.haisia.backend.blog.dto.projectlog.CreateProjectLogRequest;
import com.haisia.backend.blog.dto.projectlog.GetAllProjectLogResponse;
import com.haisia.backend.blog.dto.projectlog.GetProjectLogResponse;
import com.haisia.backend.blog.service.ProjectLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/blog/projectlog")
@RestController
public class ProjectLogController {

  private final ProjectLogService projectLogService;

  @PostMapping
  public ResponseEntity<?> createProjectLog(@RequestBody CreateProjectLogRequest request) {
    return ResponseEntity.ok(projectLogService.createProjectLog(request));
  }

  @PostMapping("/category")
  public ResponseEntity<?> createProjectLogCategory(@RequestBody CreateProjectLogCategoryRequest request) {
    return ResponseEntity.ok(projectLogService.createProjectLogCategory(request));
  }

  @PostMapping("/post")
  public ResponseEntity<?> createProjectLogPost(@RequestBody CreateProjectLogPostRequest request) {
    return ResponseEntity.ok(projectLogService.createProjectLogPost(request));
  }

  @GetMapping("/{id}")
  public ResponseEntity<GetProjectLogResponse> getProjectLogPost(@PathVariable Long id) {
    return ResponseEntity.ok(projectLogService.getProjectLogPost(id));
  }

  @GetMapping("/all")
  public ResponseEntity<GetAllProjectLogResponse> getAllProjectLogPosts() {
    return ResponseEntity.ok(projectLogService.getAllProjectLogPosts());
  }
}
