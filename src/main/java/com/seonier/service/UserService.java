package com.seonier.service;

import com.seonier.dto.request.LoginRequest;
import com.seonier.dto.request.RegisterRequest;
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
		return userMapper.findByUserNo(userNo);
	}

	public User getUserByUserId(String userId) {
		log.debug("User id: {}", userId);
		return userMapper.findByUserId(userId);
	}

	public DefaultResponse getLoginCheck(@Valid LoginRequest params) {
		User user = userMapper.findByUserId(params.getUserId());
		if (user == null || user.getUserNo() == null) {
			throw new RequestException(401, "아이디 또는 비밀번호를 다시 확인해주세요.");
		}

		if (!this.passwordEncoder.matches(params.getPasswd(), user.getPasswd())) {
			throw new RequestException(401, "아이디 또는 비밀번호를 다시 확인해주세요.");
		}

		return DefaultResponse.builder()
				.put("user_id", user.getUserId())
				.build();
	}

	public DefaultResponse registerUser(@Valid RegisterRequest params) {
		log.debug("Register user: {}", params);

		// 아이디 중복 체크
		User existingUser = userMapper.findByUserId(params.getEmail());
		if (existingUser != null) {
			throw new RequestException(409, "이미 사용 중인 아이디입니다.");
		}

		// 비밀번호 암호화
		String encryptedPassword = passwordEncoder.encode(params.getPassword());

		// User 엔티티 생성 및 데이터 매핑
		User user = new User();
		user.setUserId(params.getEmail());                     // RegisterRequest.email -> userId
		user.setPasswd(encryptedPassword);                     // 비밀번호 암호화 후 저장
		user.setUserName(params.getName());                    // 이름
		user.setMobileNumber(params.getPhoneNumber());         // 핸드폰 번호
		user.setUserGroupId(params.getGroupId() != null ? params.getGroupId() : "CUSTOMER"); // 그룹 ID (없으면 기본)
		user.setUseAT("YES");                                  // 기본값
		user.setCreateId("system");                            // 생성자 ID
		user.setUpdateId("system");                            // 수정자 ID

		// 건강 상태 (int 그대로 매핑)
		user.setUserHealth1(params.getHealth1());
		user.setUserHealth2(params.getHealth2());
		user.setUserHealth3(params.getHealth3());
		user.setUserHealth4(params.getHealth4());
		user.setUserHealth5(params.getHealth5());
		user.setUserHealth6(params.getHealth6());
		user.setUserHealth7(params.getHealth7());
		user.setUserHealth8(params.getHealth8());

		// DB 저장
		userMapper.insertUser(user);

		return DefaultResponse.builder()
				.put("user_id", user.getUserId())
				.build();
	}
}
