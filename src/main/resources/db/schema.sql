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
