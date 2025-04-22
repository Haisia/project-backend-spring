package com.haisia.backend.blog.service;

import com.haisia.backend.blog.dto.projectlog.CreateProjectLogCategoryRequest;
import com.haisia.backend.blog.dto.projectlog.CreateProjectLogPostRequest;
import com.haisia.backend.blog.dto.projectlog.CreateProjectLogRequest;
import com.haisia.backend.blog.dto.projectlog.GetAllProjectLogResponse;
import com.haisia.backend.blog.dto.projectlog.GetProjectLogResponse;

public interface ProjectLogService {
  Long createProjectLog(CreateProjectLogRequest request);
  Long createProjectLogCategory(CreateProjectLogCategoryRequest request);
  Long createProjectLogPost(CreateProjectLogPostRequest request);

  GetProjectLogResponse getProjectLog(Long projectId);
  GetProjectLogResponse getProjectLogPost(Long id);
  GetAllProjectLogResponse getAllProjectLogPosts();
}
