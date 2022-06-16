package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.demo.model.Student;

@Service
public interface IStudentRepository extends JpaRepository<Student, String > {
	List<Student> findByHoVaTenLike(String hoVaTen);
	List<Student> findByMaHocSinhLike(String maHocSinh);
//	Optional<Student> findByMaHocSinhLike(String maHocSinh);
	
}
