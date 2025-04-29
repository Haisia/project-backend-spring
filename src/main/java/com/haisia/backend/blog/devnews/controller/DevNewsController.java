package com.haisia.backend.blog.devnews.controller;

import com.haisia.backend.blog.devnews.dto.DevNewsRequest;
import com.haisia.backend.blog.devnews.dto.DevNewsResponse;
import com.haisia.backend.blog.devnews.service.DevNewsService;
import com.haisia.backend.common.dto.ResponseData;
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
  public ResponseEntity<ResponseData<?>> postDevNews(@RequestBody DevNewsRequest request) {
    devNewsService.postDevNews(request);
    return ResponseEntity.ok(ResponseData.success(null));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseData<DevNewsResponse>> getDevNews(@PathVariable Long id) {
    DevNewsResponse result = devNewsService.getDevNews(id);
    return ResponseEntity.ok(ResponseData.success(result));
  }

  @GetMapping("/all")
  public ResponseEntity<ResponseData<DevNewsResponse>> getAllDevNews() {
    DevNewsResponse result = devNewsService.getAllDevNews();
    return ResponseEntity.ok(ResponseData.success(result));
  }
}
