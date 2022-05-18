package com.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.po.Welfare;
import com.service.IWelfareService;
import com.util.DaoMapperUtil;
@Service("WelfareService")
@Transactional
public class WelfareServiceImpl implements IWelfareService {
	@Resource(name="DaoMapperUtil")
	public DaoMapperUtil daoMapperUtil;
	
	public DaoMapperUtil getDaoMapperUtil() {
		return daoMapperUtil;
	}

	public void setDaoMapperUtil(DaoMapperUtil daoMapperUtil) {
		this.daoMapperUtil = daoMapperUtil;
	}

	@Override
	public List<Welfare> findAll() {
		// TODO Auto-generated method stub
		return daoMapperUtil.welfareMapper.findAll();
	}

}
