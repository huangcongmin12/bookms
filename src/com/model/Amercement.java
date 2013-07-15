package com.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="amercement")
public class Amercement implements Serializable {

	private static final long serialVersionUID = -8654170318132366163L;
	
	private int id;
	private double mulct;		//罚款金额
	private int pay;			//是否缴纳 1:已缴纳  0: 未缴纳
	private String payTime;		//缴纳时间
	private String detail;		//罚款项目      超期/丢书
	private Student student;	//罚款学生
	private Borrow borrow;		//借书单
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="mulct",length=10)
	public double getMulct() {
		return mulct;
	}
	public void setMulct(double mulct) {
		this.mulct = mulct;
	}
	@Column(name="pay",length=2)
	public int getPay() {
		return pay;
	}
	public void setPay(int pay) {
		this.pay = pay;
	}
	@Column(name="payTime",length=50)
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	@Column(name="detail",length=50)
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	@ManyToOne
	@JoinColumn(name="student_id")
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	@OneToOne
	@JoinColumn(name="borrow_id")
	public Borrow getBorrow() {
		return borrow;
	}
	public void setBorrow(Borrow borrow) {
		this.borrow = borrow;
	}
	
	
}
