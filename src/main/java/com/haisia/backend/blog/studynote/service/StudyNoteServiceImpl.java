package com.haisia.backend.blog.studynote.service;

import com.haisia.backend.blog.studynote.dto.CreateStudyNoteCategoryRequest;
import com.haisia.backend.blog.studynote.dto.CreateStudyNoteRequest;
import com.haisia.backend.blog.studynote.dto.GetAllStudyNoteResponse;
import com.haisia.backend.blog.studynote.dto.GetStudyNoteResponse;
import com.haisia.backend.blog.studynote.entity.StudyNotePost;
import com.haisia.backend.blog.studynote.entity.StudyNoteCategory;
import com.haisia.backend.blog.studynote.repository.StudyNoteCategoryRepository;
import com.haisia.backend.blog.studynote.repository.StudyNoteRepository;
import com.haisia.backend.common.enums.ResponseCode;
import com.haisia.backend.common.exception.ApplicationException;
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
    StudyNoteCategory foundCategory = categoryRepository.findById(request.getCategoryId())
      .orElseThrow(() -> new ApplicationException(
        ResponseCode.DATABASE_ERROR,
        String.format("[blog_StudyNodeCategory 조회 실패] id=%s", request.getCategoryId())
      ));
    StudyNotePost createdStudyNotePost = foundCategory.addStudyNote(request.getTitle(), request.getContent());
    return studyNoteRepository.save(createdStudyNotePost).getId();
  }

  @Override
  public GetStudyNoteResponse getStudyNote(Long id) {
    StudyNotePost studyNotePost = studyNoteRepository.findById(id)
      .orElseThrow(() -> new ApplicationException(
        ResponseCode.DATABASE_ERROR,
        String.format("[blog_StudyNote 조회 실패] id=%s", id)
      ));
    return GetStudyNoteResponse.from(studyNotePost);
  }

  @Override
  public GetAllStudyNoteResponse getAllStudyNote() {
    return GetAllStudyNoteResponse.from(categoryRepository.findAll());
  }
}
