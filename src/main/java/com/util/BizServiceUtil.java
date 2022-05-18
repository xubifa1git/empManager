package com.util;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.service.IDepService;
import com.service.IEmpService;
import com.service.IWelfareService;

@Service("BizService")
public class BizServiceUtil {
	@Resource(name="DepService")
	public IDepService depService;
	@Resource(name="EmpService")
	public IEmpService empService;
	@Resource(name="WelfareService")
	public IWelfareService welfareService;
	public IDepService getDepService() {
		return depService;
	}
	public void setDepService(IDepService depService) {
		this.depService = depService;
	}
	public IEmpService getEmpService() {
		return empService;
	}
	public void setEmpService(IEmpService empService) {
		this.empService = empService;
	}
	public IWelfareService getWelfareService() {
		return welfareService;
	}
	public void setWelfareService(IWelfareService welfareService) {
		this.welfareService = welfareService;
	}
	
}
