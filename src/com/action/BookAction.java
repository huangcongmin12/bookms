package com.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.model.Book;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.BookService;
import com.util.Page;

@Scope("prototype")
@Controller("bookAction")
@SuppressWarnings("serial")
public class BookAction extends ActionSupport {

	@Autowired
	private BookService bookService;
	private Book book;
	private int pageNow = 1;
	private int pageSize = 12;

	// 加载图书列表

	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		Page page = new Page(this.pageNow, this.pageSize,(int) bookService.getAllBookCount());
		List<Book> books = bookService.list(this.pageNow, this.pageSize);
		Map<String, Object> request = (Map<String, Object>) ActionContext.getContext().get("request");
		request.put("book", books);
		request.put("page", page);
		return SUCCESS;
	}

	// 新增图书
	public String add() throws Exception {
		if (book.getBookNumber().trim().equals("")) {
			this.addActionMessage("图书编号不能为空！");
			return INPUT;
		} else {
			book.setBookStatus(0);
			if (bookService.add(book)) {
				return SUCCESS;
			} else {
				this.addActionMessage("图书编号已存在！");
				return INPUT;
			}
		}
	}

	// 删除图书
	public String delete() throws Exception {
		if (bookService.delete(book)) {
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	// 图书更新视图
	public String updateView() throws Exception {
		Book b = bookService.load(book.getId());
		ServletActionContext.getRequest().setAttribute("book", b);
		return SUCCESS;
	}

	// 更新图书
	public String update() throws Exception {
		if (bookService.modify(book)) {
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	// 图书查询
	public String find() throws Exception {
		Book book1 = bookService.findBook(book.getBookNumber());
		if (null != book1) {
			ServletActionContext.getRequest().setAttribute("book", book1);
			return SUCCESS;
		} else {
			this.addActionMessage("该图书不存在，请输入正确的图书编号！！");
			return INPUT;
		}
	}
	
	// 按图书名称模糊查询
	@SuppressWarnings("unchecked")
	public String search() throws Exception {
		List<Book> books = bookService.findBookByName(book.getBookname(), pageNow, pageSize);
		if(books!=null) {
			Page page = new Page(pageNow, pageSize, bookService.searchCount(book.getBookname()));
			Map<String, Object> request = (Map<String, Object>) ActionContext.getContext().get("request");
			request.put("book", books);
			request.put("page", page);
			ServletActionContext.getRequest().setAttribute("bookname", book.getBookname());
			return SUCCESS;
		} else {
			return ERROR;
		}
		
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
