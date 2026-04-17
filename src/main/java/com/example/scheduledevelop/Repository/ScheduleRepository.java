package com.example.scheduledevelop.Repository;

import com.example.scheduledevelop.Entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
