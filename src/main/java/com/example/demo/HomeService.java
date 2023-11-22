package com.example.demo;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.User.User;

/**
 * REDISTEST
user name : TESTDATABASE
pw : admin
 * @author jjjwo
 *
 */
@Service
public class HomeService {
	
	@Autowired
	private HomeDao dao;
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	public ModelAndView testRedisData() {
		
		ArrayList<User> list = (ArrayList<User>) dao.getAllList();
		int totalSize = list.size();
		ModelAndView mv = new ModelAndView();
		ArrayList<User> subList = new ArrayList<>();
		mv.addObject("listSize", totalSize);
		for(int i = 0 ; i < 10; i++) {
			subList.add(list.get(i));
		}
		mv.addObject("list", subList);
		return mv;
	}
	
	public ModelAndView testRanDomData() {
		
		User user = dao.getRanDomId();
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", user);
		return mv;
	}

	public ModelAndView getRedis() {
		
		ValueOperations<String, String> values = redisTemplate.opsForValue();
		Random rand = new Random();
		int ranDomId =  rand.nextInt(99999)+1;
		String result = values.get("id" + ranDomId);
		ModelAndView mv = new ModelAndView();
		User user = new User();
		if(result != null && result.length() > 0) {
			user.setId(ranDomId);
			user.setCustomer(result);
			mv.addObject("user", user);
		}
		return mv;
	}
}
