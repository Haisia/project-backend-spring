package com.haisia.backend.blog.studynote.repository;

import com.haisia.backend.blog.studynote.entity.StudyNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyNoteRepository extends JpaRepository<StudyNote, Long> {
}
