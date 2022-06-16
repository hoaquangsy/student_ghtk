package com.example.demo.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

import com.example.demo.model.DiemSoTuyen;
import com.example.demo.model.Student;
import com.example.demo.model.TongDiem;
import com.example.demo.repositories.IDiemSoTuyenRepository;
import com.example.demo.repositories.IStudentRepository;
import com.example.demo.repositories.ITongDiemRepository;

@Service
public class ReadFileExcel {
	
    @Autowired
    IStudentRepository iStudentRepository;
    
    @Autowired
    IDiemSoTuyenRepository iDiemSoTuyenRepository;
    
    @Autowired
    ITongDiemRepository iTongDiemRepository;
    
    @Transactional
    public List<Student> readExcel(String excelFilePath) throws IOException, ParseException {
        List<Student> listStudents = new ArrayList<>();
        List<TongDiem> listTongDiems=new ArrayList<>();
        List<DiemSoTuyen> lisDiemSoTuyens=new ArrayList<>();
 
        // Get file
        InputStream inputStream = new FileInputStream(new File(excelFilePath));
 
        // Get workbook
        Workbook workbook = getWorkbook(inputStream, excelFilePath);
 
        // Get sheet
        org.apache.poi.ss.usermodel.Sheet sheet =workbook.getSheetAt(0);
        
//        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        
        DataFormatter fmt = new DataFormatter();
 
            
            for (int i = 5; i < sheet.getPhysicalNumberOfRows(); i++) {
				Row row=sheet.getRow(i);
				  Student student = new Student();
				  TongDiem tongDiem= new TongDiem();
				  
				  
				  int tong=0;
				  student.setTruongTieuHoc(fmt.formatCellValue(row.getCell(1)));
				  student.setQuanHuyen(fmt.formatCellValue(row.getCell(2)));
				  student.setMaHocSinh(fmt.formatCellValue(row.getCell(3)));
				  student.setLop(fmt.formatCellValue(row.getCell(4)));
				  student.setHoVaTen(fmt.formatCellValue(row.getCell(5)));
				  SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
				  Date ngaySinh=DateFor.parse(row.getCell(6)+"/"+row.getCell(7)+"/"+row.getCell(8));
				  student.setNgaySinh(ngaySinh);
				  student.setGioiTinh(fmt.formatCellValue(row.getCell(9)));
				  student.setNoiSinh(fmt.formatCellValue(row.getCell(10)));
				  student.setDanToc(fmt.formatCellValue(row.getCell(11)));
				  student.setHktt(fmt.formatCellValue(row.getCell(12)));
				  student.setSdt(fmt.formatCellValue(row.getCell(13)));
				  
				  
				  Row rowWithPoint=sheet.getRow(i-1);
				  Row tendiem=sheet.getRow(4);
				  int j=15;
				  // điểm sơ tuyển
				  for ( j = 14; j <= 18; j++) {
					  DiemSoTuyen diemSoTuyen=new DiemSoTuyen();
					  diemSoTuyen.setStudent(student);
					  diemSoTuyen.setNamLop( fmt.formatCellValue(tendiem.getCell(j)));
					  diemSoTuyen.setDiem(Integer.parseInt(fmt.formatCellValue(row.getCell(j))));
					  tong+=diemSoTuyen.getDiem();
//					  System.out.println(diemSoTuyen.toString());
					  lisDiemSoTuyens.add(diemSoTuyen);
				  }
				  
				  // tổng điểm
				  if (fmt.formatCellValue(row.getCell(20)).isEmpty()) {
					tongDiem.setDiemUuTien(null);
				  }else {
					tongDiem.setDiemUuTien(Float.parseFloat(fmt.formatCellValue(row.getCell(20))));
				  }
				  
				  tongDiem.setTong(tong);
				  tongDiem.setTongDiem(student);
				  tongDiem.setGhiChu(fmt.formatCellValue(row.getCell(22)));
				  listTongDiems.add(tongDiem);
				  listStudents.add(student);
				  if (listStudents.size()%10==0 ||i==sheet.getPhysicalNumberOfRows()-1) {
					iStudentRepository.saveAll(listStudents);
					iDiemSoTuyenRepository.saveAll(lisDiemSoTuyens);
					iTongDiemRepository.saveAll(listTongDiems);
				}
			}
            workbook.close();
            inputStream.close();
            return listStudents;
        }
    
        
    
 
    // Get Workbook
    private static Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
        Workbook workbook = null;
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }
 
        return workbook;
    }
 

}
