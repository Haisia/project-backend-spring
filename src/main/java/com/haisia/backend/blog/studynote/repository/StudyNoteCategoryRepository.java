package com.haisia.backend.blog.studynote.repository;

import com.haisia.backend.blog.studynote.entity.StudyNoteCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyNoteCategoryRepository extends JpaRepository<StudyNoteCategory, Long> {
  boolean existsByTitle(String title);
}
