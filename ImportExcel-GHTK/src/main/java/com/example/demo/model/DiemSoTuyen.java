package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "BangDiem")
@AllArgsConstructor
@NoArgsConstructor
public class DiemSoTuyen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
    
    @Column
	private String namLop;
    
    @Column
    private Integer diem;    
    @ManyToOne
    @JoinColumn(name = "maHocSinh")
    private Student student;
	@Override
	public String toString() {
		return "DiemSoTuyen [id=" + id + ", namLop=" + namLop + ", diem=" + diem ;
	}
    
}
