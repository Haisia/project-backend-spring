package com.haisia.backend.blog.studynote.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.haisia.backend.blog.studynote.dto.*;
import com.haisia.backend.blog.studynote.entity.*;
import com.haisia.backend.blog.studynote.repository.*;
import com.haisia.backend.common.enums.ResponseCode;
import com.haisia.backend.common.exception.ApplicationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class StudyNoteServiceImplTest {

  @Mock private StudyNoteRepository studyNoteRepository;
  @Mock private StudyNoteCategoryRepository categoryRepository;
  @InjectMocks private StudyNoteServiceImpl studyNoteService;

  private final Long categoryId = 1L;
  private final Long noteId = 1L;
  private StudyNoteCategory category;
  private StudyNotePost post;

  @BeforeEach
  void setUp() {
    category = StudyNoteCategory.of("테스트 카테고리");
    category.setId(categoryId);

    post = category.addStudyNote("테스트 제목", "테스트 내용");
    post.setId(noteId);
  }

  @Test
  @DisplayName("스터디 노트 카테고리 생성 성공 테스트")
  void createStudyNoteCategory_Success() {
    // given
    CreateStudyNoteCategoryRequest request = new CreateStudyNoteCategoryRequest("새 카테고리");
    when(categoryRepository.existsByTitle(request.getTitle())).thenReturn(false);
    when(categoryRepository.save(any(StudyNoteCategory.class))).thenReturn(category);

    // when
    Long createdId = studyNoteService.createStudyNoteCategory(request);

    // then
    assertNotNull(createdId);
    assertEquals(categoryId, createdId);
    verify(categoryRepository, times(1)).existsByTitle(request.getTitle());
    verify(categoryRepository, times(1)).save(any(StudyNoteCategory.class));
  }

  @Test
  @DisplayName("스터디 노트 카테고리 생성 실패 테스트 - 이미 존재하는 카테고리")
  void createStudyNoteCategory_Fail_DuplicateCategory() {
    // given
    CreateStudyNoteCategoryRequest request = new CreateStudyNoteCategoryRequest("기존 카테고리");
    when(categoryRepository.existsByTitle(request.getTitle())).thenReturn(true);

    // when & then
    RuntimeException exception = assertThrows(RuntimeException.class,
      () -> studyNoteService.createStudyNoteCategory(request));

    assertEquals("Category already exists", exception.getMessage());
    verify(categoryRepository, times(1)).existsByTitle(request.getTitle());
    verify(categoryRepository, never()).save(any());
  }

  @Test
  @DisplayName("스터디 노트 생성 성공 테스트")
  void createStudyNote_Success() {
    // given
    CreateStudyNoteRequest request =
      new CreateStudyNoteRequest(categoryId, "새 노트", "노트 내용");

    when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
    when(studyNoteRepository.save(any(StudyNotePost.class))).thenReturn(post);

    // when
    Long createdId = studyNoteService.createStudyNote(request);

    // then
    assertNotNull(createdId);
    assertEquals(noteId, createdId);
    verify(categoryRepository, times(1)).findById(categoryId);
    verify(studyNoteRepository, times(1)).save(any(StudyNotePost.class));
  }

  @Test
  @DisplayName("스터디 노트 생성 실패 테스트 - 존재하지 않는 카테고리")
  void createStudyNote_Fail_CategoryNotFound() {
    // given
    CreateStudyNoteRequest request =
      new CreateStudyNoteRequest(categoryId, "새 노트", "노트 내용");

    when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

    // when & then
    ApplicationException exception = assertThrows(ApplicationException.class,
      () -> studyNoteService.createStudyNote(request));

    assertEquals(ResponseCode.DATABASE_ERROR, exception.getCode());
    assertTrue(exception.getMessage().contains("[blog_StudyNodeCategory 조회 실패]"));
    verify(categoryRepository, times(1)).findById(categoryId);
    verify(studyNoteRepository, never()).save(any());
  }

  @Test
  @DisplayName("스터디 노트 단건 조회 성공 테스트")
  void getStudyNote_Success() {
    // given
    when(studyNoteRepository.findById(noteId)).thenReturn(Optional.of(post));

    // when
    GetStudyNoteResponse response = studyNoteService.getStudyNote(noteId);

    // then
    assertNotNull(response);
    assertEquals(noteId, response.getId());
    assertEquals("테스트 제목", response.getTitle());
    verify(studyNoteRepository, times(1)).findById(noteId);
  }

  @Test
  @DisplayName("전체 스터디 노트 조회 성공 테스트")
  void getAllStudyNote_Success() {
    // given
    when(categoryRepository.findAll()).thenReturn(List.of(category));

    // when
    GetAllStudyNoteResponse response = studyNoteService.getAllStudyNote();

    // then
    assertNotNull(response);
    assertEquals(1, response.getCategories().size());
    verify(categoryRepository, times(1)).findAll();
  }
}