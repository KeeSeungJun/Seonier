
-- 사용자 정보 테이블
CREATE TABLE IF NOT EXISTS USR_INFO (
      USR_NO                            INT UNSIGNED AUTO_INCREMENT             NOT NULL                                COMMENT '사용자 번호'
    , USR_ID                            VARCHAR(50)                             NOT NULL                                COMMENT '사용자 아이디'
    , PASSWD                            VARCHAR(200)                            NOT NULL                                COMMENT '비밀번호'
    , USR_NM                            VARCHAR(50)                             NOT NULL                                COMMENT '사용자 이름'
    , USR_MBTLNUM                       VARCHAR(20)                             NOT NULL                                COMMENT '사용자 휴대폰번호'
    , USR_GRP_ID                        VARCHAR(50)                             NOT NULL                                COMMENT '사용자 그룹 아이디'
    , USE_AT                            VARCHAR(10)                             NOT NULL DEFAULT 'YES'                  COMMENT '사용 여부'
    , CREAT_ID                          VARCHAR(50)                             NOT NULL                                COMMENT '생성 ID'
    , CREAT_DT                          TIMESTAMP                               NOT NULL DEFAULT CURRENT_TIMESTAMP      COMMENT '생성 일시'
    , UPDT_ID                           VARCHAR(50)                             NOT NULL                                COMMENT '수정 ID'
    , UPDT_DT                           TIMESTAMP                               NOT NULL                                COMMENT '수정 일시'
    , CONSTRAINT PK_USR_INFO PRIMARY KEY (USR_NO)
    , UNIQUE INDEX UK_USR_INFO01 (USR_ID, USE_AT)
    , INDEX IDX_USR_INFO01 (USR_ID)
    , INDEX IDX_USR_INFO02 (USR_GRP_ID)
    , INDEX IDX_USR_INFO97 (USE_AT)
    , INDEX IDX_USR_INFO98 (CREAT_DT)
    , INDEX IDX_USR_INFO99 (UPDT_DT)
    ) AUTO_INCREMENT=1000000001 COMMENT = '사용자 정보';

-- 사용자 정보 데이터, 비밀번호 : qwer1234
INSERT INTO USR_INFO (USR_NO, USR_ID, PASSWD, USR_NM, USR_MBTLNUM, USR_GRP_ID, CREAT_ID, UPDT_ID, UPDT_DT) VALUES
  (1000000001, 'admin', '{bcrypt}$2a$10$2IeGPuGnzDVrv5nPTSIrHeHnv7ua9csBUq7B1b3gK6uuhY.K3bxW2', '어드민', '01012345678', 'ADMIN',    'admin', 'admin', NOW())
, (1000000002, 'test',  '{bcrypt}$2a$10$2IeGPuGnzDVrv5nPTSIrHeHnv7ua9csBUq7B1b3gK6uuhY.K3bxW2', '테스트', '01012345678', 'CUSTOMER', 'admin', 'admin', NOW())
, (1000000003, 'guest', '{bcrypt}$2a$10$2IeGPuGnzDVrv5nPTSIrHeHnv7ua9csBUq7B1b3gK6uuhY.K3bxW2', '게스트', '01012345678', 'GUEST',    'admin', 'admin', NOW())
    ON DUPLICATE KEY UPDATE
                         PASSWD                        = VALUES(PASSWD)
                             , USR_NM                        = VALUES(USR_NM)
                             , USR_GRP_ID                    = VALUES(USR_GRP_ID)
                             , UPDT_ID                       = VALUES(UPDT_ID)
                             , UPDT_DT                       = NOW()
;

-- group_info 테이블 생성
CREATE TABLE IF NOT EXISTS group_info (
                                          group_no                          INT UNSIGNED AUTO_INCREMENT             NOT NULL                                COMMENT '그룹 번호',
                                          group_id INT NOT NULL,
                                          group_nm VARCHAR(255) NOT NULL,
    CREAT_DT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UPDT_DT TIMESTAMP NOT NULL ,
    CONSTRAINT PK_GROUP_INFO PRIMARY KEY (group_no),
     INDEX UK_GROUP_INFO01 (group_id),
     INDEX UK_GROUP_INFO02 (group_nm)
    ) AUTO_INCREMENT=1000000001 COMMENT = '그룹 정보';

INSERT INTO group_info (group_no, group_id, group_nm, UPDT_DT) VALUES
(1000000001, 0, '어드민', NOW())
, (1000000002, 1,  '시니어', NOW())
    ON DUPLICATE KEY UPDATE
                         group_id                        = VALUES(group_id)
                             , group_nm                        = VALUES(group_nm)
                             , UPDT_DT                         = NOW()
;




/*
-- user_info 테이블 생성
CREATE TABLE IF NOT EXISTS user_info (
                                         user_no BIGINT AUTO_INCREMENT PRIMARY KEY,
                                         user_id VARCHAR(255) NOT NULL,
    user_nm VARCHAR(255),
    group_id INT,
    user_addr VARCHAR(255),
    user_addr_lat DECIMAL(10,6),
    user_addr_lon DECIMAL(10,6),
    user_gender CHAR(1),
    user_phone VARCHAR(20),
    user_job_score INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_user_group FOREIGN KEY (group_id) REFERENCES group_info(group_id)
    );

-- user_health 테이블 생성
CREATE TABLE IF NOT EXISTS user_health (
                                           user_health_no BIGINT AUTO_INCREMENT PRIMARY KEY,
                                           user_id VARCHAR(255) NOT NULL,
    user_health1_YN BOOLEAN,
    user_health2_YN BOOLEAN,
    user_health3_YN BOOLEAN,
    user_health4_YN BOOLEAN,
    user_health5_YN BOOLEAN,
    user_health6_YN BOOLEAN,
    user_health7_YN BOOLEAN,
    user_health8_YN BOOLEAN,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_user_health FOREIGN KEY (user_id) REFERENCES user_info(user_id)
    );

-- job_info 테이블 생성
CREATE TABLE IF NOT EXISTS job_info (
                                        job_no BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        user_id VARCHAR(255),
    job_title VARCHAR(255),
    job_task VARCHAR(255),
    job_desc VARCHAR(1000),
    job_cate VARCHAR(255),
    job_addr VARCHAR(255),
    job_addr_lat DECIMAL(10,6),
    job_addr_lon DECIMAL(10,6),
    job_url VARCHAR(255),
    job_pay VARCHAR(100),
    job_tel VARCHAR(20),
    job_req_gender CHAR(1),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_job_user FOREIGN KEY (user_id) REFERENCES user_info(user_id)
    );

-- user_job 테이블 생성
CREATE TABLE IF NOT EXISTS user_job (
                                        user_job_no BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        user_id VARCHAR(255) NOT NULL,
    job_no BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_user_job_user FOREIGN KEY (user_id) REFERENCES user_info(user_id),
    CONSTRAINT fk_user_job_job FOREIGN KEY (job_no) REFERENCES job_info(job_no)
    );

-- faq 테이블 생성
CREATE TABLE IF NOT EXISTS faq (
                                   faq_no BIGINT AUTO_INCREMENT PRIMARY KEY,
                                   user_id VARCHAR(255) NOT NULL,
    faq_title VARCHAR(255),
    faq_body TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_faq_user FOREIGN KEY (user_id) REFERENCES user_info(user_id)
    );

-- qna 테이블 생성
CREATE TABLE IF NOT EXISTS qna (
                                   qna_no BIGINT AUTO_INCREMENT PRIMARY KEY,
                                   user_id VARCHAR(255) NOT NULL,
    qna_title VARCHAR(255),
    qna_body TEXT,
    qna_comment_yn BOOLEAN DEFAULT FALSE,
    qna_comment_body TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_qna_user FOREIGN KEY (user_id) REFERENCES user_info(user_id)
    );
*/