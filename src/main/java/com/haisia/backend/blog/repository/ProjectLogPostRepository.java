package com.haisia.backend.blog.repository;

import com.haisia.backend.blog.entity.ProjectLogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectLogPostRepository extends JpaRepository<ProjectLogPost, Long> {
}
