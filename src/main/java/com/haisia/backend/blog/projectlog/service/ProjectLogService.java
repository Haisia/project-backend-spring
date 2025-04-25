package com.haisia.backend.blog.projectlog.service;

import com.haisia.backend.blog.projectlog.dto.CreateProjectLogCategoryRequest;
import com.haisia.backend.blog.projectlog.dto.CreateProjectLogPostRequest;
import com.haisia.backend.blog.projectlog.dto.CreateProjectLogRequest;
import com.haisia.backend.blog.projectlog.dto.GetAllProjectLogResponse;
import com.haisia.backend.blog.projectlog.dto.GetProjectLogPostResponse;

public interface ProjectLogService {
  Long createProjectLog(CreateProjectLogRequest request);
  Long createProjectLogCategory(CreateProjectLogCategoryRequest request);
  Long createProjectLogPost(CreateProjectLogPostRequest request);

  GetProjectLogPostResponse getProjectLog(Long projectId);
  GetProjectLogPostResponse getProjectLogPost(Long id);
  GetAllProjectLogResponse getAllProjectLogPosts();
}
