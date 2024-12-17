package com.sms.config;

import java.util.List;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sms.service.DynamicSchemaService;

import io.swagger.v3.oas.models.media.StringSchema;

@Configuration
public class SwaggerConfig {

	@Autowired
	private DynamicSchemaService schemaService;

	@Bean
	public OpenApiCustomizer customOpenApi() {
		return openApi -> openApi.getPaths().forEach((path, pathItem) -> {
           
			/*
			if (path.contains("/schoolCode")) {
				pathItem.readOperations().forEach(operation -> {
					operation.getParameters().stream().filter(parameter -> "schoolType".equals(parameter.getName()))
							.forEach(parameter -> {
								// Fetch latest school codes dynamically
								List<String> schoolCodes = schemaService.getSchoolName();

								StringSchema schema = new StringSchema();
								schema.setEnum(schoolCodes);
								parameter.setSchema(schema);
							});
				});
			}
		*/	
         /*
			else if (path.contains("/subjects")) {
				System.out.println("Matched /subjects path");
				pathItem.readOperations().forEach(operation -> {
					operation.getParameters().stream().filter(parameter -> "subject".equals(parameter.getName()))
							.forEach(parameter -> {
								// Fetch latest school codes dynamically

								List<String> list = List.of("English", "Math", "Hindi");

								StringSchema schema = new StringSchema();
								schema.setEnum(list);
								parameter.setSchema(schema);
								System.out.println("Set enum for subject: " + list);
							});
				});
			}
		*/	
			 if (path.contains("/schoolCode") && path.contains("/subjects")) {
	            System.out.println("Both /schoolCode and /subjects path matched");

	            pathItem.readOperations().forEach(operation -> {
	                // Handle schoolType parameter
	                operation.getParameters().stream()
	                        .filter(parameter -> "schoolType".equals(parameter.getName()))
	                        .forEach(parameter -> {
	                            List<String> schoolCodes = schemaService.getSchoolName();

	                            StringSchema schoolTypeSchema = new StringSchema();
	                            schoolTypeSchema.setEnum(schoolCodes);
	                            parameter.setSchema(schoolTypeSchema);
	                            System.out.println("Set enum for schoolType: " + schoolCodes);
	                        });

	                // Handle subject parameter
	                operation.getParameters().stream()
	                        .filter(parameter -> "subject".equals(parameter.getName()))
	                        .forEach(parameter -> {
	                            List<String> subjects = List.of("English", "Math", "Hindi");

	                            StringSchema subjectSchema = new StringSchema();
	                            subjectSchema.setEnum(subjects);
	                            parameter.setSchema(subjectSchema);
	                            System.out.println("Set enum for subject: " + subjects);
	                        });
	            });
	        }
			 
			 else if (path.contains("/schoolCode")) {
					pathItem.readOperations().forEach(operation -> {
						operation.getParameters().stream().filter(parameter -> "schoolType".equals(parameter.getName()))
								.forEach(parameter -> {
									// Fetch latest school codes dynamically
									List<String> schoolCodes = schemaService.getSchoolName();

									StringSchema schema = new StringSchema();
									schema.setEnum(schoolCodes);
									parameter.setSchema(schema);
								});
					});
				}
			 
			 
			 else if(path.contains("/subjects") && path.contains("/classNames")) {
				 
				 pathItem.readOperations().forEach(operation -> {
						operation.getParameters().stream().filter(parameter -> "subject".equals(parameter.getName()))
								.forEach(parameter -> {
									// Fetch latest school codes dynamically
								    List<String> subjects = List.of(
								    		             "English","Marathi",
								    		             "Hindi","Mathematics",
								    		             "Environmental_Studies","Physical_Education",
								    		             "Drawing","Computer_Science","Science",
								    		             "Social_Science"
								    		             
								    		);

									StringSchema schema = new StringSchema();
									schema.setEnum(subjects);
									parameter.setSchema(schema);
								});
						
						
						 operation.getParameters().stream()
	                        .filter(parameter -> "className".equals(parameter.getName()))
	                        .forEach(parameter -> {
	                        	
	                        	
	                            List<String> classNo = List.of(
	                            		"Class-1","Class-2","Class-3",
			           					"Class-4","Class-5","Class-6",
			           					"Class-7","Class-8","Class-9",
			           					"Class-10");
	                            		

	                            StringSchema schoolTypeSchema = new StringSchema();
	                            schoolTypeSchema.setEnum(classNo);
	                            parameter.setSchema(schoolTypeSchema);
	                            //System.out.println("Set enum for schoolType: " + classNo);
	                        });
						
					});
				 
				 
				 
			 }

			/*
			 * else if (path.contains("/classNo")) {
			 * 
			 * pathItem.readOperations().forEach(operation -> {
			 * operation.getParameters().stream().filter(parameter ->
			 * "classType".equals(parameter.getName())) .forEach(parameter -> { // Fetch
			 * latest school codes dynamically
			 * 
			 * StringSchema schema = new StringSchema();
			 * schema.setEnum(List.of("Class-1","Class-2","Class-3"));
			 * parameter.setSchema(schema); }); }); }
			 */

		});
	}

}