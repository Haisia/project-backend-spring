package com.haisia.backend.blog.service;

import com.haisia.backend.blog.dto.devnews.*;
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

  public BlogDevNewsResponse getAllDevNews() {
    List<BlogDevNews> all = blogDevNewsRepository.findAll();
    return BlogDevNewsResponse.from(all);
  }

  public BlogGetAllLatestDveNewsResponse getAllLatestDevNews() {
    BlogDevNews latestNews = blogDevNewsRepository.findFirstByOrderByCreatedAtDesc().orElseThrow();

    LocalDateTime createdAt = latestNews.getCreatedAt();
    YearMonth yearMonth = YearMonth.from(createdAt);

    LocalDateTime startDate = yearMonth.atDay(1).atStartOfDay();
    LocalDateTime endDate = yearMonth.atEndOfMonth().atTime(23, 59, 59);

    List<BlogDevNews> filteredNews = blogDevNewsRepository.findAllByCreatedAtBetween(startDate, endDate);

    return BlogGetAllLatestDveNewsResponse.from(
      filteredNews,
      String.valueOf(yearMonth.getYear()),
      String.valueOf(yearMonth.getMonthValue())
    );
  }

  public BlogGetAllYearMonthResponse getAllYearMonth() {
    return new BlogGetAllYearMonthResponse(blogDevNewsRepository.findAllUniqueYearMonth());
  }

  public BlogDevNewsResponse getAllByYearMonth(String year, String month) {
    YearMonth yearMonth;
    try {
      yearMonth = YearMonth.of(Integer.parseInt(year), Integer.parseInt(month));
    } catch (NumberFormatException | DateTimeException e) {
      throw new IllegalArgumentException("Invalid year or month format");
    }

    LocalDateTime startDate = yearMonth.atDay(1).atStartOfDay();
    LocalDateTime endDate = yearMonth.atEndOfMonth().atTime(23, 59, 59);

    List<BlogDevNews> newsList = blogDevNewsRepository.findAllByCreatedAtBetween(startDate, endDate);

    return BlogDevNewsResponse.from(newsList);
  }
}
