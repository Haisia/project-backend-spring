package com.haisia.backend.blog.entity;

import com.haisia.backend.blog.entity.vo.BlogContentData;
import com.haisia.backend.common.entity.BaseJpaEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
@Entity
public class BlogDevNews extends BaseJpaEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blogdevnews_seq_gen")
  @SequenceGenerator(name = "blogdevnews_seq_gen", sequenceName = "blogdevnews_seq", allocationSize = 1)
  private Long id;

  private BlogContentData contentData;

  @Builder
  public BlogDevNews(Long id, String title, String content) {
    this.id = id;
    this.contentData = new BlogContentData(title, content);
  }
}
