package com.dao.implement;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.dao.BorrowDao;
import com.model.Book;
import com.model.Borrow;
import com.model.Student;
import com.util.MyDateUtil;

@Repository
@Scope("singleton")
public class BorrowDaoImpl extends BaseDao implements BorrowDao {

	//添加借书单
	public void add(Borrow borrow) {
		this.getHibernateTemplate().save(borrow);
	}

	//删除借书单
	public void delete(Borrow borrow) {
		this.getHibernateTemplate().delete(this.getHibernateTemplate().load(Borrow.class, borrow.getId()));
	}

	//修改借书单信息
	public void modify(Borrow borrow) {
		this.getHibernateTemplate().merge(borrow);
	}

	//加载借书单信息
	public Borrow load(int id) {
		return this.getHibernateTemplate().get(Borrow.class, id);
	}

	//加载借书单信息列表
	@SuppressWarnings("unchecked")
	public List<Borrow> list(int pageNow, int pageSize) {
		Session session = getSession();
		Query query = session.createQuery("from Borrow");
		query.setFirstResult((pageNow-1)*pageSize);
		query.setMaxResults(pageSize);
		return query.list();
	}

	//按学生学号查找借书单
	@SuppressWarnings("unchecked")
	public List<Borrow> findBorrowByStudetNumber(String studentNumber) {
		Session session = getSession();
		Query queryStudent = session.createQuery("from Student s where s.studentNumber = :stuNumber");
		queryStudent.setString("stuNumber", studentNumber);
		if(queryStudent.list().size()>0){
			Student student = (Student)queryStudent.list().get(0);
			Query query = session.createQuery("from Borrow b where b.student=:stu");
			query.setEntity("stu", student);
			return query.list();
		}
		else{
			return null;
		}
	}
	
	//按图书编号查找借阅记录
	public Borrow findBorrowByBookNumber(String bookNumber){
		Session session = getSession();
		Query queryBook = session.createQuery("from Book b where b.bookNumber = :bookNumber");
		queryBook.setString("bookNumber", bookNumber);
		if(queryBook.list().size()>0){
			Book book = (Book)queryBook.list().get(0);
			Query query = session.createQuery("from Borrow b where b.book=:book");
			query.setEntity("book", book);
			Borrow borrow = (Borrow)query.list().get(0);
			return borrow;
		}
		else{
			return null;
		}
	}
	//获取借书单总数
	
	public long getAllBorrowCount() {
		Session session = getSession();
		Query query = session.createQuery("select count(*) from Borrow");
		return (Long) query.uniqueResult();
	}

	//按学生学号查找借阅历史
	@SuppressWarnings("unchecked")
	public List<Borrow> findBorrowByStudetNumberHistory(String studentNumber) {
		Session session = getSession();
		Query queryStudent = session.createQuery("from Student s where s.studentNumber = :stuNumber");
		queryStudent.setString("stuNumber", studentNumber);
		if(queryStudent.list().size()>0){
			Student student = (Student)queryStudent.list().get(0);
			Query query = session.createQuery("from Borrow b where b.student=:stu and b.deal=1");
			query.setEntity("stu", student);
		return query.list();
		}
		else{
			return null;
		}
	}
	
	//按学生学号查找未图书的还借阅记录
		@SuppressWarnings("unchecked")
		public List<Borrow> findBorrowByStudetNumberOfNoReturn(String studentNumber){
			Session session = getSession();
			Query queryStudent = session.createQuery("from Student s where s.studentNumber = :stuNumber");
			queryStudent.setString("stuNumber", studentNumber);
			if(queryStudent.list().size()>0){
				Student student = (Student)queryStudent.list().get(0);
				Query query = session.createQuery("from Borrow b where b.student=:stu and b.deal=0");
				query.setEntity("stu", student);
			return query.list();
			}
			else{
				return null;
			}
		}

		//加载催还列表
		@SuppressWarnings("unchecked")
		public List<Borrow> callBack(String studentNumber){
			Session session = getSession();
			Query queryStu = session.createQuery("from Student s where s.studentNumber=:studentNumber");
			queryStu.setString("studentNumber", studentNumber);
			if(queryStu.list().size()>0){
				Student stu = (Student)queryStu.list().get(0);
				Query query = session.createQuery("from Borrow b where b.student=:stu");
				query.setEntity("stu", stu);
				if(query.list().size()>0){
					MyDateUtil date = new MyDateUtil();
					List<Borrow> borrows = query.list();
					List<Borrow> list = new ArrayList<Borrow>();
 					for(Borrow temp:borrows){
 						int days = date.daysBetweenTwoDate( date.getNowTime(),temp.getEndTime());
 						if(days<=3&&temp.getDeal()==0){
 							list.add(temp);
 						}
 					}
 					return list;
				}
				else{
					return null;
				}
			}
			else{
				return null;
			}
		}
		
}
