package com.livenne.controller;

import com.livenne.ResponseEntity;
import com.livenne.annotation.*;
import com.livenne.common.model.dto.CourseCommentDTO;
import com.livenne.common.model.dto.CourseFavoriteDTO;
import com.livenne.common.model.dto.CourseLikeDTO;
import com.livenne.common.model.entity.Course;
import com.livenne.common.model.vo.ChapterVO;
import com.livenne.common.model.vo.CourseCommentVO;
import com.livenne.common.model.vo.CourseVO;
import com.livenne.service.CourseService;

import java.util.List;

@Controller("/course")
public class CourseController{

    @Autowired
    private CourseService courseService;

    @GetMapping("/get/{courseId}")
    public ResponseEntity<CourseVO> getCourse(@PathVariable("courseId") Long courseId,@Attribute("userId") Long userId){
        if (!courseService.isExistById(courseId)){
            return ResponseEntity.failureMsg("课程: %d 不存在", courseId);
        }
        Course course = courseService.getById(courseId);
        CourseVO courseVO = new CourseVO(
                courseId,
                course.getName(),
                course.getIntroduction(),
                course.getCoverUrl(),
                course.getPrice(),
                course.getTeacher(),
                courseService.purchase(courseId),
                courseService.likes(courseId),
                courseService.favorites(courseId),
                courseService.rating(courseId),
                courseService.chapters(courseId),
                courseService.comments(courseId),
                courseService.isLike(new CourseLikeDTO(userId,courseId)),
                courseService.isFavorite(new CourseFavoriteDTO(userId,courseId))
        );

        return ResponseEntity.success(courseVO);
    }

    @GetMapping("/list")
    public ResponseEntity<List<CourseVO>> getAll(@Attribute("userId") Long userId){
        return ResponseEntity.success(courseService.getAll()
                .stream()
                .map(course -> getCourse(course.getCourseId(), userId).getData())
                .toList());
    }

    @PostMapping("/list")
    public ResponseEntity<List<CourseVO>> getList(@RequestBody List<Long> idList,@Attribute("userId") Long userId){
        return ResponseEntity.success(courseService.getByIdList(idList)
                .stream()
                .map(course -> getCourse(course.getCourseId(),userId).getData())
                .toList());
    }

    @GetMapping("/search")
    public ResponseEntity<List<CourseVO>> search(@RequestParm("kw") String kw,@Attribute("userId") Long userId){
        return ResponseEntity.success(courseService.queryByName(kw)
                .stream()
                .map(course -> getCourse(course.getCourseId(),userId).getData())
                .toList());
    }

    @GetMapping("/{courseId}/chapter")
    public ResponseEntity<List<ChapterVO>> getChapter(@PathVariable("courseId") Long courseId){
        return ResponseEntity.success(courseService.getChapter(courseId).stream().map(ChapterVO::new).toList());
    }

    @GetMapping("/{courseId}/comment")
    public ResponseEntity<List<CourseCommentVO>> getComment(@PathVariable("courseId") Long courseId){
        return ResponseEntity.success(courseService.getComment(courseId).stream().map(CourseCommentVO::new).toList());
    }

    @PostMapping("/{courseId}/comment")
    public ResponseEntity<Long> comment(@PathVariable("courseId") Long courseId,@Attribute("userId") Long userId,@RequestBody CourseCommentDTO courseCommentDTO){
        if (!courseService.isExistById(courseId)){
            return ResponseEntity.failureMsg("课程: %d 不存在", courseId);
        }
        courseCommentDTO.setUserId(userId);
        courseCommentDTO.setCreateTime(System.currentTimeMillis());
        return ResponseEntity.success(courseService.comment(courseId,courseCommentDTO));
    }

    @PostMapping("/{courseId}/like")
    public ResponseEntity<Boolean> like(@PathVariable("courseId") Long courseId,@Attribute("userId") Long userId){
        if (!courseService.isExistById(courseId)){
            return ResponseEntity.failureMsg("课程: %d 不存在", courseId);
        }
        return ResponseEntity.success(courseService.like(new CourseLikeDTO(userId,courseId)));
    }
    @PostMapping("/{courseId}/favorite")
    public ResponseEntity<Boolean> favorite(@PathVariable("courseId") Long courseId,@Attribute("userId") Long userId){
        if (!courseService.isExistById(courseId)){
            return ResponseEntity.failureMsg("课程: %d 不存在", courseId);
        }
        return ResponseEntity.success(courseService.favorite(new CourseFavoriteDTO(userId,courseId)));
    }


}
