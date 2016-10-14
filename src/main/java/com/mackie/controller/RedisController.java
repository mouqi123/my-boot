package com.mackie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.alibaba.fastjson.JSONObject;
import com.mackie.domain.UserDO;

@RestController
public class RedisController {
	
	@Autowired
	private RedisTemplate<String,UserDO> redisTemplate;
	
	@RequestMapping("redisTest")
	public UserDO redisTest() {
		UserDO user = new UserDO();
		user.setUserName("mouqi123");
		System.out.println(JSONObject.toJSON(user));
		redisTemplate.opsForValue().set("mouqi", user);
		return redisTemplate.opsForValue().get("mouqi");
	}
	
}
