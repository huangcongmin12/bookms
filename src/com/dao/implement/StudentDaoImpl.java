package com.dao.implement;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.dao.StudentDao;
import com.model.Student;

@Repository
@Scope("singleton")
public class StudentDaoImpl extends BaseDao implements StudentDao {
	
	//学生用户登录
	public boolean login(Student student){
		Session session = getSession();
		Query query = session.createQuery("from Student s where s.studentNumber=:stuNumber");
		query.setString("stuNumber", student.getStudentNumber());
		Student queryStudent = (Student)query.list().get(0);
		if(null!=queryStudent){
			if(queryStudent.getPassword().equals(student.getPassword()))
				return true;
			else
				return false;
		}
		else{
			return false;
		}
	}
	
	//添加学生
	public void add(Student student) {
		this.getHibernateTemplate().save(student);
	}

	//删除学生
	public void delete(Student student) {
		this.getHibernateTemplate().delete(this.getHibernateTemplate().load(Student.class, student.getId()));
	}

	//修改学生信息
	public void modify(Student student) {
		this.getHibernateTemplate().merge(student);
	}

	//加载学生信息
	public Student load(int id) {
		return this.getHibernateTemplate().get(Student.class, id);
	}

	//加载学生信息列表
	@SuppressWarnings("unchecked")
	public List<Student> list(int pageNow, int pageSize) {
		Session session = getSession();
		Query query = session.createQuery("from Student");
		query.setFirstResult((pageNow-1)*pageSize);
		query.setMaxResults(pageSize);
		return query.list();
	}

	//按学生学号查找
	@SuppressWarnings("unchecked")
	public Student findStudent(String studentNumber) {
		List<Student> list = this.getHibernateTemplate().find("from Student s where s.studentNumber=?",studentNumber);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		else{
			return null;
		}
	}

	//获取学生总数
	public long getAllStudentCount() {
		Session session = getSession();
		Query query = session.createQuery("select count(*) from Student");
		return  (Long) query.list().get(0);
	}

}
