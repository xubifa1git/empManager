package com.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.po.EmpWelfare;
import com.po.Welfare;

@Service("empWelfareDao")
public interface IEmpWelfareMapper {
public int save(EmpWelfare ewf);
//通过员工编号删除福利
public int delByEid(Integer eid);
//通过员工编号查询福利
public List<Welfare> findByEid(Integer eid);
}
