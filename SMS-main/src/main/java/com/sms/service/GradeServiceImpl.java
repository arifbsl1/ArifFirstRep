package com.sms.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.entity.Classes;
import com.sms.entity.Grade;
import com.sms.entity.Student;
import com.sms.payload.GradeRequest;
import com.sms.payload.GradeResponse;
import com.sms.repo.GradeRepo;

//@Service
public class GradeServiceImpl {

//	@Autowired
//	private GradeRepo gradeRepo;
	
//	@Autowired
//	private IClassesService classesService;
	
//	@Autowired
//	private IStudentService studentService;
	
	/*
	@Override
	public Boolean addGrade(GradeRequest gradeRequest) {
		
		Grade grade = new Grade();
		
		Classes classes = classesService.getClasById(gradeRequest.getClassesId());
		
		Student student = studentService.getStudentById(gradeRequest.getStudentId());
		
		BeanUtils.copyProperties(gradeRequest, grade);
		
		grade.setClasses(classes);
		
	//	grade.setStudent(student);
		
		Grade gradeSave = gradeRepo.save(grade);
		
		return gradeSave.getGrade()!=null;
	}

	@Override
	public GradeResponse getGrade(Integer gradeId) {
		
		GradeResponse gradeResponse = new GradeResponse();
		Grade grade = getGradeById(gradeId);
		
		BeanUtils.copyProperties(grade,gradeResponse);
		
		gradeResponse.setClassesId(grade.getClasses().getClassId());
		
		gradeResponse.setClassesName(grade.getClasses().getClassName());
		
	//	gradeResponse.setStudentId(grade.getStudent().getStudentId());
		
	//	gradeResponse.setStudentName(grade.getStudent().getStudentFirstName()+" "+grade.getStudent().getStudentLastName());
		
		return gradeResponse;
	}
*/
	
	public Grade getGradeById(Integer gradeId) {
		
//		Optional<Grade> gradeById = gradeRepo.findById(gradeId);
		
		/*
		 * if(gradeById.isPresent()) { return gradeById.get(); }
		 */
		
		return null;
	}

}
