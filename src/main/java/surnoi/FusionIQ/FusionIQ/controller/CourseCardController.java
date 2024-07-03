package surnoi.FusionIQ.FusionIQ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import surnoi.FusionIQ.FusionIQ.data.CourseCard;
import surnoi.FusionIQ.FusionIQ.repo.CourseCardRepo;
import surnoi.FusionIQ.FusionIQ.service.CourseCardService;

import java.util.List;
import java.util.Optional;

@RestController
public class CourseCardController {

    @Autowired
    private final CourseCardService courseCardService;
    @Autowired
    private CourseCardRepo courseCardRepo;


//    @PostMapping("/uploadCard")
//    public ResponseEntity<CourseCard> uploadImage(@RequestParam("courseImage") MultipartFile courseImage,@RequestParam("file") MultipartFile mentorImage,@RequestParam double fee, @RequestParam String title, @RequestParam String duration, @RequestParam String skills, @RequestParam String courseType, @RequestParam String targetAudience,@RequestParam String prerequisites,@RequestParam String mentorName,@RequestParam String courseTitle,@RequestParam String designation,@RequestParam String projectTitle,@RequestParam String projectOverview,@RequestParam String projectObjectives,@RequestParam String projectScope,@RequestParam String projectTimeline,@RequestParam String projectMethodology) throws Exception {
//        CourseCard newImage = courseCardService.saveImage(courseImage,mentorImage,title,fee, duration, skills, courseType,targetAudience,prerequisites,mentorName,courseTitle,designation,projectTitle,projectOverview,projectObjectives,projectScope,projectTimeline,projectMethodology);
//        return new ResponseEntity<>(newImage, HttpStatus.CREATED);
//
//    }
@PostMapping("/uploadCard/{basicInfoId}")
public ResponseEntity<CourseCard> uploadImage(@PathVariable Long basicInfoId, @RequestParam("courseImage") MultipartFile courseImage, @RequestParam("file") MultipartFile mentorImage, @RequestParam double fee, @RequestParam String title, @RequestParam String duration, @RequestParam String skills, @RequestParam String courseType, @RequestParam String targetAudience, @RequestParam String prerequisites, @RequestParam String mentorName, @RequestParam String courseTitle, @RequestParam String designation, @RequestParam String projectTitle, @RequestParam String projectOverview, @RequestParam String projectObjectives, @RequestParam String projectScope, @RequestParam String projectTimeline, @RequestParam String projectMethodology
) throws Exception {
    CourseCard newImage = courseCardService.saveImage(courseImage, mentorImage, title, fee, duration, skills, courseType, targetAudience, prerequisites, mentorName, courseTitle, designation, projectTitle, projectOverview, projectObjectives, projectScope, projectTimeline, projectMethodology, basicInfoId);
    return new ResponseEntity<>(newImage, HttpStatus.CREATED);
}

//    @GetMapping("/courseCards/{basicInfoId}")
//    public ResponseEntity<CourseCard> getCourseCard(@PathVariable Long basicInfoId) {
//        CourseCard courseCard = courseCardService.getCourseCardByBasicInfoId(basicInfoId);
//
//        if (courseCard == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        CourseCard response = new CourseCard();
//        response.setTitle(courseCard.getTitle());
//        response.setFee(courseCard.getFee());
//        response.setDuration(courseCard.getDuration());
//        response.setSkills(courseCard.getSkills());
//        response.setCourseType(courseCard.getCourseType());
//        response.setTargetAudience(courseCard.getTargetAudience());
//        response.setPrerequisites(courseCard.getPrerequisites());
//        response.setMentorName(courseCard.getMentorName());
//        response.setCourseTitle(courseCard.getCourseTitle());
//        response.setDesignation(courseCard.getDesignation());
//        response.setProjectTitle(courseCard.getProjectTitle());
//        response.setProjectOverview(courseCard.getProjectOverview());
//        response.setProjectObjectives(courseCard.getProjectObjectives());
//        response.setProjectScope(courseCard.getProjectScope());
//        response.setProjectTimeline(courseCard.getProjectTimeline());
//        response.setProjectMethodolgy(courseCard.getProjectMethodolgy());
//
//        // Set other properties as needed
//
//        return ResponseEntity.ok(response);
//    }
    @GetMapping("/courseCardsByBasicInfo/{basicInfoId}")
    public ResponseEntity<List<CourseCard>> getCourseCardsByBasicInfoId(@PathVariable Long basicInfoId) {
        List<CourseCard> courseCards = courseCardService.getCourseCardsByBasicInfoId(basicInfoId);
        if (!courseCards.isEmpty()) {
            return new ResponseEntity<>(courseCards, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //    @GetMapping("/getCard")
//    public ResponseEntity<List<CourseCard>> getCard() {
//        return new ResponseEntity<>(courseCardRepo.findAll(), HttpStatus.OK);
//    }
    @GetMapping("/getCard")
    public List<CourseCard> getAllUsers() {
        return courseCardService.getAllUsers();
    }

//    @GetMapping("/getCard")
//    public ResponseEntity<List<CourseCard>> getCards() {
//        List<CourseCard> cards = courseCardService.getAllCards();
//        if (cards.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(cards, HttpStatus.OK);
//    }


    @GetMapping("/courseCard/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        Optional<CourseCard> imageOptional = courseCardService.getImageById(id);
        if (imageOptional.isPresent()) {
            CourseCard image = imageOptional.get();
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image.getCourseImage());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @PostMapping("/uploadImage")
//    public ResponseEntity<CourseCard> uploadImage(@RequestParam("image") MultipartFile image, @RequestParam Long cardId) throws Exception {
//        CourseCard updatedCard = courseCardService.saveImage(cardId, image);
//        return new ResponseEntity<>(updatedCard, HttpStatus.CREATED);
//    }

//    @PostMapping("/addWithEnroll")
//    public ResponseEntity<CourseCard> createCourseCardWithEnrolls(@RequestBody CourseCard courseCard) {
//        CourseCard CreateCourseCardWithEnrolls = courseCardService.createCourseCardWithEnrolls(courseCard);
//        return ResponseEntity.status(HttpStatus.CREATED).body(CreateCourseCardWithEnrolls);
//    }

    @PutMapping("update/projects/{id}")
    public ResponseEntity<CourseCard> updateProject(@PathVariable long id, CourseCard courseCard) {
        Optional<CourseCard> project1 = courseCardRepo.findById(id);
        if (project1.isPresent()) {
            CourseCard existingFee = project1.get();
            existingFee.setCourseTitle(courseCard.getCourseTitle());

            existingFee.setProjectOverview(courseCard.getProjectOverview());
            existingFee.setDesignation(courseCard.getDesignation());
            existingFee.setPrerequisites(courseCard.getPrerequisites());
            existingFee.setProjectScope(courseCard.getProjectScope());
            existingFee.setProjectTimeline(courseCard.getProjectTimeline());
            existingFee.setMentorName(courseCard.getMentorName());
            existingFee.setProjectMethodolgy(courseCard.getProjectMethodolgy());
            existingFee.setTargetAudience(courseCard.getTargetAudience());
            existingFee.setProjectTitle(courseCard.getProjectTitle());
            existingFee.setProjectObjectives(courseCard.getProjectObjectives());


            return new ResponseEntity<>(courseCardRepo.save(existingFee), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/overview/{courseTitle}")
    public ResponseEntity<CourseCard> getCourseByCourseTitle(@PathVariable String courseTitle) {
        CourseCard courseCard = courseCardService.getCourseByCourseTitle(courseTitle);
        if (courseCard != null) {
            return ResponseEntity.ok(courseCard);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/allCourse/{courseTitle}")

    public  List<CourseCard> getCoursesByTitleKeyword(@PathVariable String courseTitle) {
        return courseCardService.getCoursesByTitleKeyword(courseTitle);

    }
    @GetMapping("/getByCourse/{id}")
    public ResponseEntity<CourseCard> getCourseCardById(@PathVariable Long id) {
        Optional<CourseCard> courseCardOptional = courseCardService.getCourseCardById(id);
        if (courseCardOptional.isPresent()) {
            return new ResponseEntity<>(courseCardOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    --
@GetMapping("/course/{title}")

public List<CourseCard> getCourseByTitle(@PathVariable String title) {

    return courseCardService.getCourseByTitle(title);

}
    @Autowired
    public CourseCardController(CourseCardService courseCardService) {
        this.courseCardService = courseCardService;
    }

    @GetMapping("/courseCards")
    public List<CourseCard> getCourseCardsByTitleAndCourseTitleAndMentorName(@RequestParam String title,
                                                                             @RequestParam String courseTitle,
                                                                             @RequestParam String mentorName) {
        return courseCardService.getCourseCardsByTitleAndCourseTitleAndMentorName(title, courseTitle, mentorName);
    }
}

