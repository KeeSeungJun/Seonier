package com.seonier.persistence.model;

import java.io.Serial;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 사용자 정보
 *
 * @version 1.0.0
 */
@Getter
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

}
