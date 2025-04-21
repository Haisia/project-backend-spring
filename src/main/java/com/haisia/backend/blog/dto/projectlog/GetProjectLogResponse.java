package com.haisia.backend.blog.dto.projectlog;

import com.haisia.backend.blog.entity.ProjectLog;
import com.haisia.backend.blog.entity.ProjectLogCategory;
import com.haisia.backend.blog.entity.ProjectLogPost;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class GetProjectLogResponse {
  public Long projectId;
  public Long categoryId;
  public Long postId;
  public String projectTitle;
  public String categoryTitle;
  public String postTitle;
  public String postContent;
  public LocalDateTime createdAt;
  public LocalDateTime updatedAt;

  public static GetProjectLogResponse from(ProjectLogPost entity) {
    ProjectLogCategory category = entity.getCategory();
    ProjectLog projectLog = category.getProjectLog();

    return GetProjectLogResponse.builder()
      .projectId(projectLog.getId())
      .categoryId(category.getId())
      .postId(entity.getId())
      .projectTitle(projectLog.getTitle())
      .categoryTitle(category.getTitle())
      .postTitle(entity.getContentData().getTitle())
      .postContent(entity.getContentData().getContent())
      .createdAt(entity.getCreatedAt())
      .updatedAt(entity.getUpdatedAt())
      .build();
  }
}
