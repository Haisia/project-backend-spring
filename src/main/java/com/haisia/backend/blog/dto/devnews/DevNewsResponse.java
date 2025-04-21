package com.haisia.backend.blog.dto.devnews;

import com.haisia.backend.blog.entity.DevNews;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class DevNewsResponse {

  public List<DevNewsDto> blogDevNewses;

  public DevNewsResponse(DevNewsDto article) {
    this.blogDevNewses = new ArrayList<>();
    blogDevNewses.add(article);
  }

  public static DevNewsResponse from(DevNews entity) {
    DevNewsDto dto = DevNewsDto.from(entity);
    return new DevNewsResponse(dto);
  }

  public static DevNewsResponse from(List<DevNews> entities) {
    List<DevNewsDto> articles = entities.stream()
      .map(DevNewsDto::from)
      .collect(Collectors.toList());

    return new DevNewsResponse(articles);
  }
}
