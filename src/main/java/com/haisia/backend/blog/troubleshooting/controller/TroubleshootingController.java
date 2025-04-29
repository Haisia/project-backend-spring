package com.haisia.backend.blog.troubleshooting.controller;

import com.haisia.backend.blog.troubleshooting.dto.*;
import com.haisia.backend.blog.troubleshooting.service.TroubleshootingService;
import com.haisia.backend.common.dto.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/blog/troubleshooting")
@RestController
public class TroubleshootingController {

  private final TroubleshootingService troubleshootingService;

  @PostMapping
  public ResponseEntity<ResponseData<Long>> createTroubleshooting(@RequestBody CreateTroubleshootingRequest request) {
    Long result = troubleshootingService.createTroubleshooting(request);
    return ResponseEntity.ok(ResponseData.success(result));
  }

  @PostMapping("/category")
  public ResponseEntity<ResponseData<Long>> createTroubleshootingCategory(@RequestBody CreateTroubleshootingCategoryRequest request) {
    Long result = troubleshootingService.createCategory(request);
    return ResponseEntity.ok(ResponseData.success(result));
  }

  @PostMapping("/post")
  public ResponseEntity<ResponseData<Long>> createTroubleshootingPost(@RequestBody CreateTroubleshootingPostRequest request) {
    Long result = troubleshootingService.createPost(request);
    return ResponseEntity.ok(ResponseData.success(result));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseData<GetTroubleshootingPostResponse>> getTroubleshootingPost(@PathVariable Long id) {
    GetTroubleshootingPostResponse result = troubleshootingService.getPost(id);
    return ResponseEntity.ok(ResponseData.success(result));

  }

  @GetMapping("/all")
  public ResponseEntity<ResponseData<GetAllTroubleshootingPostsResponse>> getAllTroubleshootingPosts() {
    GetAllTroubleshootingPostsResponse result = troubleshootingService.getAllPosts();
    return ResponseEntity.ok(ResponseData.success(result));

  }
}
