package com.livenne.repository;

import com.livenne.BaseMapper;
import com.livenne.annotation.Cond;
import com.livenne.annotation.Insert;
import com.livenne.annotation.Query;
import com.livenne.annotation.Repository;
import com.livenne.common.model.dto.UserCourseDTO;
import com.livenne.common.model.entity.UserCourse;

import java.util.List;

@Repository
public interface UserCourseRepository extends BaseMapper<UserCourse> {

    @Query
    List<UserCourse> getByUserId(@Cond("userId") Long userId);

    @Insert
    Long save(UserCourseDTO userCourse);

}
