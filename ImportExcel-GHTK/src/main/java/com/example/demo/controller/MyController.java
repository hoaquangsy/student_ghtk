package com.example.demo.controller;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.swing.table.TableStringConverter;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.DiemSoTuyen;
import com.example.demo.model.Student;
import com.example.demo.model.TongDiem;
import com.example.demo.repositories.IDiemSoTuyenRepository;
import com.example.demo.repositories.IStudentRepository;
import com.example.demo.service.DiemSoTuyenService;
import com.example.demo.service.MyService;
import com.example.demo.service.StudentService;
import com.example.demo.service.TongDiemService;

@Controller
@MultipartConfig
public class MyController {

	@Autowired
	MyService myService;

	@Autowired
	StudentService studentService;

	@Autowired
	IStudentRepository iStudentRepository;
	
	@Autowired
	DiemSoTuyenService diemSoTuyenService;
	
	@Autowired
	TongDiemService tongDiemService;

	@GetMapping("/home")
	public String home() {
		return "import";
	}

	@PostMapping("/import")
	public String importExcel(HttpServletRequest request) {
		myService.importExecl(request);
		return "search";
	}

	@GetMapping("/searchForm")
	public String getSearchForm(Model model) {
		model.addAttribute("students", studentService.getAll());
		return "search";
	}

	@GetMapping("/searchStudent")
	public String findStudent(@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "mssv", required = false) String mssv, Model model) {
//		model.addAttribute("students", studentService.findByMaHocSinhLike("%" + mssv + "%"));
       System.out.println(name+"tren");
		List<Student> student = null;
		if (name=="" && mssv=="") {
			model.addAttribute("students", studentService.getAll());
		} else if (mssv!="" && name=="") {
			System.out.println("alo");
			student = studentService.findByMaHocSinhLike("%" + mssv + "%");
			DiemSoTuyen lop1=diemSoTuyenService.geDiemSoTuyen(mssv, "Tổng điểm năm lớp 1");
			DiemSoTuyen lop2=diemSoTuyenService.geDiemSoTuyen(mssv, "Tổng điểm năm lớp 2");
			DiemSoTuyen lop3=diemSoTuyenService.geDiemSoTuyen(mssv, "Tổng điểm năm lớp 3");
			DiemSoTuyen lop4=diemSoTuyenService.geDiemSoTuyen(mssv, "Tổng điểm năm lớp 4");
			DiemSoTuyen lop5=diemSoTuyenService.geDiemSoTuyen(mssv, "Tổng điểm năm lớp 5");
			
			model.addAttribute("students", student);
			model.addAttribute("lop1", lop1.getDiem());
			model.addAttribute("lop2", lop2.getDiem());
			model.addAttribute("lop3", lop3.getDiem());
			model.addAttribute("lop4", lop4.getDiem());
			model.addAttribute("lop5", lop5.getDiem());
			System.out.println(student.toString());
			TongDiem tongDiem=tongDiemService.geTongDiem(mssv);
			model.addAttribute("tongDiem", tongDiem);
		}else if(mssv=="" && name!=""){
			System.out.println("all");
			System.out.println(name);
			student = studentService.findByHoVaTenLike("%" + name + "%");
			model.addAttribute("students", student);
		}else {
			model.addAttribute("students", studentService.getAll());
		}

		return "search";
	}
}
