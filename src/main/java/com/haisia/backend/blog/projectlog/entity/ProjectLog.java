package com.haisia.backend.blog.projectlog.entity;

import com.haisia.backend.blog.common.vo.BlogContentData;
import com.haisia.backend.common.entity.BaseJpaEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Table(name = "blog_project_log")
@Entity
public class ProjectLog extends BaseJpaEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blogprojectlog_seq_gen")
  @SequenceGenerator(name = "blogprojectlog_seq_gen", sequenceName = "blogprojectlog_seq", allocationSize = 1)
  private Long id;
  private BlogContentData contentData;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectLog", orphanRemoval = true)
  @OrderBy("id ASC")
  private List<ProjectLogCategory> categories = new ArrayList<>();

  public ProjectLog(String title, String content) {
    this.contentData = BlogContentData.of(title, content);
  }

  public static ProjectLog of(String title, String content) {
    return new ProjectLog(title, content);
  }

  public ProjectLogCategory addProjectLogCategory(String title) {
    ProjectLogCategory createdCategory = ProjectLogCategory.of(title);
    categories.add(createdCategory);
    createdCategory.setProjectLog(this);
    return createdCategory;
  }
}
