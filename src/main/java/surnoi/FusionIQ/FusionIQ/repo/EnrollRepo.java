package surnoi.FusionIQ.FusionIQ.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import surnoi.FusionIQ.FusionIQ.data.BasicInfo;
import surnoi.FusionIQ.FusionIQ.data.CourseCard;
import surnoi.FusionIQ.FusionIQ.data.Enroll;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollRepo  extends JpaRepository<Enroll,Long> {
    @Query("SELECT e FROM Enroll e " +
            "JOIN FETCH e.basicInfo bi " +
            "JOIN FETCH e.courseCard cc " +
            "WHERE e.enrollId = :enrollId")
    Optional<Enroll> findByEnrollIdWithDetails(Long enrollId);

    List<Enroll> findByBasicInfo(BasicInfo basicInfo);
    List<Enroll> findByCourseCard(CourseCard courseCard);

    List<Enroll> findByBasicInfoId(Long basicInfoId);
}
 