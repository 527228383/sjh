package com.sjh.login.controller;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sjh.login.entity.User;
import com.sjh.login.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * restful 风格的4种基本请求方式
 *    GET: 查
 *   POST: 增
 *    PUT: 改
 * DELETE: 删
 * 
 * 前端调用相应的请求时，GET 和 POST 和之前一样
 * 而 PUT 和 DELETE 请求需要用 _method 指定真正的请求参数，即把post请求转化为对应的请求,即可将表单的数据提交到后台
 *    PUT: <input type="hidden" name="_method" value="PUT" />
 * DELETE: <input type="hidden" name="_method" value="DELETE" />
 * 
 * swagger2 相关属性
 * 
 * @Api：用在类上，说明该类的作用
 * @ApiOperation：用在方法上，说明方法的作用
 * @ApiImplicitParams：用在方法上包含一组参数说明
 * @ApiImplicitParam：用在 @ApiImplicitParams 注解中，指定一个请求参数的各个方面
 * 		name：               参数名
 * 		value：            参数的意思
 * 		required：   参数是否必须传
 * 		paramType：参数放在哪个地方
 *         · header --> 请求参数的获取：@RequestHeader
 * 		   · query  --> 请求参数的获取：@RequestParam
 * 		   · path（用于restful接口）--> 请求参数的获取：@PathVariable
 * 		   · body（不常用）
 *         · form（不常用）
 *      dataType：   参数类型
 *	@ApiResponses：用于表示一组响应
 *	@ApiResponse：   用在@ApiResponses中，一般用于表达一个错误的响应信息
 *		code：               数字，例如400
 *		message：      信息，例如"请求参数没填好"
 *		response：   抛出异常的类
 *	@ApiModel：描述一个Model的信息（这种一般用在post创建的时候，使用@RequestBody这样的场景，
 *		请求参数无法使用@ApiImplicitParam注解进行描述的时候）
 *	@ApiModelProperty：描述一个model的属性
 *     
 *  注意1：@ApiImplicitParam 和 @ApiParam 方式均能指定参数规则。
 *  注意2：使用 @ApiImplicitParam 的时候，需要指定 paramType。
 *  接口文档访问地址: http://{hostname}:{port}/swagger-ui.html
 *     
 * @author ex-sujh
 * @date 2018年4月26日16:51:39
 * 
 */
@Api(value = "用户模块")
@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private UserService UserService;
	
	/**
	 * 获取单个用户
	 * 
	 * 注意: paramType需要指定为 path, 不然不能正常获取参数
	 * @ApiImplicitParam(name = "id", value = "用户id", required = true, paramType = "path", dataType = "String")
	 * @param id 
	 * @return
	 */
	@ApiOperation(value = "获取单个用户", notes = "传入id获取单个用户")
	@ApiResponses({
		@ApiResponse(code = 400, message = "请求参数没填好"),
		@ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
	})
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User getUserInfo(@ApiParam(value = "用户id", required = true) @PathVariable(value = "id") String id) {
		return UserService.getUserInfo(id);
		
	}
	
	/**
	 * 获取用户列表
	 * @return
	 */
	@ApiOperation(value = "获取用户列表", notes = "获取用户列表")
	@ApiResponses({
		@ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
	})
	@GetMapping(value = "")
	//@RequestMapping(value = "", method = RequestMethod.GET)
	public List<User> queryUserList() {
		return UserService.queryUserList();
	}
	
	/**
	 * 新建用户
	 * 
	 * 注意: paramType需要指定为 body, 不然不能正常获取参数
	 * @ApiImplicitParam(name = "user", value = "用户数据", required = true, paramType = "body", dataType = "User")
	 * @param user
	 * @return
	 */
	@ApiOperation(value = "新建用户", notes = "新建一个用户")
	@ApiResponses({
		@ApiResponse(code = 400, message = "请求参数没填好"),
		@ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
	})
	@PostMapping(value = "")
	//@RequestMapping(value = "", method = RequestMethod.POST)
	public String saveUserInfo(@ApiParam(value = "用户数据", required = true) @RequestBody User user) {
		int row = UserService.saveUserInfo(user);
		if (row > 0) return "success 新建user: " + user;
			else return "fail 新建user: " + user;
	}
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "删除用户", notes = "通过用户id删除用户")
	@ApiImplicitParam(name = "id", value = "用户id", required = true, paramType = "path", dataType = "String")
	@ApiResponses({
		@ApiResponse(code = 400, message = "请求参数没填好"),
		@ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
	})
	@DeleteMapping(value = "/{id}")
	//@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String deleteUserInfo(@PathVariable(value = "id") String id) {
		int row = UserService.deleteUserInfo(id);
		if (row > 0) return "success 删除 user: " + id;
			else return "fail 删除 user id: " + id;
	}
	
	/**
	 * 更新用户
	 * @param user
	 * @return
	 */
	@ApiOperation(value = "更新用户", notes = "更新用户")
	@ApiImplicitParam(name = "user", value = "用户数据", required = true, paramType = "body", dataType = "User")
	@ApiResponses({
		@ApiResponse(code = 400, message = "请求参数没填好"),
		@ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
	})
	@PutMapping(value = "")
	//@RequestMapping(value = "", method = RequestMethod.PUT)
	public String updateUserInfo(@RequestBody User user) {
		int row = UserService.updateUserInfo(user);
		if (row > 0) return "success 更新 user: " + user;
			else return "fail 更新 user: " + user;
	}

	public static void main(String[] args) {

	}
}
