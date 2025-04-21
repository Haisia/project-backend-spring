package com.haisia.backend.blog.repository;

import com.haisia.backend.blog.entity.ProjectLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectLogRepository extends JpaRepository<ProjectLog, Long> {
}
