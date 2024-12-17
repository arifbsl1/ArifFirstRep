package com.sms.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.entity.Subjects;
import com.sms.payload.SubjectResponse;
import com.sms.repo.SubjectRepo;

@Service
public class SubjectServiceImpl implements ISubjectService{

	@Autowired
	private SubjectRepo subjectRepo;
	
	@Override
	public SubjectResponse addSubject(String subject,String className) {
		
		Integer classNo = Integer.parseInt(className.replaceAll("[^0-9]", ""));
		 String subCode =subject.substring(0, 4).toUpperCase()+String.format("%03d", classNo);
		 SubjectResponse subjectResponse = new SubjectResponse();
		 Subjects subjects = new Subjects();
		
		 Optional<Subjects> code = subjectRepo.findBySbujectCode(subCode);
		 
		 if(code.isPresent()) {
			 
			 subjectResponse.setMessage("Subject Code "+subCode+" Already Present add another Subject");
			 return subjectResponse;
		 }
		
		if(classNo<5) {
			subjects.setSbujectCode(subCode);
		}
		
		else if(classNo >= 5 && classNo <= 7) {
			subjects.setSbujectCode(subCode);
		}
		
		else if((classNo >= 8 && classNo <= 10)) {
			subjects.setSbujectCode(subCode);
		}
		
		subjects.setSubjectName(subject);
	
		 subjectRepo.save(subjects);
		 
		 BeanUtils.copyProperties(subjects, subjectResponse);
	
	    subjectResponse.setMessage("Subject Added..");
			
		return subjectResponse;
	}
	
	@Override
	public List<String> fetchAllSubjectCode() {
	
		return subjectRepo.findAll().stream()
				  .map(subjectCode->subjectCode.getSbujectCode())
				  .collect(Collectors.toList());
		
	}
	
	@Override
	public List<Subjects> getAllSubjectByCode(String subId) {
		
		List<Subjects> subjectList = subjectRepo.findAll().stream()
		.filter(subject->subject.getSbujectCode().substring(subject.getSbujectCode().length()-3).equals(subId))
		.collect(Collectors.toList());
		
		return subjectList;
	}

}
