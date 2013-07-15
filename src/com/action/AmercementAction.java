package com.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.model.Amercement;
import com.model.Borrow;
import com.model.Student;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.AmercementService;
import com.service.BorrowService;
import com.service.StudentService;
import com.util.MyDateUtil;
import com.util.Page;

@Scope("prototype")
@Controller("amercementAction")
@SuppressWarnings("serial")
public class AmercementAction extends ActionSupport {

	@Autowired
	private AmercementService amercementService;
	@Autowired
	private BorrowService borrowService;
	@Autowired
	private StudentService studentService;
	private Amercement amercement;
	private int pageNow = 1;
	private int pageSize = 12;
	private String bookNumber;
	private String studentNumber;

	// 加载罚单列表
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String list() throws Exception {
		List<Amercement> amercement = amercementService.list(this.pageNow,
				this.pageSize);
		if (amercement != null && amercement.size() > 0) {
			Page page = new Page(this.pageNow, this.pageSize,
					(int) amercementService.getAllAmercementCount());
			Map request = (Map) ActionContext.getContext().get("request");
			request.put("page", page);
			request.put("amercement", amercement);
			return SUCCESS;
		} else {
			this.addActionMessage("无罚单记录！！");
			return ERROR;
		}

	}

	// 新增罚单
	public String add() throws Exception {
		Borrow borrow = borrowService.findBorrowByBookNumber(this.bookNumber);
		Student stu = studentService.findStudent(amercement.getStudent()
				.getStudentNumber());
		if (null == stu) {
			this.addActionMessage("该学生不存在，请输入正确的学生学号！");
			return INPUT;
		} else if (null == borrow) {
			this.addActionMessage("该图书未借出，请输入正确的图书编号！！");
			return INPUT;
		} else {
			amercement.setStudent(stu);
			amercement.setBorrow(borrow);
			amercement.setPay(0); // 罚金默认未缴纳
			amercementService.add(amercement);
			return SUCCESS;
		}
	}

	// 删除罚单
	public String delete() throws Exception {
		if (amercementService.delete(amercement)) {
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	// 更新罚单视图
	public String updateView() throws Exception {
		Amercement amercement1 = amercementService.load(amercement.getId());
		ServletActionContext.getRequest().setAttribute("amercement",
				amercement1);
		return SUCCESS;
	}

	// 更新罚单
	public String update() throws Exception {
		Borrow borrow = borrowService.findBorrowByBookNumber(amercement
				.getBorrow().getBook().getBookNumber());
		Student stu = studentService.findStudent(amercement.getStudent()
				.getStudentNumber());
		if (null == stu) {
			this.addActionMessage("该学生不存在，请输入正确的学生学号！");
			ServletActionContext.getRequest().setAttribute("amercement",
					amercementService.load(amercement.getId()));
			return "reinput";
		} else if (null == borrow) {
			this.addActionMessage("该图书未借出，请输入正确的图书编号！！");
			ServletActionContext.getRequest().setAttribute("amercement",
					amercementService.load(amercement.getId()));
			return "reinput";
		} else {
			amercement.setStudent(stu);
			amercement.setBorrow(borrow);
			if (amercementService.modify(amercement)) {
				return SUCCESS;
			} else {
				return ERROR;
			}
		}
	}

	// 罚单查询
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String find() throws Exception {
		if (null == studentService.findStudent(studentNumber)) {
			this.addActionMessage("该学生不存在，请输入正确的学生学号！");
			return INPUT;
		} else {
			List<Amercement> list = amercementService
					.findAmercementList(studentNumber);
			if (list != null && list.size() > 0) {
				Page page = new Page(this.pageNow, this.pageSize, list.size());
				Map request = (Map) ActionContext.getContext().get("request");
				request.put("page", page);
				request.put("amercement", list);
				return SUCCESS;
			} else {
				return ERROR;
			}
		}
	}

	// 罚款缴纳
	public String pay() throws Exception {
		Borrow borrow = borrowService.findBorrowByBookNumber(this.bookNumber);
		Student stu = studentService.findStudent(this.studentNumber);
		if (null == stu) {
			this.addActionMessage("该学生不存在，请输入正确的学生学号！");
			return INPUT;
		} else if (null == borrow) {
			this.addActionMessage("该图书未借出，请输入正确的图书编号！");
			return INPUT;
		} else {
			MyDateUtil date = new MyDateUtil();
			Amercement amerce = amercementService
					.findAmercementByBookNumber(bookNumber);
			if (amerce != null && amerce.getPay() == 0) {
				amerce.setStudent(stu);
				amerce.setBorrow(borrow);
				amerce.setPay(1);
				amerce.setPayTime(date.getNowTime());
				amercementService.modify(amerce);
				return SUCCESS;
			} else {
				this.addActionMessage("该罚单不存在或罚款已缴纳！！");
				return INPUT;
			}
		}
	}

	public Amercement getAmercement() {
		return amercement;
	}

	public void setAmercement(Amercement amercement) {
		this.amercement = amercement;
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

	public String getBookNumber() {
		return bookNumber;
	}

	public void setBookNumber(String bookNumber) {
		this.bookNumber = bookNumber;
	}

	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

}
