package com.sms.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.entity.School;
import com.sms.repo.SchoolRepo;

@Service
public class DynamicSchemaService {

	@Autowired
	private SchoolRepo schoolRepo;

	public List<String> getSchoolName() {

		return schoolRepo.findAll().stream().map(School::getSchoolCode).collect(Collectors.toList());
	}
}

/*
@Bean
public OpenApiCustomizer customOpenApi() {
	return openApi -> openApi.getPaths().forEach((path, pathItem) -> {
		if (path.contains("/students")) {
			pathItem.readOperations().forEach(operation -> {
				operation.getParameters().stream().filter(parameter -> "schoolcode".equals(parameter.getName()))
						.forEach(parameter -> {
							// Fetch latest school codes dynamically
							List<String> schoolCodes = schoolServices.getAllSchoolCode();
							
							
							StringSchema schema = new StringSchema();
								schema.setEnum(schoolCodes);
								parameter.setSchema(schema);
							});
				});
			}
		});
	}


*/