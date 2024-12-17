package com.sms.service;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.sms.entity.Admission;

@Service
public class DocumentService {
	
	

	   public String generateAdmissionReceiptAndSave(Admission admission) {
	        try {
	            // Define the file path to save the PDF
	        	
	        	  UUID randomUUID = UUID.randomUUID();
	              
	              // Get the first 4 characters of the UUID string
	              String random = randomUUID.toString().substring(0, 6);
	        	
	            String filePath = "src/main/resources/static/"+admission.getRegistrationId()+".pdf"; // Adjust path if needed

	            File file = new File(filePath);
	            file.getParentFile().mkdirs();

	            // Create PDF writer and document
	            PdfWriter writer = new PdfWriter(new FileOutputStream(file));
	            PdfDocument pdfDoc = new PdfDocument(writer);
	            Document document = new Document(pdfDoc);

	            // Adjust document margins
	            document.setMargins(20, 20, 20, 20);

	            // Add title
	            document.add(new Paragraph("Welcome to "+admission.getSchoolName())
	                    .setTextAlignment(TextAlignment.CENTER)
	                    .setFontSize(16)
	                    .setBold()
	            );
	            document.add(new Paragraph("Empowering Young Minds for a Brighter Future")
	                    .setTextAlignment(TextAlignment.CENTER)
	                    .setFontSize(10)
	            );

	            // Add Receipt ID and Date
	            document.add(new Paragraph("Receipt ID: "+admission.getReceiptId())
	                    .setTextAlignment(TextAlignment.CENTER)
	                    .setFontSize(10)
	            );
	            document.add(new Paragraph("Receipt Date: "+LocalDate.now()+" Time : "+LocalDateTime.now().toLocalTime().toString().substring(0, 8))
	                    .setTextAlignment(TextAlignment.CENTER) 
	                    .setFontSize(10)
	                    .setMarginBottom(10)
	            );

	            // Add receipt header
	            document.add(new Paragraph("----- Admission Receipt -----")
	                    .setTextAlignment(TextAlignment.CENTER)
	                    .setBold()
	                    .setMarginBottom(10)
	            );

	            // Create table for receipt details
	            float[] columnWidths = {150f, 300f}; // Set column widths
	            Table table = new Table(columnWidths);
	            table.setHorizontalAlignment(HorizontalAlignment.CENTER); // Align table to the center

	            // Add header row
//	            table.addCell("Field").setBackgroundColor(ColorConstants.LIGHT_GRAY).setBold();
//	            table.addCell("Details").setBackgroundColor(ColorConstants.LIGHT_GRAY).setBold();

	            table.addCell(new Paragraph("Field")
	                    .setBackgroundColor(ColorConstants.LIGHT_GRAY)
	                    .setBold()
	                    .setTextAlignment(TextAlignment.CENTER)); // Align text in the header cell
	            table.addCell(new Paragraph("Details")
	                    .setBackgroundColor(ColorConstants.LIGHT_GRAY)
	                    .setBold()
	                    .setTextAlignment(TextAlignment.CENTER)); // Align text in the header cell

	            
	            
	            // Add data rows
	            table.addCell("School Code");
	            table.addCell(admission.getSchoolCode());

	            table.addCell("School Timing");
	            table.addCell(admission.getSchoolHours());

	            table.addCell("Registration Id");
	            table.addCell(admission.getRegistrationId());

	            table.addCell("Student Name");
	            table.addCell(admission.getStudentName());

	            table.addCell("Study Class");
	            table.addCell(admission.getStudyClass());

	            table.addCell("Admission Date");
	            table.addCell(admission.getAdmissionDate().toLocalDate()+"");

	            table.addCell("Date of Birth");
	            table.addCell(admission.getDateOfBirth()+"- ("+Period.between(admission.getDateOfBirth(),LocalDate.now()).getYears()+")");

	            table.addCell("Gender");
	            table.addCell(admission.getGender());

	            table.addCell("Admission Fee");
	            table.addCell(admission.getAdmissionFee()+"");

	            table.addCell("Monthly Fee");
	            table.addCell(admission.getMonthlyFee()+"");

	            // Add table to document
	            document.add(table);

	            // Add footer text
	            document.add(new Paragraph("Thank you for choosing "+admission.getSchoolName()+"! We are committed to excellence in education.")
	                    .setTextAlignment(TextAlignment.CENTER)
	                    .setFontSize(10)
	                    .setMarginTop(20)
	            );

	            document.add(new Paragraph("Contact Us: \nMobile: "+admission.getSchoolPhoneNo()+" \nEmail: "+admission.getSchoolEmailId()+" \nAddress: "+admission.getSchoolAddress())
	                    .setTextAlignment(TextAlignment.CENTER)
	                    .setFontSize(10)
	            );

	            // Close the document
	            document.close();

	            return filePath; // Return the file path for further use
	        } catch (Exception e) {
	            throw new RuntimeException("Error generating and saving PDF", e);
	        }
	    }
}
