package surnoi.FusionIQ.FusionIQ.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import surnoi.FusionIQ.FusionIQ.data.BasicInfo;
import surnoi.FusionIQ.FusionIQ.data.CourseCard;
import surnoi.FusionIQ.FusionIQ.data.Enroll;
import surnoi.FusionIQ.FusionIQ.data.LoginRequest;
import surnoi.FusionIQ.FusionIQ.exception.UserNotFoundException;
import surnoi.FusionIQ.FusionIQ.repo.BasicInfoRepository;
import surnoi.FusionIQ.FusionIQ.repo.CourseCardRepo;
import surnoi.FusionIQ.FusionIQ.repo.EnrollRepo;
import surnoi.FusionIQ.FusionIQ.service.EmailSenderService;
import surnoi.FusionIQ.FusionIQ.service.EnrollService;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/enroll")
public class EnrollController {
    @Autowired
    private EnrollRepo enrollRepo;
    @Autowired
    private BasicInfoRepository basicInfoRepository;
    @Autowired
    private CourseCardRepo courseCardRepo;
    @Autowired
    private EnrollService enrollService;
    @Autowired
    private EmailSenderService emailSenderService;

// ------

//    @PostMapping("/enroll")
//    public ResponseEntity<?> enrollInCourse(@RequestParam Long basicInfoId, @RequestParam Long courseId) {
//        BasicInfo basicInfo = basicInfoRepository.findById(basicInfoId)
//                .orElseThrow(() -> new UserNotFoundException("User not found with id " + basicInfoId));
//        CourseCard course = courseCardRepo.findById(courseId)
//                .orElseThrow(() -> new UserNotFoundException("Course not found with id " + courseId));
//
//        Enroll enrollment = new Enroll();
//        enrollment.setBasicInfo(basicInfo);
//        enrollment.setCourseCard(course);
//        Enroll savedEnrollment = enrollService.enrollInCourse(enrollment);
//
//        // Send enrollment confirmation email
//        sendEnrollmentConfirmationEmail(basicInfo.getEmail(), basicInfo.getFirstName(), course.getTitle(), course.getFee());
//
//        return new ResponseEntity<>(savedEnrollment, HttpStatus.CREATED);
//    }
@PostMapping("/{basicInfoId}/course/{courseId}")
public ResponseEntity<?> enrollInCourse(@PathVariable Long basicInfoId, @PathVariable Long courseId) {
    Enroll savedEnrollment = enrollService.enrollInCourse(basicInfoId, courseId);

    // Send enrollment confirmation email
    BasicInfo basicInfo = savedEnrollment.getBasicInfo();
    CourseCard course = savedEnrollment.getCourseCard();
    sendEnrollmentConfirmationEmail(basicInfo.getEmail(), basicInfo.getFirstName(), course.getTitle(), course.getFee());

    return new ResponseEntity<>(savedEnrollment, HttpStatus.CREATED);
}
    private void sendEnrollmentConfirmationEmail(String to, String firstName, String courseTitle, Double Fee) {
        String subject = "Course Enrollment Successful";
        String body = "Dear " + firstName + ",\n\n" +
                "You have successfully enrolled in the course: " + courseTitle + ".\n" +
                "Course Fee: $" + Fee + "\n\n" +
                "We appreciate your trust in us and look forward to providing you with the best learning experience. If you have any questions or need assistance, please feel free to reach out to us. We will get back to you as soon as possible.\n\n" +
                "Best regards,\n" +
                "FUSIONIQ.AI";

        emailSenderService.sendEmail(to, subject, body);
    }


//    @PostMapping("/{basicInfoId}/course/{courseId}")
//    public ResponseEntity<Enroll> enrollInCourse(@PathVariable long basicInfoId, @PathVariable long courseId) {
//        Enroll enrollment = enrollService.enrollInCourse(basicInfoId, courseId);
//        return new ResponseEntity<>(enrollment, HttpStatus.CREATED);
//    }

//    @GetMapping("/user/{basicInfoId}")
//    public ResponseEntity<List<Enroll>> getEnrollmentsByUser(@PathVariable Long basicInfoId) {
//        List<Enroll> enrollments = enrollService.getEnrollmentsByUser(basicInfoId);
//        return new ResponseEntity<>(enrollments, HttpStatus.OK);
//    }
    @GetMapping("/user/{basicInfoId}")
    public ResponseEntity<List<Enroll>> getEnrollmentsByUser(@PathVariable Long basicInfoId) {
        List<Enroll> enrollments = enrollService.getEnrollmentsByUser(basicInfoId);
        return new ResponseEntity<>(enrollments, HttpStatus.OK);
    }
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Enroll>> getEnrollmentsByCourse(@PathVariable Long courseId) {
        List<Enroll> enrollments = enrollService.getEnrollmentsByCourse(courseId);
        return new ResponseEntity<>(enrollments, HttpStatus.OK);
    }

//    -----

//    @PostMapping("enroll")
//    public ResponseEntity<Enroll> saveRequest(@RequestBody Enroll enroll) {
//        return new ResponseEntity<>(enrollRepo.save(enroll), HttpStatus.CREATED);
//    }

    @GetMapping("get/enroll")

    public ResponseEntity<List<Enroll>> getEmployeesSortedByIdDescending() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return new ResponseEntity<>(enrollRepo.findAll(sort), HttpStatus.OK);
    }
    @GetMapping("/get/enroll/{id}")
    public ResponseEntity<Enroll> getEmployee(@PathVariable long id ) {
        Optional<Enroll> fusionEntity= enrollRepo.findById(id);
        if (fusionEntity.isPresent()) {
            return new ResponseEntity<>(fusionEntity.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enroll> getEnrollById(@PathVariable Long id) {
        Optional<Enroll> enroll = enrollRepo.findByEnrollIdWithDetails(id);
        return enroll.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

//        Use it for Send OTP
//    @PostMapping("/verify")
//    public ResponseEntity<String> verifyOTP(@RequestParam String email, @RequestParam String otp) {
//        boolean isOTPValid = enrollService.verifyOTP(email, otp);
//        if (isOTPValid) {
//            return new ResponseEntity<>("OTP verified successfully", HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Invalid or expired OTP", HttpStatus.UNAUTHORIZED);
//        }
//    }
    @PostMapping("/generateOtp")
    public ResponseEntity<String> sendOtp(@RequestBody LoginRequest loginRequest) {
        String otp = enrollService.generateAndSaveOTP(loginRequest.getEmail());
        if (otp != null) {
            enrollService.sendOTPByEmail(loginRequest.getEmail(), otp);
            return new ResponseEntity<>("OTP has been sent to your email", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to generate OTP", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyOTP(@RequestParam String email,
                                            @RequestParam String otp,
                                            @RequestParam Long basicInfoId,
                                            @RequestParam Long courseId) {
        boolean isOTPValid = enrollService.verifyOTP(email, otp);
        if (isOTPValid) {
            Enroll savedEnrollment = enrollService.enrollInCourse(basicInfoId, courseId);

            BasicInfo basicInfo = savedEnrollment.getBasicInfo();
            CourseCard course = savedEnrollment.getCourseCard();
            enrollService.sendEnrollmentConfirmationEmail(basicInfo.getEmail(), basicInfo.getFirstName(), course.getTitle(), course.getFee());

            return new ResponseEntity<>("OTP verified and enrolled successfully. Confirmation email sent.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid or expired OTP", HttpStatus.UNAUTHORIZED);
        }
    }

}
 
 
 
 
 
 