package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.DiemSoTuyen;
import com.example.demo.model.TongDiem;

public interface ITongDiemRepository extends JpaRepository<TongDiem, Integer> {
	@Query("select td from TongDiem td where td.tongDiem.maHocSinh like %:maHocSinh%")
	public TongDiem findTongDiemStudent(@Param("maHocSinh")String maHocSinh);
}
