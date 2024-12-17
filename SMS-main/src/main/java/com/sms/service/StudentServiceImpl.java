	package com.sms.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.entity.Admission;
import com.sms.entity.School;
import com.sms.entity.Student;
import com.sms.payload.StudentRequest;
import com.sms.payload.StudentResponse;
import com.sms.repo.AdmissionRepo;
import com.sms.repo.SchoolRepo;
import com.sms.repo.StudentRepo;
import com.sms.utils.HelperUtils;

@Service
public class StudentServiceImpl implements IStudentService {

	@Autowired
	private StudentRepo studentRepo;

	@Autowired
	private SchoolRepo schoolRepo;

	@Autowired
	private ISchoolService schoolService;

	@Autowired
	private AdmissionRepo admissionRepo;

	@Autowired
	private HelperUtils helperUtils;
	
	@Autowired
	private DocumentService documentService;

	@Override
	public StudentResponse registerStudent(StudentRequest studentRequest, String schoolCode, String studyClass)
			throws Exception {

		School school = schoolService.getSchoolByCode(schoolCode);

		System.out.println(school.getSchoolCode());

		Optional<Student> lastStudent = studentRepo.findTopByOrderByStudentIdDesc();

		Integer registrationId = 1;

		if (lastStudent.isPresent()) {
			registrationId = (lastStudent.get().getStudentId()) + registrationId;
		} else {
			registrationId = 1;
		}

		Student student = new Student();

		BeanUtils.copyProperties(studentRequest, student);

		if (studentRequest.getDateOfBirth() != null) {

			LocalDate dob = helperUtils.dateFormate(studentRequest.getDateOfBirth() + "");

			/*
			 * LocalDate dob = LocalDate.parse(studentRequest.getDateOfBirth() + "",
			 * DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			 */

			student.setDateOfBirth(dob);

		}

		LocalDateTime registerDateTime = LocalDateTime.now();

		student.setRegistrationDateTime(registerDateTime);
		student.setRegistrationId(
				registerDateTime.toLocalDate().toString().substring(0, 4) + "" + String.format("%04d", registrationId));
		student.setStudyClass(studyClass);
		student.setSchoolCode(school.getSchoolCode());

		student.setSchool(school);
		  

		Admission admission = helperUtils.getAdmission(student, school);

		

		StudentResponse studentResponse = null;

		if (admission.getGender()==null) {

			studentResponse = new StudentResponse();
			studentResponse.setMessage("kindly Select Right study class with school Type..");

			return studentResponse;
		}
        
		student.setStudyClass(admission.getStudyClass());
		
		Student studentSave = studentRepo.save(student);

		
			Admission admissionSave = admissionRepo.save(admission);
			
			String filePath = null;
			
			if(admissionSave.getAdmissionId()!=null){
				
				filePath=documentService.generateAdmissionReceiptAndSave(admissionSave);
			}
			
		
		studentResponse = new StudentResponse();

		BeanUtils.copyProperties(studentSave, studentResponse);

		studentResponse.setSchoolId(school.getSchoolId());
		studentResponse.setSchoolName(school.getSchoolName());
		studentResponse.setStudentFullName(studentSave.getStudentFirstName() + " " + studentSave.getStudentLastName());
		studentResponse.setRegistrationDateTime(studentSave.getRegistrationDateTime().toLocalDate() + " "
				+ studentSave.getRegistrationDateTime().toLocalTime());
		studentResponse.setMessage("Student Enrollment done , Admission recipt path here "+filePath);

		return studentResponse;
	}

	@Override
	public StudentResponse getStudent(Integer StudentId) throws Exception {

		Student student = studentRepo.findById(StudentId)
				.orElseThrow(() -> new Exception(" Student not found with " + StudentId));

		StudentResponse studentResponse = new StudentResponse();

		BeanUtils.copyProperties(student, studentResponse);

		studentResponse.setSchoolId(student.getSchool().getSchoolId());
		studentResponse.setSchoolName(student.getSchool().getSchoolName());

		return studentResponse;
	}

	@Override
	public Student getStudentById(Integer studentId) {

		Student student = new Student();

		try {

			student = studentRepo.findById(studentId)
					.orElseThrow(() -> new Exception("Student not found with : " + studentId));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}

}
