package surnoi.FusionIQ.FusionIQ.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import surnoi.FusionIQ.FusionIQ.data.CourseCard;

import java.util.List;

@Repository
public interface CourseCardRepo extends JpaRepository<CourseCard,Long> {
    CourseCard findByCourseTitle(String courseTitle);

    List<CourseCard> findByCourseTitleContainingIgnoreCase(String courseTitle);
    @Query("SELECT c FROM CourseCard c WHERE c.title = :title AND c.courseTitle = :courseTitle AND c.mentorName = :mentorName")
    List<CourseCard> findByTitleAndCourseTitleAndMentorName(@Param("title") String title,
                                                            @Param("courseTitle") String courseTitle,
                                                            @Param("mentorName") String mentorName);

    List<CourseCard> findByTitle(String title);
    List<CourseCard> findByBasicInfoId(Long basicInfoId);

//    CourseCurriculum findByCourseTitle(String courseTitle);

//    List<CourseCurriculum> findByCourseTitleContainingIgnoreCase(String courseTitle);
}

