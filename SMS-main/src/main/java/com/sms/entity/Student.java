package com.sms.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer studentId;
	private String schoolCode;
	private String studentFirstName;
	private String studentLastName;
	private String studentAddress;
	private LocalDate dateOfBirth;
	private String gender;
	private Long emergencyNumber;
	private Long mobileNumber;	
	private String studentEmail;
	private String studyClass;
	private String registrationId;
	private LocalDateTime registrationDateTime;

	
	@ManyToOne
	@JoinColumn(name = "school_id")
	private School school;
	
	/*
	 * @OneToMany(mappedBy = "student",cascade = CascadeType.ALL) private
	 * List<Grade> grades;
	 */
}
