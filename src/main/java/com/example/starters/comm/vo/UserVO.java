package com.example.starters.comm.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class UserVO {
    private String userId;
    private String userEmail;
    private String userPswd;
    private String userNm;
    private String userTel;
    private String useAt;
    private List<String> userRoleList;

}
