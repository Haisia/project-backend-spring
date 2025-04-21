package com.haisia.backend.blog.dto.devnews;

import com.haisia.backend.blog.entity.BlogDevNews;
import com.haisia.backend.blog.entity.vo.BlogContentData;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class BlogDevNewsDto {
  public Long id;
  public BlogContentData contentData;
  public LocalDateTime createdAt;
  public LocalDateTime updatedAt;

  public static BlogDevNewsDto from(BlogDevNews entity) {
    return BlogDevNewsDto.builder()
      .id(entity.getId())
      .contentData(entity.getContentData())
      .createdAt(entity.getCreatedAt())
      .updatedAt(entity.getUpdatedAt())
      .build();
  }

}
