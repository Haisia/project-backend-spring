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

  @GetMapping("/all/latest")
  public ResponseEntity<?> getAllLatestDevNews() {
    return ResponseEntity.ok(devNewsService.getAllLatestDevNews());
  }

  @GetMapping("/all/year-month")
  public ResponseEntity<?> getAllYearMonth() {
    return ResponseEntity.ok(devNewsService.getAllYearMonth());
  }

  @GetMapping("/all/by/year-month/{yearMonth}")
  public ResponseEntity<?> getAllByYearMonth(@PathVariable String yearMonth) {
    // 유효성 검증
    if (!yearMonth.matches("\\d{6}")) { // 6자리 숫자 확인
      throw new IllegalArgumentException("Invalid yearMonth format. It must be 6 digits (e.g., 202304)");
    }

    // yearMonth 처리 로직 작성
    // 예: year = "2023", month = "04"
    String year = yearMonth.substring(0, 4); // 연도 분리
    String month = yearMonth.substring(4);   // 월 분리

    return ResponseEntity.ok(devNewsService.getAllByYearMonth(year, month));
  }
}
