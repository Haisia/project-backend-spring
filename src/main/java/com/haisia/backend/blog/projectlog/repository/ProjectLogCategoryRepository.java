package com.haisia.backend.blog.projectlog.repository;

import com.haisia.backend.blog.projectlog.entity.ProjectLogCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectLogCategoryRepository extends JpaRepository<ProjectLogCategory, Long> {
}
