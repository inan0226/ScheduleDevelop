package com.example.scheduledevelop.schedule.Repository;

import com.example.scheduledevelop.schedule.Entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
