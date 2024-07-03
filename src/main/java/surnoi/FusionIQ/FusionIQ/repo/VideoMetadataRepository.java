package surnoi.FusionIQ.FusionIQ.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import surnoi.FusionIQ.FusionIQ.data.CourseCard;
import surnoi.FusionIQ.FusionIQ.data.VideoMetadata;

import java.util.List;

public interface VideoMetadataRepository extends JpaRepository<VideoMetadata,Long> {
    List<VideoMetadata> findByCourseCard(CourseCard courseCard);
}
