package com.seonier.persistence.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.seonier.persistence.model.User;

@Mapper
public interface UserMapper {

	User findByUserNo(long userNo);

	User findByUserId(String userId);

	void insertUser(User user);  // 🔥 추가! 반드시 필요
}
