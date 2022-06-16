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
@Table(name = "TongDiem")
@AllArgsConstructor
@NoArgsConstructor
public class TongDiem {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private Float diemUuTien;
	
	@Column
	private float tong;
	
	@Column
    private	String ghiChu;
	
	 @ManyToOne
	    @JoinColumn(name = "maHocSinh")
	    private Student tongDiem;
}
