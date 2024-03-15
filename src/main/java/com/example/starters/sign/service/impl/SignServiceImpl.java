package com.example.starters.sign.service.impl;

import com.example.starters.sign.mapper.SignMapper;
import com.example.starters.sign.param.CheckStringParam;
import com.example.starters.sign.service.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SignServiceImpl implements SignService {

    private final SignMapper signMapper;

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
}
