package com.haisia.backend.blog.troubleshooting.entity;

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
@Table(name = "blog_troubleshooting_category")
@Entity
public class TroubleshootingCategory extends BaseJpaEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blogtroubleshootingcategory_seq_gen")
  @SequenceGenerator(name = "blogtroubleshootingcategory_seq_gen", sequenceName = "blogtroubleshootingcategory_seq", allocationSize = 1)
  private Long id;
  private String title;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "blog_troubleshooting_id")
  private Troubleshooting troubleshooting;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "category", orphanRemoval = true, fetch = FetchType.LAZY)
  @OrderBy("id ASC")
  private List<TroubleshootingPost> posts = new ArrayList<>();

  protected TroubleshootingCategory(String title) {
    this.title = title;
  }

  protected static TroubleshootingCategory of(String title) {
    return new TroubleshootingCategory(title);
  }

  public TroubleshootingPost addPost(String title, String content) {
    TroubleshootingPost createdPost = TroubleshootingPost.of(title, content);
    createdPost.setCategory(this);
    posts.add(createdPost);
    return createdPost;
  }
}
