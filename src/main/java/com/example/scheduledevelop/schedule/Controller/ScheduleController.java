package com.example.scheduledevelop.schedule.Controller;

import com.example.scheduledevelop.schedule.Service.ScheduleService;
import com.example.scheduledevelop.schedule.dto.ScheduleResponse;
import com.example.scheduledevelop.schedule.dto.ScheduleSaveRequest;
import com.example.scheduledevelop.schedule.dto.ScheduleSaveResponse;
import com.example.scheduledevelop.schedule.dto.ScheduleUpdateRequest;
import com.example.scheduledevelop.user.dto.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 일정 생성
    @PostMapping
    public ResponseEntity<ScheduleSaveResponse> create(
            @RequestBody ScheduleSaveRequest request,
            @SessionAttribute(name = "loginUser") SessionUser sessionUser
    ) {

        if (sessionUser == null) {
            throw new IllegalStateException("권한이 없습니다.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.save(request,sessionUser.getId()));
    }

    // 일정 조회
    @GetMapping
    public ResponseEntity<List<ScheduleResponse>> findAll() {
        return ResponseEntity.ok(scheduleService.findAll());
    }

    // 일정 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleService.findById(id));
    }

    // 일정 수정
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponse> update(
            @PathVariable Long id,
            @RequestBody ScheduleUpdateRequest request,
            @SessionAttribute(name = "loginUser") SessionUser sessionUser
    ) {
        if (sessionUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(scheduleService.update(id, request));
    }

    // 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id,
            @RequestParam String password,
            HttpSession session
    ) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("loginUser");
        if (sessionUser == null) {
            throw new IllegalStateException("권한이 없습니다.");
        }

        scheduleService.delete(id, password);
        return ResponseEntity.noContent().build();
    }
}