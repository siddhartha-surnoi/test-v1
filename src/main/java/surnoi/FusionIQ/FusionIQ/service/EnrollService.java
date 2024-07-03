package surnoi.FusionIQ.FusionIQ.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import surnoi.FusionIQ.FusionIQ.data.BasicInfo;
import surnoi.FusionIQ.FusionIQ.data.CourseCard;
import surnoi.FusionIQ.FusionIQ.data.Enroll;
import surnoi.FusionIQ.FusionIQ.exception.UserNotFoundException;
import surnoi.FusionIQ.FusionIQ.repo.BasicInfoRepository;
import surnoi.FusionIQ.FusionIQ.repo.CourseCardRepo;
import surnoi.FusionIQ.FusionIQ.repo.EnrollRepo;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class EnrollService {
    @Autowired
    private final EnrollRepo enrollRepo;
    @Autowired
    private final BasicInfoRepository basicInfoRepository;
    @Autowired
    private final CourseCardRepo courseCardRepo;
    @Autowired
    private final JavaMailSender javaMailSender;
    @Autowired
    public EnrollService(EnrollRepo enrollRepo, BasicInfoRepository basicInfoRepository, CourseCardRepo courseCardRepo, JavaMailSender javaMailSender) {
        this.enrollRepo = enrollRepo;
        this.basicInfoRepository = basicInfoRepository;
        this.courseCardRepo = courseCardRepo;
        this.javaMailSender = javaMailSender;
    }
//    public Enroll enrollInCourse(Long basicInfoId, Long courseId) {
//        BasicInfo basicInfo = basicInfoRepository.findById(basicInfoId)
//                .orElseThrow(() -> new UserNotFoundException("User not found with id " + basicInfoId));
//        CourseCard course = courseCardRepo.findById(courseId)
//                .orElseThrow(() -> new UserNotFoundException("Course not found with id " + courseId));
//
//        Enroll enrollment = new Enroll();
//        enrollment.setBasicInfo(basicInfo);
//        enrollment.setCourseCard(course);
//
//        return enrollRepo.save(enrollment);
//    }

//    public List<Enroll> getEnrollmentsByUser(Long basicInfoId) {
//        BasicInfo basicInfo = basicInfoRepository.findById(basicInfoId)
//                .orElseThrow(() -> new UserNotFoundException("User not found with id " + basicInfoId));
//        return enrollRepo.findByBasicInfo(basicInfo);
//    }
    public List<Enroll> getEnrollmentsByCourse(Long courseId) {
        CourseCard courseCard = courseCardRepo.findById(courseId)
                .orElseThrow(() -> new UserNotFoundException("Course not found with id " + courseId));
        return enrollRepo.findByCourseCard(courseCard);
    }



    public Enroll enrollCourse(Enroll enroll) {
        return enrollRepo.save(enroll);
    }
    public Optional<Enroll> findByBasicInfoId(Long basicInfoId) {
        return enrollRepo.findById(basicInfoId);
    }
    public Optional<Enroll> getEnrollWithDetails(long id) {
        return enrollRepo.findByEnrollIdWithDetails(id);
    }
//    public BasicInfo createBasicInfowithEnrolls(BasicInfo basicInfo) {
//        for (Enroll enroll : basicInfo.getEnrolls()) {
//            enroll.setBasicInfo(basicInfo);
//        }
//        return basicInfoRepository.save(basicInfo);
//    }

//    public CourseCard saveRequest(CourseCard courseCard) {
//        for (Enroll enroll : courseCard.getEnrolls()) {
//            enroll.setCourseCard(courseCard);
//        }
//        return courseCardRepo.save(courseCard);
//    }

    public List<Enroll> getAllEnrolls() {
        return enrollRepo.findAll();
    }

    public Optional<Enroll> getEnrollById(Long id) {
        return enrollRepo.findById(id);
    }
//---for otp
    private static final int OTP_LENGTH = 6;
    private static final int OTP_EXPIRY_MINUTES = 10;

    public String generateAndSaveOTP(String email) {
        String otp = generateRandomOTP();
        BasicInfo user = basicInfoRepository.findByEmail(email);
        if (user != null) {
            user.setOtp(otp);
            user.setOtpGeneratedTime(LocalDateTime.now());
            basicInfoRepository.save(user);
            return otp;
        }
        return null;
    }

    private String generateRandomOTP() {
        String numbers = "0123456789";
        StringBuilder otpBuilder = new StringBuilder(OTP_LENGTH);
        Random random = new Random();
        for (int i = 0; i < OTP_LENGTH; i++) {
            otpBuilder.append(numbers.charAt(random.nextInt(numbers.length())));
        }
        return otpBuilder.toString();
    }

//    public void sendOTPByEmail(String email, String otp) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(email);
//        message.setSubject("Your One-Time Password (OTP)");
//        message.setText("Your One Time Password to Enroll FusionIQ.AI is: " + otp +
//                ".Please do not share this code with anyone");
//        javaMailSender.send(message);
//    }
public void sendOTPByEmail(String email, String otp) {
    try {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(email);
        helper.setSubject("Your One-Time Password (OTP)");

        String content = "<p>Your One Time Password to Enroll FusionIQ.AI is: <b>" + otp + "</b>.</p>"
                + "<p>Please do not share this code with anyone.</p>"
                + "<p>This OTP is valid for 10 minutes.</p>";

        helper.setText(content, true); // Set to true to enable HTML

        javaMailSender.send(message);
    } catch (MessagingException e) {
        // Handle the exception as needed
        e.printStackTrace();
    }
}

//    public boolean verifyOTP(String email, String otp) {
//        BasicInfo user = basicInfoRepository.findByEmail(email);
//        if (user != null && user.getOtp() != null && user.getOtp().equals(otp)) {
//            if (isOTPExpired(user.getOtpGeneratedTime())) {
//                return false;
//            }
//            user.setOtp(null);
//            user.setOtpGeneratedTime(null);
//            basicInfoRepository.save(user);
//            return true;
//        }
//        return false;
//    }
//    private boolean isOTPExpired(LocalDateTime otpGeneratedTime) {
//        return otpGeneratedTime.plusMinutes(OTP_EXPIRY_MINUTES).isBefore(LocalDateTime.now());
//    }
    public boolean verifyOTP(String email, String otp) {
        BasicInfo user = basicInfoRepository.findByEmail(email);
        if (user != null && user.getOtp() != null && user.getOtp().equals(otp)) {
            if (isOTPExpired(user.getOtpGeneratedTime())) {
                return false;
            }
            user.setOtp(null);
            user.setOtpGeneratedTime(null);
            basicInfoRepository.save(user);
            return true;
        }
        return false;
    }

    private boolean isOTPExpired(LocalDateTime otpGeneratedTime) {
        return otpGeneratedTime.plusMinutes(OTP_EXPIRY_MINUTES).isBefore(LocalDateTime.now());
    }

    public Enroll enrollInCourse(Long basicInfoId, Long courseId) {
        BasicInfo basicInfo = basicInfoRepository.findById(basicInfoId)
                .orElseThrow(() -> new RuntimeException("BasicInfo not found"));
        CourseCard course = courseCardRepo.findById(courseId)
                .orElseThrow(() -> new RuntimeException("CourseCard not found"));

        Enroll enroll = new Enroll();
        enroll.setBasicInfo(basicInfo);
        enroll.setCourseCard(course);

        return enrollRepo.save(enroll);
    }

    public void sendEnrollmentConfirmationEmail(String to, String firstName, String courseTitle, Double fee) {
        String subject = "Course Enrollment Successful";
        String body = "Dear " + firstName + ",\n\n" +
                "You have successfully enrolled in the course: " + courseTitle + ".\n" +
                "Course Fee: $" + fee + "\n\n" +
                "We appreciate your trust in us and look forward to providing you with the best learning experience. If you have any questions or need assistance, please feel free to reach out to us. We will get back to you as soon as possible.\n\n" +
                "Best regards,\n" +
                "FUSIONIQ.AI";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
    }
    @Transactional(readOnly = true)
    public List<Enroll> getEnrollmentsByUser(Long basicInfoId) {
        List<Enroll> enrollments = enrollRepo.findByBasicInfoId(basicInfoId);
        enrollments.forEach(enroll -> {
            if (enroll.getCourseCard() != null) {
                enroll.getCourseCard().getCourseId(); // Ensure lazy-loaded field is fetched
            }
        });
        return enrollments;
    }
}
