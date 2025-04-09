package com.seonier.service;

import com.seonier.dto.request.LoginRequest;
import com.seonier.dto.response.DefaultResponse;
import com.seonier.persistence.mapper.UserMapper;
import com.seonier.persistence.model.User;
import com.seonier.web.lang.RequestException;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService extends AbstractService {

	private final PasswordEncoder passwordEncoder;

	private final UserMapper userMapper;

	public User getUserByUserNo(long userNo) {
		log.debug("User no: {}", userNo);

		// 이 곳에서 비지니스 로직 처리

		return userMapper.findByUserNo(userNo);
	}

	public User getUserByUserId(String userId) {
		log.debug("User id: {}", userId);

		// 이 곳에서 비지니스 로직 처리

		return userMapper.findByUserId(userId);
	}

	public DefaultResponse getLoginCheck(@Valid LoginRequest params) {
		User user = userMapper.findByUserId(params.getUserId());
		if (user == null || user.getUserNo() == null) {
			throw new RequestException(401, "아이디 또는 비밀번호를 다시 확인해주세요.");
		}

		// 암호화된 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 확인한다.
		if (!this.passwordEncoder.matches(params.getPasswd(), user.getPasswd())) { // 비밀번호 확인
			throw new RequestException(401, "아이디 또는 비밀번호를 다시 확인해주세요.");
		}

		return DefaultResponse.builder().put("user_id", user.getUserId()).build();
	}

}
