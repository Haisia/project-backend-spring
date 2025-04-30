package com.haisia.backend.blog.devnews.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.haisia.backend.blog.devnews.dto.DevNewsDto;
import com.haisia.backend.blog.devnews.dto.DevNewsRequest;
import com.haisia.backend.blog.devnews.dto.DevNewsResponse;
import com.haisia.backend.blog.devnews.entity.DevNews;
import com.haisia.backend.blog.devnews.repository.DevNewsRepository;
import com.haisia.backend.common.enums.ResponseCode;
import com.haisia.backend.common.exception.ApplicationException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DevNewsServiceImplTest {

  @Mock private DevNewsRepository devNewsRepository;
  @InjectMocks private DevNewsServiceImpl devNewsService;

  private DevNews devNews;
  private DevNewsRequest request;
  private final Long testId = 1L;

  @BeforeEach
  void setUp() {
    request = new DevNewsRequest("테스트 제목", "테스트 내용");
    devNews = DevNews.builder()
      .id(testId)
      .title(request.getTitle())
      .content(request.getContent())
      .build();
  }

  @Test
  @DisplayName("DevNews 등록 성공 테스트")
  void postDevNews_Success() {
    // given
    when(devNewsRepository.save(any(DevNews.class))).thenReturn(devNews);

    // when
    devNewsService.postDevNews(request);

    // then
    verify(devNewsRepository, times(1)).save(any(DevNews.class));
  }

  @Test
  @DisplayName("DevNews 단건 조회 성공 테스트")
  void getDevNews_Success() {
    // given
    when(devNewsRepository.findById(testId)).thenReturn(Optional.of(devNews));

    // when
    DevNewsResponse response = devNewsService.getDevNews(testId);

    // then
    assertNotNull(response);
    assertEquals(1, response.getBlogDevNewses().size());
    DevNewsDto devNewsDto = response.getBlogDevNewses().getFirst();

    assertEquals(testId, devNewsDto.getId());
    assertEquals(request.getTitle(), devNewsDto.getTitle());
    assertEquals(request.getContent(), devNewsDto.getContent());
    verify(devNewsRepository, times(1)).findById(testId);
  }

  @Test
  @DisplayName("DevNews 단건 조회 실패 테스트 - 존재하지 않는 ID")
  void getDevNews_Fail_NotFound() {
    // given
    when(devNewsRepository.findById(testId)).thenReturn(Optional.empty());

    // when & then
    ApplicationException exception = assertThrows(ApplicationException.class,
      () -> devNewsService.getDevNews(testId));
    assertEquals(ResponseCode.DATABASE_ERROR, exception.getCode());
    assertTrue(exception.getMessage().contains("[blog_DevNews 조회 실패]"));
    verify(devNewsRepository, times(1)).findById(testId);
  }

  @Test
  @DisplayName("DevNews 전체 조회 성공 테스트")
  void getAllDevNews_Success() {
    // given
    DevNews devNews2 = DevNews.builder()
      .id(2L)
      .title("두 번째 제목")
      .content("두 번째 내용")
      .build();

    List<DevNews> devNewsList = Arrays.asList(devNews, devNews2);
    when(devNewsRepository.findAll()).thenReturn(devNewsList);

    // when
    DevNewsResponse response = devNewsService.getAllDevNews();

    // then
    assertNotNull(response);
    assertEquals(2, response.getBlogDevNewses().size());
    assertEquals("테스트 제목", response.getBlogDevNewses().getFirst().getTitle());
    assertEquals("두 번째 제목", response.getBlogDevNewses().get(1).getTitle());
    verify(devNewsRepository, times(1)).findAll();
  }
}