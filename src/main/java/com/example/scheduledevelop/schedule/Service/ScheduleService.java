package com.example.scheduledevelop.schedule.Service;

import com.example.scheduledevelop.schedule.Entity.Schedule;
import com.example.scheduledevelop.schedule.Repository.ScheduleRepository;
import com.example.scheduledevelop.schedule.dto.ScheduleResponse;
import com.example.scheduledevelop.schedule.dto.ScheduleSaveRequest;
import com.example.scheduledevelop.schedule.dto.ScheduleSaveResponse;
import com.example.scheduledevelop.schedule.dto.ScheduleUpdateRequest;
import com.example.scheduledevelop.user.entity.User;
import com.example.scheduledevelop.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserService userService;

    //  일정 생성
    @Transactional
    public ScheduleSaveResponse save(ScheduleSaveRequest request, Long id) {
        User user = userService.scheduleUser(id);
        Schedule schedule = new Schedule(request.getTitle(), request.getContent(), request.getPassword(), user);
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new ScheduleSaveResponse(
                savedSchedule.getId(),
                savedSchedule.getTitle(),
                savedSchedule.getContent(),
                savedSchedule.getCreateAt(),
                savedSchedule.getModifiedAt()
        );
    }

    //  전체 조회
    @Transactional(readOnly = true)
    public List<ScheduleResponse> findAll() {
        return scheduleRepository.findAll().stream()
                .map(schedule -> new ScheduleResponse(
                        schedule.getId(), schedule.getTitle(), schedule.getContent()
                        , schedule.getCreateAt(), schedule.getModifiedAt()
                ))
                .toList();
    }

    // 단건 조회
    @Transactional(readOnly = true)
    public ScheduleResponse findById(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 없습니다."));
        return new ScheduleResponse(
                schedule.getId(), schedule.getTitle(), schedule.getContent(),
                schedule.getCreateAt(), schedule.getModifiedAt()
        );
    }

    // 수정
    @Transactional
    public ScheduleResponse update(Long id, ScheduleUpdateRequest request) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 없습니다."));

        if (!schedule.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        schedule.update(request.getTitle(), request.getContent());

        return new ScheduleResponse(
                schedule.getId(), schedule.getTitle(), schedule.getContent(),
                schedule.getCreateAt(), schedule.getModifiedAt()
        );
    }

    //  삭제
    @Transactional
    public void delete(Long id, String password) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 없습니다."));

        if (!schedule.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        scheduleRepository.delete(schedule);
    }
}