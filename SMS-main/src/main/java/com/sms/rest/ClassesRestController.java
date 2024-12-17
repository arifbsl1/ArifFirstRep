package com.sms.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sms.payload.ClassesRequest;
import com.sms.payload.ClassesResponse;
import com.sms.payload.StudentRequest;
import com.sms.service.IClassesService;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/classes")
public class ClassesRestController {

	@Autowired
	private IClassesService classesService;

	@PostMapping("/addClass/schoolCode/{schoolType}/{classNo}")
	public ResponseEntity<?> enrollClass(@PathVariable String schoolType,    
			   @Parameter(description = "School search", required = true, 
			schema = @Schema(allowableValues = { 
					"Class-1","Class-2","Class-3",
					"Class-4","Class-5","Class-6",
					"Class-7","Class-8","Class-9",
					"Class-10"
					})) @PathVariable String classNo,
			         @RequestBody ClassesRequest classesRequest
			) {
        
		ClassesResponse classesResponse = classesService.addClasses(classesRequest, schoolType, classNo);
		
		return ResponseEntity.ok(classesResponse);

	}

	@GetMapping("/fetch/{classId}")
	public ResponseEntity<ClassesResponse> fetchClass(@PathVariable Integer classId) {

		return null;

	}
}
