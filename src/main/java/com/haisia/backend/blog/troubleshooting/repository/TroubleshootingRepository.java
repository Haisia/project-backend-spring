package com.haisia.backend.blog.troubleshooting.repository;

import com.haisia.backend.blog.troubleshooting.entity.Troubleshooting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TroubleshootingRepository extends JpaRepository<Troubleshooting, Long> {
}
