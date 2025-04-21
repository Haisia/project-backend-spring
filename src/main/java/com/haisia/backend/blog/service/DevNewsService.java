package com.haisia.backend.blog.service;

import com.haisia.backend.blog.dto.devnews.DevNewsRequest;
import com.haisia.backend.blog.dto.devnews.DevNewsResponse;

public interface DevNewsService {
  void postDevNews(DevNewsRequest request);
  DevNewsResponse getDevNews(Long id);
  DevNewsResponse getAllDevNews();
}
