package com.sms.payload;

import java.time.LocalDate;

import lombok.Data;

@Data
public class StudentRequest {

	
	private String studentFirstName;
	private String studentLastName;
	private String gender;
	private Long mobileNumber;
	private LocalDate dateOfBirth;
	private Long emergencyNumber;
	private String studentEmail;
	private String studentAddress;
	

}
