package com.sms.payload;

import lombok.Data;

@Data
public class GradeResponse {
    
	private Integer gradeId;
	private Integer classesId;
	private String classesName;
    private Integer studentId;
    private String studentName;
    private String grade;
	private String subject;
	private String term;
}
