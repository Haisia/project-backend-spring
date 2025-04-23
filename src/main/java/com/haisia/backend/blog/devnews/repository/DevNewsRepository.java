package com.haisia.backend.blog.devnews.repository;

import com.haisia.backend.blog.devnews.entity.DevNews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DevNewsRepository extends JpaRepository<DevNews, Long> {
}
