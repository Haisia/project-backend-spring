package com.haisia.backend.blog.troubleshooting.controller;

import com.haisia.backend.blog.troubleshooting.dto.CreateTroubleshootingCategoryRequest;
import com.haisia.backend.blog.troubleshooting.dto.CreateTroubleshootingPostRequest;
import com.haisia.backend.blog.troubleshooting.dto.CreateTroubleshootingRequest;
import com.haisia.backend.blog.troubleshooting.service.TroubleshootingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/blog/troubleshooting")
@RestController
public class TroubleshootingController {

  private final TroubleshootingService troubleshootingService;

  @PostMapping
  public ResponseEntity<Long> createTroubleshooting(@RequestBody CreateTroubleshootingRequest request) {
    return ResponseEntity.ok(troubleshootingService.createTroubleshooting(request));
  }

  @PostMapping("/category")
  public ResponseEntity<Long> createTroubleshootingCategory(@RequestBody CreateTroubleshootingCategoryRequest request) {
    return ResponseEntity.ok(troubleshootingService.createCategory(request));
  }

  @PostMapping("/post")
  public ResponseEntity<Long> createTroubleshootingPost(@RequestBody CreateTroubleshootingPostRequest request) {
    return ResponseEntity.ok(troubleshootingService.createPost(request));
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getTroubleshootingPost(@PathVariable Long id) {
    return ResponseEntity.ok(troubleshootingService.getPost(id));
  }

  @GetMapping("/all")
  public ResponseEntity<?> getAllTroubleshootingPosts() {
    return ResponseEntity.ok(troubleshootingService.getAllPosts());
  }
}
