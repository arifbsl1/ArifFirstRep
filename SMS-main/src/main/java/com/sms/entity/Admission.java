package com.sms.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Admission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer admissionId;
	private String schoolCode;
	private String schoolName;
	private Long schoolPhoneNo;
	private String schoolEmailId;
	private String schoolAddress;
	private String studentName;
	private String schoolHours;
	private String receiptId;
	private String registrationId;
	private String studyClass;
	private LocalDateTime admissionDate;
	private LocalDate dateOfBirth;
	private String gender;
	private Double admissionFee;
	private Double monthlyFee;
}
