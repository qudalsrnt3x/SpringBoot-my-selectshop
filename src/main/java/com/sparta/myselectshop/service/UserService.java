package com.sparta.myselectshop.service;

import com.sparta.myselectshop.domain.user.SignupRequestDto;
import com.sparta.myselectshop.domain.user.User;
import com.sparta.myselectshop.domain.user.UserRepository;
import com.sparta.myselectshop.domain.user.UserRole;
import com.sparta.myselectshop.security.kakao.KakaoOAuth2;
import com.sparta.myselectshop.security.kakao.KakaoUserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final KakaoOAuth2 kakaoOAuth2;
    private final AuthenticationManager authenticationManager;

    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";

    @Transactional
    public void registerUser(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();

        // 회원 ID 중복확인
        Optional<User> findUser = userRepository.findByUsername(username);

        if (findUser.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID가 존재합니다.");
        }
        
        // 패스워드 인코딩
        String encPassword = requestDto.getPassword();
        String password = bCryptPasswordEncoder.encode(encPassword);

        String email = requestDto.getEmail();

        // 사용자 ROLE 확인
        UserRole role = UserRole.USER;
        if (requestDto.isAdmin()) {
            if (!requestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRole.ADMIN;
        }

        User user = new User(username, password, email, role);
        userRepository.save(user);
    }

    public void kakaoLogin(String authorizedCode) {
        // 카카오 OAuth2를 통해 카카오 사용자 정보 조회
        KakaoUserInfo userInfo = kakaoOAuth2.getUserInfo(authorizedCode);
        Long kakaoId = userInfo.getId();
        String nickname = userInfo.getNickname();
        String email = userInfo.getEmail();

        // userDb에 넣을 username, password 설정
        // 회원 Id = 카카오 nickname
        String username = nickname;
        // 패스워드 = 카카오 Id + ADMIN TOKEN
        String password = kakaoId + ADMIN_TOKEN;

        // db에 중복된 kakaoId가 있는지 확인
        User kakaoUser = userRepository.findByKakaoId(kakaoId).orElse(null);

        // 카카오 정보로 회원가입
        if (kakaoUser == null) {
            // 패스워드 인코딩
            String encodedPassword = bCryptPasswordEncoder.encode(password);
            // ROLE = 사용자
            UserRole role = UserRole.USER;

            kakaoUser = new User(nickname, encodedPassword, email, role, kakaoId);

            userRepository.save(kakaoUser);
        }

        // 로그인 처리
        Authentication kakaoUsernamePassword = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(kakaoUsernamePassword);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
