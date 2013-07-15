package com.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ManagerDao;
import com.model.Manager;
import com.service.ManagerService;

@Service
@Scope("prototype")
@Transactional
public class ManagerServiceImpl implements ManagerService {
	
	@Autowired
	private ManagerDao managerDao;

	//管理员登录
	public boolean login(Manager manager){
		if(managerDao.login(manager))
			return true;
		else
			return false;
	}
	
	//添加管理员
	@Transactional(readOnly=false,propagation = Propagation.REQUIRED,rollbackFor=Throwable.class)
	public boolean add(Manager manager) {
		if(null==managerDao.findManager(manager.getManagerID())){
			managerDao.add(manager);
			return true;
		}
		else{
			return false;
		}
	}

	//删除管理员
	@Transactional
	public boolean delete(Manager manager) {
		if(null!=this.load(manager.getId())){
			managerDao.delete(manager);
			return true;
		}
		else{
			return false;
		}
	}

	//修改管理员
	@Transactional
	public boolean modify(Manager manager) {
		Manager dbm = managerDao.findManager(manager.getManagerID());
		if(manager.getId()==dbm.getId()){
			managerDao.modify(manager);
			return true;
		}
		else{
			return false;
		}
	}

	//加载管理员信息
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Manager load(int id) {
		if(null!=managerDao.load(id))
			return managerDao.load(id);
		else
			return null;
	}

	//加载管理员信息列表
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<Manager> list(int pageNow, int pageSize) {
		if(this.getAllManagerCount()==0){
			return null;
		}
		else{
			return managerDao.list(pageNow, pageSize);
		}
	}

	//按管理员名称查找
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Manager findManager(String managerID) {
		if(null!=managerDao.findManager(managerID)){
			return managerDao.findManager(managerID);
		}
		else{
			return null;
		}
	}

	//获取管理员总数
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public long getAllManagerCount() {
		return managerDao.getAllManagerCount();
	}
	
}
