package com.wx.web;

import com.common.jsonAnalysis.JsonParameterAnalysis;
import com.common.util.StringUtil;
import com.wx.dto.ResponseResult;
import com.wx.entity.User;
import com.wx.service.BackUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;


/**
 * 
 * @author wesion 后台用户Controller
 *
 */
@RestController
@RequestMapping("/backuser")
public class BackUserController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BackUserService service;
	
	@Autowired
	private JsonParameterAnalysis analysis;

	/**
	 * 添加后台用户
	 * 
	 * @param user:
	 *            后台管理用户
	 * @return responseResult: 响应结果 {code = 1 , message = "成功" }
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult addUser(@RequestBody User user) {

		logger.info("--info--user:" + user.getUserName());
		logger.debug("--debug--user:" + user.getUserName());

		if (1 == service.addUser(user)) {
			return new ResponseResult();
		}

		return new ResponseResult(0, "失败");
	}

	/**
	 * 添加后台用户
	 *
	 *            后台管理用户
	 * @return responseResult: 响应结果 {code = 1 , message = "成功" }
	 */
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseBody
	public ResponseResult addUser() {

		return new ResponseResult(0, "失败");
	}



	/**
	 * 后台用户修改密码
	 *
	 * @return responseResult: 响应结果 {code = 1 , message = "成功" }
	 */
	@RequestMapping(value = "/modify/password", method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult modifyPassword(@RequestBody User user) {

		if (StringUtil.isBlank(user.getPassword()) || user.getId() == null) {
			return new ResponseResult(0, "id 和 密码不能为空");
		}

		if (1 == service.modifyPassword(user)) {
			return new ResponseResult();
		}
		return new ResponseResult(0, "失败");
	}
	
	/**
	 *  前端json参数解析
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@RequestMapping(value = "/analysis/json", method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult analysisJson(@RequestBody String jsonParam) throws InstantiationException, IllegalAccessException, InvocationTargetException {
		
		logger.info("---入参：---" + jsonParam);
		
		Map<String,Object> paramMap =  analysis.parameterAndBeanAnalysis(jsonParam,"user");
		
		
		System.out.println("----keyy----: " + (paramMap.get("keyy")).toString());
		System.out.println("----keyy.Password----: " + ((User) paramMap.get("keyy")).getPassword());
		
		return new ResponseResult();
	}
}
