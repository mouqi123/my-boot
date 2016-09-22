package com.mackie.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;
import com.mackie.domain.UserDO;

@Repository
public class UserDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public UserDO queryUserOne(int userId) {
		return jdbcTemplate.query("select * from t_user where user_id = ?", new Object[] { userId }, (rs) -> {
			UserDO user = new UserDO();
			if (rs.next()) {
				user.setUserName(rs.getString("user_name"));
				user.setPhoneNumber(rs.getString("phone_number"));
				JSONObject.toJSONString(user);
			}
			return user;
		});
	}
}
