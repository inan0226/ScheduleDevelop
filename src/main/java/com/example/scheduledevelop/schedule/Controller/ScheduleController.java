package com.example.scheduledevelop.schedule.Controller;

import com.example.scheduledevelop.schedule.Service.ScheduleService;
import com.example.scheduledevelop.schedule.dto.ScheduleResponse;
import com.example.scheduledevelop.schedule.dto.ScheduleSaveRequest;
import com.example.scheduledevelop.schedule.dto.ScheduleSaveResponse;
import com.example.scheduledevelop.schedule.dto.ScheduleUpdateRequest;
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

    @PostMapping
    public ResponseEntity<ScheduleSaveResponse> create(@RequestBody ScheduleSaveRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.save(request));
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponse>> findAll() {
        return ResponseEntity.ok(scheduleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponse> update(
            @PathVariable Long id,
            @RequestBody ScheduleUpdateRequest request) {
        return ResponseEntity.ok(scheduleService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id,
            @RequestParam String password) {
        scheduleService.delete(id, password);
        return ResponseEntity.noContent().build();
    }
}