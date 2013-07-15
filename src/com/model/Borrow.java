package com.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="borrow")
public class Borrow implements Serializable {

	private static final long serialVersionUID = 173639886520090975L;
	
	private int id;
	private  String startTime;		//开始时间
	private String endTime;			//结束时间
	private int renew;			    //是否续借标志 1:是  0:否
	private int deal;				//借书状态  0:未归还    1:已归还    2:丢失
	private Student student;		//借书学生
	private Book book;				//所借图书
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="startTime",length=50)
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	@Column(name="endTime",length=50)
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	@Column(name="renew",length=2)
	public int getRenew() {
		return renew;
	}
	public void setRenew(int renew) {
		this.renew = renew;
	}
	@Column(name="deal",length=2)
	public int getDeal() {
		return deal;
	}
	public void setDeal(int deal) {
		this.deal = deal;
	}
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="student_id")
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="book_id")
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	
	
}
