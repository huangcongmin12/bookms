package com.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dao.BookDao;
import com.model.Book;
import com.service.BookService;

@Service
@Scope("prototype")
@Transactional
public class BookServiceImpl extends HibernateDaoSupport implements BookService {

	@Autowired
	private BookDao bookDao;

	// 添加图书
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public boolean add(Book book) {
		Book book1 = bookDao.findBook(book.getBookNumber());
		if (null == book1) {
			bookDao.add(book);
			return true;
		} else {
			return false;
		}
	}

	// 删除图书
	@Transactional
	public boolean delete(Book book) {
		if (null != this.load(book.getId())) {
			bookDao.delete(book);
			return true;
		} else {
			return false;
		}
	}

	// 修改图书信息
	@Transactional
	public boolean modify(Book book) {
		Book dbBook = this.findBook(book.getBookNumber());
		if (book.getId() == dbBook.getId()) {
			bookDao.modify(book);
			return true;
		} else {
			return false;
		}
	}

	// 加载图书信息
	@Transactional(readOnly = true)
	public Book load(int id) {
		Book book = bookDao.load(id);
		if (null != book)
			return book;
		else
			return null;
	}

	// 加载图书信息列表
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Book> list(int pageNow, int pageSize) {
		if (this.getAllBookCount() == 0) {
			return null;
		} else {
			return bookDao.list(pageNow, pageSize);
		}
	}

	// 按图书编号查找图书
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Book findBook(String bookNumber) {
		Book book = bookDao.findBook(bookNumber);
		if (null != book)
			return book;
		else
			return null;
	}

	// 按图书名进行模糊查询
	public List<Book> findBookByName(String bookName, int pageNow, int pageSize) {
		List<Book> books = bookDao.findBookByName(bookName, pageNow, pageSize);
		if(books.size()>0) {
			return books;
		} else {
			return null;
		}
	}

	// 获取学生总数
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public long getAllBookCount() {
		return bookDao.getAllBookCount();
	}

	// 搜索数量
	public int searchCount(String bookname) {
		return bookDao.searchCount(bookname);
	}

}
