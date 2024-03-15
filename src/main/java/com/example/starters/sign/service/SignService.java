package com.example.starters.sign.service;

import com.example.starters.sign.param.CheckStringParam;

import java.util.Map;

public interface SignService {
    Map<String, Object> checkIdExist(CheckStringParam param);
}
