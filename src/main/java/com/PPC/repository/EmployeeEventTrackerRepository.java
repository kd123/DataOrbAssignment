package com.PPC.repository;

import com.PPC.entity.EmployeeEventTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeEventTrackerRepository extends JpaRepository<EmployeeEventTracker,Long> {
    List<EmployeeEventTracker> findByEventNameIn(List<String> eventName);
    List<EmployeeEventTracker> findByEventYear(Integer year);
}
