package com.sms.service;

import java.util.List;

import com.sms.entity.Classes;
import com.sms.payload.ClassesRequest;
import com.sms.payload.ClassesResponse;

public interface IClassesService {

	ClassesResponse addClasses(ClassesRequest classesRequest,String schoolType,String classNo);
	List<ClassesResponse> getAllClasses();
//	ClassesResponse getClass(Integer classId);
	Classes getClasById(Integer classId);
}
