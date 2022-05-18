package com.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.serializer.PropertyFilter;

public class AJAXUtil {
	/**
	 * 输出相应到客户端
	 * @param response
	 * @param str
	 */
	public static void printJsonString(HttpServletResponse response,String str) {
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter out=response.getWriter();
			out.print(str);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	/***
	 * 过滤属性
	 * */
	public static PropertyFilter filterProperts(final String...propNames){
		PropertyFilter propertyFilter=new PropertyFilter() {			
			public boolean apply(Object arg0, String propertyName, Object arg2) {
				for (String pname : propNames) {
					if(propertyName.equals(pname)){
						return false;//过滤
					}
				}
				return true;
			}
		};		
		return propertyFilter;
	}
}
