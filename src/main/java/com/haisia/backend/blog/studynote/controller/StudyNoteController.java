package com.haisia.backend.blog.studynote.controller;

import com.haisia.backend.blog.studynote.dto.CreateStudyNoteCategoryRequest;
import com.haisia.backend.blog.studynote.dto.CreateStudyNoteRequest;
import com.haisia.backend.blog.studynote.dto.GetAllStudyNoteResponse;
import com.haisia.backend.blog.studynote.dto.GetStudyNoteResponse;
import com.haisia.backend.blog.studynote.service.StudyNoteService;
import com.haisia.backend.common.dto.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

  @Operation(
    summary = "Study Note Category 등록",
    description = "새로운 Study Note Category 를 등록합니다.",
    responses = {
      @ApiResponse(responseCode = "200", description = "Study Note Category 등록 성공"),
      @ApiResponse(responseCode = "500", description = "Study Note Category 등록 실패"),
    }
  )
  @PostMapping("/category")
  public ResponseEntity<ResponseData<Long>> createStudyNoteCategory(@RequestBody CreateStudyNoteCategoryRequest request) {
    Long result = studyNoteService.createStudyNoteCategory(request);
    return ResponseEntity.ok(ResponseData.success(result));
  }

  @Operation(
    summary = "Study Note 등록",
    description = "새로운 Study Note 를 등록합니다.",
    responses = {
      @ApiResponse(responseCode = "200", description = "Study Note 등록 성공"),
      @ApiResponse(responseCode = "500", description = "Study Note 등록 실패"),
    }
  )
  @PostMapping
  public ResponseEntity<ResponseData<Long>> createStudyNote(@RequestBody CreateStudyNoteRequest request) {
    Long result = studyNoteService.createStudyNote(request);
    return ResponseEntity.ok(ResponseData.success(result));
  }

  @Operation(
    summary = "Study Note 단건 조회",
    description = "Id를 기반으로 Study Note 를 조회합니다.",
    responses = {
      @ApiResponse(responseCode = "200", description = "Study Note 조회 성공"),
      @ApiResponse(responseCode = "500", description = "Study Note 조회 실패"),
    }
  )
  @GetMapping("/{id}")
  public ResponseEntity<ResponseData<GetStudyNoteResponse>> getStudyNote(@PathVariable Long id) {
    GetStudyNoteResponse result = studyNoteService.getStudyNote(id);
    return ResponseEntity.ok(ResponseData.success(result));
  }

  @Operation(
    summary = "모든 Study Note 조회",
    description = "모든 Study Note 를 조회합니다.",
    responses = {
      @ApiResponse(responseCode = "200", description = "Study Note 조회 성공"),
      @ApiResponse(responseCode = "500", description = "Study Note 조회 실패"),
    }
  )
  @GetMapping("/all")
  public ResponseEntity<ResponseData<GetAllStudyNoteResponse>> getAllStudyNotes() {
    GetAllStudyNoteResponse result = studyNoteService.getAllStudyNote();
    return ResponseEntity.ok(ResponseData.success(result));
  }
}
