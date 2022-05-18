package com.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.po.Dep;
import com.po.Emp;
import com.po.PageBean;
import com.po.Welfare;
import com.util.AJAXUtil;
import com.util.BizServiceUtil;

@Controller
public class EmpController {
	@Resource(name="BizService")
	public BizServiceUtil bizServiceUtil;

	public BizServiceUtil getBizServiceUtil() {
		return bizServiceUtil;
	}

	public void setBizServiceUtil(BizServiceUtil bizServiceUtil) {
		this.bizServiceUtil = bizServiceUtil;
	}
	@RequestMapping(value="save_Emp.do")
	public String save(HttpServletRequest request,HttpServletResponse response,Emp emp) {
		String realpath=request.getRealPath("/");
		 /************文件上传****************/
		   //获取文件上传的对象
		   MultipartFile multipartFile=emp.getPic();
		   if(multipartFile!=null&&!multipartFile.isEmpty()){
			   //获取文件上传名称
			   String fname=multipartFile.getOriginalFilename();
			   //更名
			   if(fname.lastIndexOf(".")!=-1){
				   //获取存在的后缀
				   String ext=fname.substring(fname.lastIndexOf("."));
				   //判断后缀格式
				   if(ext.equalsIgnoreCase(".jpg")){
					   String newfname=new Date().getTime()+ext;
					   //创建文件对象指定上传路径
					   File dostfile=new File(realpath+"/uppic/"+newfname);
					   //复制文件内容,上传
					   try {
						FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), dostfile);
						emp.setPhoto(newfname);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				   }
			   }
		   }
		 /************文件上传end****************/
		boolean flag=bizServiceUtil.empService.save(emp);
		if (flag) {
			String jsonStr=JSONObject.toJSONString(1);
			AJAXUtil.printJsonString(response, jsonStr);
		}else {
			String jsonStr=JSONObject.toJSONString(0);
			AJAXUtil.printJsonString(response, jsonStr);
		}
		return null;
	}
	@RequestMapping(value="delById_Emp.do")
	public String delById(HttpServletRequest request,HttpServletResponse response,Integer eid) {
		boolean flag=bizServiceUtil.empService.delById(eid);
		if (flag) {
			String jsonStr=JSONObject.toJSONString(1);
			AJAXUtil.printJsonString(response, jsonStr);
		}else {
			String jsonStr=JSONObject.toJSONString(0);
			AJAXUtil.printJsonString(response, jsonStr);
		}
		return null;
	}
	@RequestMapping(value="update_Emp.do")
	public String update(HttpServletRequest request,HttpServletResponse response,Emp emp) {
		//获取原来照片
		String oldphoto=bizServiceUtil.empService.findById(emp.getEid()).getPhoto();
		String realpath=request.getRealPath("/");
		 /************文件上传****************/
		   //获取文件上传的对象
		   MultipartFile multipartFile=emp.getPic();
		   if(multipartFile!=null&&!multipartFile.isEmpty()){
			   //获取文件上传名称
			   String fname=multipartFile.getOriginalFilename();
			   //更名
			   if(fname.lastIndexOf(".")!=-1){
				   //获取存在的后缀
				   String ext=fname.substring(fname.lastIndexOf("."));
				   //判断后缀格式
				   if(ext.equalsIgnoreCase(".jpg")){
					   String newfname=new Date().getTime()+ext;
					   //创建文件对象指定上传路径
					   File dostfile=new File(realpath+"/uppic/"+newfname);
					   //复制文件内容,上传
					   try {
						FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), dostfile);
						emp.setPhoto(newfname);
						//删除原来照片
						File oldfile=new File(realpath+"/uppic/"+oldphoto);
						if(oldfile.exists()&&!oldphoto.equalsIgnoreCase("default.jpg")){
							oldfile.delete();
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				   }
			   }
		   }else{
			   emp.setPhoto(oldphoto);
		   }
		 /************文件上传end****************/
		boolean flag=bizServiceUtil.empService.update(emp);
		if (flag) {
			String jsonStr=JSONObject.toJSONString(1);
			AJAXUtil.printJsonString(response, jsonStr);
		}else {
			String jsonStr=JSONObject.toJSONString(0);
			AJAXUtil.printJsonString(response, jsonStr);
		}
		return null;
	}
	@RequestMapping(value="findById_Emp.do")
	public String findById(HttpServletRequest request,HttpServletResponse response,Integer eid) {
		Emp oldEmp=bizServiceUtil.empService.findById(eid);
		PropertyFilter propertyFilter=AJAXUtil.filterProperts("birthday","pic");
		String jsonStr=JSONObject.toJSONString(oldEmp,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
		AJAXUtil.printJsonString(response, jsonStr);
		return null;
	}
	@RequestMapping(value="findDetail_Emp.do")
	public String findDetail(HttpServletRequest request,HttpServletResponse response,Integer eid){
		Emp oldEmp=bizServiceUtil.getEmpService().findById(eid);
		PropertyFilter propertyFilter=AJAXUtil.filterProperts("birthday","pic");
		String jsonstr=JSONObject.toJSONString(oldEmp,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
		AJAXUtil.printJsonString(response,jsonstr);  
		return null;
	  }
	@RequestMapping(value="findPageAll_Emp.do")
	public String findPageAll(HttpServletRequest request,HttpServletResponse response,Integer page, Integer rows){
		/****** 分页查询 ******/
		PageBean pb =  new PageBean();
		page = page == null || page < 1 ? pb.getPage() : page;
		rows = rows == null || rows < 1 ? pb.getRows() : rows;
		//获取员工集合
		pb.setPage(page);
		pb.setRows(rows);
		List<Emp> empPageList = bizServiceUtil.empService.findPageAll(pb);
		// 获取总行数
		int maxRows=bizServiceUtil.empService.findMaxRows();
		//按照easyUI的格式封装数据
		Map<String,Object> map= new HashMap<String,Object>();
		map.put("page", page);
		map.put("rows", empPageList);
		map.put("total", maxRows);
		PropertyFilter propertyFilter=AJAXUtil.filterProperts("birthday","pic");
		String jsonStr=JSONObject.toJSONString(map,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
		AJAXUtil.printJsonString(response, jsonStr);
		return null;
	}
	@RequestMapping(value="doinit_Emp.do")
	public String doinit(HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> map= new HashMap<String,Object>();
		List<Dep> lsdep=bizServiceUtil.depService.findAll();
		List<Welfare> lswf=bizServiceUtil.welfareService.findAll();
		map.put("lsdep", lsdep);
		map.put("lswf", lswf);
		PropertyFilter propertyFilter=AJAXUtil.filterProperts("birthday","pic");
		String jsonstr=JSONObject.toJSONString(map,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
		AJAXUtil.printJsonString(response,jsonstr);
		return null;
	}
	
}
