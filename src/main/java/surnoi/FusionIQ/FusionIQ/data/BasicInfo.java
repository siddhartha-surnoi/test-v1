package surnoi.FusionIQ.FusionIQ.data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
@Entity
public class BasicInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private long id;
    @Lob
    @Column(length = 100000)
    private byte[] image;
    @Lob
    @Column(length = 100000)
    private byte[] resume;
    private String firstName;
    private String lastName;
    private long phoneNumber;
    private String email;
    private String password;
    private String otp;
    private LocalDateTime otpGeneratedTime;
    private String role;
    private String city;
    private String pinCode;
    private String state;
    private String country;
    private String dateOfBirth;
    private String yourNameOnCertificate;
    private String linkedInProfileUrl;
    private String sscBoard;
    private String sscInstitution;
    private String sscYop;
    private String sscCGPA;
    private String intermediateOrDiplomaInstitution;
    private String intermediateOrDiplomaStream;
    private String intermediateOrDiplomaYop;
    private String intermediateOrDiplomaCGPA;
    private String degreeUniversity;
    private String degreeStream;
    private String degreeYop;
    private String degreeCGPA;
    private String postGraduateUniversity;
    private String postGraduateStream;
    private String postGraduateYop;
    private String postGraduateCGPA;
    private String companyName;
    private String designation;
    private String yearsOfExperience;
    private String companyAddress;

    @OneToMany(mappedBy = "basicInfo", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CourseCard> courseCard;

    @OneToMany(mappedBy = "basicInfo", cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore
    private List<Enroll> enrolls;
    @OneToMany(mappedBy = "basicInfo")
    private List<VideoMetadata> videos;

    public BasicInfo(List<CourseCard> courseCard) {
        this.courseCard = courseCard;
    }

    public BasicInfo(){
        super();
    }
    public BasicInfo(long id, byte[] image, byte[] resume, String firstName, String lastName, long phoneNumber, String email, String password, String otp, LocalDateTime otpGeneratedTime, String role, String city, String pinCode, String state, String country, String dateOfBirth, String yourNameOnCertificate, String linkedInProfileUrl, String sscBoard, String sscInstitution, String sscYop, String sscCGPA, String intermediateOrDiplomaInstitution, String intermediateOrDiplomaStream, String intermediateOrDiplomaYop, String intermediateOrDiplomaCGPA, String degreeUniversity, String degreeStream, String degreeYop, String degreeCGPA, String postGraduateUniversity, String postGraduateStream, String postGraduateYop, String postGraduateCGPA, String companyName, String designation, String yearsOfExperience, String companyAddress, List<VideoMetadata> videos) {
        this.id = id;
        this.image = image;
        this.resume = resume;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.otp = otp;
        this.otpGeneratedTime = otpGeneratedTime;
        this.role = role;
        this.city = city;
        this.pinCode = pinCode;
        this.state = state;
        this.country = country;
        this.dateOfBirth = dateOfBirth;
        this.yourNameOnCertificate = yourNameOnCertificate;
        this.linkedInProfileUrl = linkedInProfileUrl;
        this.sscBoard = sscBoard;
        this.sscInstitution = sscInstitution;
        this.sscYop = sscYop;
        this.sscCGPA = sscCGPA;
        this.intermediateOrDiplomaInstitution = intermediateOrDiplomaInstitution;
        this.intermediateOrDiplomaStream = intermediateOrDiplomaStream;
        this.intermediateOrDiplomaYop = intermediateOrDiplomaYop;
        this.intermediateOrDiplomaCGPA = intermediateOrDiplomaCGPA;
        this.degreeUniversity = degreeUniversity;
        this.degreeStream = degreeStream;
        this.degreeYop = degreeYop;
        this.degreeCGPA = degreeCGPA;
        this.postGraduateUniversity = postGraduateUniversity;
        this.postGraduateStream = postGraduateStream;
        this.postGraduateYop = postGraduateYop;
        this.postGraduateCGPA = postGraduateCGPA;
        this.companyName = companyName;
        this.designation = designation;
        this.yearsOfExperience = yearsOfExperience;
        this.companyAddress = companyAddress;
        this.videos = videos;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public BasicInfo(String sscBoard) {
        this.sscBoard = sscBoard;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public byte[] getImage() {
        return image;
    }

    public byte[] getResume() {
        return resume;
    }

    public void setResume(byte[] resume) {
        this.resume = resume;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
    public long getPhoneNumber() {
        return phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public LocalDateTime getOtpGeneratedTime() {
        return otpGeneratedTime;
    }

    public void setOtpGeneratedTime(LocalDateTime otpGeneratedTime) {
        this.otpGeneratedTime = otpGeneratedTime;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getCity() {
        return city;
    }
    public List<CourseCard> getCourseCard() {
        return courseCard;
    }
    public void setCourseCard(List<CourseCard> courseCard) {
        this.courseCard = courseCard;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getPinCode() {
        return pinCode;
    }
    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public String getYourNameOnCertificate() {
        return yourNameOnCertificate;
    }
    public void setYourNameOnCertificate(String yourNameOnCertificate) {
        this.yourNameOnCertificate = yourNameOnCertificate;
    }
    public String getLinkedInProfileUrl() {
        return linkedInProfileUrl;
    }
    public void setLinkedInProfileUrl(String linkedInProfileUrl) {
        this.linkedInProfileUrl = linkedInProfileUrl;
    }
    public String getSscInstitution() {
        return sscInstitution;
    }
    public void setSscInstitution(String sscInstitution) {
        this.sscInstitution = sscInstitution;
    }
    public String getSscYop() {
        return sscYop;
    }
    public void setSscYop(String sscYop) {
        this.sscYop = sscYop;
    }
    public String getSscCGPA() {
        return sscCGPA;
    }
    public void setSscCGPA(String sscCGPA) {
        this.sscCGPA = sscCGPA;
    }
    public String getIntermediateOrDiplomaInstitution() {
        return intermediateOrDiplomaInstitution;
    }
    public void setIntermediateOrDiplomaInstitution(String intermediateOrDiplomaInstitution) {
        this.intermediateOrDiplomaInstitution = intermediateOrDiplomaInstitution;
    }
    public String getIntermediateOrDiplomaStream() {
        return intermediateOrDiplomaStream;
    }
    public void setIntermediateOrDiplomaStream(String intermediateOrDiplomaStream) {
        this.intermediateOrDiplomaStream = intermediateOrDiplomaStream;
    }
    public String getIntermediateOrDiplomaYop() {
        return intermediateOrDiplomaYop;
    }
    public void setIntermediateOrDiplomaYop(String intermediateOrDiplomaYop) {
        this.intermediateOrDiplomaYop = intermediateOrDiplomaYop;
    }
    public String getIntermediateOrDiplomaCGPA() {
        return intermediateOrDiplomaCGPA;
    }
    public void setIntermediateOrDiplomaCGPA(String intermediateOrDiplomaCGPA) {
        this.intermediateOrDiplomaCGPA = intermediateOrDiplomaCGPA;
    }
    public String getDegreeUniversity() {
        return degreeUniversity;
    }
    public void setDegreeUniversity(String degreeUniversity) {
        this.degreeUniversity = degreeUniversity;
    }
    public String getDegreeStream() {
        return degreeStream;
    }
    public void setDegreeStream(String degreeStream) {
        this.degreeStream = degreeStream;
    }
    public String getDegreeYop() {
        return degreeYop;
    }
    public void setDegreeYop(String degreeYop) {
        this.degreeYop = degreeYop;
    }
    public String getDegreeCGPA() {
        return degreeCGPA;
    }
    public void setDegreeCGPA(String degreeCGPA) {
        this.degreeCGPA = degreeCGPA;
    }
    public String getPostGraduateUniversity() {
        return postGraduateUniversity;
    }
    public void setPostGraduateUniversity(String postGraduateUniversity) {
        this.postGraduateUniversity = postGraduateUniversity;
    }
    public String getPostGraduateStream() {
        return postGraduateStream;
    }
    public void setPostGraduateStream(String postGraduateStream) {
        this.postGraduateStream = postGraduateStream;
    }
    public String getPostGraduateYop() {
        return postGraduateYop;
    }
    public void setPostGraduateYop(String postGraduateYop) {
        this.postGraduateYop = postGraduateYop;
    }
    public String getPostGraduateCGPA() {
        return postGraduateCGPA;
    }
    public String getSscBoard() {
        return sscBoard;
    }
    public void setSscBoard(String sscBoard) {
        this.sscBoard = sscBoard;
    }
    public void setPostGraduateCGPA(String postGraduateCGPA) {
        this.postGraduateCGPA = postGraduateCGPA;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(String yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public List<Enroll> getEnrolls() {
        return enrolls;
    }

    public void setEnrolls(List<Enroll> enrolls) {
        this.enrolls = enrolls;
    }

    public List<VideoMetadata> getVideos() {
        return videos;
    }

    public void setVideos(List<VideoMetadata> videos) {
        this.videos = videos;
    }

    @Override
    public String toString() {
        return "BasicInfo{" +
                "id=" + id +
                ", image=" + Arrays.toString(image) +
                ", resume=" + Arrays.toString(resume) +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", otp='" + otp + '\'' +
                ", otpGeneratedTime=" + otpGeneratedTime +
                ", role='" + role + '\'' +
                ", city='" + city + '\'' +
                ", pinCode='" + pinCode + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", yourNameOnCertificate='" + yourNameOnCertificate + '\'' +
                ", linkedInProfileUrl='" + linkedInProfileUrl + '\'' +
                ", sscBoard='" + sscBoard + '\'' +
                ", sscInstitution='" + sscInstitution + '\'' +
                ", sscYop='" + sscYop + '\'' +
                ", sscCGPA='" + sscCGPA + '\'' +
                ", intermediateOrDiplomaInstitution='" + intermediateOrDiplomaInstitution + '\'' +
                ", intermediateOrDiplomaStream='" + intermediateOrDiplomaStream + '\'' +
                ", intermediateOrDiplomaYop='" + intermediateOrDiplomaYop + '\'' +
                ", intermediateOrDiplomaCGPA='" + intermediateOrDiplomaCGPA + '\'' +
                ", degreeUniversity='" + degreeUniversity + '\'' +
                ", degreeStream='" + degreeStream + '\'' +
                ", degreeYop='" + degreeYop + '\'' +
                ", degreeCGPA='" + degreeCGPA + '\'' +
                ", postGraduateUniversity='" + postGraduateUniversity + '\'' +
                ", postGraduateStream='" + postGraduateStream + '\'' +
                ", postGraduateYop='" + postGraduateYop + '\'' +
                ", postGraduateCGPA='" + postGraduateCGPA + '\'' +
                ", companyName='" + companyName + '\'' +
                ", designation='" + designation + '\'' +
                ", yearsOfExperience='" + yearsOfExperience + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", courseCard=" + courseCard +
                ", enrolls=" + enrolls +
                ", videos=" + videos +
                '}';
    }

    public List<CourseCard> getCourseCards() {
        return courseCard;
    }
}
 