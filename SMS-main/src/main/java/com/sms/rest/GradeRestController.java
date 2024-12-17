package com.sms.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.sms.payload.GradeRequest;
import com.sms.payload.GradeResponse;
import com.sms.service.IGradeService;

//@RestController
//@RequestMapping("/grade")
public class GradeRestController {

	//@Autowired
	private IGradeService gradeService;

	//@PostMapping("/post")
	public ResponseEntity<String> postGrade(@RequestBody GradeRequest gradeRequest) {

		Boolean isGrade = gradeService.addGrade(gradeRequest);

		return isGrade ? ResponseEntity.ok("Grade Added..") : ResponseEntity.ok(" Something went wrong..");
	}

	//@GetMapping("/fetch/{gradeId}")
	public ResponseEntity<GradeResponse> fetchGrade(@PathVariable Integer gradeId){
		
		GradeResponse gradeResponse = gradeService.getGrade(gradeId);
		
		return ResponseEntity.ok(gradeResponse);
	}
}
