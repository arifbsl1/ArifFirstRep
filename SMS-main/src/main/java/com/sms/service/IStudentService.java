package com.sms.service;

import com.sms.entity.Student;
import com.sms.payload.StudentRequest;
import com.sms.payload.StudentResponse;

public interface IStudentService {

	StudentResponse registerStudent(StudentRequest studentRequest,String schoolCode,String studyClass) throws Exception;

	StudentResponse getStudent(Integer StudentId) throws Exception;
	
	Student getStudentById(Integer studentId);
	
	
}
