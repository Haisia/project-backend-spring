package com.haisia.backend.blog.repository;

import com.haisia.backend.blog.entity.DevNews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DevNewsRepository extends JpaRepository<DevNews, Long> {
}
