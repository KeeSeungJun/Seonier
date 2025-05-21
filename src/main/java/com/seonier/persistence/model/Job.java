package com.seonier.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Job extends AbstractModel {
    private Long jobNo;
    private String userId;
    private String jobTitle;
    private String jobTask;
    private String jobDesc;
    private String jobCate;
    private String jobAddr;
    private Double jobAddrLat;
    private Double jobAddrLon;
    private String jobUrl;
    private String jobPay;
    private String jobTel;
    private String jobReqGender;
    
    // 질병 제한 필드
    private String jobHealth1Restrict;
    private String jobHealth2Restrict;
    private String jobHealth3Restrict;
    private String jobHealth4Restrict;
    private String jobHealth5Restrict;
    private String jobHealth6Restrict;
    private String jobHealth7Restrict;
    private String jobHealth8Restrict;
}
