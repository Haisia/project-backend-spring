package com.haisia.backend.blog.projectlog.service;

import com.haisia.backend.blog.projectlog.dto.CreateProjectLogCategoryRequest;
import com.haisia.backend.blog.projectlog.dto.CreateProjectLogPostRequest;
import com.haisia.backend.blog.projectlog.dto.CreateProjectLogRequest;
import com.haisia.backend.blog.projectlog.dto.GetAllProjectLogResponse;
import com.haisia.backend.blog.projectlog.dto.GetProjectLogPostResponse;
import com.haisia.backend.blog.projectlog.entity.ProjectLog;
import com.haisia.backend.blog.projectlog.entity.ProjectLogCategory;
import com.haisia.backend.blog.projectlog.entity.ProjectLogPost;
import com.haisia.backend.blog.projectlog.repository.ProjectLogCategoryRepository;
import com.haisia.backend.blog.projectlog.repository.ProjectLogPostRepository;
import com.haisia.backend.blog.projectlog.repository.ProjectLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class ProjectLogServiceImpl implements ProjectLogService {

  private final ProjectLogRepository projectRepository;
  private final ProjectLogCategoryRepository categoryRepository;
  private final ProjectLogPostRepository postRepository;

  public Long createProjectLog(CreateProjectLogRequest request) {
    ProjectLog projectLog = ProjectLog.of(request.title, request.content);
    ProjectLog savedProjectLog = projectRepository.save(projectLog);
    return savedProjectLog.getId();
  }

  @Override
  public Long createProjectLogCategory(CreateProjectLogCategoryRequest request) {
    ProjectLog foundProjectLog = projectRepository.findById(request.getProjectLogId()).orElseThrow();
    ProjectLogCategory createdCategory = foundProjectLog.addProjectLogCategory(request.getTitle());
    ProjectLogCategory savedCategory = categoryRepository.save(createdCategory);
    return savedCategory.getId();
  }

  @Override
  public Long createProjectLogPost(CreateProjectLogPostRequest request) {
    ProjectLogCategory category = categoryRepository.findById(request.getCategoryId()).orElseThrow();
    ProjectLogPost createdPost = category.addPost(request.title, request.content);
    ProjectLogPost savedPost = postRepository.save(createdPost);
    return savedPost.getId();
  }

  @Override
  public GetProjectLogPostResponse getProjectLog(Long projectId) {
    return GetProjectLogPostResponse.from(projectRepository.findById(projectId).orElseThrow());
  }

  @Override
  public GetProjectLogPostResponse getProjectLogPost(Long id) {
    return GetProjectLogPostResponse.from(postRepository.findById(id).orElseThrow());
  }

  @Override
  public GetAllProjectLogResponse getAllProjectLogPosts() {
    return GetAllProjectLogResponse.from(projectRepository.findAll());
  }
}
