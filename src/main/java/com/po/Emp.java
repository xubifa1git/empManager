package com.po;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Emp implements Serializable{
private Integer eid;
private String ename;
private String sex;
private String address;
private Date birthday;
private String photo="default.jdp";
private Integer depid;
//与前台页面有关的属性
private String depname;//与部门有关
private String[] wids;//与福利有关，接收页面复选框的值
private List<Welfare> welfares;//展示时用到的福利集合
private Float emoney;//与薪资由观
private MultipartFile pic;
private String sdate;
public Emp() {
	super();
	// TODO Auto-generated constructor stub
}

public Emp(String ename, String sex, String address, Date birthday, String photo, Integer depid) {
	super();
	this.ename = ename;
	this.sex = sex;
	this.address = address;
	this.birthday = birthday;
	this.photo = photo;
	this.depid = depid;
}

public Integer getEid() {
	return eid;
}
public void setEid(Integer eid) {
	this.eid = eid;
}
public String getEname() {
	return ename;
}
public void setEname(String ename) {
	this.ename = ename;
}
public String getSex() {
	return sex;
}
public void setSex(String sex) {
	this.sex = sex;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public Date getBirthday() {
	return birthday;
}
public void setBirthday(Date birthday) {
	this.birthday = birthday;
}
public String getPhoto() {
	return photo;
}
public void setPhoto(String photo) {
	this.photo = photo;
}
public Integer getDepid() {
	return depid;
}
public void setDepid(Integer depid) {
	this.depid = depid;
}
public String getDepname() {
	return depname;
}
public void setDepname(String depname) {
	this.depname = depname;
}
public String[] getWids() {
	return wids;
}
public void setWids(String[] wids) {
	this.wids = wids;
}
public List<Welfare> getWelfares() {
	return welfares;
}
public void setWelfares(List<Welfare> welfares) {
	this.welfares = welfares;
}
public Float getEmoney() {
	return emoney;
}
public void setEmoney(Float emoney) {
	this.emoney = emoney;
}
public MultipartFile getPic() {
	return pic;
}
public void setPic(MultipartFile pic) {
	this.pic = pic;
}
public String getSdate() {
	sdate= new SimpleDateFormat("yyyy-MM-dd").format(birthday);
	return sdate;
}
public void setSdate(String sdate) throws ParseException {
	birthday=new SimpleDateFormat("yyyy-MM-dd").parse(sdate);
	this.sdate = sdate;
}
@Override
public String toString() {
	return "emp [eid=" + eid + ", ename=" + ename + ", sex=" + sex + ", address=" + address + ", birthday=" + birthday
			+ ", photo=" + photo + ", depid=" + depid + ", depname=" + depname + ", wids=" + Arrays.toString(wids)
			+ ", welfares=" + welfares + ", emoney=" + emoney + ", pic=" + pic + ", sdate=" + sdate + "]";
}

}
