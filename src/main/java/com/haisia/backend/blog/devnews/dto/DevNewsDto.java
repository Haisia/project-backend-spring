package com.haisia.backend.blog.devnews.dto;

import com.haisia.backend.blog.devnews.entity.DevNews;
import com.haisia.backend.blog.common.vo.BlogContentData;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class DevNewsDto {
  public Long id;
  public String title;
  public String content;
  public LocalDateTime createdAt;
  public LocalDateTime updatedAt;

  public static DevNewsDto from(DevNews entity) {
    return DevNewsDto.builder()
      .id(entity.getId())
      .title(entity.getContentData().getTitle())
      .content(entity.getContentData().getContent())
      .createdAt(entity.getCreatedAt())
      .updatedAt(entity.getUpdatedAt())
      .build();
  }

}
