package com.haisia.backend.blog.projectlog.repository;

import com.haisia.backend.blog.projectlog.entity.ProjectLogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectLogPostRepository extends JpaRepository<ProjectLogPost, Long> {
}
