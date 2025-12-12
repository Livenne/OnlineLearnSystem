package com.livenne.service;

import com.livenne.common.model.dto.UserDTO;
import com.livenne.common.model.dto.UserShoppingCartDTO;
import com.livenne.common.model.entity.Course;
import com.livenne.common.model.entity.User;
import com.livenne.common.model.vo.CourseVO;

import java.util.List;

public interface UserService {
    User getById(Long id);
    User getByName(String name);
    List<User> getAll();
    List<User> getByIdList(List<Long> idList);
    Boolean isExistById(Long id);
    Boolean isExistByName(String name);
    Long save(UserDTO user);
    List<Course> getShoppingCart(Long userId);
    List<Course> getOrder(Long userId);
    Boolean isInCart(UserShoppingCartDTO userShoppingCartDTO);
    Boolean joinCart(UserShoppingCartDTO userShoppingCartDTO);
    Boolean leaveCart(UserShoppingCartDTO userShoppingCartDTO);

}
