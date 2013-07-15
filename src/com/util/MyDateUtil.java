package com.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class MyDateUtil {
	
	 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
	
	 //获取当前时间
	 public String getNowTime(){
		 return df.format(new Date());
	 }
	 
	//当前时间加n天
	public  String  getEndTime(int  n){ 
        Calendar   rightNow   =   Calendar.getInstance(); 
        rightNow.add(Calendar.DAY_OF_MONTH,+n); 
        return   df.format(rightNow.getTime()); 
	} 

    //计算两个日期相隔的天数  
    public  int daysBetweenTwoDate(String  firstString,String secondString)   {  
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
        Date firstDate=null;  
        Date secondDate=null;  
        try{  
            firstDate = df.parse(firstString);  
            secondDate=df.parse(secondString);  
        }  
        catch(Exception   e)   {  
            //日期型字符串格式错误  
        }  
        int nDay=(int)((secondDate.getTime()-firstDate.getTime())/(24*60*60*1000));  
        return nDay;  
    }  
    
    @Test
    public void test(){
    	System.out.println(this.daysBetweenTwoDate("2012-08-23 ",this.getNowTime()));
    }
}
