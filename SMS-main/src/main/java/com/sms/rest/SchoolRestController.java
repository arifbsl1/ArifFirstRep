package com.sms.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sms.entity.School;
import com.sms.payload.SchoolRequest;
import com.sms.payload.SchoolResponse;
import com.sms.payload.StudentResponse;
import com.sms.payload.TeacherResponse;
import com.sms.service.DynamicSchemaService;
import com.sms.service.ISchoolService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/school")
public class SchoolRestController {

	// schoolCode ASHU/0000/2024/NDLs

	@Autowired
	private ISchoolService schoolService;

	@Autowired
	private DynamicSchemaService schemaService;

	/*
	 * @Parameter(description = "Staff status ", required = true, schema
	 * = @Schema(allowableValues = { "ONLINE", "OFFLINE" })) @PathVariable String
	 * status
	 */
	 
	
	@Operation(summary = "Create a new school", description = "Add a school with its details")
	@PostMapping("/addSchool/{type}")
	public ResponseEntity<?> registerSchool(
			                   @RequestBody SchoolRequest request,
			                   @Parameter(description = "School search", required = true, schema = @Schema(allowableValues = {"Primary(1 TO 4)", "Secondary(5 TO 7)","Higher Secondary(8 TO 10)"})) @PathVariable String type
			                  
			                        ) {

		ResponseEntity response = null;

		SchoolResponse schoolResponse = schoolService.enrollSchool(request,type);
		
		System.out.println(type);

		if (schoolResponse != null) {
			response = ResponseEntity.ok(schoolResponse);
		} else {
			response = ResponseEntity.internalServerError().body("Something went Wrong");
		}

		return response;

	}
	
	
	// get SchoolName 
	
	
	@GetMapping("/schoolCode")
	public ResponseEntity<SchoolResponse> getSchoolName(@PathParam("schoolType") String schoolType){
		SchoolResponse schoolByCode = schoolService.fetchSchoolByCode(schoolType);
		return ResponseEntity.ok(schoolByCode);
	}
	 
	@GetMapping("/classNo")
	public ResponseEntity<String> getClassType(@PathParam("classType") String classType){
		
		return ResponseEntity.ok(" school Type : "+classType);
	}
	 
	
	
	@GetMapping("/schoolCode/{schoolType}/subjects/{classType}/")
	public ResponseEntity<String> getClassType(
			@PathVariable  String schoolType,
			//@PathParam("schoolType") String schoolType,
            
			@PathParam("subject") String subject,
			
			@Parameter(description = "School search", required = true, 
			schema = @Schema(allowableValues = { 
					"Class-1","Class-2","Class-3",
					"Class-4","Class-5","Class-6",
					"Class-7","Class-8","Class-9",
					"Class-10"
					})) @PathVariable String classType
			
			     
			    

			){
		
		return ResponseEntity.ok(" school Type : "+schoolType+" studyClass "+classType+" Subject "+subject);
	}
	
	
	@GetMapping("/schoolCode/subjects")
	public ResponseEntity<String> getSubject(
            
			@PathParam("subject") String subject,
			@PathParam("schoolType") String schoolType
			){
		
		return ResponseEntity.ok(" school Type : "+schoolType+" studyClass "+null+" Subject "+subject);
	}
	
	
	
	
	
/*
	@Operation(summary = "Create a new school", description = "Add a school with its details")
	@PostMapping("/post")
	public ResponseEntity<?> registerSchool(@RequestBody SchoolRequest request) {

		ResponseEntity response = null;

		SchoolResponse schoolResponse = schoolService.enrollSchool(request);

		if (schoolResponse != null) {
			response = ResponseEntity.ok(schoolResponse);
		} else {
			response = ResponseEntity.internalServerError().body("Something went Wrong");
		}

		return response;

	}

*/

	@GetMapping("/fetch/{schoolId}")
	public ResponseEntity<?> fetchSchool(@PathVariable Integer schoolId) {

		ResponseEntity response = null;

		try {
			SchoolResponse school = schoolService.getSchool(schoolId);

			response = ResponseEntity.ok(school);

		} catch (Exception e) {
			e.printStackTrace();
			response = ResponseEntity.internalServerError().body("Somthing went Wrong..");
		}
		return response;
	}

	@GetMapping("/fetchAll")
	public ResponseEntity<List<SchoolResponse>> fetchAllSchool() {

		List<SchoolResponse> allSchool = schoolService.getAllSchool();

		return ResponseEntity.ok(allSchool);
	}


	@GetMapping("/search/{schoolId}/{type}")
	public ResponseEntity<?> search(@PathVariable Integer schoolId,
			@Parameter(description = "School search", required = true, schema = @Schema(allowableValues = { "student",
					"teacher", "classes" })) @PathVariable String type) {

		ResponseEntity response = null;

		switch (type) {

		case "student": {
			List<StudentResponse> studentResponses = schoolService.allStudentsBySchoolId(schoolId);
			response = ResponseEntity.ok(studentResponses);
			break;
		}

		case "teacher": {
			List<TeacherResponse> teacherResponses = schoolService.allteacherBySchoolId(schoolId);
			response = ResponseEntity.ok(teacherResponses);
			break;
		}
/*
		case "classes": {

			List<ClassesResponse> classesResponse = schoolService.alltClassesBySchoolId(schoolId);
			response = ResponseEntity.ok(classesResponse);
			break;
		}

*/		
		default:

			throw new IllegalArgumentException("Unexpected value: " + type);

		}

		return response;
	}

}
