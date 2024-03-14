package com.example.starters.comm.mapper;

import com.example.starters.comm.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoginMapper {
    UserVO selectUserInfo(String username);
    List<String> selectListUserRole(String username);
}
