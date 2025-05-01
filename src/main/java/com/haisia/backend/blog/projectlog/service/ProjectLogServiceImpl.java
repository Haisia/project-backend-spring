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
import com.haisia.backend.common.enums.ResponseCode;
import com.haisia.backend.common.exception.ApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    ProjectLog foundProjectLog = projectRepository.findById(request.getProjectLogId())
      .orElseThrow(() -> new ApplicationException(
        ResponseCode.DATABASE_ERROR,
        String.format("[blog_ProjectLog 조회 실패] id=%s", request.getProjectLogId())
      ));
    ProjectLogCategory createdCategory = foundProjectLog.addProjectLogCategory(request.getTitle());
    ProjectLogCategory savedCategory = categoryRepository.save(createdCategory);
    return savedCategory.getId();
  }

  @Override
  public Long createProjectLogPost(CreateProjectLogPostRequest request) {
    ProjectLogCategory category = categoryRepository.findById(request.getCategoryId())
      .orElseThrow(() -> new ApplicationException(
        ResponseCode.DATABASE_ERROR,
        String.format("[blog_ProjectLogCategory 조회 실패] id=%s", request.getCategoryId())
      ));
    ProjectLogPost createdPost = category.addPost(request.title, request.content);
    ProjectLogPost savedPost = postRepository.save(createdPost);
    return savedPost.getId();
  }

  @Override
  public GetProjectLogPostResponse getProjectLog(Long id) {
    return GetProjectLogPostResponse.from(projectRepository.findById(id)
      .orElseThrow(() -> new ApplicationException(
        ResponseCode.DATABASE_ERROR,
        String.format("[blog_ProjectLog 조회 실패] id=%s", id)
      ))
    );
  }

  @Override
  public GetProjectLogPostResponse getProjectLogPost(Long id) {
    return GetProjectLogPostResponse.from(postRepository.findById(id)
      .orElseThrow(() -> new ApplicationException(
        ResponseCode.DATABASE_ERROR,
        String.format("[blog_ProjectLogPost 조회 실패] id=%s", id)
      ))
    );
  }

  @Override
  public GetAllProjectLogResponse getAllProjectLogPosts() {
    return GetAllProjectLogResponse.from(projectRepository.findAll());
  }
}
