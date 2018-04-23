package com.kedacom.demo.model;

import java.io.Serializable;

/**
 * 用户实体类
 * @author 钱其清
 */
public class User implements Serializable {
	//序列化ID
	private static final long serialVersionUID = -5809782578272943999L;

	//用户id
	private Integer id;

	//用户名
	private String name;

	//密码
	private String password;

	//邮箱
	private String email;

	//电话
	private String phone;

	//头像服务器地址
	private String photo;

	//分组
	private Integer group;

	//用户角色
	private Integer role;

	//在线状态
	private Integer status = 0;

	//是否被选中，非数据库字段
	private boolean selected;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Integer getGroup() {
		return group;
	}

	public void setGroup(Integer group) {
		this.group = group;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public User(){}
	
	public User(String name, String password){
		this.name = name;
		this.password = password;
	}

}
