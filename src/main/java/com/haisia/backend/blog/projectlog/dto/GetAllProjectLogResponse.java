package com.haisia.backend.blog.projectlog.dto;

import com.haisia.backend.blog.projectlog.entity.ProjectLog;
import com.haisia.backend.blog.projectlog.entity.ProjectLogCategory;
import com.haisia.backend.blog.projectlog.entity.ProjectLogPost;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class GetAllProjectLogResponse {
  private List<Project> projects = new ArrayList<>();

  public static GetAllProjectLogResponse from(List<ProjectLog> entities) {
    List<Project> projects = entities.stream().map(Project::from).toList();
    return new GetAllProjectLogResponse(projects);
  }

  @Builder
  @NoArgsConstructor @AllArgsConstructor
  @Getter @Setter
  private static class Project {
    private Long id;
    private String title;
    private String content;
    private List<Category> categories = new ArrayList<>();

    public static Project from(ProjectLog entity) {
      List<Category> categories = entity.getProjectLogCategories().stream()
        .map(Category::from)
        .toList();

      return Project.builder()
        .id(entity.getId())
        .title(entity.getContentData().getTitle())
        .content(entity.getContentData().getContent())
        .categories(categories)
        .build()
        ;
    }
  }

  @Builder
  @NoArgsConstructor @AllArgsConstructor
  @Getter @Setter
  private static class Category {
    private Long id;
    private String title;
    private List<Post> posts = new ArrayList<>();

    public static Category from(ProjectLogCategory entity) {
      List<Post> posts = entity.getProjectLogPosts().stream()
        .map(Post::from)
        .toList();

      return Category.builder()
        .id(entity.getId())
        .title(entity.getTitle())
        .posts(posts)
        .build()
        ;
    }
  }

  @Builder
  @NoArgsConstructor @AllArgsConstructor
  @Getter @Setter
  private static class Post {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static Post from(ProjectLogPost entity) {
      return Post.builder()
        .id(entity.getId())
        .title(entity.getContentData().getTitle())
        .content(entity.getContentData().getContent())
        .createdAt(entity.getCreatedAt())
        .updatedAt(entity.getUpdatedAt())
        .build();
    }
  }
}
