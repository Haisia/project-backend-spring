package com.haisia.backend.blog.troubleshooting.service;

import com.haisia.backend.blog.troubleshooting.dto.*;

public interface TroubleshootingService {
  Long createTroubleshooting(CreateTroubleshootingRequest request);
  Long createCategory(CreateTroubleshootingCategoryRequest request);
  Long createPost(CreateTroubleshootingPostRequest request);

  GetTroubleshootingPostResponse getPost(Long id);
  GetAllTroubleshootingPostsResponse getAllPosts();
}
