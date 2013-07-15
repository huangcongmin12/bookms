package com.dao;

import java.util.List;

import com.model.Amercement;

public interface AmercementDao {
	// 添加罚单
	public void add(Amercement amercement);

	// 删除罚单
	public void delete(Amercement amercement);

	// 修改罚单信息
	public void modify(Amercement amercement);

	// 加载罚单信息
	public Amercement load(int id);

	// 加载罚单信息列表
	public List<Amercement> list(int pageNow, int pageSize);

	// 按学生学号查找罚单
	public List<Amercement> findAmercementList(String studentNumber);

	// 按图书编号查找罚单
	public Amercement findAmercementByBookNumber(String bookNumber);

	// 获取总罚单数
	public long getAllAmercementCount();
}
