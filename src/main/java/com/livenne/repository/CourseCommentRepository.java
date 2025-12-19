package com.livenne.repository;


import io.github.livenne.BaseMapper;
import com.livenne.common.model.dto.CourseCommentDTO;
import com.livenne.common.model.entity.CourseComment;
import io.github.livenne.annotation.orm.Repository;
import io.github.livenne.annotation.orm.Cond;
import io.github.livenne.annotation.orm.Insert;
import io.github.livenne.annotation.orm.Query;

import java.util.List;

@Repository
public interface CourseCommentRepository extends BaseMapper<CourseComment> {
    @Query
    List<CourseComment> getByCourseId(@Cond("courseId") Long courseId);

    @Insert
    Long save(CourseCommentDTO courseComment);

}
