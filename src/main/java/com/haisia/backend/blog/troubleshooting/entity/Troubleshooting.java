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
@Table(name = "blog_troubleshooting")
@Entity
public class Troubleshooting extends BaseJpaEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blogtroubleshooting_seq_gen")
  @SequenceGenerator(name = "blogtroubleshooting_seq_gen", sequenceName = "blogtroubleshooting_seq", allocationSize = 1)
  private Long id;
  private String title;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "troubleshooting", orphanRemoval = true)
  private List<TroubleshootingCategory> categories = new ArrayList<>();

  protected Troubleshooting(String title) {
    this.title = title;
  }

  public static Troubleshooting of(String title) {
    return new Troubleshooting(title);
  }

  public TroubleshootingCategory addCategory(String title) {
    TroubleshootingCategory category = TroubleshootingCategory.of(title);
    category.setTroubleshooting(this);
    categories.add(category);
    return category;
  }
}
