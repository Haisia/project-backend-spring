package com.haisia.backend.blog.troubleshooting.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.haisia.backend.blog.troubleshooting.dto.*;
import com.haisia.backend.blog.troubleshooting.entity.*;
import com.haisia.backend.blog.troubleshooting.repository.*;
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
class TroubleshootingServiceImplTest {

  @Mock private TroubleshootingRepository troubleshootingRepository;
  @Mock private TroubleshootingCategoryRepository categoryRepository;
  @Mock private TroubleshootingPostRepository postRepository;
  @InjectMocks private TroubleshootingServiceImpl troubleshootingService;

  private final Long testTroubleshootingId = 1L;
  private final Long testCategoryId = 1L;
  private final Long testPostId = 1L;
  private Troubleshooting troubleshooting;
  private TroubleshootingCategory category;
  private TroubleshootingPost post;

  @BeforeEach
  void setUp() {
    troubleshooting = Troubleshooting.of("테스트 트러블슈팅");
    troubleshooting.setId(testTroubleshootingId);

    category = troubleshooting.addCategory("테스트 카테고리");
    category.setId(testCategoryId);

    post = category.addPost("테스트 포스트", "포스트 내용");
    post.setId(testPostId);
  }

  @Test
  @DisplayName("트러블슈팅 생성 성공 테스트")
  void createTroubleshooting_Success() {
    // given
    CreateTroubleshootingRequest request = new CreateTroubleshootingRequest("새 트러블슈팅");
    when(troubleshootingRepository.save(any(Troubleshooting.class))).thenReturn(troubleshooting);

    // when
    Long createdId = troubleshootingService.createTroubleshooting(request);

    // then
    assertNotNull(createdId);
    assertEquals(testTroubleshootingId, createdId);
    verify(troubleshootingRepository, times(1)).save(any(Troubleshooting.class));
  }

  @Test
  @DisplayName("트러블슈팅 카테고리 생성 성공 테스트")
  void createCategory_Success() {
    // given
    CreateTroubleshootingCategoryRequest request =
      new CreateTroubleshootingCategoryRequest(testTroubleshootingId, "새 카테고리");

    when(troubleshootingRepository.findById(testTroubleshootingId)).thenReturn(Optional.of(troubleshooting));
    when(categoryRepository.save(any(TroubleshootingCategory.class))).thenReturn(category);

    // when
    Long createdId = troubleshootingService.createCategory(request);

    // then
    assertNotNull(createdId);
    assertEquals(testCategoryId, createdId);
    verify(troubleshootingRepository, times(1)).findById(testTroubleshootingId);
    verify(categoryRepository, times(1)).save(any(TroubleshootingCategory.class));
  }

  @Test
  @DisplayName("트러블슈팅 카테고리 생성 실패 테스트 - 트러블슈팅 없음")
  void createCategory_Fail_TroubleshootingNotFound() {
    // given
    CreateTroubleshootingCategoryRequest request =
      new CreateTroubleshootingCategoryRequest(testTroubleshootingId, "새 카테고리");

    when(troubleshootingRepository.findById(testTroubleshootingId)).thenReturn(Optional.empty());

    // when & then
    ApplicationException exception = assertThrows(ApplicationException.class,
      () -> troubleshootingService.createCategory(request));

    assertEquals(ResponseCode.DATABASE_ERROR, exception.getCode());
    assertTrue(exception.getMessage().contains("[blog_TroubleshootingCategory 조회 실패]"));
    verify(troubleshootingRepository, times(1)).findById(testTroubleshootingId);
    verify(categoryRepository, never()).save(any());
  }

  @Test
  @DisplayName("트러블슈팅 포스트 생성 성공 테스트")
  void createPost_Success() {
    // given
    CreateTroubleshootingPostRequest request =
      new CreateTroubleshootingPostRequest(testCategoryId, "새 포스트", "포스트 내용");

    when(categoryRepository.findById(testCategoryId)).thenReturn(Optional.of(category));
    when(postRepository.save(any(TroubleshootingPost.class))).thenReturn(post);

    // when
    Long createdId = troubleshootingService.createPost(request);

    // then
    assertNotNull(createdId);
    assertEquals(testPostId, createdId);
    verify(categoryRepository, times(1)).findById(testCategoryId);
    verify(postRepository, times(1)).save(any(TroubleshootingPost.class));
  }

  @Test
  @DisplayName("트러블슈팅 포스트 조회 성공 테스트")
  void getPost_Success() {
    // given
    when(postRepository.findById(testPostId)).thenReturn(Optional.of(post));

    // when
    GetTroubleshootingPostResponse response = troubleshootingService.getPost(testPostId);

    // then
    assertNotNull(response);
    assertEquals(testPostId, response.getId());
    assertEquals("테스트 포스트", response.getTitle());
    verify(postRepository, times(1)).findById(testPostId);
  }

  @Test
  @DisplayName("전체 트러블슈팅 포스트 조회 성공 테스트")
  void getAllPosts_Success() {
    // given
    when(troubleshootingRepository.findAll()).thenReturn(List.of(troubleshooting));

    // when
    GetAllTroubleshootingPostsResponse response = troubleshootingService.getAllPosts();

    // then
    assertNotNull(response);
    assertEquals(1, response.getTroubleshootings().size());
    verify(troubleshootingRepository, times(1)).findAll();
  }
}