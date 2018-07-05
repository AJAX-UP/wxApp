package com.wx.service;


import com.common.redisCon.RedisUtil;
import com.wx.dao.BackUserDao;
import com.wx.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BackUserService {
	
	@Autowired
	private BackUserDao dao;
	
	@Autowired
	private RedisUtil redisUtil;
	
	// 添加后台用户
	public int addUser(User user) {
		
		int result = dao.insertUser(user);
		if (1 == result) {
			//redisUtil.Put("back_user." + user.getEmployId(),user);
			dao.insertUser(user);
		}
		return result;
	}
	// 修改用户密码
	public int modifyPassword(User user) {
		return dao.updatePassword(user);
	}
}	