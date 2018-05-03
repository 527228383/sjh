package com.sjh.login.service;

import java.util.List;

import com.sjh.login.entity.User;

public interface UserService {
	
	/**
	 * 根据 id 查询用户信息
	 * @param id
	 * @return
	 */
	User getUserInfo(String id);
	
	/**
	 * 查询用户列表
	 * @return
	 */
	List<User> queryUserList();
	
	/**
	 * 新建用户
	 * @param user
	 * @return
	 */
	int saveUserInfo(User user);
	
	/**
	 * 根据 id 删除用户信息
	 * @param id
	 * @return
	 */
	int deleteUserInfo(String id);
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	int updateUserInfo(User user);
}
