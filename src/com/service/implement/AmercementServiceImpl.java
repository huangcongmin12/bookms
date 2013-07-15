package com.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dao.AmercementDao;
import com.model.Amercement;
import com.service.AmercementService;

@Service
@Scope("prototype")
@Transactional
public class AmercementServiceImpl implements AmercementService {

	@Autowired
	private AmercementDao amercementDao;

	// 添加罚单
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void add(Amercement amercement) {
		amercementDao.add(amercement);
	}

	// 删除罚单
	@Transactional
	public boolean delete(Amercement amercement) {
		if (null != amercementDao.load(amercement.getId())) {
			amercementDao.delete(amercement);
			return true;
		} else {
			return false;
		}
	}

	// 修改罚单信息
	@Transactional
	public boolean modify(Amercement amercement) {
		if (null != amercementDao.load(amercement.getId())) {
			amercementDao.modify(amercement);
			return true;
		} else {
			return false;
		}
	}

	// 加载罚单信息
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Amercement load(int id) {
		Amercement amercement = amercementDao.load(id);
		if (null != amercement)
			return amercement;
		else
			return null;
	}

	// 加载罚单信息列表
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Amercement> list(int pageNow, int pageSize) {
		if (this.getAllAmercementCount() == 0)
			return null;
		else
			return amercementDao.list(pageNow, pageSize);

	}

	// 按学生学号查找罚单
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Amercement> findAmercementList(String studentNumber) {
		List<Amercement> amercements = amercementDao
				.findAmercementList(studentNumber);
		if (null != amercements)
			return amercements;
		else
			return null;
	}

	// 按图书编号查找罚单
	public Amercement findAmercementByBookNumber(String bookNumber) {
		Amercement amercement = amercementDao
				.findAmercementByBookNumber(bookNumber);
		return amercement;
	}

	// 获取总罚单数
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public long getAllAmercementCount() {
		return amercementDao.getAllAmercementCount();
	}

}
