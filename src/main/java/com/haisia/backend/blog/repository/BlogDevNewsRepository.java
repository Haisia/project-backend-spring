package com.haisia.backend.blog.repository;

import com.haisia.backend.blog.entity.BlogDevNews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BlogDevNewsRepository extends JpaRepository<BlogDevNews, Long> {
  Optional<BlogDevNews> findFirstByOrderByCreatedAtDesc();
  List<BlogDevNews> findAllByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
}
