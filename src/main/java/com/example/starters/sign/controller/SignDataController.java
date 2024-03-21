package com.example.starters.sign.controller;

import com.example.starters.comm.ResultMap;
import com.example.starters.sign.param.CheckStringParam;
import com.example.starters.sign.param.SignUpParam;
import com.example.starters.sign.service.SignService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/sign/rest")
public class SignDataController {

    private final SignService signService;

    /** 아이디 체크 */
    @PostMapping("/check/id")
    public ResultMap checkIdExist(@RequestBody CheckStringParam param) {
        ResultMap result = new ResultMap();
        try{
            result.setData(signService.checkIdExist(param));
            result.setSuccess();
        } catch (Exception e) {
            log.error("", e);
            result.setFailure();
        }
        return result;
    }


    /** 이메일 체크 */
    @PostMapping("/check/email")
    public ResultMap checkEmailExist(@RequestBody CheckStringParam param) {
        ResultMap result = new ResultMap();
        try{
            result.setData(signService.checkEmailExist(param));
            result.setSuccess();
        } catch (Exception e) {
            log.error("", e);
            result.setFailure();
        }
        return result;
    }


    /** 회원가입 */
    @PostMapping("/signup")
    public ResultMap signUp(@RequestBody SignUpParam param) {
        ResultMap result = new ResultMap();
        try{
            signService.signUp(param);
            result.setSuccess();
        } catch (Exception e) {
            log.error("", e);
            result.setFailure();
        }
        return result;
    }


}
