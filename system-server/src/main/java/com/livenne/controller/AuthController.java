package com.livenne.controller;

import com.livenne.ResponseEntity;
import com.livenne.annotation.Autowired;
import com.livenne.annotation.Controller;
import com.livenne.annotation.PostMapping;
import com.livenne.annotation.RequestBody;
import com.livenne.common.constant.AuthConstant;
import com.livenne.common.model.User;
import com.livenne.common.model.dto.UserLoginDTO;
import com.livenne.common.model.dto.UserRegisterDTO;
import com.livenne.service.UserService;
import com.livenne.utils.JwtUtils;
import com.livenne.utils.StringUtils;

import java.util.Map;


@Controller("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginDTO userLoginDTO) {
        System.out.println(StringUtils.toJson(userLoginDTO));
        if (userLoginDTO == null) return ResponseEntity.failureMsg("");
        String username = userLoginDTO.getUsername();
        String password = userLoginDTO.getPassword();
        if (!userService.isExistByName(username)) return ResponseEntity.failureMsg("");
        User user = userService.getByName(username);
        if (!user.getPassword().equals(password)) return ResponseEntity.failureMsg("");
        return ResponseEntity.success(JwtUtils.getToken(user.getUserId()));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegisterDTO userRegisterDTO) {
        if  (userRegisterDTO == null) return ResponseEntity.failureMsg("");
        String username = userRegisterDTO.getUsername();
        String password = userRegisterDTO.getPassword();
        String confirmPassword = userRegisterDTO.getConfirmPassword();

        if(!password.equals(confirmPassword)) return ResponseEntity.failureMsg("");
        if (!AuthConstant.USERNAME_PATTERN.matcher(username).matches()) return ResponseEntity.failureMsg("");;
        if (!AuthConstant.PASSWORD_PATTERN.matcher(password).matches()) return ResponseEntity.failureMsg("");;

        if (userService.isExistByName(username)) return ResponseEntity.failureMsg("");

        User temp = User.builder()
                .username(username)
                .password(password)
                .score(0L)
                .avatarUrl("default_avatar.png")
                .build();
        User user = userService.save(temp);
        String token = JwtUtils.getToken(user.getUserId());
        return ResponseEntity.success(token);
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verify(@RequestBody Map<String, String> map) {
        return ResponseEntity.success(JwtUtils.validateToken(map.get("token")));
    }
}
