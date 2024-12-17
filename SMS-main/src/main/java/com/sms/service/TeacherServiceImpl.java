package com.sms.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.entity.School;
import com.sms.entity.Teacher;
import com.sms.payload.TeacherRequest;
import com.sms.payload.TeacherResponse;
import com.sms.repo.SchoolRepo;
import com.sms.repo.TeacherRepo;
import com.sms.utils.HelperUtils;

@Service
public class TeacherServiceImpl implements ITeacherService {

	@Autowired
	private TeacherRepo teacherRepo;

	@Autowired
	private SchoolRepo schoolRepo;
	
	@Autowired
	private ISchoolService schoolService;
	
	@Autowired
	private HelperUtils helperUtils;

	@Override
	public TeacherResponse registerTeacher(TeacherRequest teacherRequest, String schoolcode,String education){

		School school = schoolService.getSchoolByCode(schoolcode);

    
		Teacher teacher = setTeacher(teacherRequest,school,education);
		
		Teacher teacherSave = teacherRepo.save(teacher);
		
		TeacherResponse teacherResponse = new TeacherResponse();
		
		if(teacherSave.getTeacherId()==null) {
			
			teacherResponse.setMessage("Teacher Registration Failed..");
			
			return teacherResponse;
		}

		

		BeanUtils.copyProperties(teacherSave, teacherResponse);
		
		teacherResponse.setMessage("Teacher Added..");
		teacherResponse.setSchoolId(teacherSave.getSchool().getSchoolId());

		return teacherResponse;
	}

	@Override
	public TeacherResponse getTeacher(Integer teacherId) throws Exception {

		Teacher teacher = teacherRepo.findById(teacherId)
				.orElseThrow(() -> new Exception(" Teacher Not Available With " + teacherId));

		TeacherResponse teacherResponse = new TeacherResponse();

		BeanUtils.copyProperties(teacher, teacherResponse);

		teacherResponse.setSchoolId(teacher.getSchool().getSchoolId());

		teacherResponse.setSchoolName(teacher.getSchool().getSchoolName());

		return teacherResponse;
	}

	@Override
	public List<TeacherResponse> getAllTeachers() {
		
		List<Teacher> allTeacher = teacherRepo.findAll();

		List<TeacherResponse>teacherResponses = new ArrayList<>();
		
		for(Teacher teachers :allTeacher) {
			
			TeacherResponse response = new TeacherResponse();
			
			BeanUtils.copyProperties(teachers, response);
			
			response.setSchoolId(teachers.getSchool().getSchoolId());

			response.setSchoolName(teachers.getSchool().getSchoolName());
			
		    teacherResponses.add(response);
		}
		
		return teacherResponses;
	}

	@Override
	public Teacher getTeacherById(Integer teacherId) {

		Teacher teacher = null;

		try {

			teacher = teacherRepo.findById(teacherId)
					.orElseThrow(() -> new Exception(" Teacher Not Available With " + teacherId));

		} catch (Exception e) {

			e.printStackTrace();
		}

		return teacher;
	}

	@Override
	public Teacher setTeacher(TeacherRequest request,School school,String higherEducation ) {
		
		Teacher teacher = new Teacher();
		
		BeanUtils.copyProperties(request, teacher);
		
		if (request.getDateOfJoin() != null) {
			
			LocalDate doj = helperUtils.dateFormate(request.getDateOfJoin()+"");

			teacher.setDateOfJoin(doj);

		}
		
		if (request.getDateOfBirth() != null) {
			
			LocalDate dob = helperUtils.dateFormate(request.getDateOfBirth()+"");

			teacher.setDateOfBirth(dob);

		}
		

		
		// List<String> subList = request.getSubjectSpecialization().stream().map(subject->subject).collect(Collectors.toList());
		
		teacher.setHigherEducation(higherEducation);
		
		
		if(higherEducation.contains("D.Ed")) {
			
			teacher.setGraduationType("Diploma");
		}
		else if (higherEducation.contains("B.Ed")) {
			teacher.setGraduationType("Undergraduate");
		}
		
		else if (higherEducation.contains("M.Ed")) {
			teacher.setGraduationType("Postgraduate");
		}
		
		String schoolCode = school.getSchoolCode();
		teacher.setSchoolCode(schoolCode);
		teacher.setSchoolName(school.getSchoolName());
		teacher.setSchool(school);
		
		if (schoolCode.contains("PRI")) {
          
			teacher.setSalary(20000.00);
			teacher.setTeacherType("Primary Teacher");
		}

		else if (schoolCode.contains("SEC")) {

			teacher.setSalary(30000.00);
			teacher.setTeacherType("Secondary Teacher");
		}

		else if (schoolCode.contains("HISE")) {

			teacher.setSalary(40000.00);
			teacher.setTeacherType("Higher Secondary Teacher");
		}

		
	/*
	..    private String firstName;
..	private String lastName;
..	private String email;
..	private LocalDate dateOfBirth;
..	private LocalDate dateOfJoin;
..	private String address;
..	private Long mobileNo;
	
	..	private String firstName;
	..	private String lastName;
	..	private Long mobileNo;
	..  private LocalDate dateOfBirth;
	..  private String email;
	..	private String address;
	
	** private String higherEducation;
    **	private List<String> subjectSpecialization;
		
	++ private String graduationType;
	++  private LocalDate dateOfJoin;
	++ private Double salary;
		
		private String schoolCode;
		private String schoolName;
		
		private String teacherType;
		
		
		
	    @ElementCollection
	    @CollectionTable(name = "subjects_info", joinColumns = @JoinColumn(name = "teacher_id"))
	    @Column(name = "subject")
	    private List<String> subjects;
		
		@ManyToOne
		@JoinColumn(name = "school_id")
		private School school;
		
	*/	
		
		return teacher;
	}

}
