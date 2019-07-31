package com.sjh.login.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.sjh.login.entity.User;
import com.sjh.login.provider.UserProvider;

/** 
* 动态 sql 注解
* @InsertProvider
* @UpdateProvider
* @DeleteProvider
* @SelectProvider
* 		  type：  指定对应的class
* 		method: 对应的方法名
*/
public interface UserDao {
	
	/**
	 * 根据 id 查询用户信息
	 * @param id
	 * @return
	 */
	@SelectProvider(type = UserProvider.class, method = "gerUserInfo")
	User getUserInfo(@Param("id") String id);
	
	/**
	 * 查询用户列表
	 * @return
	 */
	@Select("SELECT id,user_name,password,salt FROM t_user")
	List<User> queryUserList();
	
	/**
	 * 新建用户
	 * @param user
	 * @return
	 */
	@InsertProvider(type = UserProvider.class, method = "saveUserInfo")
	int saveUserInfo(User user);
	
	/**
	 * 根据 id 删除用户信息
	 * @param id
	 * @return
	 */
	@Delete("DELETE FROM t_user WHERE id = #{id}")
	int deleteUserInfo(@Param("id") String id);
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	@UpdateProvider(type = UserProvider.class, method = "updateUserInfo")
	int updateUserInfo(User user);
}
