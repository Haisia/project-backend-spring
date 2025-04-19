package com.haisia.backend.blog.repository;

import com.haisia.backend.blog.entity.TestPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestPostRepository extends JpaRepository<TestPost, Long> {
}
