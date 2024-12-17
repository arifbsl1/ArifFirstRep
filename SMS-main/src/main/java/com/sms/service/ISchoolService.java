package com.sms.service;

import java.util.List;

import com.sms.entity.School;
import com.sms.payload.ClassesResponse;
import com.sms.payload.SchoolRequest;
import com.sms.payload.SchoolResponse;
import com.sms.payload.StudentResponse;
import com.sms.payload.TeacherResponse;

public interface ISchoolService {

	SchoolResponse enrollSchool(SchoolRequest schoolRequest,String type);
	
	SchoolResponse getSchool(Integer schoolId) throws Exception;
	
	List<SchoolResponse> getAllSchool();
	
	School getSchoolById(Integer schoolId);
	
	List<TeacherResponse> allteacherBySchoolId(Integer schoolId);
	
	List<StudentResponse> allStudentsBySchoolId(Integer schoolId);
	
	//List<ClassesResponse> alltClassesBySchoolId(Integer schoolId);
	
	SchoolResponse fetchSchoolByCode(String schoolCode);
	
	School getSchoolByCode(String schoolCode);
	
	// List<Object> searchSchoolResult(Integer schoolId,String type);
}
