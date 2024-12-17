package com.sms.entity;

import lombok.Data;

@Data
//@Entity
public class Grade {

	 //   @Id
	  //  @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer gradeId;
	    private String grade;
	    private String subject;
	    private String term;

//	    @ManyToOne
//	    @JoinColumn(name = "classes_id")
//	    private Classes classes;

		/*
		 * @ManyToOne
		 * 
		 * @JoinColumn(name = "student_id") private Student student;
		 */
}
