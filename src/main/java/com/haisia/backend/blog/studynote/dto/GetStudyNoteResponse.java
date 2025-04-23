package com.haisia.backend.blog.studynote.dto;

import com.haisia.backend.blog.studynote.entity.StudyNote;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class GetStudyNoteResponse {
  public Long categoryId;
  public String categoryTitle;
  public Long id;
  public String title;
  public String content;

  public static GetStudyNoteResponse from(StudyNote entity) {
    return GetStudyNoteResponse.builder()
      .categoryId(entity.getCategory().getId())
      .categoryTitle(entity.getCategory().getTitle())
      .id(entity.getId())
      .title(entity.getContentData().getTitle())
      .content(entity.getContentData().getContent())
      .build();
  }
}
