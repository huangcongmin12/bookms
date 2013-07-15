package com.service;

import java.util.List;

import com.model.Manager;

public interface ManagerService {
		
		//管理员登录
		public boolean login(Manager manager);
	    //添加管理员
		public boolean add(Manager manager);
		//删除管理员
		public boolean delete(Manager manager);
		//修改管理员
		public boolean modify(Manager manager);
		//加载管理员信息
		public Manager load(int id);
		//加载管理员信息列表
		public List<Manager> list(int pageNow,int pageSize);
		//按管理员名称查找
		public Manager findManager(String managerID);
		//获取管理员总数
		public long getAllManagerCount();
}
