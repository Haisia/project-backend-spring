package com.haisia.backend.blog.dto.devnews;

import com.haisia.backend.blog.entity.BlogDevNews;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class BlogLatestDevNewsTitleResponse {
  public List<BlogDevNewsDto> articles;
  public String year;
  public String month;

  public static BlogLatestDevNewsTitleResponse from(List<BlogDevNews> entities, String year, String month) {
    List<BlogDevNewsDto> articles = entities.stream()
      .map(BlogDevNewsDto::from)
      .collect(Collectors.toList());

    return BlogLatestDevNewsTitleResponse.builder()
      .articles(articles)
      .year(year)
      .month(month)
      .build();
  }
}
