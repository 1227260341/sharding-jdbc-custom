package com.zj.content.controller;
import java.text.SimpleDateFormat;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zj.content.entity.User;
import com.zj.content.service.UserSerDao;


@RestController
public class UserController {

	@Autowired
	private UserSerDao userSer;

	/**
	 * 模拟插入数据
	 */
	User userList = new User();
	/**
	 * 初始化插入数据,@PostConstruct这个注解代表类实施事就执行这个方法，类似于构造函数（仅仅是我的理解）
	 */
	@PostConstruct
	private void getData() {
		SimpleDateFormat sfEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		userList=new User(3,"dadada1", 298,"2025-05-15 14:39:18");//会发现被存入第二个库中tab_user0表中
		userList=new User(3,"dadada2021年", 298,"2021-05-15 14:39:18");//test
//		userList=new User(3,"dadada1", 298,sfEnd.format(new Date()));//当前时间2020年，会发现被存入第一个库中tab_user0表中
		System.out.println("测试~"+userList.getCreate_time());
	}

	/**
	 * 添加数据，
	 */
	@GetMapping("/save-user")
	public Object saveUser() {
		return userSer.addUser(userList);
	}
	
	@GetMapping("/getOrderUser")
    public Object getOrderUser() {
        return userSer.getOrderUser();
    }
}
