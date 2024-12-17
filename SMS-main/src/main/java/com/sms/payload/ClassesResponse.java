package com.sms.payload;

import java.util.Set;

import com.sms.entity.Subjects;

import lombok.Data;

@Data
public class ClassesResponse {

	private String message;
	private Integer classId;
	private String className;
    private String schoolCode;
    private String schoolName;
    //private Integer teacherId;
    //private String  teacherName;
    private String roomNo;
    private String studentCapacity;
    
    private Set<SubjectResponse> subjects;
    
    
}
