package com.haisia.backend.blog.service;

import com.haisia.backend.blog.dto.devnews.BlogDevNewsRequest;
import com.haisia.backend.blog.dto.devnews.BlogDevNewsResponse;

import java.util.List;

public interface DevNewsService {
  void postDevNews(BlogDevNewsRequest request);
  BlogDevNewsResponse getDevNews(Long id);
  List<BlogDevNewsResponse> getAllDevNews();
}
