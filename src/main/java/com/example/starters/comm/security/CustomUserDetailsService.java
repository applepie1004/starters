package com.example.starters.comm.security;

import com.example.starters.comm.mapper.LoginMapper;
import com.example.starters.comm.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final LoginMapper loginMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserVO userVO = loginMapper.selectUserInfo(username);
        if (userVO != null) {

            List<String> userRoleList = loginMapper.selectListUserRole(username);
            userVO.setUserRoleList(userRoleList);

            return new CustomUserDetails(userVO);
        } else {
            throw new UsernameNotFoundException("Username not found");
        }
    }
}
