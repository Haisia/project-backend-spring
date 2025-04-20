package com.haisia.backend.blog.service;

import com.haisia.backend.blog.dto.devnews.BlogLatestDevNewsTitleResponse;
import com.haisia.backend.blog.dto.devnews.BlogDevNewsRequest;
import com.haisia.backend.blog.dto.devnews.BlogDevNewsResponse;
import com.haisia.backend.blog.entity.BlogDevNews;
import com.haisia.backend.blog.repository.BlogDevNewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DevNewsServiceImpl implements DevNewsService {

  private final BlogDevNewsRepository blogDevNewsRepository;

  public void postDevNews(BlogDevNewsRequest request) {
    BlogDevNews devNews = BlogDevNews.builder()
      .title(request.title)
      .content(request.content)
      .build();

    blogDevNewsRepository.save(devNews);
  }

  public BlogDevNewsResponse getDevNews(Long id) {
    BlogDevNews blogDevNews = blogDevNewsRepository.findById(id).orElseThrow();
    return BlogDevNewsResponse.from(blogDevNews);
  }

  public List<BlogDevNewsResponse> getAllDevNews() {
    List<BlogDevNews> all = blogDevNewsRepository.findAll();
    return BlogDevNewsResponse.from(all);
  }

  public BlogLatestDevNewsTitleResponse getAllLatest() {
    BlogDevNews latestNews = blogDevNewsRepository.findFirstByOrderByCreatedAtDesc().orElseThrow();

    LocalDateTime createdAt = latestNews.getCreatedAt();
    YearMonth yearMonth = YearMonth.from(createdAt);

    LocalDateTime startDate = yearMonth.atDay(1).atStartOfDay();
    LocalDateTime endDate = yearMonth.atEndOfMonth().atTime(23, 59, 59);

    List<BlogDevNews> filteredNews = blogDevNewsRepository.findAllByCreatedAtBetween(startDate, endDate);

    return BlogLatestDevNewsTitleResponse.from(
      filteredNews,
      String.valueOf(yearMonth.getYear()),
      String.valueOf(yearMonth.getMonthValue())
    );
  }
}
