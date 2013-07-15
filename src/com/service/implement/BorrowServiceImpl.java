package com.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dao.BorrowDao;
import com.model.Borrow;
import com.service.BorrowService;

@Service
@Scope("prototype")
@Transactional
public class BorrowServiceImpl implements BorrowService{
	
	@Autowired
	private BorrowDao borrowDao;
	
	//添加借书单
	@Transactional(readOnly=false,propagation = Propagation.REQUIRED,rollbackFor=Throwable.class)
	public void add(Borrow borrow){
		borrowDao.add(borrow);
	}
	
	//删除借书单
	@Transactional
	public boolean delete(Borrow borrow){
		if(null!=this.load(borrow.getId())){
			borrowDao.delete(borrow);
			return true;
		}
		else{
			return false;
		}
	}
	
	//修改借书单信息
	@Transactional
	public boolean modify(Borrow borrow){
		if(null!=this.load(borrow.getId())){
			borrowDao.modify(borrow);
			return true;
		}
		else{
			return false;
		}
	}
	
	//加载借书单信息
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Borrow load(int id){
		Borrow borrow = borrowDao.load(id);
		if(null!=borrow)
			return borrow;
		else
			return null;
		
	}
	
	//加载借书单信息列表
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<Borrow> list(int pageNow,int pageSize){
		if(this.getAllBorrowCount()==0){
			return null;
		}
		else{
			return borrowDao.list(pageNow, pageSize);
		}
	}
	
	//按学生学号查找所有借书单
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<Borrow> findBorrowByStudetNumber(String studentNumber){
		return borrowDao.findBorrowByStudetNumber(studentNumber);
	}
	
	//按图书编号查找借阅记录
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Borrow findBorrowByBookNumber(String bookNumber){
		Borrow borrow = borrowDao.findBorrowByBookNumber(bookNumber);
		return borrow;
	}
	
	//获取借书单总数
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public long getAllBorrowCount(){
		return borrowDao.getAllBorrowCount();
		
	}

	//按学生学号查找借阅历史
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<Borrow> findBorrowByStudetNumberHistory(String studentNumber) {
		return borrowDao.findBorrowByStudetNumberHistory(studentNumber);
	}
	
	//按学生学号查找未图书的还借阅记录
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<Borrow> findBorrowByStudetNumberOfNoReturn(String studentNumber){
		return borrowDao.findBorrowByStudetNumberOfNoReturn(studentNumber);	
	}
	
	//加载催还列表,离截止日期小于3天，或者过期未还
	public List<Borrow> callBack(String studentNumber){
		return borrowDao.callBack(studentNumber);
	}
	
}
