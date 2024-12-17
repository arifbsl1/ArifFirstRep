package com.sms.service;

import java.util.List;

import com.sms.entity.Subjects;
import com.sms.payload.SubjectResponse;

public interface ISubjectService {

	SubjectResponse addSubject(String subject,String className);
	
	List<String> fetchAllSubjectCode();
	
	List<Subjects> getAllSubjectByCode(String subId);
}
