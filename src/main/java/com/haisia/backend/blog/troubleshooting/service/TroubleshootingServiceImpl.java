package com.haisia.backend.blog.troubleshooting.service;

import com.haisia.backend.blog.troubleshooting.dto.*;
import com.haisia.backend.blog.troubleshooting.entity.Troubleshooting;
import com.haisia.backend.blog.troubleshooting.entity.TroubleshootingCategory;
import com.haisia.backend.blog.troubleshooting.entity.TroubleshootingPost;
import com.haisia.backend.blog.troubleshooting.repository.TroubleshootingCategoryRepository;
import com.haisia.backend.blog.troubleshooting.repository.TroubleshootingPostRepository;
import com.haisia.backend.blog.troubleshooting.repository.TroubleshootingRepository;
import com.haisia.backend.common.enums.ResponseCode;
import com.haisia.backend.common.exception.ApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TroubleshootingServiceImpl implements TroubleshootingService {

  private final TroubleshootingRepository troubleshootingRepository;
  private final TroubleshootingCategoryRepository categoryRepository;
  private final TroubleshootingPostRepository postRepository;

  @Override
  public Long createTroubleshooting(CreateTroubleshootingRequest request) {
    Troubleshooting saved = troubleshootingRepository.save(Troubleshooting.of(request.getTitle()));
    return saved.getId();
  }

  @Override
  public Long createCategory(CreateTroubleshootingCategoryRequest request) {
    Troubleshooting foundTroubleshooting = troubleshootingRepository.findById(request.getTroubleshootingId())
      .orElseThrow(() -> new ApplicationException(
        ResponseCode.DATABASE_ERROR,
        String.format("[blog_TroubleshootingCategory 조회 실패] id=%s", request.getTroubleshootingId())
      ));
    TroubleshootingCategory createdCategory = foundTroubleshooting.addCategory(request.getTitle());
    return categoryRepository.save(createdCategory).getId();
  }

  @Override
  public Long createPost(CreateTroubleshootingPostRequest request) {
    TroubleshootingCategory foundCategory = categoryRepository.findById(request.getCategoryId())
      .orElseThrow(() -> new ApplicationException(
        ResponseCode.DATABASE_ERROR,
        String.format("[blog_TroubleshootingCategory 조회 실패] id=%s", request.getCategoryId())
      ));
    TroubleshootingPost createdPost = foundCategory.addPost(request.getTitle(), request.getContent());
    return postRepository.save(createdPost).getId();
  }

  @Override
  public GetTroubleshootingPostResponse getPost(Long id) {
    TroubleshootingPost createdPost = postRepository.findById(id)
      .orElseThrow(() -> new ApplicationException(
        ResponseCode.DATABASE_ERROR,
        String.format("[blog_TroubleshootingPost 조회 실패] id=%s", id)
      ));
    return GetTroubleshootingPostResponse.from(createdPost);
  }

  @Override
  public GetAllTroubleshootingPostsResponse getAllPosts() {
    return GetAllTroubleshootingPostsResponse.from(troubleshootingRepository.findAll());
  }
}
