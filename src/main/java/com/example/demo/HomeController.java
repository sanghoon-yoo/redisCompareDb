package com.example.demo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class HomeController {
	
	
	private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
    private KafkaTemplate kafkaTemplate;
	
	@Autowired
	private HomeService homeService; 
	
	@RequestMapping("/")
	public String index() {
		LocalDateTime date = LocalDateTime.now();
		ModelAndView mv = homeService.testRedisData();
		mv.setViewName("/home");	
		mv.addObject("key", "1234");
		return "index";
	}
	
	@RequestMapping("/home.do")
	public ModelAndView home() {
		LocalDateTime date = LocalDateTime.now();
//		ModelAndView mv = homeService.testRedisData();
		log.info("/home.do로 오는 요청");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home");	
//		mv.addObject("key", "1234");
		return mv;
	}
	
	@RequestMapping("/getDb.do")
	public ModelAndView getDb() {
		log.info("/getDb.do로 오는 요청");
		ModelAndView mv = homeService.testRanDomData();
		mv.setViewName("test");	
		return mv;
	}
	
	@RequestMapping("/getRedis.do")
	public ModelAndView getRedis() {
		log.info("/getRedis.do로 오는 요청");
		ModelAndView mv = homeService.getRedis();
		mv.setViewName("test");	
		return mv;
	}
	
	@RequestMapping("/kafka.do")
	public void kafka(String msg) {
		LocalDateTime date = LocalDateTime.now();
		String dateStr = date.format(fmt);
		kafkaTemplate.send("jjjwodls", "8080 send " + dateStr + " : " + msg);
	}
	
}
