package com.sms.service;

import com.sms.entity.Grade;
import com.sms.payload.GradeRequest;
import com.sms.payload.GradeResponse;

public interface IGradeService {

	Boolean addGrade(GradeRequest gradeRequest);
	GradeResponse getGrade(Integer gradeId);
	Grade getGradeById(Integer gradeId);
}
