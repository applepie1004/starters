package com.example.starters.sign.service.impl;

import com.example.starters.sign.mapper.SignMapper;
import com.example.starters.sign.param.CheckStringParam;
import com.example.starters.sign.param.SignUpParam;
import com.example.starters.sign.service.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SignServiceImpl implements SignService {

    private final SignMapper signMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Map<String, Object> checkIdExist(CheckStringParam param) {
        Map<String, Object> result = new HashMap<>();
        int cnt = signMapper.selectIdExist(param);
        result.put("isIdExist", false);
        if(cnt > 0) {
            result.put("isIdExist", true);
        }
        return result ;
    }


    @Override
    public Map<String, Object> checkEmailExist(CheckStringParam param) {
        Map<String, Object> result = new HashMap<>();
        int cnt = signMapper.selectEmailExist(param);
        result.put("isEmailExist", false);
        if(cnt > 0) {
            result.put("isEmailExist", true);
        }
        return result ;
    }


    @Override
    public void signUp(SignUpParam param) {
        param.setPassword(passwordEncoder.encode(param.getPassword()));
        signMapper.insertUser(param);
    }
}
