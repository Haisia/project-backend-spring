package com.haisia.backend.blog.entity;

import com.haisia.backend.blog.entity.vo.BlogContentData;
import com.haisia.backend.common.entity.BaseJpaEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
@Table(name = "blog_project_log_post")
@Entity
public class ProjectLogPost extends BaseJpaEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "projectlogpost_seq_gen")
  @SequenceGenerator(name = "projectlogpost_seq_gen", sequenceName = "projectlogpost_seq", allocationSize = 1)
  private Long id;
  private BlogContentData contentData;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "blog_project_log_category_id")
  private ProjectLogCategory category;

  private ProjectLogPost(BlogContentData contentData) {
    this.contentData = contentData;
  }

  public static ProjectLogPost of(BlogContentData contentData) {
    return new ProjectLogPost(contentData);
  }
}
