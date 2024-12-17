package com.sms.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Subjects {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer subjectId;
	private String sbujectCode;
	private String subjectName;

	
	@ManyToMany(mappedBy = "subjects",cascade = CascadeType.ALL)
    private Set<Classes> classes;

}
