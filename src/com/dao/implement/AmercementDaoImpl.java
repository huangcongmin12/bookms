package com.dao.implement;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.dao.AmercementDao;
import com.model.Amercement;
import com.model.Book;
import com.model.Borrow;
import com.model.Student;

@Repository
@Scope("singleton")
public class AmercementDaoImpl extends BaseDao implements AmercementDao{
	
	//添加罚单
	public void add(Amercement amercement){
		this.getHibernateTemplate().save(amercement);
	}
	
	//删除罚单
	public void delete(Amercement amercement){
		this.getHibernateTemplate().delete(this.getHibernateTemplate().load(Amercement.class, amercement.getId()));
	}
	
	//修改罚单信息
	public void modify(Amercement amercement){
		this.getHibernateTemplate().merge(amercement);
	}
	
	//加载罚单信息
	public Amercement load(int id){
		return this.getHibernateTemplate().get(Amercement.class, id);
	}
	
	//加载罚单信息列表
	@SuppressWarnings("unchecked")
	public List<Amercement> list(int pageNow,int pageSize){
		Session session = getSession();
		Query query = session.createQuery("from Amercement");
		query.setFirstResult((pageNow-1)*pageSize);
		query.setMaxResults(pageSize);
		if(query.list().size()>0)
			return query.list();
		else 
			return null;
	}
	
	//按学生学号查找罚单
	@SuppressWarnings("unchecked")
	public List<Amercement> findAmercementList(String studentNumber){
		Session session = getSession();
		Query queryStudent = session.createQuery("from Student s where s.studentNumber=:stuNumber");
		queryStudent.setString("stuNumber", studentNumber);
		if(queryStudent.list().size()>0){
			Student student = (Student)queryStudent.list().get(0);
			Query query = session.createQuery("from Amercement a where a.student=:stu");
			query.setEntity("stu", student);
			return query.list();
		}
		else{
			return null;
		}
	}
	
	//按图书编号查找罚单
	public Amercement findAmercementByBookNumber(String bookNumber){
		Session session = getSession();
		Query queryBook = session.createQuery("from Book b where b.bookNumber=:bNumber");
		queryBook.setString("bNumber", bookNumber);
		if(queryBook.list().size()>0){
			Book book = (Book)queryBook.list().get(0);
			Query queryBorrow = session.createQuery("from Borrow a where a.book=:book");
			queryBorrow.setEntity("book", book);
			if(queryBorrow.list().size()>0){
				Borrow borrow = (Borrow) queryBorrow.list().get(0);
				Query query = session.createQuery("from Amercement a where a.borrow=:borrow");
				query.setEntity("borrow", borrow);
				return (Amercement) query.list().get(0);
			}
			else{
				return null;
			}
		}
		else{
			return null;
		}
	}
	
	//获取总罚单数
	public long getAllAmercementCount(){
		return (Long) this.getHibernateTemplate().find("select count(*) from Amercement").iterator().next();
	}

}
