package com.sms.payload;

import lombok.Data;

@Data
public class GradeRequest {

	private Integer classesId;
	private Integer studentId;
	private String grade;
	private String subject;
	private String term;

}
