package com.sms.payload;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SchoolRequest {

	
	private String schoolName;
	private String SchoolAddress;
	private Long phoneNo;
	private String emailId;
	private LocalDate establishedAt;
}
