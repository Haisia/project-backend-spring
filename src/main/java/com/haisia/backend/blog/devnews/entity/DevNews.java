package com.haisia.backend.blog.devnews.entity;

import com.haisia.backend.blog.common.vo.BlogContentData;
import com.haisia.backend.common.entity.BaseJpaEntity;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Table(name = "blog_dev_news")
@Entity
public class DevNews extends BaseJpaEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blogdevnews_seq_gen")
  @SequenceGenerator(name = "blogdevnews_seq_gen", sequenceName = "blogdevnews_seq", allocationSize = 1)
  private Long id;

  private BlogContentData contentData;

  @Builder
  protected DevNews(Long id, String title, String content) {
    this.id = id;
    this.contentData = new BlogContentData(title, content);
  }
}
