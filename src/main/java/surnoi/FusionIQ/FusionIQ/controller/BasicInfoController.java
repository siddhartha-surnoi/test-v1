package surnoi.FusionIQ.FusionIQ.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import surnoi.FusionIQ.FusionIQ.data.BasicInfo;
import surnoi.FusionIQ.FusionIQ.data.JwtResponse;
import surnoi.FusionIQ.FusionIQ.data.LoginRequest;
import surnoi.FusionIQ.FusionIQ.data.SimplifiedUserDetails;
import surnoi.FusionIQ.FusionIQ.service.BasicInfoService;
import surnoi.FusionIQ.FusionIQ.service.EmailSenderService;
import surnoi.FusionIQ.FusionIQ.service.TokenService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/basicInfo")
public class BasicInfoController {
    private final BasicInfoService basicInfoService;
    private final TokenService tokenService;
    private final EmailSenderService emailSenderService;
    @Autowired
    public BasicInfoController(BasicInfoService basicInfoService, TokenService tokenService, EmailSenderService emailSenderService) {
        this.basicInfoService = basicInfoService;
        this.tokenService = tokenService;
        this.emailSenderService = emailSenderService;
    }
    @GetMapping("/all")
    public ResponseEntity<List<BasicInfo>> getAllBasicInfo() {
        List<BasicInfo> basicInfos = basicInfoService.getAllBasicInfo();
        return ResponseEntity.ok(basicInfos);
    }
    @PostMapping("/add")
    public ResponseEntity<BasicInfo> createBasicInfo(@RequestBody BasicInfo basicInfo) {
        BasicInfo createdBasicInfo = basicInfoService.saveBasicInfoWithEnroll(basicInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBasicInfo);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBasicInfoById(@PathVariable("id") long id) {
        basicInfoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<BasicInfo> getBasicInfoById(@PathVariable("id") long id) {
        Optional<BasicInfo> basicInfoOptional = basicInfoService.getBasicInfoById(id);
        return basicInfoOptional.map(basicInfo -> ResponseEntity.ok().body(basicInfo))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/withImage/{id}")
    public ResponseEntity<BasicInfo> createBasicInfoWithImage(@PathVariable("id")long id, @RequestParam("imageFile") MultipartFile imageFile, @RequestParam("resume")MultipartFile resume, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("phoneNumber") long phoneNumber, @RequestParam("email") String email, @RequestParam("city") String city, @RequestParam("pinCode") String pinCode, @RequestParam("state") String state, @RequestParam("dateOfBirth") String dateOfBirth, @RequestParam("yourNameOnCertificate") String yourNameOnCertificate, @RequestParam("linkedInProfileUrl") String linkedInProfileUrl, @RequestParam("sscBoard") String sscBoard, @RequestParam("sscInstitution") String sscInstitution, @RequestParam("sscYop") String sscYop, @RequestParam("sscCGPA") String sscCGPA, @RequestParam("intermediateOrDiplomaInstitution") String intermediateOrDiplomaInstitution, @RequestParam("intermediateOrDiplomaStream") String intermediateOrDiplomaStream, @RequestParam("intermediateOrDiplomaYop") String intermediateOrDiplomaYop, @RequestParam("intermediateOrDiplomaCGPA") String intermediateOrDiplomaCGPA, @RequestParam("degreeUniversity") String degreeUniversity, @RequestParam("degreeStream") String degreeStream, @RequestParam("degreeYop") String degreeYop, @RequestParam("degreeCGPA") String degreeCGPA, @RequestParam("postGraduateUniversity") String postGraduateUniversity, @RequestParam("postGraduateStream") String postGraduateStream, @RequestParam("postGraduateYop") String postGraduateYop, @RequestParam("postGraduateCGPA") String postGraduateCGPA) {
        try {
            BasicInfo basicInfo = basicInfoService.saveBasicInfoWithImage(id ,imageFile,resume, firstName, lastName, phoneNumber, email, city, pinCode, state, dateOfBirth, yourNameOnCertificate, linkedInProfileUrl, sscBoard, sscInstitution, sscYop, sscCGPA, intermediateOrDiplomaInstitution, intermediateOrDiplomaStream, intermediateOrDiplomaYop, intermediateOrDiplomaCGPA, degreeUniversity, degreeStream, degreeYop, degreeCGPA, postGraduateUniversity, postGraduateStream, postGraduateYop, postGraduateCGPA);
            return ResponseEntity.status(HttpStatus.CREATED).body(basicInfo);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
     @PutMapping("/update/image/{id}")
    public ResponseEntity<BasicInfo> updateImage(@PathVariable("id") Long id, @RequestParam("imageFile") MultipartFile imageFile) {
        try {
            Optional<BasicInfo> existingBasicInfoOptional = basicInfoService.getBasicInfoById(id);
            if (existingBasicInfoOptional.isPresent()) {
                BasicInfo existingBasicInfo = existingBasicInfoOptional.get();
                existingBasicInfo.setImage(imageFile.getBytes());
                BasicInfo updatedBasicInfo = basicInfoService.saveBasicInfo(existingBasicInfo);
                return ResponseEntity.ok(updatedBasicInfo);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping("/update/resume/{id}")
    public ResponseEntity<BasicInfo> updateResume(@PathVariable("id") Long id, @RequestParam("resume") MultipartFile resume) {
        try {
            Optional<BasicInfo> existingBasicInfoOptional = basicInfoService.getBasicInfoById(id);
            if (existingBasicInfoOptional.isPresent()) {
                BasicInfo existingBasicInfo = existingBasicInfoOptional.get();
                existingBasicInfo.setImage(resume.getBytes());
                BasicInfo updatedBasicInfo = basicInfoService.saveBasicInfo(existingBasicInfo);
                return ResponseEntity.ok(updatedBasicInfo);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        if (loginRequest == null || loginRequest.getEmail() == null || loginRequest.getPassword() == null) {
            return new ResponseEntity<>("Invalid login request", HttpStatus.BAD_REQUEST);
        }
        BasicInfo authenticatedUser = basicInfoService.login(loginRequest.getEmail(), loginRequest.getPassword());
        if (authenticatedUser != null) {
            String jwtToken = tokenService.generateToken(loginRequest.getEmail());
            SimplifiedUserDetails simplifiedUserDetails = new SimplifiedUserDetails();
            simplifiedUserDetails.setId(authenticatedUser.getId());
            simplifiedUserDetails.setFirstName(authenticatedUser.getFirstName());
            simplifiedUserDetails.setLastName(authenticatedUser.getLastName());
            simplifiedUserDetails.setRole(authenticatedUser.getRole());
            JwtResponse response = new JwtResponse(jwtToken, simplifiedUserDetails);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody BasicInfo registrationData) {
        if (basicInfoService.existsByEmail(registrationData.getEmail())) {
            return new ResponseEntity<>("Email is already taken. Please use another email.", HttpStatus.CONFLICT);
        }
        BasicInfo registeredUser = basicInfoService.registerUser(registrationData);
        if (registeredUser != null) {
            sendRegistrationSuccessEmail(registeredUser.getEmail(), registeredUser.getFirstName());
            return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void sendRegistrationSuccessEmail(String to, String firstName) {
        String subject = "Registration Successful";
        String body = "Dear " + firstName + ",\n\n" +
                "Thank you for registering with our system. Your account has been successfully created." + "\n\n" +
                "We appreciate your trust in us and look forward to serving you. If you have any questions or need assistance, please feel free to reach out to us. We will get back to you as soon as possible.\n\n" +
                "Best regards,\n" +
                "SURNOI TECHNOLOGY PRIVATE LIMITED";

        emailSenderService.sendEmail(to, subject, body);
    }


    @PutMapping("/update-password")
    public ResponseEntity<?> updatePassword(@RequestParam String email, @RequestParam String currentPassword, @RequestParam String newPassword) {
        boolean updated = basicInfoService.updatePassword(email, currentPassword, newPassword);
        if (updated) {
            return new ResponseEntity<>("Password updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Incorrect current password or user not found", HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam("email") String email) {
        if (basicInfoService.existsByEmail(email)) {
            return ResponseEntity.ok("Password reset link sent to your email.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not found");
    }

//    @PostMapping("/reset-password")
//    public ResponseEntity<String> resetPassword(@RequestParam("email") String email,
//                                                @RequestParam("currentPassword") String currentPassword,
//                                                @RequestParam("newPassword") String newPassword) {
//        boolean isUpdated = basicInfoService.updatePassword(email, currentPassword, newPassword);
//        return isUpdated ? ResponseEntity.ok("Password updated successfully") : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Current password is incorrect");
//    }

@PutMapping("/update/{id}")
    public ResponseEntity<BasicInfo> updateBasicInfo(@PathVariable("id") long id, @RequestBody BasicInfo updatedBasicInfo) {
    Optional<BasicInfo> existingBasicInfoOptional = basicInfoService.getBasicInfoById(id);
        if (existingBasicInfoOptional.isPresent()) {
            BasicInfo existingBasicInfo = existingBasicInfoOptional.get();

            if (updatedBasicInfo.getFirstName() != null) {
                existingBasicInfo.setFirstName(updatedBasicInfo.getFirstName());
            }
            if (updatedBasicInfo.getCompanyName() != null) {
                existingBasicInfo.setCompanyName(updatedBasicInfo.getCompanyName());
            }
            if (updatedBasicInfo.getDesignation() != null) {
                existingBasicInfo.setDesignation(updatedBasicInfo.getDesignation());
            }
            if (updatedBasicInfo.getYearsOfExperience() != null) {
                existingBasicInfo.setYearsOfExperience(updatedBasicInfo.getYearsOfExperience());
            }
            if (updatedBasicInfo.getCompanyAddress() != null) {
                existingBasicInfo.setCompanyAddress(updatedBasicInfo.getCompanyAddress());
            }
            if (updatedBasicInfo.getLastName() != null) {
                existingBasicInfo.setLastName(updatedBasicInfo.getLastName());
            }
            if (updatedBasicInfo.getPhoneNumber() != 0) {
                existingBasicInfo.setPhoneNumber(updatedBasicInfo.getPhoneNumber());
            }
            if (updatedBasicInfo.getEmail() != null) {
                existingBasicInfo.setEmail(updatedBasicInfo.getEmail());
            }
            if (updatedBasicInfo.getCountry() != null) {
                existingBasicInfo.setCountry(updatedBasicInfo.getCountry());
            }
            if (updatedBasicInfo.getCity() != null) {
                existingBasicInfo.setCity(updatedBasicInfo.getCity());
            }
            if (updatedBasicInfo.getPinCode() != null) {
                existingBasicInfo.setPinCode(updatedBasicInfo.getPinCode());
            }
            if (updatedBasicInfo.getState() != null) {
                existingBasicInfo.setState(updatedBasicInfo.getState());
            }
            if (updatedBasicInfo.getDateOfBirth() != null) {
                existingBasicInfo.setDateOfBirth(updatedBasicInfo.getDateOfBirth());
            }
            if (updatedBasicInfo.getYourNameOnCertificate() != null) {
                existingBasicInfo.setYourNameOnCertificate(updatedBasicInfo.getYourNameOnCertificate());
            }
            if (updatedBasicInfo.getLinkedInProfileUrl() != null) {
                existingBasicInfo.setLinkedInProfileUrl(updatedBasicInfo.getLinkedInProfileUrl());
            }
            if (updatedBasicInfo.getSscBoard() != null) {
                existingBasicInfo.setSscBoard(updatedBasicInfo.getSscBoard());
            }
            if (updatedBasicInfo.getSscInstitution() != null) {
                existingBasicInfo.setSscInstitution(updatedBasicInfo.getSscInstitution());
            }
            if (updatedBasicInfo.getSscYop() != null) {
                existingBasicInfo.setSscYop(updatedBasicInfo.getSscYop());
            }
            if (updatedBasicInfo.getSscCGPA() != null) {
                existingBasicInfo.setSscCGPA(updatedBasicInfo.getSscCGPA());
            }
            if (updatedBasicInfo.getIntermediateOrDiplomaInstitution() != null) {
                existingBasicInfo.setIntermediateOrDiplomaInstitution(updatedBasicInfo.getIntermediateOrDiplomaInstitution());
            }
            if (updatedBasicInfo.getIntermediateOrDiplomaStream() != null) {
                existingBasicInfo.setIntermediateOrDiplomaStream(updatedBasicInfo.getIntermediateOrDiplomaStream());
            }
            if (updatedBasicInfo.getIntermediateOrDiplomaYop() != null) {
                existingBasicInfo.setIntermediateOrDiplomaYop(updatedBasicInfo.getIntermediateOrDiplomaYop());
            }
            if (updatedBasicInfo.getIntermediateOrDiplomaCGPA() != null) {
                existingBasicInfo.setIntermediateOrDiplomaCGPA(updatedBasicInfo.getIntermediateOrDiplomaCGPA());
            }
            if (updatedBasicInfo.getDegreeUniversity() != null) {
                existingBasicInfo.setDegreeUniversity(updatedBasicInfo.getDegreeUniversity());
            }
            if (updatedBasicInfo.getDegreeStream() != null) {
                existingBasicInfo.setDegreeStream(updatedBasicInfo.getDegreeStream());
            }
            if (updatedBasicInfo.getDegreeYop() != null) {
                existingBasicInfo.setDegreeYop(updatedBasicInfo.getDegreeYop());
            }
            if (updatedBasicInfo.getDegreeCGPA() != null) {
                existingBasicInfo.setDegreeCGPA(updatedBasicInfo.getDegreeCGPA());
            }
            if (updatedBasicInfo.getPostGraduateUniversity() != null) {
                existingBasicInfo.setPostGraduateUniversity(updatedBasicInfo.getPostGraduateUniversity());
            }
            if (updatedBasicInfo.getPostGraduateStream() != null) {
                existingBasicInfo.setPostGraduateStream(updatedBasicInfo.getPostGraduateStream());
            }
            if (updatedBasicInfo.getPostGraduateYop() != null) {
                existingBasicInfo.setPostGraduateYop(updatedBasicInfo.getPostGraduateYop());
            }
            if (updatedBasicInfo.getPostGraduateCGPA() != null) {
                existingBasicInfo.setPostGraduateCGPA(updatedBasicInfo.getPostGraduateCGPA());
            }
            BasicInfo savedBasicInfo = basicInfoService.saveBasicInfo(existingBasicInfo);
            return ResponseEntity.ok(savedBasicInfo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

 