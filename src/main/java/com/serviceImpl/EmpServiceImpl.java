package com.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.po.Emp;
import com.po.EmpWelfare;
import com.po.PageBean;
import com.po.Salary;
import com.po.Welfare;
import com.service.IEmpService;
import com.util.DaoMapperUtil;
@Service("EmpService")
@Transactional
public class EmpServiceImpl implements IEmpService {
	@Resource(name="DaoMapperUtil")
	public DaoMapperUtil daoMapperUtil;
	
	public DaoMapperUtil getDaoMapperUtil() {
		return daoMapperUtil;
	}

	public void setDaoMapperUtil(DaoMapperUtil daoMapperUtil) {
		this.daoMapperUtil = daoMapperUtil;
	}
	@Override
	public boolean save(Emp emp) {
		/* 添加员工 */
		int code=daoMapperUtil.empMapper.save(emp);
		if (code>0) {
			/* 给员工添加薪资 */
			//获取员工编号
			Integer eid=daoMapperUtil.empMapper.findMaxEid();
			//添加薪资
			Salary sal=new Salary(eid,emp.getEmoney());
			daoMapperUtil.salaryMapper.save(sal);			
			/* 给员工添加福利 */
			String[] wids=emp.getWids();
			if(wids!=null&&wids.length>0){
				for(int i=0;i<wids.length;i++){
					EmpWelfare ewf=new EmpWelfare(eid,new Integer(wids[i]));
					daoMapperUtil.empWelfareMapper.save(ewf);
				}
			}			
			return true;
		}
		return false ;
	}

	@Override
	public boolean delById(Integer eid) {
		/*删除员工薪资*/
		daoMapperUtil.salaryMapper.delByEid(eid);
		/* 删除员工福利 */
		daoMapperUtil.empWelfareMapper.delByEid(eid);
		/*删除员工*/
		int code=daoMapperUtil.empMapper.delById(eid);
		if (code>0) {
			return true;
		}
		return false;		
	}

	@Override
	public boolean update(Emp emp) {
		/*修改员工信息*/
		int code=daoMapperUtil.empMapper.update(emp);
		if (code>0) {
			/*修改员工薪资*/
			//修改薪资（1.原来薪资2.薪资修改）
			Salary oldSal=daoMapperUtil.salaryMapper.findByEid(emp.getEid());
			if (oldSal!=null&&oldSal.getEmoney()!=null) {
				if (oldSal.getEmoney()<emp.getEmoney()) {
					oldSal.setEmoney(emp.getEmoney());
					daoMapperUtil.salaryMapper.updateByEid(oldSal);
				}
			}else {//原来没有薪资
				Salary newSal=new Salary(emp.getEid(),emp.getEmoney());
				daoMapperUtil.salaryMapper.save(newSal);
			}
			/* 修改员工福利 */
			//1.删除原来的福利
			//获取原来的员工福利
			List<Welfare> lswf=daoMapperUtil.empWelfareMapper.findByEid(emp.getEid());
			if (lswf!=null&&lswf.size()>0) {
				//删除原来的福利
				daoMapperUtil.empWelfareMapper.delByEid(emp.getEid());
			}
			//2.添加新的福利
			String[] wids=emp.getWids();
			if(wids!=null&&wids.length>0){
				for(int i=0;i<wids.length;i++){
					EmpWelfare newEwf=new EmpWelfare(emp.getEid(),new Integer(wids[i]));
					daoMapperUtil.empWelfareMapper.save(newEwf);
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public Emp findById(Integer eid) {
		//获取员工对象
		Emp oldEmp=daoMapperUtil.empMapper.findById(eid);
		//获取员工薪资
		Salary oldSal=daoMapperUtil.salaryMapper.findByEid(eid);
		if (oldSal!=null&&oldSal.getEmoney()!=null) {
			oldEmp.setEmoney(oldSal.getEmoney());
		}
		//获取员工福利
		List<Welfare> lswf=daoMapperUtil.empWelfareMapper.findByEid(eid);
		if (lswf!=null&&lswf.size()>0) {
			String[] wids=new String[lswf.size()];
			for (int i = 0; i <lswf.size(); i++) {
				Integer wid=lswf.get(i).getWid();
				wids[i]=Integer.toString(wid);			
			}
			oldEmp.setWids(wids);
		}
		oldEmp.setWelfares(lswf);
		return oldEmp;
	}

	@Override
	public List<Emp> findPageAll(PageBean pb) {
		// TODO Auto-generated method stub
		return daoMapperUtil.empMapper.findPageAll(pb);
	}

	@Override
	public int findMaxRows() {
		// TODO Auto-generated method stub
		return daoMapperUtil.empMapper.findMaxRows();
	}

}
