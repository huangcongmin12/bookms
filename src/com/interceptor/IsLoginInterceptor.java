package com.interceptor;

import java.util.Map;

import com.model.Manager;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class IsLoginInterceptor extends AbstractInterceptor {

	@SuppressWarnings("rawtypes")
	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		Map session = actionInvocation.getInvocationContext().getSession();
		Manager manager = (Manager)session.get("manager");
		if(null!=manager){
			return actionInvocation.invoke();
		}
		else{
			ActionSupport action= (ActionSupport)actionInvocation.getAction();
			action.addActionError("Sorry！请先登录！！");
			return Action.LOGIN;
		}
	}

}
