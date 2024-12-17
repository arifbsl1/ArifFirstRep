package com.sms.payload;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class TeacherRequest {

//    private Integer schoolId;
	private String firstName;
	private String lastName;
	private String email;
	private LocalDate dateOfBirth;
	private LocalDate dateOfJoin;
	private String address;
	private Long mobileNo;
	
	private List<String> subjectSpecialization;
	
	
}
