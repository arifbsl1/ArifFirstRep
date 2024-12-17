package com.sms.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.entity.Classes;
import com.sms.entity.School;
import com.sms.entity.Student;
import com.sms.entity.Teacher;
import com.sms.payload.ClassesResponse;
import com.sms.payload.SchoolRequest;
import com.sms.payload.SchoolResponse;
import com.sms.payload.SchoolTypeResponse;
import com.sms.payload.StudentResponse;
import com.sms.payload.TeacherResponse;
import com.sms.repo.SchoolRepo;
import com.sms.utils.HelperUtils;

@Service
public class SchoolSerivceImpl implements ISchoolService {

	@Autowired
	private SchoolRepo schoolRepo;
	
	@Autowired
	private HelperUtils helperUtils;

	@Override
	public SchoolResponse enrollSchool(SchoolRequest schoolRequest, String type) {

		School school = new School();
		
		Optional<School> lastSchool = schoolRepo.findTopByOrderByCreatedAtDesc();
		
		Integer schoolId = 1;
        
		if(lastSchool.isPresent()) {
			schoolId= lastSchool.get().getSchoolId()+schoolId;
		}else {
			schoolId=1;
		}
		
		LocalDateTime createdAt = LocalDateTime.now();
		
		

		BeanUtils.copyProperties(schoolRequest, school);

		// school.setSchoolName(schoolRequest.getSchoolName());

		if (schoolRequest.getEstablishedAt() != null) {
			
			LocalDate dateFormate = helperUtils.dateFormate(schoolRequest.getEstablishedAt()+"");

			school.setEstablishedAt(dateFormate);

		}
		else {
			school.setEstablishedAt(createdAt.toLocalDate());
		}

		
		SchoolTypeResponse schoolType = helperUtils.getSchoolType(type);
		
		String prefix = schoolType.getSchoolCode();
		
		school.setSchoolId(schoolId);
		school.setSchoolHours(schoolType.getSchoolHours());
		school.setHolidays(schoolType.getHolidays());
		
        String sufix = school.getSchoolName().substring(0, 4).toUpperCase();

		String date = createdAt.getYear()+"";
		
		String formattedSchoolId = String.format("%04d", schoolId);


		school.setSchoolCode(prefix+formattedSchoolId+"-"+sufix+"-"+date);

		school.setCreatedAt(createdAt);
		
		System.out.println(school);

		School schooln = schoolRepo.save(school);

		SchoolResponse schoolResponse = new SchoolResponse();

		BeanUtils.copyProperties(schooln, schoolResponse);
		
		schoolResponse.setCreatedAt(createdAt.toLocalDate()+"");

		return schoolResponse;
	}

	@Override
	public SchoolResponse getSchool(Integer schoolId) throws Exception {

		SchoolResponse response = new SchoolResponse();

		School school = schoolRepo.findById(schoolId)
				.orElseThrow(() -> new Exception("Not Available With id : " + schoolId));

		BeanUtils.copyProperties(school, response);

		return response;
	}

	@Override
	public List<SchoolResponse> getAllSchool() {
		// TODO Auto-generated method stub

		List<School> schoolList = schoolRepo.findAll();

		List<SchoolResponse> schoolResonseList = new ArrayList<>();

		/*
		 * for(School sch:schoolList) {
		 * 
		 * BeanUtils.copyProperties(schoolList, schoolResonseList); }
		 */

		for (School all : schoolList) {

			SchoolResponse schoolResponse = new SchoolResponse();

			BeanUtils.copyProperties(all, schoolResponse);
					
			schoolResponse.setCreatedAt(all.getCreatedAt().toLocalDate()+"");
			schoolResonseList.add(schoolResponse);

		}

		return schoolResonseList;
	}

	@Override
	public School getSchoolById(Integer schoolId) {

		School school = null;

		try {

			school = schoolRepo.findById(schoolId)
					.orElseThrow(() -> new Exception("Not Available With id : " + schoolId));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return school;
	}

	
	
	
	@Override
	public List<TeacherResponse> allteacherBySchoolId(Integer schoolId) {
		List<Teacher> teacher = schoolRepo.findTeachersBySchoolId(schoolId);

		List<TeacherResponse> teacherResponses = new ArrayList<>();

		for (Teacher teacher2 : teacher) {

			TeacherResponse teacherResponse = new TeacherResponse();

			BeanUtils.copyProperties(teacher2, teacherResponse);

			teacherResponse.setSchoolId(teacher2.getSchool().getSchoolId());
			teacherResponse.setSchoolName(teacher2.getSchool().getSchoolName());

			teacherResponses.add(teacherResponse);

		}

		return teacherResponses;
	}

	@Override
	public List<StudentResponse> allStudentsBySchoolId(Integer schoolId) {

		List<Student> students = schoolRepo.findStudentBySchoolId(schoolId);

		List<StudentResponse> studentResponses = new ArrayList<>();

		students.forEach(student -> {

			StudentResponse studentResponse = new StudentResponse();

			BeanUtils.copyProperties(student, studentResponse);

			studentResponse.setSchoolId(student.getSchool().getSchoolId());
			studentResponse.setSchoolName(student.getSchool().getSchoolName());

			studentResponses.add(studentResponse);

		});

		return studentResponses;
	}
/*
	@Override
	public List<ClassesResponse> alltClassesBySchoolId(Integer schoolId) {

		List<Classes> classes = schoolRepo.findClassesBySchoolId(schoolId);

		List<ClassesResponse> classesResponse = new ArrayList<>();

		classes.forEach(cls -> {

			ClassesResponse clsResponse = new ClassesResponse();

			BeanUtils.copyProperties(cls, clsResponse);

			clsResponse.setSchoolId(cls.getSchool().getSchoolId());
			clsResponse.setSchoolName(cls.getSchool().getSchoolName());
			clsResponse.setTeacherId(cls.getTeacher().getTeacherId());
			clsResponse.setTeacherName(cls.getTeacher().getFirstName() + " " + cls.getTeacher().getFirstName());

			classesResponse.add(clsResponse);

		});
		return classesResponse;
	}
	*/

	@Override
	public SchoolResponse fetchSchoolByCode(String schoolCode) {
		
		SchoolResponse schoolResponse = new SchoolResponse();
		
		try {
			
		  School school= schoolRepo.findBySchoolCode(schoolCode).orElseThrow(()->new Exception(" School not found "));
			
		  BeanUtils.copyProperties(school,schoolResponse);
		  schoolResponse.setMessage("School found : "+school.getSchoolCode());
		  schoolResponse.setCreatedAt(school.getCreatedAt()+"");
		  
		} catch (Exception e) {
		    System.out.println("Something went wrong..");
		    schoolResponse.setMessage(" School Not Found : "+schoolCode);
		    e.printStackTrace();
		    
		}
	
		return schoolResponse;
	}
	
	
	@Override
	public School getSchoolByCode(String schoolCode) {
		
		School school = null;
		
		try {
			
		   school= schoolRepo.findBySchoolCode(schoolCode).orElseThrow(()->new Exception(" School not found "));

		  
		} catch (Exception e) {
		    System.out.println("Something went wrong..");
		    e.printStackTrace();
		    
		}
	
		return school;
	}
	
	
	
	

}