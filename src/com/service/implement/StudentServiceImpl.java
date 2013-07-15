package com.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dao.StudentDao;
import com.model.Student;
import com.service.StudentService;

@Service
@Scope("prototype")
@Transactional
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;

	// 学生用户登录
	public boolean login(Student student) {
		if (studentDao.login(student))
			return true;
		else
			return false;
	}

	// 添加学生
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public boolean add(Student student) {
		Student stu = studentDao.findStudent(student.getStudentNumber());
		if (null == stu) {
			studentDao.add(student);
			return true;
		} else {
			return false;
		}
	}

	// 删除学生
	@Transactional
	public boolean delete(Student student) {
		if (null != studentDao.load(student.getId())) {
			studentDao.delete(student);
			return true;
		} else {
			return false;
		}
	}

	// 修改学生信息
	@Transactional
	public boolean modify(Student student) {
		Student dbStu = studentDao.findStudent(student.getStudentNumber());
		if (student.getId() == dbStu.getId()) {
			studentDao.modify(student);
			return true;
		} else {
			return false;
		}
	}

	// 加载学生信息
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Student load(int id) {
		Student student = studentDao.load(id);
		if (null != student)
			return student;
		else
			return null;
	}

	// 加载学生信息列表
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Student> list(int pageNow, int pageSize) {
		List<Student> students = studentDao.list(pageNow, pageSize);
		if (students.size() > 0)
			return students;
		else
			return null;
	}

	// 按学生学号查找
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Student findStudent(String studentNumber) {
		Student student = studentDao.findStudent(studentNumber);
		if (null != student)
			return student;
		else
			return null;
	}

	// 获取学生总数
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public long getAllStudentCount() {
		return studentDao.getAllStudentCount();
	}

}
