package com.sms.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer teacherId;
	private String firstName;
	private String lastName;
	private Long mobileNo;
	
	private String higherEducation;
	private String graduationType;
	private LocalDate dateOfBirth;
	private LocalDate dateOfJoin;
	private String email;
	private String address;
	private Double salary;
	
	private String schoolCode;
	private String schoolName;
	
	private String teacherType;
	
	
	
    @ElementCollection
    @CollectionTable(name = "subjects_info", joinColumns = @JoinColumn(name = "teacher_id"))
    @Column(name = "subject")
    private List<String> subjectSpecialization;
   
	
	@ManyToOne
	@JoinColumn(name = "school_id")
	private School school;
	
//	@OneToMany(mappedBy = "teacher",cascade = CascadeType.ALL,orphanRemoval = true)
//	private List<Classes> classes;
}
