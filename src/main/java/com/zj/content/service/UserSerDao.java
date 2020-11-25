package com.zj.content.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zj.content.entity.User;
import com.zj.content.mapper.UserMa;
import com.zj.content.utils.ResponseUser;

@Service
public class UserSerDao {

	@Autowired
	private UserMa userMa;
	
	public ResponseUser addUser(User user) {
	    ResponseUser resUser = new ResponseUser();
		int count = userMa.addUser(
				user.getId(),user.getName(),user.getAge(),user.getCreate_time());
		if(count==0)
		    resUser = resUser.setAll(1, "添加失败", null);
		else
		    resUser = resUser.setAll(0, "添加成功", count);
		return resUser;
	}
	
	public List<User> getOrderUser() {
	    List<User> orderUser = userMa.getOrderUser();
//	    List<User> orderUser = null;
        return orderUser;
    }
}