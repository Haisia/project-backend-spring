package com.haisia.backend.blog.studynote.entity;

import com.haisia.backend.common.entity.BaseJpaEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Table(name = "blog_study_note_category")
@Entity
public class StudyNoteCategory extends BaseJpaEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blogstudynotecategory_seq_gen")
  @SequenceGenerator(name = "blogstudynotecategory_seq_gen", sequenceName = "blogstudynotecategory_seq", allocationSize = 1)
  private Long id;
  private String title;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "category", orphanRemoval = true, fetch = FetchType.LAZY)
  private List<StudyNotePost> posts = new ArrayList<>();

  private StudyNoteCategory(String title) {
    this.title = title;
  }

  public static StudyNoteCategory of(String title) {
    return new StudyNoteCategory(title);
  }

  public StudyNotePost addStudyNote(String title, String content) {
    StudyNotePost createdStudyNotePost = StudyNotePost.of(title, content);
    createdStudyNotePost.setCategory(this);
    posts.add(createdStudyNotePost);
    return createdStudyNotePost;
  }

}
