package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.TongDiem;
import com.example.demo.repositories.ITongDiemRepository;

@Service
public class TongDiemService {
  @Autowired
  private ITongDiemRepository iTongDiemRepository;
  
  public TongDiem geTongDiem(String mssv) {
	  return iTongDiemRepository.findTongDiemStudent(mssv);
  }
}
