package com.haisia.backend.blog.controller;

import com.haisia.backend.blog.dto.devnews.BlogDevNewsRequest;
import com.haisia.backend.blog.dto.devnews.BlogDevNewsResponse;
import com.haisia.backend.blog.service.DevNewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/blog/devnews")
@RestController
public class DevNewsController {

  private final DevNewsService devNewsService;

  @PostMapping
  public ResponseEntity<?> postDevNews(@RequestBody BlogDevNewsRequest request) {
    devNewsService.postDevNews(request);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getDevNews(@PathVariable Long id) {
    BlogDevNewsResponse response = devNewsService.getDevNews(id);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/all")
  public ResponseEntity<?> getAllDevNews() {
    List<BlogDevNewsResponse> responses = devNewsService.getAllDevNews();
    return ResponseEntity.ok(responses);
  }
}
