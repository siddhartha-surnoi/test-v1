package surnoi.FusionIQ.FusionIQ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import surnoi.FusionIQ.FusionIQ.data.BasicInfo;
import surnoi.FusionIQ.FusionIQ.data.CourseCard;
import surnoi.FusionIQ.FusionIQ.data.VideoMetadata;
import surnoi.FusionIQ.FusionIQ.exception.UserNotFoundException;
import surnoi.FusionIQ.FusionIQ.repo.BasicInfoRepository;
import surnoi.FusionIQ.FusionIQ.repo.CourseCardRepo;
import surnoi.FusionIQ.FusionIQ.repo.VideoMetadataRepository;

import java.util.List;

@Service
public class VideoMetadataService {
    @Autowired
    private final VideoMetadataRepository videoMetadataRepository;
    @Autowired
    private final CourseCardRepo courseCardRepo;
    @Autowired
    private final BasicInfoRepository basicInfoRepository;
    @Autowired
    public VideoMetadataService(VideoMetadataRepository videoMetadataRepository, CourseCardRepo courseCardRepo, BasicInfoRepository basicInfoRepository) {
        this.videoMetadataRepository = videoMetadataRepository;
        this.courseCardRepo = courseCardRepo;
        this.basicInfoRepository = basicInfoRepository;
    }

//    public VideoMetadata uploadVideo(Long courseId, VideoMetadata videoMetadata) {
//        CourseCard course = courseCardRepo.findById(courseId)
//                .orElseThrow(() -> new UserNotFoundException("Course not found with id " + courseId));
//        videoMetadata.setCourseCard(course);
//        return videoMetadataRepository.save(videoMetadata);
//    }
    public List<VideoMetadata> getVideosByCourse(Long courseId) {
        CourseCard course = courseCardRepo.findById(courseId)
                .orElseThrow(() -> new UserNotFoundException("Course not found with id " + courseId));
        return videoMetadataRepository.findByCourseCard(course);
    }
public VideoMetadata uploadVideo(Long courseId, Long basicInfoId, VideoMetadata videoMetadata) {
    CourseCard course = courseCardRepo.findById(courseId)
            .orElseThrow(() -> new UserNotFoundException("Course not found with id " + courseId));
    BasicInfo basicInfo = basicInfoRepository.findById(basicInfoId)
            .orElseThrow(() -> new UserNotFoundException("BasicInfo not found with id " + basicInfoId));

    videoMetadata.setCourseCard(course);
    videoMetadata.setBasicInfo(basicInfo);
    return videoMetadataRepository.save(videoMetadata);
}
    public VideoMetadata updateWatchedPercentage(Long videoId, String watchedPercentage) {
        VideoMetadata video = videoMetadataRepository.findById(videoId)
                .orElseThrow(() -> new UserNotFoundException("Video not found with id " + videoId));
        video.setWatchedPercentage(watchedPercentage);
        return videoMetadataRepository.save(video);
    }

    public VideoMetadata updateLastWatchedTime(Long videoId, String lastWatchedTime) {
        VideoMetadata video = videoMetadataRepository.findById(videoId)
                .orElseThrow(() -> new UserNotFoundException("Video not found with id " + videoId));
        video.setLastWatchedTime(lastWatchedTime);
        return videoMetadataRepository.save(video);
    }
}
