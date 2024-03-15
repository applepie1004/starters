package com.example.starters.sign.mapper;

import com.example.starters.sign.param.CheckStringParam;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SignMapper {
    int selectIdExist(CheckStringParam param);
}
