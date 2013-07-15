package com.dao.implement;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.dao.AppointmentDao;
import com.model.Appointment;
import com.model.Book;
import com.model.Student;

@Repository
@Scope("singleton")
public class AppointmentDaoImpl extends BaseDao implements AppointmentDao{
	
	//添加预约
	public void add(Appointment appointment){
		Book book = this.getHibernateTemplate().load(Book.class, appointment.getBook().getId());
		if(book.getBookStatus()==0)
			this.getHibernateTemplate().save(appointment);
	}
	
	//删除预约
	public void delete(Appointment appointment){
		this.getHibernateTemplate().delete(this.getHibernateTemplate().load(Appointment.class, appointment.getId()));
	}
	
	//修改预约信息
	public void modify(Appointment appointment){
		this.getHibernateTemplate().merge(appointment);
	}
	
	//加载预约信息
	public Appointment load(int id){
		return this.getHibernateTemplate().get(Appointment.class, id);
	}
	
	//加载预约信息列表
	@SuppressWarnings("unchecked")
	public List<Appointment> list(int pageNow,int pageSize){
		Session session = getSession();
		Query query = session.createQuery("from Appointment");
		query.setFirstResult((pageNow-1)*pageSize);
		query.setMaxResults(pageSize);
		return query.list();
	}
	
	//按学生学号查找预约
	@SuppressWarnings("unchecked")
	public List<Appointment> findAppointmentList(String studentNumber){
		Session session = getSession();
		Query queryStudent = session.createQuery("from Student s where s.studentNumber=:stuNumber");
		queryStudent.setString("stuNumber", studentNumber);
		if(queryStudent.list().size()>0){
			Student student = (Student)queryStudent.list().get(0);
			Query query = session.createQuery("from Appointment a where a.student=:stu");
			query.setEntity("stu", student);
			return query.list();
		}
		else{
			return null;
		}
	}
	
	//获取总预约数
	public long getAllAppointmentCount(){
		return (Long) getHibernateTemplate().find("select count(*) from Appointment").iterator().next();
	}

}
