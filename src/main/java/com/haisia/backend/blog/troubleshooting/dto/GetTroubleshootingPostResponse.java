package com.haisia.backend.blog.troubleshooting.dto;

import com.haisia.backend.blog.troubleshooting.entity.Troubleshooting;
import com.haisia.backend.blog.troubleshooting.entity.TroubleshootingCategory;
import com.haisia.backend.blog.troubleshooting.entity.TroubleshootingPost;
import lombok.*;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class GetTroubleshootingPostResponse {
  public Long troubleshootingId;
  public String troubleshootingTitle;
  public Long categoryId;
  public String categoryTitle;
  public Long id;
  public String title;
  public String content;

  public static GetTroubleshootingPostResponse from(TroubleshootingPost entity) {
    TroubleshootingCategory category = entity.getCategory();
    Troubleshooting troubleshooting = category.getTroubleshooting();

    return GetTroubleshootingPostResponse.builder()
      .troubleshootingId(troubleshooting.getId())
      .troubleshootingTitle(troubleshooting.getTitle())
      .categoryId(category.getId())
      .categoryTitle(category.getTitle())
      .id(entity.getId())
      .title(entity.getContentData().getTitle())
      .content(entity.getContentData().getContent())
      .build();
  }
}
