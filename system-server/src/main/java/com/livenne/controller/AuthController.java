package com.livenne.controller;

import com.livenne.ResponseEntity;
import com.livenne.annotation.Controller;
import com.livenne.annotation.PostMapping;
import com.livenne.annotation.RequestBody;
import com.livenne.common.model.User;
import com.livenne.common.model.dto.UserLoginDTO;
import com.livenne.common.model.dto.UserRegisterDTO;
import com.livenne.utils.JwtUtils;
import com.livenne.service.UserService;
import com.livenne.service.impl.UserServiceImpl;

import java.util.regex.Pattern;

@Controller("/auth")
public class AuthController {
    public UserService userService = UserServiceImpl.instance;
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9_]{6,18}$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^[a-zA-Z!@#$%^&*\\-+_=]{8,18}$");

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO userLoginDTO) {
        if (userLoginDTO == null) return ResponseEntity.failureMsg("");
        String username = userLoginDTO.getUsername();
        String password = userLoginDTO.getPassword();
        if (!userService.isExitsUsername(username)) return ResponseEntity.failureMsg("");
        User user = userService.getByUsername(username);
        if (!user.getPassword().equals(password)) return ResponseEntity.failureMsg("");
        return ResponseEntity.success(JwtUtils.getToken(user.getUserId()));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterDTO userRegisterDTO) {
        if  (userRegisterDTO == null) return ResponseEntity.failureMsg("");
        String username = userRegisterDTO.getUsername();
        String password = userRegisterDTO.getPassword();
        String confirmPassword = userRegisterDTO.getConfirmPassword();

        if(!password.equals(confirmPassword)) return ResponseEntity.failureMsg("");
        if (!USERNAME_PATTERN.matcher(username).matches()) return ResponseEntity.failureMsg("");;
        if (!PASSWORD_PATTERN.matcher(password).matches()) return ResponseEntity.failureMsg("");;

        if (userService.isExitsUsername(username)) return ResponseEntity.failureMsg("");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setAvatarUrl("default_avatar.png");
        User newUser = userService.add(user);
        String token = JwtUtils.getToken(newUser.getUserId());
        return ResponseEntity.success(token);
    }

}
