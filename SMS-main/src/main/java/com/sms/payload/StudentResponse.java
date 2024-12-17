package com.sms.payload;

import java.time.LocalDate;

import lombok.Data;

@Data
public class StudentResponse {

	private String message;
	private Integer schoolId;
	private String schoolCode;
	private String schoolName;
	private Integer studentId;
	private String studentFullName;
	private Long mobileNumber;
	private LocalDate dateOfBirth;
	private Long emergencyNumber;
	private String gender;
	private String studentAddress;
	private String studentEmail;
	private String studyClass;
	private String registrationId;
	private String registrationDateTime;
	
	

	
}
