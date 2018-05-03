package com.sjh.login.provider;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import com.sjh.login.entity.User;

/**
 * 动态 sql
 * @author ex-sujh
 * @date 2018年4月27日10:35:21
 */
public class UserProvider {

	/**
	 * 根据 id 查询用户信息
	 * @param id
	 * @return
	 */
	public String gerUserInfo(String id) {
		return new SQL() {{
			SELECT("ID, USER_NAME, PASSWORD, SALT");
			FROM("USER");
			if (StringUtils.isNotBlank(id)) {
				WHERE("ID = #{id}");
			} else {
				WHERE("1 = 2");
			}
		}}.toString();
	}
	
	/**
	 * 新建用户
	 * @param user
	 * @return
	 */
	public String saveUserInfo(User user) {
		return new SQL() {{
			INSERT_INTO("USER");
			VALUES("USER_NAME", "#{userName}");
			VALUES("PASSWORD", "#{passWord}");
			VALUES("SALT", "#{salt}");
			
		}}.toString();
	}
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	public String updateUserInfo(User user) {
		return new SQL() {{
			UPDATE("USER");
			if (StringUtils.isNotBlank(user.getUserName())) {
				SET("USER_NAME = #{userName}");
			}
			if (StringUtils.isNotBlank(user.getPassWord())) {
				SET("PASSWORD = #{passWord}");
			}
			if (StringUtils.isNotBlank(user.getSalt())) {
				SET("SALT = #{salt}");
			}
			WHERE("ID = #{id}");
		}}.toString();
	}
}