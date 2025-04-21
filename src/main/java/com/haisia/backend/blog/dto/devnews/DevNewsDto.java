package com.haisia.backend.blog.dto.devnews;

import com.haisia.backend.blog.entity.DevNews;
import com.haisia.backend.blog.entity.vo.BlogContentData;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class DevNewsDto {
  public Long id;
  public BlogContentData contentData;
  public LocalDateTime createdAt;
  public LocalDateTime updatedAt;

  public static DevNewsDto from(DevNews entity) {
    return DevNewsDto.builder()
      .id(entity.getId())
      .contentData(entity.getContentData())
      .createdAt(entity.getCreatedAt())
      .updatedAt(entity.getUpdatedAt())
      .build();
  }

}
