package com.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.model.Amercement;
import com.model.Book;
import com.model.Borrow;
import com.model.Student;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.AmercementService;
import com.service.BookService;
import com.service.BorrowService;
import com.service.StudentService;
import com.util.MyDateUtil;
import com.util.Page;

@Scope("prototype")
@Controller("borrowAction")
@SuppressWarnings("serial")
public class BorrowAction extends ActionSupport {

	@Autowired
	private BorrowService borrowService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private BookService bookService;
	@Autowired
	private AmercementService amercementService;
	private Borrow borrow;
	private int pageNow = 1;
	private int pageSize = 12;
	private String studentNumber;

	// 加载借阅列表
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String list() throws Exception {
		List<Borrow> borrow = borrowService.list(this.pageNow, this.pageSize);
		Page page = new Page(this.pageNow, this.pageSize,
				(int) borrowService.getAllBorrowCount());
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("borrow", borrow);
		request.put("page", page);
		return SUCCESS;
	}

	// 新增借阅
	public String add() throws Exception {
		Student student = studentService.findStudent(borrow.getStudent()
				.getStudentNumber());
		if (null == student) {
			this.addActionMessage("学号不存在，请输入正确的学生学号！");
			return INPUT;
		}
		if (student.getPermitted() == 0) {
			this.addActionMessage("用户已被锁定，不能借书！");
			return INPUT;
		}
		List<Borrow> list = borrowService.findBorrowByStudetNumber(borrow
				.getStudent().getStudentNumber());
		if (list.size() > 0) {
			MyDateUtil date = new MyDateUtil();
			for (Borrow temp : list) { // 遍历学生借书列表，判断学生是否还有超期的书未还,若有，则不能借阅。
				if (temp.getDeal() == 0
						&& date.daysBetweenTwoDate(temp.getEndTime(),
								date.getNowTime()) > 0) {
					this.addActionMessage("超期图书未归还，不能借阅！！");
					return INPUT;
				}
			}
		}
		List<Amercement> amercementlist = amercementService
				.findAmercementList(borrow.getStudent().getStudentNumber());
		if (amercementlist.size() > 0) {
			for (Amercement t : amercementlist) { // 遍历学生罚单列表，如存在罚单，且未缴纳，则不能借书
				if (t.getPay() == 0) {
					this.addActionMessage("罚款未缴纳，不能借阅！！");
					return INPUT;
				}
			}
		}
		Book book = bookService.findBook(borrow.getBook().getBookNumber());
		if (null == book) {
			this.addActionMessage("该图书不存在，请输入正确的图书编号！");
			return INPUT;
		}
		if (book.getBookStatus() == 0) { // 判断图书状态,如在库则可借，否则，不可借。 0:在库，1:借出，2:丢失
			MyDateUtil date = new MyDateUtil();
			book.setBookStatus(1); // 图书状态更新为借出(1)
			bookService.modify(book); // 执行更新
			borrow.setDeal(0); // 借书状态设为0即未归还， 0:未归还 1:已归还 2:丢失
			borrow.setRenew(0); // 是否续借设为0即未续借，是否续借标志 1:是 0:否
			borrow.setStartTime(date.getNowTime()); // 设置借阅时间即当前时间
			borrow.setEndTime(date.getEndTime(15)); // 借阅期限为15天
			borrow.setStudent(student);
			borrow.setBook(book);
			borrowService.add(borrow);
			return SUCCESS;
		} else if (book.getBookStatus() == 1) {
			this.addActionMessage("该图书已借出！");
			return INPUT;
		} else {
			this.addActionMessage("该图书已丢失！");
			return INPUT;
		}
	}

	// 删除借阅
	public String delete() throws Exception {
		if (borrowService.delete(borrow)) {
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	// 借阅更新视图
	public String updateView() throws Exception {
		Borrow borrow1 = borrowService.load(borrow.getId());
		ServletActionContext.getRequest().setAttribute("borrow", borrow1);
		return SUCCESS;
	}

	// 更新借阅
	public String update() throws Exception {
		Student student = studentService.findStudent(borrow.getStudent()
				.getStudentNumber());
		Book book = bookService.findBook(borrow.getBook().getBookNumber());
		if (null == student) {
			this.addActionMessage("该学生不存在，请输入正确的学生学号！");
			ServletActionContext.getRequest().setAttribute("borrow",
					borrowService.load(borrow.getId()));
			return "reinput";
		} else if (null == book) {
			this.addActionMessage("该图书不存在，请输入正确的图书编号！");
			ServletActionContext.getRequest().setAttribute("borrow",
					borrowService.load(borrow.getId()));
			return "reinput";
		} else {
			if (borrow.getDeal() == 0) { // 如果借阅状态更新为未归还(1)，则图书状态设为借出(1)
				book.setBookStatus(1);
			} else if (borrow.getDeal() == 1) { // 如果借阅状态更新为已归还(1)，则图书状态设为在库(0)
				book.setBookStatus(0);
			} else { // 如果借阅状态更新为丢失(2)，则图书状态设为丢失(2)
				book.setBookStatus(2);
			}
			bookService.modify(book);
			borrow.setBook(book);
			borrow.setStudent(student);
			if (borrowService.modify(borrow)) {
				return SUCCESS;
			} else {
				return ERROR;
			}
		}
	}

	// 借阅查询
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String find() throws Exception {
		Student student = studentService.findStudent(this.studentNumber);
		if (student == null) {
			this.addActionMessage("该学生不存在，请输入正确的学生学号！");
			return INPUT;
		} else {
			List<Borrow> borrow = borrowService
					.findBorrowByStudetNumber(this.studentNumber);
			if (borrow != null && borrow.size() > 0) {
				Map request = (Map) ActionContext.getContext().get("request");
				request.put("borrow", borrow);
				return SUCCESS;
			} else {
				return ERROR;
			}
		}
	}

	// 归还图书
	public String back() throws Exception {
		Student student = studentService.findStudent(borrow.getStudent()
				.getStudentNumber());
		Book book = bookService.findBook(borrow.getBook().getBookNumber());
		if (null == student) {
			this.addActionMessage("该学生不存在，请输入正确的学生学号！");
			return INPUT;
		} else if (null == book) {
			this.addActionMessage("该图书不存在，请输入正确的图书编号！");
			return INPUT;
		} else if (book.getBookStatus() != 1) {
			this.addActionMessage("该图书未借出，请输入正确的图书编号！");
			return INPUT;
		} else {
			MyDateUtil date = new MyDateUtil();
			Borrow borrow1 = borrowService.findBorrowByBookNumber(book
					.getBookNumber());
			int days = date.daysBetweenTwoDate(borrow1.getEndTime(),
					date.getNowTime());
			if (days > 0) { // 如果图书是超期归还，则新增一个罚单。
				Amercement a = new Amercement();
				a.setBorrow(borrow1);
				a.setStudent(student);
				a.setDetail("超期");
				a.setPay(0);
				a.setMulct(0.1 * days); // 罚款金额=超期天数x0.1(元)
				amercementService.add(a);
			}
			book.setBookStatus(0); // 归还图书，图书状态更新为在库(0)
			bookService.modify(book); // 执行图书更新
			borrow1.setDeal(1); // 借阅状态更新为已归还(1)
			borrowService.modify(borrow1);
			return SUCCESS;
		}
	}

	// 图书挂失
	public String lose() throws Exception {
		Student student = studentService.findStudent(borrow.getStudent()
				.getStudentNumber());
		Book book = bookService.findBook(borrow.getBook().getBookNumber());
		if (null == student) {
			this.addActionMessage("该学生不存在，请输入正确的学生学号！");
			return INPUT;
		} else if (null == book) {
			this.addActionMessage("该图书不存在，请输入正确的图书编号！");
			return INPUT;
		} else if (book.getBookStatus() != 1) {
			this.addActionMessage("该图书未借出，请输入正确的图书编号！");
			return INPUT;
		} else {
			book.setBookStatus(2); // 借阅挂失，图书状态更新为丢失，且新增罚单
			bookService.modify(book);
			Borrow borrow1 = borrowService.findBorrowByBookNumber(book
					.getBookNumber()); // 查找借阅记录
			borrow1.setDeal(2); // 借阅状态更新为丢失
								// 新增罚单
			Amercement amercement = new Amercement();
			amercement.setBorrow(borrow1);
			amercement.setStudent(student);
			amercement.setDetail("丢书");
			amercement.setPay(0);
			amercement.setMulct(book.getBookPrice() * 3); // 罚款金额设为图书价格的3倍
			amercementService.add(amercement);
			borrowService.modify(borrow1);
			return SUCCESS;
		}
	}

	public Borrow getBorrow() {
		return borrow;
	}

	public void setBorrow(Borrow borrow) {
		this.borrow = borrow;
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

	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

}
