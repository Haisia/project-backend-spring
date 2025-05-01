package com.haisia.backend.blog.projectlog.entity;

import com.haisia.backend.blog.common.vo.BlogContentData;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Table(name = "blog_project_log_category")
@Entity
public class ProjectLogCategory {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "projectlogcategory_seq_gen")
  @SequenceGenerator(name = "projectlogcategory_seq_gen", sequenceName = "projectlogcategory_seq", allocationSize = 1)
  private Long id;
  private String title;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "blog_project_log_id")
  private ProjectLog projectLog;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "category", orphanRemoval = true, fetch = FetchType.LAZY)
  @OrderBy("id ASC")
  private List<ProjectLogPost> posts = new ArrayList<>();

  private ProjectLogCategory(String title) {
    this.title = title;
  }

  protected static ProjectLogCategory of(String title) {
    return new ProjectLogCategory(title);
  }

  public ProjectLogPost addPost(String title, String content) {
    BlogContentData contentData = BlogContentData.of(title, content);
    ProjectLogPost createdPost = ProjectLogPost.of(contentData);
    posts.add(createdPost);
    createdPost.setCategory(this);
    return createdPost;
  }
}
