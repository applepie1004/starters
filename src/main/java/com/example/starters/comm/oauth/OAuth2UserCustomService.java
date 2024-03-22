package com.example.starters.comm.oauth;

import com.example.starters.comm.mapper.LoginMapper;
import com.example.starters.comm.security.CustomUserDetails;
import com.example.starters.comm.vo.UserVO;
import com.example.starters.sign.mapper.SignMapper;
import com.example.starters.sign.param.CheckStringParam;
import com.example.starters.sign.param.SignUpParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
@Slf4j
@Service
@RequiredArgsConstructor
public class OAuth2UserCustomService extends DefaultOAuth2UserService {

    private final SignMapper signMapper;
    private final LoginMapper loginMapper;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(userRequest); // 요청을 바탕으로 유저 정보를 담은 객체 반환
        saveOrUpdate(user);

        return user;
    }


    // 유저가 없으면 유저 생성
    private void saveOrUpdate(OAuth2User oAuth2User) {
        Map<String, Object> attributes = oAuth2User.getAttributes();

        String uuid = UUID.randomUUID().toString().replace("-", "");
        String id = uuid.substring(0,10);
        String email = (String) attributes.get("email");
        String nm = (String) attributes.get("name");

        CheckStringParam csParam = new CheckStringParam();
        csParam.setCheckString(email);

        if (signMapper.selectEmailExist(csParam) == 0) {
            SignUpParam sign = new SignUpParam();
            sign.setId(id);
            sign.setEmail(email);
            sign.setNm(nm);
            signMapper.insertUser(sign);
        }

        UserVO user = loginMapper.selectUserInfo(id);

        new CustomUserDetails(user);
    }
}
