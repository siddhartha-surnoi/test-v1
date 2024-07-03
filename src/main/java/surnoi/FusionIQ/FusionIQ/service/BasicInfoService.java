package surnoi.FusionIQ.FusionIQ.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import surnoi.FusionIQ.FusionIQ.data.BasicInfo;

import surnoi.FusionIQ.FusionIQ.data.CourseCard;
import surnoi.FusionIQ.FusionIQ.data.Enroll;
import surnoi.FusionIQ.FusionIQ.repo.BasicInfoRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
@Service
public class BasicInfoService {
    private final BasicInfoRepository basicInfoRepository;
    private final TokenService tokenService;
    @Autowired
    public BasicInfoService(BasicInfoRepository basicInfoRepository, TokenService tokenService) {
        this.basicInfoRepository = basicInfoRepository;
        this.tokenService = tokenService;
    }
    public List<BasicInfo> getAllBasicInfo() {
        return basicInfoRepository.findAll();
    }
    public Optional<BasicInfo> getBasicInfoById(long id) {
        return basicInfoRepository.findById(id);
    }
    public BasicInfo saveBasicInfo(BasicInfo basicInfo) {
        return basicInfoRepository.save(basicInfo);
    }

//    public BasicInfo saveBasicInfoWithEnroll(BasicInfo basicInfo) {
//        for (Enroll enroll : basicInfo.getEnrolls()) {
//            enroll.setBasicInfo(basicInfo);
//        }
//        for (CourseCard courseCard : basicInfo.getCourseCard()) {
//            courseCard.setBasicInfo(basicInfo);
//        }
//        return basicInfoRepository.save(basicInfo);
//    }

    public BasicInfo saveBasicInfoWithEnroll(BasicInfo basicInfo) {
        List<Enroll> enrolls = basicInfo.getEnrolls();
        List<CourseCard> courseCards = basicInfo.getCourseCards();

        if (enrolls != null) {
            for (int i = 0; i < enrolls.size(); i++) {
                Enroll enroll = enrolls.get(i);
                enroll.setBasicInfo(basicInfo);
            }
        }

        if (courseCards != null) {
            for (int i = 0; i < courseCards.size(); i++) {
                CourseCard courseCard = courseCards.get(i);
                courseCard.setBasicInfo(basicInfo);
            }
        }

        return basicInfoRepository.save(basicInfo);
    }

    public BasicInfo saveBasicInfoWithImage(long id,MultipartFile imageFile,MultipartFile resume, String firstName, String lastName, long phoneNumber, String email, String city, String pinCode, String state, String dateOfBirth, String yourNameOnCertificate, String linkedInProfileUrl, String sscBoard, String sscInstitution, String sscYop, String sscCGPA, String intermediateOrDiplomaInstitution, String intermediateOrDiplomaStream, String intermediateOrDiplomaYop, String intermediateOrDiplomaCGPA, String degreeUniversity, String degreeStream, String degreeYop, String degreeCGPA, String postGraduateUniversity, String postGraduateStream, String postGraduateYop, String postGraduateCGPA) throws IOException {
        BasicInfo basicInfo = new BasicInfo();
        basicInfo.setId(id);
        basicInfo.setFirstName(firstName);
        basicInfo.setLastName(lastName);
        basicInfo.setPhoneNumber(phoneNumber);
        basicInfo.setEmail(email);
        basicInfo.setCity(city);
        basicInfo.setPinCode(pinCode);
        basicInfo.setState(state);
        basicInfo.setDateOfBirth(dateOfBirth);
        basicInfo.setYourNameOnCertificate(yourNameOnCertificate);
        basicInfo.setLinkedInProfileUrl(linkedInProfileUrl);
        basicInfo.setSscBoard(sscBoard);
        basicInfo.setSscInstitution(sscInstitution);
        basicInfo.setSscYop(sscYop);
        basicInfo.setSscCGPA(sscCGPA);
        basicInfo.setIntermediateOrDiplomaInstitution(intermediateOrDiplomaInstitution);
        basicInfo.setIntermediateOrDiplomaStream(intermediateOrDiplomaStream);
        basicInfo.setIntermediateOrDiplomaYop(intermediateOrDiplomaYop);
        basicInfo.setIntermediateOrDiplomaCGPA(intermediateOrDiplomaCGPA);
        basicInfo.setDegreeUniversity(degreeUniversity);
        basicInfo.setDegreeStream(degreeStream);
        basicInfo.setDegreeYop(degreeYop);
        basicInfo.setDegreeCGPA(degreeCGPA);
        basicInfo.setPostGraduateUniversity(postGraduateUniversity);
        basicInfo.setPostGraduateStream(postGraduateStream);
        basicInfo.setPostGraduateYop(postGraduateYop);
        basicInfo.setPostGraduateCGPA(postGraduateCGPA);
        basicInfo.setImage(imageFile.getBytes());
        basicInfo.setResume(resume.getBytes());
        return basicInfoRepository.save(basicInfo);
    }
    public void deleteById(long id) {
        basicInfoRepository.deleteById(id);
    }
    public BasicInfo registerUser(BasicInfo registrationData) {
        return basicInfoRepository.save(registrationData);
    }
    public boolean validateLogin(String email, String password) {
        BasicInfo user = basicInfoRepository.findByEmailAndPassword(email, password);
        return user != null;
    }
    public BasicInfo getByEmail(String email) {
        return basicInfoRepository.findByEmail(email);
    }
    public boolean existsByEmail(String email) {
        return basicInfoRepository.existsByEmail(email);
    }
    public boolean updatePassword(String email, String currentPassword, String newPassword) {
        BasicInfo user = getByEmail(email);
        if (user != null && user.getPassword().equals(currentPassword)) {
            user.setPassword(newPassword);
            basicInfoRepository.save(user);
            return true;
        }
        return false;
    }
    public BasicInfo login(String mail, String password) {
        BasicInfo user = basicInfoRepository.findByEmail(mail);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public String generateTokenForUser(BasicInfo user) {
        return tokenService.generateToken(user.getEmail());
    }
    public boolean validateToken(String token, BasicInfo user) {
        return tokenService.validateToken(token, user);
    }

//    --------
//    public void saveImagePath(String imagePath) {
//        // Save the image path to MySQL
//        BasicInfo basicInfo = new BasicInfo();
//       basicInfo.setImage(imagePath);
//        basicInfoRepository.save(basicInfo);
//    }
//    public void saveResumePath(String resumePath) {
//        // Save the image path to MySQL
//        BasicInfo basicInfo = new BasicInfo();
//        basicInfo.setResume(resumePath);
//        basicInfoRepository.save(basicInfo);
//    }
//    public List<BasicInfo> getAllImage() {
//        return basicInfoRepository.findAll();
//    }
}
 