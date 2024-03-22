package com.example.starters.comm.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter @Setter
public class UserVO implements Serializable{
    private String userId;
    private String userEmail;
    private String userPswd;
    private String userNm;
    private String userTel;
    private String useAt;
    private List<String> userRoleList;

    public UserVO(String userId, String userEmail) {
        this.userId = userId;
        this.userEmail = userEmail;
    }
}
