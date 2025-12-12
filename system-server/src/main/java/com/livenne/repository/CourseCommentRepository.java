package com.livenne.repository;


import com.livenne.BaseMapper;
import com.livenne.annotation.Cond;
import com.livenne.annotation.Insert;
import com.livenne.annotation.Query;
import com.livenne.annotation.Repository;
import com.livenne.common.model.dto.CourseCommentDTO;
import com.livenne.common.model.entity.CourseComment;

import java.util.List;

@Repository
public interface CourseCommentRepository extends BaseMapper<CourseComment> {
    @Query
    List<CourseComment> getByCourseId(@Cond("courseId") Long courseId);

    @Insert
    Long save(CourseCommentDTO courseComment);

}
