package com.zj.content.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.zj.content.entity.User;

//@Repository
@Mapper
public interface UserMa {

	/**
	 * 添加，只需向逻辑表中添加即可，
	 */
	@Insert({
			" INSERT INTO tab_user (id,name,age,create_time) ",
			" VALUES ( " ,
			"#{id,jdbcType=INTEGER}, ",
			"#{name,jdbcType=VARCHAR}, ",
			"#{age,jdbcType=INTEGER}, ",
			"#{create_time,jdbcType=VARCHAR})"
	})
	int addUser(@Param("id")  Integer id,
				@Param("name") String name,
				@Param("age") Integer age,
				@Param("create_time") String create_time);
	
//	@Select("select * from  `t_order` o ")
	@Select("select * from tab_user u join `t_order` o on o.user_id = u.id ")
//	@Select("select * from  `tab_user` o ")
	public List<User> getOrderUser();
	
}