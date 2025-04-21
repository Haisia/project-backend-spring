package com.haisia.backend.blog.entity;

import com.haisia.backend.common.entity.BaseJpaEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "blog_project_log")
@Entity
public class ProjectLog extends BaseJpaEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blogprojectlog_seq_gen")
  @SequenceGenerator(name = "blogprojectlog_seq_gen", sequenceName = "blogprojectlog_seq", allocationSize = 1)
  private Long id;
  private String title;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectLog", orphanRemoval = true)
  private List<ProjectLogCategory> projectLogCategories = new ArrayList<>();

  public ProjectLog(String title) {
    this.title = title;
  }

  public static ProjectLog of(String title) {
    return new ProjectLog(title);
  }

  public ProjectLogCategory addProjectLogCategory(String title) {
    ProjectLogCategory createdCategory = ProjectLogCategory.of(title);
    projectLogCategories.add(createdCategory);
    createdCategory.setProjectLog(this);
    return createdCategory;
  }
}
