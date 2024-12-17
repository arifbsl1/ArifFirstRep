package com.sms.payload;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SchoolResponse {
    
	private String message;
	private Integer schoolId;
	private String schoolCode;
	private String schoolName;
	private String SchoolAddress;
	private String schoolHours;
	private String holidays;
	private Long phoneNo;
	private String emailId;
	private LocalDate establishedAt;
	private String createdAt;

}
