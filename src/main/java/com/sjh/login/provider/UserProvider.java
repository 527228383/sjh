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
			SELECT("id, user_name, password, salt");
			FROM("t_user");
			if (StringUtils.isNotBlank(id)) {
				WHERE("id = #{id}");
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
			INSERT_INTO("t_user");
			VALUES("user_name", "#{userName}");
			VALUES("password", "#{passWord}");
			VALUES("salt", "#{salt}");
			
		}}.toString();
	}
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	public String updateUserInfo(User user) {
		return new SQL() {{
			UPDATE("t_user");
			if (StringUtils.isNotBlank(user.getUserName())) {
				SET("user_name = #{userName}");
			}
			if (StringUtils.isNotBlank(user.getPassWord())) {
				SET("password = #{passWord}");
			}
			if (StringUtils.isNotBlank(user.getSalt())) {
				SET("salt = #{salt}");
			}
			WHERE("id = #{id}");
		}}.toString();
	}
}