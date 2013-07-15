package com.dao;

import java.util.List;

import com.model.Appointment;

public interface AppointmentDao {
	// 添加预约
	public void add(Appointment appointment);

	// 删除预约
	public void delete(Appointment appointment);

	// 修改预约信息
	public void modify(Appointment appointment);

	// 加载预约信息
	public Appointment load(int id);

	// 加载预约信息列表
	public List<Appointment> list(int pageNow, int pageSize);

	// 按学生学号查找预约
	public List<Appointment> findAppointmentList(String studentNumber);

	// 获取总预约数
	public long getAllAppointmentCount();
}
