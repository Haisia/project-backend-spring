package com.haisia.backend.blog.repository;

import com.haisia.backend.blog.dto.devnews.YearMonthProjection;
import com.haisia.backend.blog.entity.BlogDevNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BlogDevNewsRepository extends JpaRepository<BlogDevNews, Long> {
  Optional<BlogDevNews> findFirstByOrderByCreatedAtDesc();

  List<BlogDevNews> findAllByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

  @Query(
    value =
      "SELECT DISTINCT " +
        "TO_CHAR(created_at, 'YYYY') AS year" +
        ", TO_CHAR(created_at, 'MM') AS month " +
      "FROM blog_dev_news " +
      "ORDER BY year, month",
    nativeQuery = true
  )
  List<YearMonthProjection> findAllUniqueYearMonth();

}
