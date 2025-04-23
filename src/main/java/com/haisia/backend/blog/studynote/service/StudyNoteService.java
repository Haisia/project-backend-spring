package com.haisia.backend.blog.studynote.service;

import com.haisia.backend.blog.studynote.dto.CreateStudyNoteCategoryRequest;
import com.haisia.backend.blog.studynote.dto.CreateStudyNoteRequest;
import com.haisia.backend.blog.studynote.dto.GetAllStudyNoteResponse;
import com.haisia.backend.blog.studynote.dto.GetStudyNoteResponse;

public interface StudyNoteService {
  Long createStudyNoteCategory(CreateStudyNoteCategoryRequest request);
  Long createStudyNote(CreateStudyNoteRequest request);

  GetStudyNoteResponse getStudyNote(Long id);
  GetAllStudyNoteResponse getAllStudyNote();
}
