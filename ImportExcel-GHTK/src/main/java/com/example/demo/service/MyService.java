package com.example.demo.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Student;
import com.example.demo.utils.ReadFileExcel;

@Service
public class MyService {
	@Autowired
	ReadFileExcel readFileExcel;
	

	
   public void importExecl(HttpServletRequest request) {
	   try {
			File dir= new File(request.getServletContext().getRealPath("/fileImport"));
			if (!dir.exists()) {
				dir.mkdirs();
			} 
			Part importPart= request.getPart("import");
			File photoFile = new File(dir,importPart.getSubmittedFileName());
			importPart.write(photoFile.getAbsolutePath());
			String xString= photoFile.getAbsolutePath()+"";
		    List<Student> students =readFileExcel.readExcel(xString);
		} catch (Exception e) {
			e.printStackTrace();
		}
   }


}
