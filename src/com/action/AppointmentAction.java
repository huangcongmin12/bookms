package com.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.model.Appointment;
import com.model.Book;
import com.model.Student;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.AppointmentService;
import com.service.BookService;
import com.service.StudentService;
import com.util.MyDateUtil;
import com.util.Page;

@Scope("prototype")
@Controller("appointmentAction")
@SuppressWarnings("serial")
public class AppointmentAction extends ActionSupport {

	@Autowired
	private AppointmentService appointmentService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private BookService bookService;
	private Appointment appointment;
	private int pageNow = 1;
	private int pageSize = 12;

	// 加载预约列表
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String list() throws Exception {
		List<Appointment> appointment = appointmentService.list(this.pageNow,
				this.pageSize);
		if (appointment != null && appointment.size() > 0) {
			Page page = new Page(this.pageNow, this.pageSize,
					(int) appointmentService.getAllAppointmentCount());
			Map request = (Map) ActionContext.getContext().get("request");
			request.put("page", page);
			request.put("appointment", appointment);
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	// 新增预约
	public String add() throws Exception {
		Student student = studentService.findStudent(appointment.getStudent()
				.getStudentNumber());
		Book book = bookService.findBook(appointment.getBook().getBookNumber());
		if (null == student) {
			this.addActionMessage("该学生不存在，请输入正确的学生学号！");
			return INPUT;
		} else if (null == book) {
			this.addActionMessage("该图书不存在，请输入正确的图书编号！");
			return INPUT;
		} else {
			if (book.getBookStatus() == 0) { // 判断图书状态 0:在库 1:借出 2:丢失
				MyDateUtil date = new MyDateUtil();
				appointment.setEndTime(date.getEndTime(3)); // 预约截止时间为3天
				appointment.setBook(book);
				appointment.setStudent(student);
				appointmentService.add(appointment);
				return SUCCESS;
			} else {
				this.addActionMessage("该图书借出或已丢失，不能预约！");
				return INPUT;
			}
		}
	}

	// 删除预约
	public String delete() throws Exception {
		appointmentService.delete(appointment);
		return SUCCESS;
	}

	// 预约查询
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String find() throws Exception {
		Student stu = studentService.findStudent(appointment.getStudent()
				.getStudentNumber());
		if (null == stu) {
			this.addActionMessage("该学生不存在，请输入正确的学生学号！");
			return INPUT;
		} else {
			List<Appointment> list = appointmentService
					.findAppointmentList(appointment.getStudent()
							.getStudentNumber());
			if (list != null && list.size() > 0) {
				Page page = new Page(this.pageNow, this.pageSize,
						(int) appointmentService.getAllAppointmentCount());
				Map request = (Map) ActionContext.getContext().get("request");
				request.put("page", page);
				request.put("appointment", list);
				return SUCCESS;
			} else {
				return ERROR;
			}
		}
	}

	// 更新视图
	public String updateView() throws Exception {
		Appointment appointment1 = appointmentService.load(appointment.getId());
		ServletActionContext.getRequest().setAttribute("appointment",
				appointment1);
		return SUCCESS;
	}

	// 预约更新
	public String update() throws Exception {
		Student student = studentService.findStudent(appointment.getStudent()
				.getStudentNumber());
		Book book = bookService.findBook(appointment.getBook().getBookNumber());
		if (null == student) {
			this.addActionMessage("该学生不存在，请输入正确的学生学号！");
			ServletActionContext.getRequest().setAttribute("appointment",
					appointmentService.load(appointment.getId()));
			return "reinput";
		} else if (null == book) {
			this.addActionMessage("该图书不存在，请输入正确的图书编号！");
			ServletActionContext.getRequest().setAttribute("appointment",
					appointmentService.load(appointment.getId()));
			return "reinput";
		} else {
			if (book.getBookStatus() == 0) {
				appointment.setBook(book);
				appointment.setStudent(student);
				if (appointmentService.modify(appointment)) {
					return SUCCESS;
				} else {
					return ERROR;
				}
			} else {
				this.addActionMessage("该图书借出或已丢失，不能预约！");
				ServletActionContext.getRequest().setAttribute("appointment",
						appointmentService.load(appointment.getId()));
				return "reinput";
			}
		}
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
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
