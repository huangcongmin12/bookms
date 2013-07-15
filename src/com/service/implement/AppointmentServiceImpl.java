package com.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dao.AppointmentDao;
import com.model.Appointment;
import com.service.AppointmentService;

@Service
@Scope("prototype")
@Transactional
public class AppointmentServiceImpl implements AppointmentService {
	
	@Autowired
	private AppointmentDao appointmentDao;
	
	//添加预约
	@Transactional(readOnly=false,propagation = Propagation.REQUIRED,rollbackFor=Throwable.class)
	public void add(Appointment appointment){
		appointmentDao.add(appointment);
	}
	
	//删除预约
	@Transactional
	public void delete(Appointment appointment){
		if(null!=this.load(appointment.getId())){
			appointmentDao.delete(appointment);
		}
	}
	
	//修改预约信息
	@Transactional
	public boolean modify(Appointment appointment){
		if(null!=this.load(appointment.getId())){
			appointmentDao.modify(appointment);
			return true;
		}
		else
			return false;
	}
	
	//加载预约信息
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Appointment load(int id){
		Appointment appointment = appointmentDao.load(id);
		if(null!=appointment)
			return appointment;
		else
			return null;
	}
	
	//加载预约信息列表
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<Appointment> list(int pageNow,int pageSize){
		List<Appointment> appointments = appointmentDao.list(pageNow, pageSize);
		if(appointments.size()>0)
			return appointments;
		else
			return null;
	}
	
	//按学生学号查找预约
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<Appointment> findAppointmentList(String studentNumber){
		List<Appointment> appointments = appointmentDao.findAppointmentList(studentNumber);
		if(appointments.size()>0)
			return appointments;
		else
			return null;
	}
	
	//获取总预约数
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public long getAllAppointmentCount() {
		return appointmentDao.getAllAppointmentCount();
	}
}
