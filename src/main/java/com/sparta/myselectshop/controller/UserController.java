package com.sparta.myselectshop.controller;

import com.sparta.myselectshop.domain.user.SignupRequestDto;
import com.sparta.myselectshop.domain.user.UserRepository;
import com.sparta.myselectshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    // 로그인 실패
    @GetMapping("/user/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    // 회원가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(SignupRequestDto requestDto) {
        userService.registerUser(requestDto);
        return "redirect:/";
    }

    @GetMapping("/user/forbidden")
    public String forbidden() {
        return "forbidden";
    }

    // 카카오 로그인 콜백
    // 로그인 성공 시 인가토큰이 전달된다.
    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(String code) {
        // authorizedCode: 카카오 서버로부터 받은 인가 코드
        userService.kakaoLogin(code);

        return "redirect:/";
    }
}
