package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Student;
import com.example.demo.repositories.IStudentRepository;

@Service
public class StudentService {
  @Autowired
  private IStudentRepository iStudentRepository;
  
  public List<Student> getAll(){
	  return iStudentRepository.findAll();
  }

public List<Student> findByHoVaTenLike(String hoVaTen) {
	return iStudentRepository.findByHoVaTenLike(hoVaTen);
}

public List<Student> findByMaHocSinhLike(String maHocSinh) {
	return iStudentRepository.findByMaHocSinhLike(maHocSinh);
}
//public Optional<Student> getById(String id) {
//	return iStudentRepository.findByIdLike(id);
//}
  
  
  
}
