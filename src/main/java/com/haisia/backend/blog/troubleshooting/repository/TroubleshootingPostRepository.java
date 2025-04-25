package com.haisia.backend.blog.troubleshooting.repository;

import com.haisia.backend.blog.troubleshooting.entity.TroubleshootingPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TroubleshootingPostRepository extends JpaRepository<TroubleshootingPost, Long> {
}
