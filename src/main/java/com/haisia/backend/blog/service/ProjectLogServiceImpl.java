package com.haisia.backend.blog.service;

import com.haisia.backend.blog.dto.projectlog.CreateProjectLogCategoryRequest;
import com.haisia.backend.blog.dto.projectlog.CreateProjectLogPostRequest;
import com.haisia.backend.blog.dto.projectlog.CreateProjectLogRequest;
import com.haisia.backend.blog.dto.projectlog.GetAllProjectLogResponse;
import com.haisia.backend.blog.dto.projectlog.GetProjectLogResponse;
import com.haisia.backend.blog.entity.ProjectLog;
import com.haisia.backend.blog.entity.ProjectLogCategory;
import com.haisia.backend.blog.entity.ProjectLogPost;
import com.haisia.backend.blog.repository.ProjectLogCategoryRepository;
import com.haisia.backend.blog.repository.ProjectLogPostRepository;
import com.haisia.backend.blog.repository.ProjectLogRepository;
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
  public GetProjectLogResponse getProjectLog(Long projectId) {
    return GetProjectLogResponse.from(projectRepository.findById(projectId).orElseThrow());
  }

  @Override
  public GetProjectLogResponse getProjectLogPost(Long id) {
    return GetProjectLogResponse.from(postRepository.findById(id).orElseThrow());
  }

  @Override
  public GetAllProjectLogResponse getAllProjectLogPosts() {
    return GetAllProjectLogResponse.from(projectRepository.findAll());
  }
}
