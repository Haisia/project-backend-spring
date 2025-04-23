package com.haisia.backend.blog.devnews.service;

import com.haisia.backend.blog.devnews.dto.DevNewsRequest;
import com.haisia.backend.blog.devnews.dto.DevNewsResponse;

public interface DevNewsService {
  void postDevNews(DevNewsRequest request);
  DevNewsResponse getDevNews(Long id);
  DevNewsResponse getAllDevNews();
}
