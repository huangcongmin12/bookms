package com.dao.implement;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.dao.BookDao;
import com.model.Book;

@Repository
@Scope("singleton")
public class BookDaoImpl extends BaseDao implements BookDao {

	//添加图书
	public void add(Book book) {
		this.getHibernateTemplate().save(book);
	}

	//删除图书
	public void delete(Book book) {
		this.getHibernateTemplate().delete(this.getHibernateTemplate().load(Book.class, book.getId()));
	}

	//修改图书信息
	public void modify(Book book) {
		this.getHibernateTemplate().merge(book);
	}

	//加载图书信息
	public Book load(int id) {
		return this.getHibernateTemplate().get(Book.class, id);
	}

	//加载图书信息列表
	@SuppressWarnings("unchecked")
	public List<Book> list(int pageNow, int pageSize) {
		Session session = getSession();
		Query query = session.createQuery("from Book");
		query.setFirstResult((pageNow-1)*pageSize);
		query.setMaxResults(pageSize);
		return query.list();
	}

	//按图书编号查找图书
	@SuppressWarnings("unchecked")
	public Book findBook(String bookNumber) {
		List<Book> list = this.getHibernateTemplate().find("from Book b where b.bookNumber=?",bookNumber);
		if(list.size()>0)
			return (Book)list.get(0);
		else
			return null;
	}
	
	// 按图书名进行模糊查询
	@SuppressWarnings("unchecked")
	public List<Book> findBookByName(String bookName, int pageNow, int pageSize) {
		 String queryString = "from Book b where b.bookname like'%"+bookName+"%'";  
         Query queryObject = getSession().createQuery(queryString);  
         queryObject.setFirstResult((pageNow-1)*pageSize);
         queryObject.setMaxResults(pageSize);
         return queryObject.list();  
	}

	//获取学生总数
	public long getAllBookCount() {
		return (Long) getHibernateTemplate().find("select count(*) from Book").iterator().next();
	}

	public int searchCount(String bookname) {
		 String queryString = "from Book b where b.bookname like'%"+bookname+"%'";  
         Query queryObject = getSession().createQuery(queryString);  
         return queryObject.list().size();
	}

}
