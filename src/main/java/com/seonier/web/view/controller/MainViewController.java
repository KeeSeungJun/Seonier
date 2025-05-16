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
import org.springframework.web.bind.annotation.GetMapping;


//@Slf4j
//@RequiredArgsConstructor
//@Controller
//public class MainViewController extends AbstractViewController {
//
//	private final UserService userService;
//
//	/**
//	 * Reqeust Mapping이 정의 되지 않은 페이지 처리.
//	 *
//	 * @param model model attributes.
//	 * @return view page.
//	 */
//	@RequestMapping(path = { "/", "main" })
//	public String main(Model model, @RequestParam(required = false, name = "user_id") String userId) {
//		log.debug("Access the main page.");
//		User user = userService.getUserByUserId(StringUtils.isBlank(userId) ? "admin" : userId);
//
//		// DB 쿼리 방법을 보여주기 위해서 TMP로 다시 조회하는 로직 추가.
//		user = userService.getUserByUserNo(user.getUserNo());
//
//		model.addAttribute("message", user.getUserName() + "님 환영합니다.");
//		return "view/main";
//	}
//
//}
@Slf4j
@RequiredArgsConstructor
@Controller
public class MainViewController extends AbstractViewController {

	private final UserService userService;

	/**
	 * Reqeust Mapping이 정의 되지 않은 페이지 처리.
	 */
	@RequestMapping(path = { "/", "main" })
	public String main(
			Model model,
			@RequestParam(required = false, name = "user_id") String userId
	) {
		log.debug("Access the main page.");
		User user = userService.getUserByUserId(
				StringUtils.isBlank(userId) ? "admin" : userId
		);
		// TMP: 다시 조회 예시
		user = userService.getUserByUserNo(user.getUserNo());

		// --- 1) userName 속성 추가 ---
		model.addAttribute("userName", user.getUserName());

		// 2) 만약 ADMIN 이면 admin 전용 페이지로
		if ("ADMIN".equalsIgnoreCase(user.getUserGroupId())) {
			model.addAttribute("message", user.getUserName() + "님, 관리자 페이지에 오신 것을 환영합니다.");
			return "view/adminmain";
		}
		// 그 외 사용자
		model.addAttribute("message", user.getUserName() + "님 환영합니다.");
		return "view/main";
	}

	/**
	 * 자주 묻는 질문 페이지
	 */
	@GetMapping("/faq")
	public String faqPage() {
		log.debug("Access the FAQ page.");
		// 필요한 모델이 있으면 model.addAttribute(...) 해 주세요
		return "view/faq";
	}

	/**
	 * 일자리 추천 페이지
	 */
	@GetMapping("/job")
	public String jobRecommendPage() {
		log.debug("Access the Job Recommend page.");
		// 필요한 모델이 있으면 model.addAttribute(...) 해 주세요
		return "view/job";
	}

	/** 자주 묻는 질문 (FAQ) 관리 페이지 */
	@GetMapping("/faqmanage")
	public String faqManagePage(Model model) {
		log.debug("Access the FAQ Manage page.");
		// TODO: service 호출하여 model.addAttribute("faqs", faqService.findAll());
		return "view/faqmanage";
	}

	/** 일자리 등록 페이지 */
	@GetMapping("/jobadd")
	public String jobAddPage(Model model) {
		log.debug("Access the Job Add page.");
		// TODO: 사전 데이터(카테고리 목록 등) model 에 실어주기
		return "view/jobadd";
	}

	/** Q&A 관리 페이지 */
	@GetMapping("/qnamanage")
	public String qnaManagePage(Model model) {
		log.debug("Access the QnA Manage page.");
		// TODO: service 호출하여 model.addAttribute("questions", qnaService.findAll());
		return "view/qnamanage";
	}
}