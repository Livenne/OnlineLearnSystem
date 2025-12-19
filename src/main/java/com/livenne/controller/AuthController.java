package com.livenne.controller;

import io.github.livenne.ResponseEntity;
import io.github.livenne.annotation.container.*;
import io.github.livenne.annotation.servlet.*;
import com.livenne.common.constant.AuthConstant;
import com.livenne.common.model.dto.UserDTO;
import com.livenne.common.model.entity.User;
import com.livenne.common.model.dto.UserLoginDTO;
import com.livenne.common.model.dto.UserRegisterDTO;
import com.livenne.service.UserService;
import io.github.livenne.utils.JwtUtils;

import java.util.Map;


@Controller("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserLoginDTO userLoginDTO) {
        if (userLoginDTO == null) return ResponseEntity.err().message("用户名或密码不能为空");
        String username = userLoginDTO.getUsername();
        String password = userLoginDTO.getPassword();
        if (!userService.isExistByName(username)) return ResponseEntity.err().message("用户或密码输入错误");
        User user = userService.getByName(username);
        if (!user.getPassword().equals(password)) return ResponseEntity.err().message("用户或密码输入错误");
        return ResponseEntity.ok(JwtUtils.getToken(user.getUserId()));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserRegisterDTO userRegisterDTO) {
        if  (userRegisterDTO == null) return ResponseEntity.err().message("用户名或密码不能为空");
        String username = userRegisterDTO.getUsername();
        String password = userRegisterDTO.getPassword();
        String confirmPassword = userRegisterDTO.getConfirmPassword();

        if(!password.equals(confirmPassword)) return ResponseEntity.err().message("密码重复不一致");
        if (!AuthConstant.USERNAME_PATTERN.matcher(username).matches()) return ResponseEntity.err().message("用户名不符合条件");;
        if (!AuthConstant.PASSWORD_PATTERN.matcher(password).matches()) return ResponseEntity.err().message("密码不符合条件");;

        if (userService.isExistByName(username)) return ResponseEntity.err().message("用户名已存在");

        UserDTO temp = UserDTO.builder()
                .username(username)
                .password(password)
                .score(0L)
                .avatarUrl("default_avatar.png")
                .build();
        Long userId = userService.save(temp);
        String token = JwtUtils.getToken(userId);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/verify")
    public ResponseEntity verify(@RequestBody Map<String, String> map) {
        return ResponseEntity.ok(JwtUtils.validateToken(map.get("token")));
    }
}
