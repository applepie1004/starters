package com.example.starters.sign.service;

import com.example.starters.sign.param.CheckStringParam;
import com.example.starters.sign.param.SignUpParam;

import java.util.Map;

public interface SignService {
    Map<String, Object> checkIdExist(CheckStringParam param);

    Map<String, Object> checkEmailExist(CheckStringParam param);

    void signUp(SignUpParam param);
}
