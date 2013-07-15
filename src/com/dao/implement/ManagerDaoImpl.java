package com.dao.implement;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.dao.ManagerDao;
import com.model.Manager;

@Repository
@Scope("singleton")
public class ManagerDaoImpl extends BaseDao implements ManagerDao {
	
	//管理员登录
	public boolean login(Manager manager){
		Session session = getSession();
		Query query = session.createQuery("from Manager m where m.managerID=:mID");
		query.setString("mID", manager.getManagerID());
		if(query.list().size()>0){
			Manager queryManager = (Manager)query.list().get(0);
			if(queryManager.getPassword().equals(manager.getPassword())){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}
	
	//添加管理员
	public void add(Manager manager) {
		this.getHibernateTemplate().save(manager);
	}

	//删除管理员
	public void delete(Manager manager) {
		this.getHibernateTemplate().delete(this.getHibernateTemplate().load(Manager.class, manager.getId()));
	}

	//修改管理员
	public void modify(Manager manager) {
		this.getHibernateTemplate().merge(manager);
	}

	//加载管理员信息
	public Manager load(int id) {
		return this.getHibernateTemplate().get(Manager.class, id);
	}

	//加载管理员信息列表
	@SuppressWarnings("unchecked")
	public List<Manager> list(int pageNow, int pageSize) {
		Session session = getSession();
		Query query = session.createQuery("from Manager");
		query.setFirstResult((pageNow-1)*pageSize);
		query.setMaxResults(pageSize);
		return query.list();
	}

	//按管理员名称查找
	@SuppressWarnings("unchecked")
	public Manager findManager(String managerID) {
		List<Manager> list = this.getHibernateTemplate().find("from Manager m where m.managerID=?",managerID);
		if(list.size()>0){
			return (Manager)list.get(0);
		}
		else{
			return null;
		}
	}

	//获取管理员总数
	public long getAllManagerCount() {
		return (Long) getHibernateTemplate().find("select count(*) from Manager").iterator().next();
	}
}
