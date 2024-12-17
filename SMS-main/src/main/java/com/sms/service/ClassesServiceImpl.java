package com.sms.service;

import java.beans.Beans;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.entity.Classes;
import com.sms.entity.School;
import com.sms.entity.Subjects;
import com.sms.entity.Teacher;
import com.sms.payload.ClassesRequest;
import com.sms.payload.ClassesResponse;
import com.sms.payload.SchoolResponse;
import com.sms.payload.SubjectResponse;
import com.sms.repo.ClassesRepo;


@Service
public class ClassesServiceImpl implements IClassesService{

	@Autowired
	private ClassesRepo classesRepo;
	
	@Autowired
	private ISchoolService schoolService;
	
	@Autowired
	private ITeacherService teacherService;

	@Autowired
	private ISubjectService subjectService;

	//@Override
	public Classes getClasById(Integer classId) {
		
			 Optional<Classes> classbyId = classesRepo.findById(classId);
			
			 if(classbyId.isPresent()) {
				 
				 return classbyId.get();
			 }
	
		return null;
	}



	@Override
	public ClassesResponse addClasses(ClassesRequest classesRequest,String schoolCode,String studyClass) {
		
		boolean isMatch = setClass(schoolCode, studyClass);
		Integer classNo = Integer.parseInt(studyClass.replaceAll("[^0-9]", ""));
		String subjectCode = String.format("%03d", classNo);
		ClassesResponse classesResponse = new ClassesResponse();
		
		if(isMatch) {
			
			School school = schoolService.getSchoolByCode(schoolCode);
		
		   	Set<Subjects> subjects = subjectService.getAllSubjectByCode(subjectCode)
		    		            .stream().map(sub->sub).collect(Collectors.toSet());
			Classes classes = new Classes();
			
			BeanUtils.copyProperties(classesRequest, classes);
			
			classes.setClassName(studyClass+" Div ["+classesRequest.getDivision()+"]");
			classes.setSchool(school);
			classes.setSubjects(subjects);
		
			
			Classes classesSave = classesRepo.save(classes);
			
			
			
			BeanUtils.copyProperties(classesSave, classesResponse);
			
			Set<SubjectResponse> subjectResponse = new HashSet<>();
			
			Set<Subjects> subjectList = classesSave.getSubjects();
			
			for(Subjects sublis:subjectList) {
			
				SubjectResponse subResponse = new SubjectResponse();
				
				subResponse.setSbujectCode(sublis.getSbujectCode());
				subResponse.setSubjectId(sublis.getSubjectId());
				subResponse.setSubjectName(sublis.getSubjectName());
		
				subjectResponse.add(subResponse);
			}
			classesResponse.setSchoolCode(classesSave.getSchool().getSchoolCode());
			classesResponse.setSchoolName(classesSave.getSchool().getSchoolName());
			classesResponse.setSubjects(subjectResponse);
			classesResponse.setMessage("Class added..");
		}
		
		else {
			classesResponse.setMessage("Class Not Added Somthing went Wrong..");
		}
		
		return classesResponse;
	}
	
	
	private boolean setClass(String schoolCode,String studyClass){
		
		boolean isValid =false;
		
		 Integer classNo  = Integer.parseInt(studyClass.replaceAll("[^0-9]", ""));
		
		if (schoolCode.contains("PRI") && classNo < 5 ) { 
			isValid =true;
		}
		
		else if (schoolCode.contains("SEC") && (classNo >= 5 && classNo <= 7)) {
			isValid =true;
		}
		
		else if (schoolCode.contains("HISE") && (classNo >= 8 && classNo <= 10)) {
			isValid =true;
		}
		
		return isValid;
	}



	@Override
	public List<ClassesResponse> getAllClasses() {
		List<Classes> classes = classesRepo.findAll();
		return null;
	}

}
