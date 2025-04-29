package com.haisia.backend.blog.studynote.controller;

import com.haisia.backend.blog.studynote.dto.CreateStudyNoteCategoryRequest;
import com.haisia.backend.blog.studynote.dto.CreateStudyNoteRequest;
import com.haisia.backend.blog.studynote.dto.GetAllStudyNoteResponse;
import com.haisia.backend.blog.studynote.dto.GetStudyNoteResponse;
import com.haisia.backend.blog.studynote.service.StudyNoteService;
import com.haisia.backend.common.dto.ResponseData;
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
  public ResponseEntity<ResponseData<Long>> createStudyNoteCategory(@RequestBody CreateStudyNoteCategoryRequest request) {
    Long result = studyNoteService.createStudyNoteCategory(request);
    return ResponseEntity.ok(ResponseData.success(result));
  }

  @PostMapping
  public ResponseEntity<ResponseData<Long>> createStudyNote(@RequestBody CreateStudyNoteRequest request) {
    Long result = studyNoteService.createStudyNote(request);
    return ResponseEntity.ok(ResponseData.success(result));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseData<GetStudyNoteResponse>> getStudyNote(@PathVariable Long id) {
    GetStudyNoteResponse result = studyNoteService.getStudyNote(id);
    return ResponseEntity.ok(ResponseData.success(result));
  }

  @GetMapping("/all")
  public ResponseEntity<ResponseData<GetAllStudyNoteResponse>> getAllStudyNotes() {
    GetAllStudyNoteResponse result = studyNoteService.getAllStudyNote();
    return ResponseEntity.ok(ResponseData.success(result));
  }
}
