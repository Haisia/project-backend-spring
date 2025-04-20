package com.haisia.backend.blog.dto.devnews;

import com.haisia.backend.blog.entity.BlogDevNews;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class BlogDevNewsResponse {

  public Long id;
  public String title;
  public String content;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;


  public static BlogDevNewsResponse from(BlogDevNews entity) {
    return BlogDevNewsResponse.builder()
      .id(entity.getId())
      .title(entity.getArticle().getTitle())
      .content(entity.getArticle().getContent())
      .createdAt(entity.getCreatedAt())
      .updatedAt(entity.getUpdatedAt())
      .build();
  }

  public static List<BlogDevNewsResponse> from(List<BlogDevNews> entities) {
    return entities.stream()
      .map(BlogDevNewsResponse::from)
      .collect(Collectors.toList())
      ;
  }
}
