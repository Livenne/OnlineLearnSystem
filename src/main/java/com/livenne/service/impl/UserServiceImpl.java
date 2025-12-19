package com.livenne.service.impl;

import com.livenne.common.model.dto.UserDTO;
import com.livenne.common.model.dto.UserShoppingCartDTO;
import com.livenne.common.model.entity.Course;
import com.livenne.common.model.entity.User;
import com.livenne.common.model.entity.UserOrder;
import com.livenne.common.model.entity.UserShoppingCart;
import com.livenne.repository.UserOrderRepository;
import com.livenne.repository.UserRepository;
import com.livenne.repository.UserShoppingCartRepository;
import com.livenne.service.CourseService;
import com.livenne.service.UserService;
import io.github.livenne.annotation.container.Autowired;
import io.github.livenne.annotation.container.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserShoppingCartRepository userShoppingCartRepository;
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserOrderRepository userOrderRepository;

    @Override
    public User getById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public User getByName(String name) {
        return userRepository.getByUsername(name);
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public List<User> getByIdList(List<Long> idList) {
        return idList.stream().map(this::getById).toList();
    }

    @Override
    public Boolean isExistById(Long id) {
        User usr = getById(id);
        return usr != null && usr.getUserId() != null;
    }

    @Override
    public Boolean isExistByName(String name) {
        User usr = getByName(name);
        return usr != null && usr.getUserId() != null;
    }

    @Override
    public List<Course> getShoppingCart(Long userId) {
        return userShoppingCartRepository.getByUserId(userId)
                .stream()
                .map(UserShoppingCart::getCourseId)
                .map(id->courseService.getById(id))
                .toList();
    }

    @Override
    public List<Course> getOrder(Long userId) {
        return userOrderRepository.getByUserId(userId)
                .stream()
                .map(UserOrder::getCourseId)
                .map(id->courseService.getById(id))
                .toList();
    }

    @Override
    public Long save(UserDTO user) {
        return userRepository.save(user);
    }

    @Override
    public Boolean isInCart(UserShoppingCartDTO userShoppingCartDTO) {
        UserShoppingCart inCart = userShoppingCartRepository.isInCart(userShoppingCartDTO.getUserId(), userShoppingCartDTO.getCourseId());
        return inCart != null && inCart.getId() != null;
    }

    @Override
    public Boolean joinCart(UserShoppingCartDTO userShoppingCartDTO) {
        if (isInCart(userShoppingCartDTO)){
            return false;
        }
        userShoppingCartRepository.save(userShoppingCartDTO);
        return true;
    }

    @Override
    public Boolean leaveCart(UserShoppingCartDTO userShoppingCartDTO) {
        if (!isInCart(userShoppingCartDTO)){
            return false;
        }
        userShoppingCartRepository.delete(userShoppingCartDTO.getUserId(), userShoppingCartDTO.getCourseId());
        return true;
    }

}
