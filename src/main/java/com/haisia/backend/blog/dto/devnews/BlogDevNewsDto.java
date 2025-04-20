package com.haisia.backend.blog.dto.devnews;

import com.haisia.backend.blog.entity.BlogDevNews;
import lombok.*;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class BlogDevNewsDto {
  public Long id;
  public String title;
  public String content;

  public static BlogDevNewsDto from(BlogDevNews entity) {
    return BlogDevNewsDto.builder()
      .id(entity.getId())
      .title(entity.getArticle().getTitle())
      .content(entity.getArticle().getContent())
      .build();
  }

}
