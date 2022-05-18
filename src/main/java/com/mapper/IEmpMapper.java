package com.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.po.Emp;
import com.po.PageBean;

@Service("empDao")
public interface IEmpMapper {
public int save(Emp emp);
public int delById(Integer eid);
public int update(Emp emp);
public Emp findById(Integer eid);
public List<Emp> findPageAll(PageBean pb);
public int findMaxRows();
//获取Id最大值
public int findMaxEid();
}
