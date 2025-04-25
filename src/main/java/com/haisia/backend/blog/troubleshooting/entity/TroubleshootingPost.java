package com.haisia.backend.blog.troubleshooting.entity;

import com.haisia.backend.blog.common.vo.BlogContentData;
import com.haisia.backend.common.entity.BaseJpaEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Table(name = "blog_troubleshooting_post")
@Entity
public class TroubleshootingPost extends BaseJpaEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blogtroubleshootingpost_seq_gen")
  @SequenceGenerator(name = "blogtroubleshootingpost_seq_gen", sequenceName = "blogtroubleshootingpost_seq", allocationSize = 1)
  private Long id;
  private BlogContentData contentData;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "blog_troubleshooting_category_id")
  private TroubleshootingCategory category;

  protected TroubleshootingPost(String title, String content) {
    this.contentData = BlogContentData.of(title, content);
  }

  public static TroubleshootingPost of(String title, String content) {
    return new TroubleshootingPost(title, content);
  }
}
