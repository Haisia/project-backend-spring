package com.haisia.backend.blog.dto.devnews;

import com.haisia.backend.blog.entity.BlogDevNews;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class BlogDevNewsResponse {

  public List<BlogDevNewsDto> articles;

  public BlogDevNewsResponse(BlogDevNewsDto article) {
    this.articles = new ArrayList<>();
    articles.add(article);
  }

  public static BlogDevNewsResponse from(BlogDevNews entity) {
    BlogDevNewsDto dto = BlogDevNewsDto.from(entity);
    return new BlogDevNewsResponse(dto);
  }

  public static BlogDevNewsResponse from(List<BlogDevNews> entities) {
    List<BlogDevNewsDto> articles = entities.stream()
      .map(BlogDevNewsDto::from)
      .collect(Collectors.toList());

    return new BlogDevNewsResponse(articles);
  }
}
