package com.sms.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity

public class School {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer schoolId;
	private String schoolCode;
	private String schoolName;
	private String SchoolAddress;
	private String schoolHours;
	private String holidays;
	private Long phoneNo;
	private String emailId;
    private LocalDate establishedAt;
    private LocalDateTime createdAt;
    
	@OneToMany(mappedBy = "school", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Teacher> teachers;
	
	@OneToMany(mappedBy = "school", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Student> student;
	
	@OneToMany(mappedBy = "school",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Classes> classes;
	
}
