package com.haisia.backend.blog.controller;

import com.haisia.backend.blog.dto.devnews.BlogDevNewsRequest;
import com.haisia.backend.blog.dto.devnews.BlogDevNewsResponse;
import com.haisia.backend.blog.service.DevNewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
  public ResponseEntity<BlogDevNewsResponse> getDevNews(@PathVariable Long id) {
    BlogDevNewsResponse response = devNewsService.getDevNews(id);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/all")
  public ResponseEntity<BlogDevNewsResponse> getAllDevNews() {
    BlogDevNewsResponse response = devNewsService.getAllDevNews();
    return ResponseEntity.ok(response);
  }
}
