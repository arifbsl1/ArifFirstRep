package com.sms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sms.entity.Teacher;

public interface TeacherRepo extends JpaRepository<Teacher, Integer>{

}
