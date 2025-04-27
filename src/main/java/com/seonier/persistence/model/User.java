package com.seonier.persistence.model;

import java.io.Serial;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * 사용자 정보
 *
 * @version 1.0.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class User extends AbstractModel {

	@Serial
	private static final long serialVersionUID = 3569659936430407817L;

	/**
	 * 사용자 번호
	 */
	private Long userNo;

	/**
	 * 사용자 아이디
	 */
	private String userId;

	/**
	 * 비밀번호
	 */
	@JsonIgnore
	private String passwd;

	/**
	 * 사용자 이름
	 */
	private String userName;

	/**
	 * 사용자 휴대폰번호
	 */
	private String mobileNumber;

	/**
	 * 사용자 그룹 아이디
	 */
	private String userGroupId;

	/**
	 * 사용 여부 (YES/NO)
	 */
	private String useAT;

	/**
	 * 생성자 ID
	 */
	private String createId;

	/**
	 * 수정자 ID
	 */
	private String updateId;

	// ============================================
	// 추가된 건강 상태 필드 (질병 여부 - int형)
	// ============================================

	/**
	 * 건강 상태 - 고혈압
	 */
	private int userHealth1;

	/**
	 * 건강 상태 - 당뇨병
	 */
	private int userHealth2;

	/**
	 * 건강 상태 - 목디스크
	 */
	private int userHealth3;

	/**
	 * 건강 상태 - 관절염
	 */
	private int userHealth4;

	/**
	 * 건강 상태 - 만성요통
	 */
	private int userHealth5;

	/**
	 * 건강 상태 - 심장질환
	 */
	private int userHealth6;

	/**
	 * 건강 상태 - 시력저하
	 */
	private int userHealth7;

	/**
	 * 건강 상태 - 청력저하
	 */
	private int userHealth8;
}
