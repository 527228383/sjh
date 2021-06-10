package com.sjh.login.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.sjh.login.dao.UserDao;
import com.sjh.login.entity.User;
import com.sjh.login.service.UserService;

/**
 *
 */
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	/**
	 * 根据 id 查询用户信息
	 */
	@Override
	@Cacheable(value = "getUserInfo", keyGenerator = "keyGenerator")
	public User getUserInfo(String id) {
		return userDao.getUserInfo(id);
	}

	/**
	 * 查询用户列表
	 */
	@Override
	@Cacheable(value = "queryUserList", keyGenerator = "keyGenerator")
	public List<User> queryUserList() {
		return userDao.queryUserList();
	}

	/**
	 * 新建用户
	 */
	@Override
	public int saveUserInfo(User user) {
		return userDao.saveUserInfo(user);
	}
	
	/**
	 * 根据 id 删除用户信息
	 */
	@Override
	@CacheEvict(value = "getUserInfo", key = "'com.sjh.login.service.impl.UserServiceImpl.getUserInfo.'+#id")
	public int deleteUserInfo(String id) {
		return userDao.deleteUserInfo(id);
	}

	/**
	 * 更新用户信息
	 */
	@Override
	@Caching(evict = {
			@CacheEvict(value = "getUserInfo", key = "'com.sjh.login.service.impl.UserServiceImpl.getUserInfo.'+#user.id"),
			@CacheEvict(value = "queryUserList", key = "'com.sjh.login.service.impl.UserServiceImpl.queryUserList'")
	})
	public int updateUserInfo(User user) {
		return userDao.updateUserInfo(user);
	}
}
