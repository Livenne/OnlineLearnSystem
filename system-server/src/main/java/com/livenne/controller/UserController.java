package com.livenne.controller;

import com.livenne.ResponseEntity;
import com.livenne.annotation.*;
import com.livenne.common.model.*;
import com.livenne.common.model.vo.UserVO;
import com.livenne.repository.UserRepo;
import com.livenne.service.impl.*;
import com.livenne.service.*;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

@Controller("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private HttpServletRequest req;

    @GetMapping("/{userId}")
    public ResponseEntity<UserVO> getUser(@PathVariable("userId") Long userId){
        UserVO userVO = new UserVO(userService.getById(userId));

        return ResponseEntity.success();
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserVO>> getAll(){
        List<UserVO> userVOList = new ArrayList<>();
        userService.getAll().forEach(user -> {
            userVOList.add(new UserVO(user));
        });
        return ResponseEntity.success(userVOList);
    }

    @PostMapping("/list")
    public ResponseEntity<List<UserVO>> getList(@RequestBody List<Long> idList){
        List<UserVO> userVOList = new ArrayList<>();
        userService.getByIdList(idList).forEach(user -> {
            userVOList.add(new UserVO(user));
        });
        return ResponseEntity.success(userVOList);
    }

    @GetMapping("/info")
    public ResponseEntity<UserVO> getInfo(){
        return ResponseEntity.success();
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<?> getCourse(@PathVariable("courseId") Long courseId){
        return ResponseEntity.success();
    }

    @GetMapping("/course/list")
    public ResponseEntity<?> getCourseList(){
        return ResponseEntity.success();
    }
}
