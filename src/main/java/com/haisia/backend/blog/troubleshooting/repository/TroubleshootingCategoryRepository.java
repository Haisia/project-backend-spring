package com.haisia.backend.blog.troubleshooting.repository;

import com.haisia.backend.blog.troubleshooting.entity.TroubleshootingCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TroubleshootingCategoryRepository extends JpaRepository<TroubleshootingCategory, Long> {
}
