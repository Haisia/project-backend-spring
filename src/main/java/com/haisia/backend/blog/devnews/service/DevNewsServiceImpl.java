package com.haisia.backend.blog.devnews.service;

import com.haisia.backend.blog.devnews.dto.DevNewsRequest;
import com.haisia.backend.blog.devnews.dto.DevNewsResponse;
import com.haisia.backend.blog.devnews.entity.DevNews;
import com.haisia.backend.blog.devnews.repository.DevNewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class DevNewsServiceImpl implements DevNewsService {

  private final DevNewsRepository devNewsRepository;

  public void postDevNews(DevNewsRequest request) {
    DevNews devNews = DevNews.builder()
      .title(request.title)
      .content(request.content)
      .build();

    devNewsRepository.save(devNews);
  }

  public DevNewsResponse getDevNews(Long id) {
    DevNews devNews = devNewsRepository.findById(id).orElseThrow();
    return DevNewsResponse.from(devNews);
  }

  public DevNewsResponse getAllDevNews() {
    List<DevNews> all = devNewsRepository.findAll();
    return DevNewsResponse.from(all);
  }
}
