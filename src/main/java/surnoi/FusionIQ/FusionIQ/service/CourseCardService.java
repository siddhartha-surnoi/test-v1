package surnoi.FusionIQ.FusionIQ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import surnoi.FusionIQ.FusionIQ.data.*;
import surnoi.FusionIQ.FusionIQ.repo.BasicInfoRepository;
import surnoi.FusionIQ.FusionIQ.repo.CourseCardRepo;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CourseCardService {

    @Autowired
    private final CourseCardRepo courseCardRepo;
    @Autowired
    private final BasicInfoRepository basicInfoRepository;

    @Autowired
    public CourseCardService(CourseCardRepo courseCardRepo, BasicInfoRepository basicInfoRepository) {
        this.courseCardRepo = courseCardRepo;
        this.basicInfoRepository = basicInfoRepository;
    }

    public List<CourseCard> getCourseCardsByTitleAndCourseTitleAndMentorName(String title, String courseTitle, String mentorName) {
        return courseCardRepo.findByTitleAndCourseTitleAndMentorName(title, courseTitle, mentorName);
    }
    public List<CourseCard> getCourseByTitle(String title) {
        return courseCardRepo.findByTitle(title);
    }

    public List<CourseCard> getAllUsers() {
        return courseCardRepo.findAll();
    }
    //    public List<CourseCard> getAllCards() {
//        return courseCardRepo.findAll();
//    }
//    public CourseCard saveImage(MultipartFile courseImage,MultipartFile mentorImage,String title,double fee, String duration, String skills, String courseType,String targetAudience,String prerequisites,String mentorName,String courseTitle,String designation,String projectTitle,String projectOverview,String projectObjectives,String projectScope,String projectTimeline,String projectMethodology) throws IOException {
//        CourseCard newImage = new CourseCard();
//        newImage.setTitle(title);
//        newImage.setFee(fee);
//        newImage.setDuration(duration);
//        newImage.setSkills(skills);
//        newImage.setCourseType(courseType);
//        newImage.setCourseImage(courseImage.getBytes());
//        newImage.setMentorImage(mentorImage.getBytes());
//        newImage.setTargetAudience(targetAudience);
//        newImage.setPrerequisites(prerequisites);
//        newImage.setMentorName(mentorName);
//        newImage.setCourseTitle(courseTitle);
//        newImage.setDesignation(designation);
//        newImage.setProjectTitle(projectTitle);
//        newImage.setProjectOverview(projectOverview);
//        newImage.setProjectScope(projectScope);
//        newImage.setProjectTimeline(projectTimeline);
//        newImage.setProjectMethodolgy(projectMethodology);
//        return courseCardRepo.save(newImage);
//    }
    public CourseCard saveImage(MultipartFile courseImage, MultipartFile mentorImage, String title, double fee, String duration, String skills, String courseType, String targetAudience, String prerequisites, String mentorName, String courseTitle, String designation, String projectTitle, String projectOverview, String projectObjectives, String projectScope, String projectTimeline, String projectMethodology, Long basicInfoId) throws IOException {
        CourseCard newImage = new CourseCard();
        newImage.setTitle(title);
        newImage.setFee(fee);
        newImage.setDuration(duration);
        newImage.setSkills(skills);
        newImage.setCourseType(courseType);
        newImage.setCourseImage(courseImage.getBytes());
        newImage.setMentorImage(mentorImage.getBytes());
        newImage.setTargetAudience(targetAudience);
        newImage.setPrerequisites(prerequisites);
        newImage.setMentorName(mentorName);
        newImage.setCourseTitle(courseTitle);
        newImage.setDesignation(designation);
        newImage.setProjectTitle(projectTitle);
        newImage.setProjectOverview(projectOverview);
        newImage.setProjectObjectives(projectObjectives);
        newImage.setProjectScope(projectScope);
        newImage.setProjectTimeline(projectTimeline);
        newImage.setProjectMethodolgy(projectMethodology);

        // Set the basic info
        Optional<BasicInfo> basicInfoOptional = basicInfoRepository.findById(basicInfoId);
        if (basicInfoOptional.isPresent()) {
            newImage.setBasicInfo(basicInfoOptional.get());
        } else {
            throw new IllegalArgumentException("Invalid basicInfoId");
        }

        return courseCardRepo.save(newImage);
    }

    public List<CourseCard> getCourseCardsByBasicInfoId(Long basicInfoId) {
        return courseCardRepo.findByBasicInfoId(basicInfoId);
    }
//    public CourseCard saveImage(Long cardId, MultipartFile image) throws Exception {
//        Optional<CourseCard> optionalCourseCard = courseCardRepo.findById(cardId);
//        if (!optionalCourseCard.isPresent()) {
//            throw new Exception("CourseCard not found with id: " + cardId);
//        }
//        CourseCard courseCard = optionalCourseCard.get();
//        courseCard.setData(image.getBytes());
//        return courseCardRepo.save(courseCard);
//    }

    public CourseCard updateFields(Long cardId, String title, String duration, String skills, String courseType) {
        Optional<CourseCard> optionalCourseCard = courseCardRepo.findById(cardId);
        if (!optionalCourseCard.isPresent()) {
            throw new RuntimeException("CourseCard not found with id: " + cardId);
        }
        CourseCard courseCard = optionalCourseCard.get();
        courseCard.setTitle(title);
        courseCard.setDuration(duration);
        courseCard.setSkills(skills);
        courseCard.setCourseType(courseType);
        return courseCardRepo.save(courseCard);
    }
//    public List<CourseCard> getAllCourseCard() {
//        return courseCardRepo.findAll();
//    }

    public Optional<CourseCard> getCourseCard(long id) {
        return courseCardRepo.findById(id);
    }

    //    public CourseCard createCourseCard(CourseCard courseCard) {
//        return courseCardRepo.save(courseCard);
//    }
//    public CourseCard save(CourseCard courseCard) {
//        // You may need to add additional logic here before saving the course card
//        return courseCardRepo.save(courseCard);
//    }
    public Optional<CourseCard> getImageById(Long id) {
        return courseCardRepo.findById(id);
    }

//    public CourseCard createCourseCardWithEnrolls(CourseCard courseCard) {
//        for (Enroll enroll : courseCard.getEnrolls()) {
//            enroll.setCourseCard(courseCard);
//        }
//        for (Rating rating : courseCard.getRatings()) {
//            rating.setCourseCard(courseCard);
//        }
//        for (VideoMetadata videoMetadata : courseCard.getVideos()) {
//            videoMetadata.setCourseCard(courseCard);
//        }
//
//        return courseCardRepo.save(courseCard);
//    }

//    public CourseCard createCourseCardWithEnrolls(CourseCard courseCard) {
//        List<Enroll> enrolls = courseCard.getEnrolls();
//        List<Rating> ratings = courseCard.getRatings();
//        List<VideoMetadata> videos = courseCard.getVideos();
//
//        if (enrolls != null) {
//            for (int i = 0; i < enrolls.size(); i++) {
//                Enroll enroll = enrolls.get(i);
//                enroll.setCourseCard(courseCard);
//            }
//        }
//
//        if (ratings != null) {
//            for (int i = 0; i < ratings.size(); i++) {
//                Rating rating = ratings.get(i);
//                rating.setCourseCard(courseCard);
//            }
//        }
//
//        if (videos != null) {
//            for (int i = 0; i < videos.size(); i++) {
//                VideoMetadata videoMetadata = videos.get(i);
//                videoMetadata.setCourseCard(courseCard);
//            }
//        }
//
//        return courseCardRepo.save(courseCard);
//    }

    public CourseCard getCourseByCourseTitle(String courseTitle) {
        return courseCardRepo.findByCourseTitle(courseTitle);
    }

    public  List<CourseCard> getCoursesByTitleKeyword(String keyword) {
        return courseCardRepo.findByCourseTitleContainingIgnoreCase(keyword);

    }
    public Optional<CourseCard> getCourseCardById(Long id) {
        return courseCardRepo.findById(id);
    }

    public CourseCard getCourseCardByBasicInfoId(Long basicInfoId) {
        return (CourseCard) courseCardRepo.findByBasicInfoId(basicInfoId);
    }
//    public List<CourseCard> getCoursesByTitleKeyword(String keyword) {
//        return courseCardRepo.findByCourseTitleContainingIgnoreCase(keyword);
//
//    }

}

