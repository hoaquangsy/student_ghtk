package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.DiemSoTuyen;

public interface IDiemSoTuyenRepository extends JpaRepository<DiemSoTuyen, Integer> {

@Query("select td from DiemSoTuyen td where td.student.maHocSinh like %:maHocSinh% and td.namLop=:namLop")
public DiemSoTuyen findDiemStudent(@Param("maHocSinh")String maHocSinh,@Param("namLop") String namLop);

//@Query("select td from DiemSoTuyen td where td.student.maHocSinh=:maHocSinh and td.namLop=:namLop")
//public List<DiemSoTuyen> findDiemStudent(@Param("maHocSinh")String maHocSinh,@Param("namLop") String namLop);
}
