package com.sms.payload;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class TeacherResponse {
    
	private String message;
	private Integer teacherId;
	private Integer schoolId;
	private String schoolName;
	private String schoolCode;
	
	private String firstName;
	private String lastName;
	private String email;
	private LocalDate dateOfJoin;
	private LocalDate dateOfBirth;
	private String address;
	private Long mobileNo;
	private Double salary;
	private String higherEducation;
	private String graduationType;
	private String teacherType;
	
	private List<String> subjectSpecialization;
}
