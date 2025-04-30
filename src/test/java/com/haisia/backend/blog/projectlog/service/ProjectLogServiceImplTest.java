package com.haisia.backend.blog.projectlog.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.haisia.backend.blog.projectlog.dto.*;
import com.haisia.backend.blog.projectlog.entity.*;
import com.haisia.backend.blog.projectlog.repository.*;
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
class ProjectLogServiceImplTest {

  @Mock private ProjectLogRepository projectRepository;
  @Mock private ProjectLogCategoryRepository categoryRepository;
  @Mock private ProjectLogPostRepository postRepository;
  @InjectMocks private ProjectLogServiceImpl projectLogService;

  private final Long projectId = 1L;
  private final Long categoryId = 1L;
  private final Long postId = 1L;
  private ProjectLog projectLog;
  private ProjectLogCategory category;
  private ProjectLogPost post;

  @BeforeEach
  void setUp() {
    projectLog = ProjectLog.of("테스트 프로젝트", "프로젝트 설명");
    projectLog.setId(projectId);

    String categoryTitle = "테스트 카테고리";
    category = projectLog.addProjectLogCategory(categoryTitle);
    category.setId(categoryId);

    String postTitle = "테스트 포스트";
    String postContent = "테스트 포스트";
    post = category.addPost(postTitle, postContent);
    post.setId(postId);
  }

  @Test
  @DisplayName("프로젝트 로그 생성 성공 테스트")
  void createProjectLog_Success() {
    // given
    CreateProjectLogRequest request = new CreateProjectLogRequest("새 프로젝트", "프로젝트 설명");
    when(projectRepository.save(any(ProjectLog.class))).thenReturn(projectLog);

    // when
    Long createdId = projectLogService.createProjectLog(request);

    // then
    assertNotNull(createdId);
    assertEquals(projectId, createdId);
    verify(projectRepository, times(1)).save(any(ProjectLog.class));
  }

  @Test
  @DisplayName("프로젝트 로그 카테고리 생성 성공 테스트")
  void createProjectLogCategory_Success() {
    // given
    CreateProjectLogCategoryRequest request =
      new CreateProjectLogCategoryRequest("새 카테고리", projectId);

    when(projectRepository.findById(projectId)).thenReturn(Optional.of(projectLog));
    when(categoryRepository.save(any(ProjectLogCategory.class))).thenReturn(category);

    // when
    Long createdId = projectLogService.createProjectLogCategory(request);

    // then
    assertNotNull(createdId);
    assertEquals(categoryId, createdId);
    verify(projectRepository, times(1)).findById(projectId);
    verify(categoryRepository, times(1)).save(any(ProjectLogCategory.class));
  }

  @Test
  @DisplayName("프로젝트 로그 카테고리 생성 실패 테스트 - 프로젝트 없음")
  void createProjectLogCategory_Fail_ProjectNotFound() {
    // given
    CreateProjectLogCategoryRequest request =
      new CreateProjectLogCategoryRequest("새 카테고리", projectId);

    when(projectRepository.findById(projectId)).thenReturn(Optional.empty());

    // when & then
    ApplicationException exception = assertThrows(ApplicationException.class,
      () -> projectLogService.createProjectLogCategory(request));

    assertEquals(ResponseCode.DATABASE_ERROR, exception.getCode());
    assertTrue(exception.getMessage().contains("[blog_ProjectLog 조회 실패]"));
    verify(projectRepository, times(1)).findById(projectId);
  }

  @Test
  @DisplayName("프로젝트 로그 포스트 생성 성공 테스트")
  void createProjectLogPost_Success() {
    // given
    CreateProjectLogPostRequest request =
      new CreateProjectLogPostRequest(categoryId, "새 포스트", "포스트 내용");

    when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
    when(postRepository.save(any(ProjectLogPost.class))).thenReturn(post);

    // when
    Long createdId = projectLogService.createProjectLogPost(request);

    // then
    assertNotNull(createdId);
    assertEquals(postId, createdId);
    verify(categoryRepository, times(1)).findById(categoryId);
    verify(postRepository, times(1)).save(any(ProjectLogPost.class));
  }

  @Test
  @DisplayName("프로젝트 로그 조회 성공 테스트")
  void getProjectLog_Success() {
    // given
    when(projectRepository.findById(projectId)).thenReturn(Optional.of(projectLog));

    // when
    GetProjectLogPostResponse response = projectLogService.getProjectLog(projectId);

    // then
    assertNotNull(response);
    assertEquals(projectId, response.getProjectId());
    verify(projectRepository, times(1)).findById(projectId);
  }

  @Test
  @DisplayName("프로젝트 로그 포스트 조회 성공 테스트")
  void getProjectLogPost_Success() {
    // given
    when(postRepository.findById(postId)).thenReturn(Optional.of(post));

    // when
    GetProjectLogPostResponse response = projectLogService.getProjectLogPost(postId);

    // then
    assertNotNull(response);
    assertEquals(postId, response.getPostId());
    verify(postRepository, times(1)).findById(postId);
  }

  @Test
  @DisplayName("전체 프로젝트 로그 조회 성공 테스트")
  void getAllProjectLogPosts_Success() {
    // given
    when(projectRepository.findAll()).thenReturn(List.of(projectLog));

    // when
    GetAllProjectLogResponse response = projectLogService.getAllProjectLogPosts();

    // then
    assertNotNull(response);
    assertEquals(1, response.getProjects().size());
    verify(projectRepository, times(1)).findAll();
  }
}