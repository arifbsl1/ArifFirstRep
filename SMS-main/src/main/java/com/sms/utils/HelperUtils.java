package com.sms.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Component;

import com.sms.entity.Admission;
import com.sms.entity.School;
import com.sms.entity.Student;
import com.sms.payload.AdmissionResponse;
import com.sms.payload.SchoolTypeResponse;

@Component
public class HelperUtils {

	public LocalDate dateFormate(String date) {

		LocalDate formattedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		return formattedDate;
	}

	public Admission getAdmission(String studyClass, String schoolCode) {

		Integer classNo = Integer.parseInt(studyClass.replaceAll("[^0-9]", ""));

		Admission admission = new Admission();

		if (schoolCode.contains("PRI")) {

			System.out.println("PRI" + schoolCode);

			if (classNo < 5) {

				// admission = new Admission();

				admission.setSchoolCode(schoolCode);
				admission.setStudyClass(studyClass);
				admission.setAdmissionFee(15000.00);
				admission.setMonthlyFee(2000.00);

				return admission;
			}

			else {

				return null;
			}

		}

		else if (schoolCode.contains("SEC")) {

			System.out.println("SEC : " + schoolCode);

			if (classNo >= 5 && classNo <= 7) {

				admission.setSchoolCode(schoolCode);
				admission.setStudyClass(studyClass);
				admission.setAdmissionFee(20000.00);
				admission.setMonthlyFee(2500.00);

				return admission;

			} else {
				return null;
			}
		}

		else if (schoolCode.contains("HISE")) {

			System.out.println("HISE" + schoolCode);

			if (classNo >= 8 && classNo <= 10) {

				admission.setSchoolCode(schoolCode);
				admission.setStudyClass(studyClass);
				admission.setAdmissionFee(30000.00);
				admission.setMonthlyFee(3000.00);

				return admission;

			} else {

				return null;
			}
		}

		return null;

	}

	public Admission getAdmission(Student student, School school) {

		Integer classNo = Integer.parseInt(student.getStudyClass().replaceAll("[^0-9]", ""));

		Admission admission = null;

		if (student.getSchoolCode().contains("PRI")) {

			if (classNo < 5) {

				admission = setAdmission(student, school);
				admission.setAdmissionFee(15000.00);
				admission.setMonthlyFee(2000.00);

				return admission;
			}

			else {

				return new Admission();
			}

		}

		else if (student.getSchoolCode().contains("SEC")) {

			if (classNo >= 5 && classNo <= 7) {

				admission = setAdmission(student, school);
				admission.setAdmissionFee(20000.00);
				admission.setMonthlyFee(2500.00);

				return admission;

			} else {
				return new Admission();
			}
		}

		else if (student.getSchoolCode().contains("HISE")) {

			if (classNo >= 8 && classNo <= 10) {

				admission = setAdmission(student, school);
				admission.setAdmissionFee(35000.00);
				admission.setMonthlyFee(2500.00);

				return admission;

			} else {

				return new Admission();
			}
		}

		return new Admission();
	}

	public Admission setAdmission(Student student, School school) {

		Admission admission = new Admission();

		UUID randomUUID = UUID.randomUUID();

		String random = randomUUID.toString().substring(0, 6);

		admission.setSchoolCode(school.getSchoolCode());
		admission.setSchoolName(school.getSchoolName());
		admission.setSchoolEmailId(school.getEmailId());
		admission.setSchoolPhoneNo(school.getPhoneNo());
		admission.setSchoolAddress(school.getSchoolAddress());
		admission.setStudentName(student.getStudentFirstName() + " " + student.getStudentLastName());
		admission.setSchoolHours(school.getSchoolHours());
		admission.setRegistrationId(student.getRegistrationId());
		admission.setStudyClass(student.getStudyClass());
		admission.setAdmissionDate(student.getRegistrationDateTime());
		admission.setDateOfBirth(student.getDateOfBirth());
		admission.setGender(student.getGender());
		admission.setReceiptId(random+"-"+student.getRegistrationId());
		return admission;
	}

	public SchoolTypeResponse getSchoolType(String type) {

		SchoolTypeResponse response = new SchoolTypeResponse();

		switch (type) {
		case "Primary(1 TO 4)": {

			response.setHolidays("55 Days");
			response.setSchoolHours("7:00-AM TO 12:00-PM");
			response.setSchoolCode("PRI");

			return response;

		}

		case "Secondary(5 TO 7)": {

			response.setHolidays("45 Days");
			response.setSchoolHours("8:30-AM TO 1:30-PM");
			response.setSchoolCode("SEC");

			return response;
		}

		case "Higher Secondary(8 TO 10)": {

			response.setHolidays("30 Days");
			response.setSchoolHours("6-AM TO 2:00-PM");
			response.setSchoolCode("HISE");

			return response;
		}

		default:
			throw new IllegalArgumentException("Unexpected value: " + type);

		}

	}
}

/*
 * admission.setSchoolCode(school.getSchoolCode());
 * admission.setStudentName(student.getStudentFirstName() + " " +
 * student.getStudentLastName());
 * admission.setSchoolHours(school.getSchoolHours());
 * admission.setRegistrationId(student.getRegistrationId());
 * admission.setStudyClass(student.getStudyClass());
 * admission.setAdmissionDate(student.getRegistrationDateTime());
 * admission.setDateOfBirth(student.getDateOfBirth());
 * admission.setGender(student.getGender());
 */
