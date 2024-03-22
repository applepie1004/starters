package com.example.starters.comm.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

public class OAuth2UserUtil {

    private OAuth2UserUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static DefaultOAuth2User getOAuth2User() {
        try {
            return (DefaultOAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch(NullPointerException | ClassCastException e) {
            return null;
        }
    }
}
