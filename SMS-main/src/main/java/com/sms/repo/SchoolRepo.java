package com.sms.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sms.entity.Classes;
import com.sms.entity.School;
import com.sms.entity.Student;
import com.sms.entity.Teacher;

public interface SchoolRepo extends JpaRepository<School, Integer>{

	    @Query("SELECT s.teachers FROM School s WHERE s.schoolId = :schoolId")
	    List<Teacher> findTeachersBySchoolId(@Param("schoolId") Integer schoolId);
	    
	    @Query("SELECT s.student FROM School s WHERE s.schoolId = :schoolId")
	    List<Student> findStudentBySchoolId(@Param("schoolId") Integer schoolId);
	    
	    @Query("SELECT s.classes FROM School s WHERE s.schoolId = :schoolId")
	    List<Classes> findClassesBySchoolId(@Param("schoolId") Integer schoolId);
	    
	    Optional<School> findTopByOrderByCreatedAtDesc();
	    
	    Optional<School> findBySchoolCode(String schoolCode);
}
