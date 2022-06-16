package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.DiemSoTuyen;
import com.example.demo.repositories.IDiemSoTuyenRepository;

import lombok.ToString;

@Service
public class DiemSoTuyenService {
  @Autowired
  private IDiemSoTuyenRepository iDiemSoTuyenRepository;
  
  public DiemSoTuyen geDiemSoTuyen(String mssv, String lop) {
	return iDiemSoTuyenRepository.findDiemStudent(mssv,lop);
  }
}
