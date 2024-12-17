package com.sms.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sms.entity.Admission;
import com.sms.payload.StudentRequest;
import com.sms.payload.StudentResponse;
import com.sms.service.DocumentService;
import com.sms.service.IStudentService;
import com.sms.utils.HelperUtils;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/student")
public class StudentRestController {

	@Autowired
	private IStudentService studentService;
	
	@Autowired
	private HelperUtils helperUtils;
	
	@Autowired
	private DocumentService documentService;

	@PostMapping("/schoolCode/enrollStudent/{schoolType}/{classNo}")
	public ResponseEntity<StudentResponse> enrollStudent(
			                            
			                       @PathVariable String schoolType,    
			           			   @Parameter(description = "School search", required = true, 
			           			schema = @Schema(allowableValues = { 
			           					"Class-1","Class-2","Class-3",
			           					"Class-4","Class-5","Class-6",
			           					"Class-7","Class-8","Class-9",
			           					"Class-10"
			           					})) @PathVariable String classNo,
			           			 @RequestBody StudentRequest studentRequest

	                                      ) {

	
              
			 StudentResponse studentResponse =null;
			try {
				studentResponse = studentService.registerStudent(studentRequest,schoolType,classNo);
			} catch (Exception e) {
				
			    studentResponse =new StudentResponse();
			    studentResponse.setMessage("Student enrollment failed..");
				e.printStackTrace();
				
			}


		return ResponseEntity.ok(studentResponse);
	}
	
	@GetMapping("/schoolCode/testStudent/{schoolType}/{classNo}")
	public ResponseEntity<?> setAdmision(
			
			   @PathVariable String schoolType,    
   			   @Parameter(description = "School search", required = true, 
   			schema = @Schema(allowableValues = { 
   					"Class-1","Class-2","Class-3",
   					"Class-4","Class-5","Class-6",
   					"Class-7","Class-8","Class-9",
   					"Class-10"
   					})) @PathVariable String classNo
			
			){
		
		Admission admission = helperUtils.getAdmission(classNo, schoolType);
		
		ResponseEntity responseEntity = null;
		
		if(admission !=null) {
			responseEntity =ResponseEntity.ok(admission);
		}
		else {
			responseEntity = ResponseEntity.ok("Admission is null");
		}
		
		
		return responseEntity;
	}

	@GetMapping("/download")
	public ResponseEntity<String> downloadPdf(){
		
	//	String receiptAndSave = documentService.generateAdmissionReceiptAndSave();
		
		return ResponseEntity.ok(" Pdf save here : ");
	}
	
	@GetMapping("/fetch/{studentId}")
	public ResponseEntity<StudentResponse> fetchStudent(@PathVariable Integer studentId) {

		StudentResponse studentResponse = null;

		try {
			studentResponse = studentService.getStudent(studentId);

		} catch (Exception e) {
            
			e.printStackTrace();
		}

		return ResponseEntity.ok(studentResponse);
	}
}
