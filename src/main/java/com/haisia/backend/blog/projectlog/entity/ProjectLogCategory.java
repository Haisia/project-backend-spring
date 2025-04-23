package com.haisia.backend.blog.projectlog.entity;

import com.haisia.backend.blog.common.vo.BlogContentData;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
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
  private List<ProjectLogPost> projectLogPosts = new ArrayList<>();

  private ProjectLogCategory(String title) {
    this.title = title;
  }

  public static ProjectLogCategory of(String title) {
    return new ProjectLogCategory(title);
  }

  public ProjectLogPost addPost(String title, String content) {
    BlogContentData contentData = BlogContentData.of(title, content);
    ProjectLogPost createdPost = ProjectLogPost.of(contentData);
    projectLogPosts.add(createdPost);
    createdPost.setCategory(this);
    return createdPost;
  }
}
