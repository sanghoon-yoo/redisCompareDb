package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.example.demo.User.User;

/**
 * Redis 서버가 띄어져 있는 경우 정상적으로 서버가 기동 되며
 * 그렇지 않으면 띄어지지 않음. 
 * @author jjjwodls
 *
 */
@Component
public class RedisComponent implements ApplicationRunner {

	@Autowired
	private StringRedisTemplate redisTemplate;
	
	@Autowired
	private HomeDao dao;
	
	/**
	 * 서버 수행 시 Redis에 값을 셋팅해준다.
	 */
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		ValueOperations<String, String> values = redisTemplate.opsForValue();
		List<User> list = dao.getAllList();
		for(User user : list) {
			values.set(new StringBuilder().append("id").append(user.getId()).toString(), user.getCustomer());
		}
		
	}
	
	
}
