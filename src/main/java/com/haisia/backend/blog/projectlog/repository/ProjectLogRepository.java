package com.haisia.backend.blog.projectlog.repository;

import com.haisia.backend.blog.projectlog.entity.ProjectLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectLogRepository extends JpaRepository<ProjectLog, Long> {
}
