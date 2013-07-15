package com.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.model.Manager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.ManagerService;
import com.util.Encrypt;
import com.util.Page;

@Scope("prototype")
@Controller("managerAction")
@SuppressWarnings("serial")
public class ManagerAction extends ActionSupport {

	@Autowired
	private ManagerService managerService;
	private Manager manager;
	private int pageNow = 1;
	private int pageSize = 12;
	private String oldPassword;
	private String verifycode;

	// 管理员登录
	public String login() throws Exception {
		String rand = (String) ActionContext.getContext().getSession()
				.get("rand");
		if (!rand.equals(this.getVerifycode())) {
			this.addActionError("验证码不正确！");
			return ERROR;
		}
		manager.setPassword(Encrypt.encodeMD5(manager.getPassword()));
		if (managerService.login(manager)) {
			Manager manager1 = managerService.findManager(manager.getManagerID());
			ActionContext.getContext().getSession().put("manager", manager1);
			return SUCCESS;
		} else {
			this.addActionError("用户名或密码不正确！");
			return ERROR;
		}

	}

	// 退出登录
	public String loginOut() throws Exception {
		ActionContext.getContext().getSession().remove("manager");
		return SUCCESS;
	}

	// 新增管理员
	public String add() throws Exception {
		if (manager.getPassword().trim().equals("")
				|| manager.getManagerID().trim().equals("")) {
			this.addActionMessage("管理员ID或密码不能为空！");
			return INPUT;
		} else {
			manager.setPassword(Encrypt.encodeMD5(manager.getPassword()));
			if (managerService.add(manager)) {
				this.addActionMessage("添加管理员成功！！");
				return SUCCESS;
			} else {
				this.addActionMessage("管理员ID已存在！");
				return INPUT;
			}
		}
	}

	// 删除管理员
	public String delete() throws Exception {
		if (managerService.delete(manager))
			return SUCCESS;
		else
			return ERROR;
	}

	// 管理员更新视图
	public String updateView() throws Exception {
		Manager m = managerService.load(manager.getId());
		ServletActionContext.getRequest().setAttribute("manager", m);
		return SUCCESS;
	}

	// 管理员更新
	public String update() throws Exception {
		if (manager.getPassword().trim().equals("")
				|| manager.getManagerID().trim().equals("")) {
			return ERROR;
		} else {
			manager.setPassword(Encrypt.encodeMD5(manager.getPassword()));
			if (managerService.modify(manager)) {
				return SUCCESS;
			} else {
				return ERROR;
			}
		}
	}

	// 普通管理员修改密码
	public String modify() throws Exception {
		Manager m = managerService.load(manager.getId());
		if (m.getPassword().equals(Encrypt.encodeMD5(this.getOldPassword()))) {
			manager.setPassword(Encrypt.encodeMD5(manager.getPassword()));
			if (managerService.modify(manager)) {
				return SUCCESS;
			} else {
				return ERROR;
			}
		} else {
			return ERROR;
		}
	}

	// 按managerID查找
	public String find() throws Exception {
		Manager manager1 = new Manager();
		manager1 = managerService.findManager(manager.getManagerID());
		if (manager1 != null) {
			ServletActionContext.getRequest().setAttribute("manager", manager1);
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	// 加载管理员信息
	public String load() throws Exception {

		Manager m = managerService.load(manager.getId());
		if (m != null) {
			ServletActionContext.getRequest().setAttribute("manager", m);
			return SUCCESS;
		} else
			return ERROR;
	}

	// 管理员列表
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String list() throws Exception {
		Page page = new Page(pageNow, pageSize,
				(int) managerService.getAllManagerCount());
		List<Manager> managers = managerService.list(pageNow, pageSize);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("managerList", managers);
		request.put("page", page);
		return SUCCESS;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
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

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getVerifycode() {
		return verifycode;
	}

	public void setVerifycode(String verifycode) {
		this.verifycode = verifycode;
	}

}
