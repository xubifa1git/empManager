package com.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.po.Dep;
import com.service.IDepService;
import com.util.DaoMapperUtil;
@Service("DepService")
@Transactional
public class DepServiceImpl implements IDepService {
	@Resource(name="DaoMapperUtil")
	public DaoMapperUtil daoMapperUtil;
	
	public DaoMapperUtil getDaoMapperUtil() {
		return daoMapperUtil;
	}

	public void setDaoMapperUtil(DaoMapperUtil daoMapperUtil) {
		this.daoMapperUtil = daoMapperUtil;
	}

	@Override
	public List<Dep> findAll() {
		// TODO Auto-generated method stub
		return daoMapperUtil.depMapper.findAll();
	}

}
