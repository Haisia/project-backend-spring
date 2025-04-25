package com.haisia.backend.blog.troubleshooting.dto;

import com.haisia.backend.blog.troubleshooting.entity.TroubleshootingCategory;
import com.haisia.backend.blog.troubleshooting.entity.TroubleshootingPost;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class GetAllTroubleshootingPostsResponse {

  protected List<Troubleshooting> troubleshootings = new ArrayList<>();

  public static GetAllTroubleshootingPostsResponse from(
    List<com.haisia.backend.blog.troubleshooting.entity.Troubleshooting> entities
  ) {

    List<Troubleshooting> troubleshootings = entities.stream()
      .map(Troubleshooting::from)
      .toList();

    return new GetAllTroubleshootingPostsResponse(troubleshootings);
  }

  @Builder
  @NoArgsConstructor @AllArgsConstructor
  @Getter @Setter
  protected static class Troubleshooting {
    protected Long id;
    protected String title;
    protected List<Category> categories = new ArrayList<>();

    protected static Troubleshooting from(com.haisia.backend.blog.troubleshooting.entity.Troubleshooting entity) {
      List<Category> categories = entity.getCategories()
        .stream()
        .map(Category::from)
        .toList();

      return Troubleshooting.builder()
        .id(entity.getId())
        .title(entity.getTitle())
        .categories(categories)
        .build();
    }
  }

  @Builder
  @NoArgsConstructor @AllArgsConstructor
  @Getter @Setter
  protected static class Category {
    protected Long id;
    protected String title;
    protected List<Post> posts = new ArrayList<>();

    protected static Category from(TroubleshootingCategory entity) {
      List<Post> posts = entity.getPosts()
        .stream()
        .map(Post::from)
        .toList();

      return Category.builder()
        .id(entity.getId())
        .title(entity.getTitle())
        .posts(posts)
        .build();
    }
  }

  @Builder
  @NoArgsConstructor @AllArgsConstructor
  @Getter @Setter
  protected static class Post {
    protected Long id;
    protected String title;
    protected String content;

    protected static Post from(TroubleshootingPost entity) {
      return Post.builder()
        .id(entity.getId())
        .title(entity.getContentData().getTitle())
        .content(entity.getContentData().getContent())
        .build();
    }
  }
}
