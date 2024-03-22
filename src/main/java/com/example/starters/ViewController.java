package com.example.starters;

import com.example.starters.comm.util.OAuth2UserUtil;
import com.example.starters.comm.util.UserUtil;
import com.example.starters.comm.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class ViewController {

    @GetMapping("/")
    public String viewIndex() {
        return "index";
    }

    @GetMapping("/login")
    public String viewLogin() {
        UserVO user = UserUtil.getUserVO();
        DefaultOAuth2User oAuth2User = OAuth2UserUtil.getOAuth2User();

        if(user != null || oAuth2User != null) {
            return "redirect:/";
        }
        return "login";
    }

    @GetMapping("/signup")
    public String viewSignup() {
        UserVO user = UserUtil.getUserVO();
        DefaultOAuth2User oAuth2User = OAuth2UserUtil.getOAuth2User();

        if(user != null || oAuth2User != null) {
            return "redirect:/";
        }
        return "signup";
    }
}
