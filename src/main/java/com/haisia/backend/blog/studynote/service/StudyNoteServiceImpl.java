package com.haisia.backend.blog.studynote.service;

import com.haisia.backend.blog.studynote.dto.CreateStudyNoteCategoryRequest;
import com.haisia.backend.blog.studynote.dto.CreateStudyNoteRequest;
import com.haisia.backend.blog.studynote.dto.GetAllStudyNoteResponse;
import com.haisia.backend.blog.studynote.dto.GetStudyNoteResponse;
import com.haisia.backend.blog.studynote.entity.StudyNote;
import com.haisia.backend.blog.studynote.entity.StudyNoteCategory;
import com.haisia.backend.blog.studynote.repository.StudyNoteCategoryRepository;
import com.haisia.backend.blog.studynote.repository.StudyNoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class StudyNoteServiceImpl implements StudyNoteService {

  private final StudyNoteRepository studyNoteRepository;
  private final StudyNoteCategoryRepository categoryRepository;

  @Override
  public Long createStudyNoteCategory(CreateStudyNoteCategoryRequest request) {
    if (categoryRepository.existsByTitle(request.getTitle())) throw new RuntimeException("Category already exists");
    StudyNoteCategory createdCategory = StudyNoteCategory.of(request.getTitle());
    return categoryRepository.save(createdCategory).getId();
  }

  @Override
  public Long createStudyNote(CreateStudyNoteRequest request) {
    StudyNoteCategory foundCategory = categoryRepository.findById(request.getCategoryId()).orElseThrow();
    StudyNote createdStudyNote = foundCategory.addStudyNote(request.getTitle(), request.getContent());
    return studyNoteRepository.save(createdStudyNote).getId();
  }

  @Override
  public GetStudyNoteResponse getStudyNote(Long id) {
    StudyNote studyNote = studyNoteRepository.findById(id).orElseThrow();
    return GetStudyNoteResponse.from(studyNote);
  }

  @Override
  public GetAllStudyNoteResponse getAllStudyNote() {
    return GetAllStudyNoteResponse.from(categoryRepository.findAll());
  }
}
