package com.sms.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sms.entity.Subjects;
import com.sms.service.ISubjectService;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/subject")
public class SubjectRestController {
    
	@Autowired
	private ISubjectService subjectService;
	
	@PostMapping("/addSubject/subjects/{subject}/classNames/{className}")
	public ResponseEntity<?> enrollSubject(@PathVariable String subject,@PathVariable String className){	
		return ResponseEntity.ok(subjectService.addSubject(subject, className));
	}
	
	@GetMapping("/subjectCodes")
	public ResponseEntity<List<String>> getAllSubject(){
		
		return ResponseEntity.ok(subjectService.fetchAllSubjectCode());
	}
	
	@GetMapping("/fetchSubject/{classCode}")
	public ResponseEntity<List<Subjects>> getSubjectByClass(
			@Parameter(description = "Search By Class Code", required = true, 
			schema = @Schema(allowableValues = { 
					"001","002","003",
					"004","005","006",
					"007","008","009",
					"010"
					})) @PathVariable String classCode
			
			){
		
		List<Subjects> subjectByCode = subjectService.getAllSubjectByCode(classCode);
		
		return ResponseEntity.ok(subjectByCode);
	}
}
