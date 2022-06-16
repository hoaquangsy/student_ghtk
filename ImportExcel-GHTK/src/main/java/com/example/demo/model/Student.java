package com.example.demo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


//import org.hibernate.search.annotations.Analyze;
//import org.hibernate.search.annotations.Field;
//import org.hibernate.search.annotations.Index;
//import org.hibernate.search.annotations.Indexed;
//import org.hibernate.search.annotations.Store;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "student")
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	
	@Id
	private String maHocSinh;
	
	@Column
	private String truongTieuHoc;
	@Column
	private String quanHuyen;
	
	
	@Column
	private String lop;
	
	@Column
	private String hoVaTen;
	@Column
	private Date NgaySinh;
	@Column
	private String gioiTinh;
	@Column
	private String noiSinh;
	@Column
	private String danToc;
	@Column
	private String hktt;
	@Column
	private String sdt;
	
	@OneToMany(mappedBy = "student")
    private List<DiemSoTuyen> dst;
	
	@OneToMany(mappedBy = "tongDiem")
    private List<TongDiem> tongDiem ;

	@Override
	public String toString() {
		return "Student [maHocSinh=" + maHocSinh + ", truongTieuHoc=" + truongTieuHoc + ", quanHuyen=" + quanHuyen
				+ ", lop=" + lop + ", HoVaTen=" + hoVaTen + ", NgaySinh=" + NgaySinh + ", gioiTinh=" + gioiTinh
				+ ", noiSinh=" + noiSinh + ", danToc=" + danToc + ", hktt=" + hktt + ", sdt=" + sdt + "]";
	}


}
