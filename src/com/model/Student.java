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
@Table(name="studnet")
public class Student implements Serializable{
	
	private static final long serialVersionUID = 464293619689120934L;
	
	private int id;
	private String studentNumber;		//学号即登录名
	private String password;			//登录密码	
	private String studentName;			//学生姓名
	private int studentAge;			    //学生年龄
	private String studentSex;			//学生性别
	private String studentClass;		//所在班级
	private String department;			//所在系别
	private int permitted;				//借书权限  1:有借书权限 0:没有借书权限
	private Set<Borrow> borrows = new HashSet<Borrow>();
	private Set<Amercement> amercements = new HashSet<Amercement>();
	private Set<Appointment> appointments = new HashSet<Appointment>();
	
	@Id	
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="studentNumber",length=50,nullable=false,unique=true)
	public String getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	@Column(name="password",length=50,nullable=false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name="studentName",length=50)
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	@Column(name="age",length=3)
	public int getStudentAge() {
		return studentAge;
	}
	public void setStudentAge(int studentAge) {
		this.studentAge = studentAge;
	}
	@Column(name="sex",length=2)
	public String getStudentSex() {
		return studentSex;
	}
	public void setStudentSex(String studentSex) {
		this.studentSex = studentSex;
	}
	@Column(name="class",length=50)
	public String getStudentClass() {
		return studentClass;
	}
	public void setStudentClass(String studentClass) {
		this.studentClass = studentClass;
	}
	@Column(name="department", length=50)
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	@Column(name="permitted" ,length=2)
	public int getPermitted() {
		return permitted;
	}
	public void setPermitted(int permitted) {
		this.permitted = permitted;
	}
	@OneToMany(mappedBy="student",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	public Set<Borrow> getBorrows() {
		return borrows;
	}
	public void setBorrows(Set<Borrow> borrows) {
		this.borrows = borrows;
	}
	@OneToMany(mappedBy="student",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	public Set<Amercement> getAmercements() {
		return amercements;
	}
	public void setAmercements(Set<Amercement> amercements) {
		this.amercements = amercements;
	}
	@OneToMany(mappedBy="student",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	public Set<Appointment> getAppointments() {
		return appointments;
	}
	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}
}
