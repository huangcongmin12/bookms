package com.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="book")
public class Book implements Serializable{

	private static final long serialVersionUID = 5583963042696887858L;
	
	private int id;
	private String bookNumber;		//图书编号
	private String bookname;		//图书名称
	private String author;			//图书作者
	private String publish;			//出版社
	private String bookISBN;		//ISBN号
	private String buyTime;			//购进时间
	private double bookPrice;		//图书价格
	private int bookStatus;			//图书状态  0：在库 	1：借出	2：丢失
	private Set<Borrow> borrows = new HashSet<Borrow>();
	private Set<Appointment> appointments = new HashSet<Appointment>();
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="bookNumber",length=50,unique=true)
	public String getBookNumber() {
		return bookNumber;
	}
	public void setBookNumber(String bookNumber) {
		this.bookNumber = bookNumber;
	}
	@Column(name="bookName",length=50)
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	@Column(name="author",length=50)
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	@Column(name="publish" ,length=50)
	public String getPublish() {
		return publish;
	}
	public void setPublish(String publish) {
		this.publish = publish;
	}
	@Column(name="bookISBN",length=50)
	public String getBookISBN() {
		return bookISBN;
	}
	public void setBookISBN(String bookISBN) {
		this.bookISBN = bookISBN;
	}
	@Column(name="buyTime",length=50)
	public String getBuyTime() {
		return buyTime;
	}
	public void setBuyTime(String buyTime) {
		this.buyTime = buyTime;
	}
	@Column(name="bookPrice" ,length=10)
	public double getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}
	@Column(name="bookStatus" ,length=2)
	public int getBookStatus() {
		return bookStatus;
	}
	public void setBookStatus(int bookStatus) {
		this.bookStatus = bookStatus;
	}
	@OneToMany(mappedBy="book",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	public Set<Borrow> getBorrows() {
		return borrows;
	}
	public void setBorrows(Set<Borrow> borrows) {
		this.borrows = borrows;
	}
	@OneToMany(mappedBy="book",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	public Set<Appointment> getAppointments() {
		return appointments;
	}
	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}
}
