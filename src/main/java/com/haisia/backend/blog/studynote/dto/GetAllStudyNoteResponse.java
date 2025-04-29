package com.haisia.backend.blog.studynote.dto;

import com.haisia.backend.blog.studynote.entity.StudyNoteCategory;
import com.haisia.backend.blog.studynote.entity.StudyNotePost;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class GetAllStudyNoteResponse {
  protected List<Category> categories = new ArrayList<>();

  public static GetAllStudyNoteResponse from(List<StudyNoteCategory> categoryEntities) {
    List<Category> categories = categoryEntities.stream()
      .map(Category::from)
      .toList();

    return new GetAllStudyNoteResponse(categories);
  }

  @Builder
  @NoArgsConstructor @AllArgsConstructor
  @Getter @Setter
  protected static class Category {
    public Long id;
    public String title;
    public List<StudyNote> posts = new ArrayList<>();

    public static Category from(StudyNoteCategory entity) {
      List<StudyNote> studyNotes = entity.getPosts()
        .stream()
        .map(StudyNote::from)
        .toList();

      return Category.builder()
        .id(entity.getId())
        .title(entity.getTitle())
        .posts(studyNotes)
        .build();
    }
  }

  @Builder
  @NoArgsConstructor @AllArgsConstructor
  @Getter @Setter
  protected static class StudyNote {
    public Long id;
    public String title;
    public String content;

    public static StudyNote from(StudyNotePost entity) {
      return StudyNote.builder()
        .id(entity.getId())
        .title(entity.getContentData().getTitle())
        .content(entity.getContentData().getContent())
        .build();
    }
  }
}
