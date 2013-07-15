package com.service;

import java.util.List;

import com.model.Borrow;

public interface BorrowService {
	//添加借书单
	public void add(Borrow borrow);
	//删除借书单
	public boolean delete(Borrow borrow);
	//修改借书单信息
	public boolean modify(Borrow borrow);
	//加载借书单信息
	public Borrow load(int id);
	//加载借书单信息列表
	public List<Borrow> list(int pageNow,int pageSize);
	//按学生学号查找借书单
	public List<Borrow> findBorrowByStudetNumber(String studentNumber);
	//按图书编号查找借阅记录
	public Borrow findBorrowByBookNumber(String bookNumber);
	//按学生学号查找借阅历史
	public List<Borrow> findBorrowByStudetNumberHistory(String studentNumber);
	//加载催还列表,离截止日期小于3天，或者过期未还
	public List<Borrow> callBack(String studentNumber);
	//按学生学号查找未图书的还借阅记录
	public List<Borrow> findBorrowByStudetNumberOfNoReturn(String studentNumber);
	//获取借书单总数
	public long getAllBorrowCount();
}
