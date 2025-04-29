package com.haisia.backend.blog.studynote.entity;

import com.haisia.backend.blog.common.vo.BlogContentData;
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
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "blog_study_note_post")
@Entity
public class StudyNotePost extends BaseJpaEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blogstudynotepost_seq_gen")
  @SequenceGenerator(name = "blogstudynotepost_seq_gen", sequenceName = "blogstudynotepost_seq", allocationSize = 1)
  private Long id;
  private BlogContentData contentData;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "blog_study_note_category_id")
  private StudyNoteCategory category;

  private StudyNotePost(BlogContentData contentData) {
    this.contentData = contentData;
  }

  protected static StudyNotePost of(String title, String content) {
    BlogContentData contentData = BlogContentData.of(title, content);
    return new StudyNotePost(contentData);
  }
}
