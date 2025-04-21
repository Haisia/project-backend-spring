package com.haisia.backend.blog.service;

import com.haisia.backend.blog.dto.devnews.BlogDevNewsRequest;
import com.haisia.backend.blog.dto.devnews.BlogDevNewsResponse;

public interface DevNewsService {
  void postDevNews(BlogDevNewsRequest request);
  BlogDevNewsResponse getDevNews(Long id);
  BlogDevNewsResponse getAllDevNews();
}
