package com.example.starters.comm;

import com.example.starters.comm.security.CustomUserDetails;
import com.example.starters.comm.util.OAuth2UserUtil;
import com.example.starters.comm.util.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class PropertiesAdvice {

    @ModelAttribute("userInfo")
    public Map<String, String> getUserInfo() {
        Map<String, String> userInfo = new HashMap<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String email = "email";
        if(auth.getPrincipal() instanceof DefaultOAuth2User) {
            userInfo.put("username", OAuth2UserUtil.getOAuth2User().getAttributes().get("name").toString());
            userInfo.put(email, OAuth2UserUtil.getOAuth2User().getAttributes().get(email).toString());
            userInfo.put("role", "USER");
        } else if (auth.getPrincipal() instanceof CustomUserDetails) {
            userInfo.put("username", UserUtil.getUserVO().getUserId());
            userInfo.put(email, UserUtil.getUserVO().getUserEmail());
            userInfo.put("role", auth.getAuthorities().stream().toList().get(0).toString().replace("ROLE_", ""));
        }
        return userInfo;
    }
}
