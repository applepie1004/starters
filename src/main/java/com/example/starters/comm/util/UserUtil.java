package com.example.starters.comm.util;

import com.example.starters.comm.security.CustomUserDetails;
import com.example.starters.comm.vo.UserVO;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtil {

    private UserUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static UserVO getUserVO() {
        try {
            return ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserVO();
        } catch(NullPointerException | ClassCastException e) {
            return null;
        }
    }
}
