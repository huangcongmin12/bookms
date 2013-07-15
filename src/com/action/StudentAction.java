package com.action;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.model.Amercement;
import com.model.Appointment;
import com.model.Borrow;
import com.model.Student;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.AmercementService;
import com.service.AppointmentService;
import com.service.BorrowService;
import com.service.StudentService;
import com.util.Encrypt;
import com.util.MyComparator;
import com.util.MyDateUtil;
import com.util.Page;

@Scope("prototype")
@Controller("studentAction")
@SuppressWarnings("serial")
public class StudentAction extends ActionSupport {

	@Autowired
	private StudentService studentService;
	@Autowired
	private BorrowService borrowService;
	@Autowired
	private AppointmentService appointmentService;
	@Autowired
	private AmercementService amercementService;
	private Student student;
	private String verifycode;
	private int borrowId;
	private String oldpwd;
	private int pageNow = 1;
	private int pageSize = 12;

	// 加载学生列表
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String list() throws Exception {
		Page page = new Page(this.pageNow, this.pageSize,
				(int) studentService.getAllStudentCount());
		List<Student> stu = studentService.list(this.pageNow, this.pageSize);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("page", page);
		request.put("student", stu);
		return SUCCESS;
	}

	// 新增学生
	public String add() throws Exception {
		if (student.getStudentNumber().trim().equals("")
				|| student.getPassword().trim().equals("")) {
			this.addActionMessage("学生学号和登录密码不能为空！");
			return INPUT;
		} else {
			student.setPassword(Encrypt.encodeMD5(student.getPassword()));
			if (studentService.add(student)) {
				return SUCCESS;
			} else {
				this.addActionMessage("学生学号已存在！");
				return INPUT;
			}
		}
	}

	// 删除学生
	public String delete() throws Exception {
		if (studentService.delete(student)) {
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	// 更新学生视图
	public String updateView() throws Exception {
		Student stu = studentService.load(student.getId());
		ServletActionContext.getRequest().setAttribute("student", stu);
		return SUCCESS;
	}

	// 更新学生
	public String update() throws Exception {
		student.setPassword(Encrypt.encodeMD5(student.getPassword()));
		if (studentService.modify(student)) {
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	// 查询学生
	public String find() throws Exception {
		Student stu = studentService.findStudent(student.getStudentNumber());
		if (null != stu) {
			ServletActionContext.getRequest().setAttribute("student", stu);
			return SUCCESS;
		} else {
			this.addActionMessage("该学生不存在，请输入正确的学生学号！！");
			return INPUT;
		}
	}

	// 读者登录
	public String login() throws Exception {
		Student stu = studentService.findStudent(this.getStudent().getStudentNumber());
		String rand = (String) ActionContext.getContext().getSession().get("rand");
		if (!this.getVerifycode().equals(rand)) {
			this.addActionMessage("验证码不正确！");
			return INPUT;
		}
		if (stu != null) {
			student.setPassword(Encrypt.encodeMD5(student.getPassword()));
			if (stu.getPassword().trim().equals(student.getPassword().trim())) {
				ActionContext.getContext().getSession().put("student", stu);
				return SUCCESS;
			} else {
				this.addActionMessage("登录密码不正确！");
				return INPUT;
			}
		} else {
			this.addActionMessage("查无此读者！请输入正确的读者条码！");
			return INPUT;
		}
	}

	// 读者退出登录
	public String loginOut() throws Exception {
		ActionContext.getContext().getSession().remove("student");
		return SUCCESS;
	}

	// 修改登录密码视图
	public String modifypwd_view() throws Exception {
		Student stu0 = (Student) ActionContext.getContext().getSession()
				.get("student");
		if (null == stu0) {
			this.addActionMessage("sorry ! 请先登录！");
			return LOGIN;
		}
		Student stu = studentService.load(stu0.getId());
		ServletActionContext.getRequest().setAttribute("student", stu);
		return SUCCESS;
	}

	// 修改密码
	public String modifypwd() throws Exception {
		Student stu = studentService.load(this.student.getId());
		if (stu.getPassword().equals(Encrypt.encodeMD5(this.oldpwd))) {
			stu.setPassword(Encrypt.encodeMD5(this.student.getPassword()));
			studentService.modify(stu);
			return SUCCESS;
		} else {
			this.addActionMessage("原始密码不正确！");
			ServletActionContext.getRequest().setAttribute("student", stu);
			return INPUT;
		}
	}

	// 借阅信息
	@SuppressWarnings("unchecked")
	public String borrowlist() throws Exception {
		Student stu = (Student) ActionContext.getContext().getSession()
				.get("student");
		if (null == stu) {
			this.addActionMessage("sorry ! 请先登录！");
			return LOGIN;
		}
		List<Borrow> list = borrowService
				.findBorrowByStudetNumberOfNoReturn(stu.getStudentNumber());
		if (list.size() > 0) {
			MyComparator myComparator = new MyComparator();
			Collections.sort(list, myComparator);
			@SuppressWarnings("rawtypes")
			Map request = (Map) ActionContext.getContext().get("request");
			request.put("borrow", list);
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	// 预约通知
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String appoint() throws Exception {
		Student stu0 = (Student) ActionContext.getContext().getSession()
				.get("student");
		if (null == stu0) {
			this.addActionMessage("sorry ! 请先登录！");
			return LOGIN;
		}
		List<Appointment> appointments = appointmentService
				.findAppointmentList(stu0.getStudentNumber());
		if (appointments != null && appointments.size() > 0) {
			Map request = (Map) ActionContext.getContext().get("request");
			request.put("appointment", appointments);
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	// 借阅历史
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String borrowHistory() throws Exception {
		Student stu0 = (Student) ActionContext.getContext().getSession()
				.get("student");
		if (null == stu0) {
			this.addActionMessage("sorry ! 请先登录！");
			return LOGIN;
		}
		List<Borrow> list = borrowService.findBorrowByStudetNumberHistory(stu0
				.getStudentNumber());
		if (list.size() > 0) {
			Map request = (Map) ActionContext.getContext().get("request");
			request.put("borrow", list);
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	// 催还通知
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String callBack() throws Exception {
		Student stu0 = (Student) ActionContext.getContext().getSession()
				.get("student");
		if (null == stu0) {
			this.addActionMessage("sorry ! 请先登录！");
			return LOGIN;
		}
		List<Borrow> list = borrowService.callBack(stu0.getStudentNumber());
		if (list != null && list.size() > 0) {
			Map request = (Map) ActionContext.getContext().get("request");
			request.put("borrow", list);
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	// 罚款记录
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String amerce() throws Exception {
		Student stu0 = (Student) ActionContext.getContext().getSession()
				.get("student");
		if (null == stu0) {
			this.addActionMessage("sorry ! 请先登录！");
			return LOGIN;
		}
		List<Amercement> list = amercementService.findAmercementList(stu0
				.getStudentNumber());
		if (list != null & list.size() > 0) {
			Map request = (Map) ActionContext.getContext().get("request");
			request.put("amercement", list);
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	// 续借
	public String renew() throws Exception {
		MyDateUtil date = new MyDateUtil();
		Borrow b = borrowService.load(this.borrowId);
		int days = date.daysBetweenTwoDate(b.getEndTime(), date.getNowTime());
		if (days <= -10) {
			b.setRenew(1); // 不在现在时间的基础上加10天,直接设续借标志为1,
			borrowService.modify(b);
			return SUCCESS;
		} else if (days > -10 && days <= 0) {
			b.setEndTime(date.getEndTime(10));// 实现续借，即在现在时间的基础上加10天
			b.setRenew(1); // 续借标志设为1,
			borrowService.modify(b);
			return SUCCESS;
		} else { // 超期不能续借
			return ERROR;
		}
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
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

	public String getVerifycode() {
		return verifycode;
	}

	public void setVerifycode(String verifycode) {
		this.verifycode = verifycode;
	}

	public String getOldpwd() {
		return oldpwd;
	}

	public void setOldpwd(String oldpwd) {
		this.oldpwd = oldpwd;
	}

	public int getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(int borrowId) {
		this.borrowId = borrowId;
	}
}
