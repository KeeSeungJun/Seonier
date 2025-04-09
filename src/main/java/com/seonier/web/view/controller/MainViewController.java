package com.seonier.web.view.controller;

import org.apache.commons.lang3.StringUtils;

import com.seonier.persistence.model.User;
import com.seonier.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MainViewController extends AbstractViewController {

	private final UserService userService;

	/**
	 * Reqeust Mapping이 정의 되지 않은 페이지 처리.
	 *
	 * @param model model attributes.
	 * @return view page.
	 */
	@RequestMapping(path = { "/", "main" })
	public String main(Model model, @RequestParam(required = false, name = "user_id") String userId) {
		log.debug("Access the main page.");
		User user = userService.getUserByUserId(StringUtils.isBlank(userId) ? "admin" : userId);

		// DB 쿼리 방법을 보여주기 위해서 TMP로 다시 조회하는 로직 추가.
		user = userService.getUserByUserNo(user.getUserNo());

		model.addAttribute("message", user.getUserName() + "님 환영합니다.");
		return "view/main";
	}

}
