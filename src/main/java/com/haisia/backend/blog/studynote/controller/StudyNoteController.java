package com.haisia.backend.blog.studynote.controller;

import com.haisia.backend.blog.studynote.dto.CreateStudyNoteCategoryRequest;
import com.haisia.backend.blog.studynote.dto.CreateStudyNoteRequest;
import com.haisia.backend.blog.studynote.dto.GetStudyNoteResponse;
import com.haisia.backend.blog.studynote.service.StudyNoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/blog/studynote")
@RestController
public class StudyNoteController {

  private final StudyNoteService studyNoteService;

  @PostMapping("/category")
  public ResponseEntity<Long> createStudyNoteCategory(@RequestBody CreateStudyNoteCategoryRequest request) {
    return ResponseEntity.ok(studyNoteService.createStudyNoteCategory(request));
  }

  @PostMapping
  public ResponseEntity<Long> createStudyNote(@RequestBody CreateStudyNoteRequest request) {
    return ResponseEntity.ok(studyNoteService.createStudyNote(request));
  }

  @GetMapping("/{id}")
  public ResponseEntity<GetStudyNoteResponse> getStudyNote(@PathVariable Long id) {
    return ResponseEntity.ok(studyNoteService.getStudyNote(id));
  }

  @GetMapping("/all")
  public ResponseEntity<?> getAllStudyNotes() {
    return ResponseEntity.ok(studyNoteService.getAllStudyNote());
  }
}
