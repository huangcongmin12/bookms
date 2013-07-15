package com.service;

import java.util.List;

import com.model.Student;

public interface StudentService {
		
		//学生用户登录
		public boolean login(Student student);
	    //添加学生
		public boolean add(Student student);
		//删除学生
		public boolean delete(Student student);
		//修改学生信息
		public boolean modify(Student student);
		//加载学生信息
		public Student load(int id);
		//加载学生信息列表
		public List<Student> list(int pageNow,int pageSize);
		//按学生学号查找
		public Student findStudent(String studentNumber);
		//获取学生总数
		public long getAllStudentCount();
}
