package com.livenne.repository;

import io.github.livenne.BaseMapper;
import com.livenne.common.model.dto.UserCourseDTO;
import com.livenne.common.model.entity.UserCourse;

import java.util.List;
import io.github.livenne.annotation.orm.Repository;
import io.github.livenne.annotation.orm.Cond;
import io.github.livenne.annotation.orm.Insert;
import io.github.livenne.annotation.orm.Query;

@Repository
public interface UserCourseRepository extends BaseMapper<UserCourse> {

    @Query
    List<UserCourse> getByUserId(@Cond("userId") Long userId);

    @Insert
    Long save(UserCourseDTO userCourse);

}
