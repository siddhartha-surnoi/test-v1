package surnoi.FusionIQ.FusionIQ.data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.*;
@Entity
public class CourseCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    @Lob
    @Column(length = 100000)
    private byte[] courseImage;
    @Lob
    @Column(length = 100000)
    private byte[] mentorImage;
    private String title;

    private double fee;
    private String duration;
    private String skills;
    private String courseType;
    private String targetAudience;
    private String prerequisites;
    private  String mentorName;
    private String courseTitle;
    private String designation;
    private String projectTitle;
    private String projectOverview;
    private String projectObjectives;
    private String projectScope;
    private String projectTimeline;
    private String projectMethodolgy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "basic_info_id")
    @JsonIgnoreProperties("hibernateLazyInitializer")
    private BasicInfo basicInfo;

    @OneToMany(mappedBy = "courseCard", cascade = CascadeType.ALL)
//    @JsonIgnore
    private List<VideoMetadata> videos;

    @OneToMany(mappedBy = "courseCard", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Enroll> enrolls;

    @OneToMany(mappedBy = "courseCard", cascade = CascadeType.ALL)
    private List<Rating> ratings;

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public Long getCourseId() {
        return courseId;
    }
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
    public byte[] getCourseImage() {
        return courseImage;
    }
    public void setCourseImage(byte[] courseImage) {
        this.courseImage = courseImage;
    }
    public byte[] getMentorImage() {
        return mentorImage;
    }
    public void setMentorImage(byte[] mentorImage) {
        this.mentorImage = mentorImage;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public String getSkills() {
        return skills;
    }
    public void setSkills(String skills) {
        this.skills = skills;
    }
    public String getCourseType() {
        return courseType;
    }
    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getTargetAudience() {
        return targetAudience;
    }
    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }
    public String getPrerequisites() {
        return prerequisites;
    }
    public void setPrerequisites(String prerequisites) {
        this.prerequisites = prerequisites;
    }
    public String getMentorName() {
        return mentorName;
    }
    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }
    public String getCourseTitle() {
        return courseTitle;
    }
    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }
    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    public String getProjectTitle() {
        return projectTitle;
    }
    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }
    public String getProjectOverview() {
        return projectOverview;
    }
    public void setProjectOverview(String projectOverview) {
        this.projectOverview = projectOverview;
    }
    public String getProjectObjectives() {
        return projectObjectives;
    }
    public void setProjectObjectives(String projectObjectives) {
        this.projectObjectives = projectObjectives;
    }
    public String getProjectScope() {
        return projectScope;
    }
    public void setProjectScope(String projectScope) {
        this.projectScope = projectScope;
    }
    public String getProjectTimeline() {
        return projectTimeline;
    }
    public void setProjectTimeline(String projectTimeline) {
        this.projectTimeline = projectTimeline;
    }
    public String getProjectMethodolgy() {
        return projectMethodolgy;
    }
    public void setProjectMethodolgy(String projectMethodolgy) {
        this.projectMethodolgy = projectMethodolgy;
    }
    public BasicInfo getBasicInfo() {
        return basicInfo;
    }
    public void setBasicInfo(BasicInfo basicInfo) {
        this.basicInfo = basicInfo;
    }
    public List<VideoMetadata> getVideos() {
        return videos;
    }
    public void setVideos(List<VideoMetadata> videos) {
        this.videos = videos;
    }
    public List<Enroll> getEnrolls() {
        return enrolls;
    }
    public void setEnrolls(List<Enroll> enrolls) {
        this.enrolls = enrolls;
    }

    public CourseCard(Long courseId, byte[] data, byte[] mentorImage, String title, double fee, String duration, String skills, String courseType, String targetAudience, String prerequisites, String mentorName, String courseTitle, String designation, String projectTitle, String projectOverview, String projectObjectives, String projectScope, String projectTimeline, String projectMethodolgy, BasicInfo basicInfo, List<VideoMetadata> videos, List<Enroll> enrolls) {
        this.courseId = courseId;
        this.courseImage = courseImage;
        this.mentorImage = mentorImage;
        this.title = title;
        this.fee = fee;
        this.duration = duration;
        this.skills = skills;
        this.courseType = courseType;
        this.targetAudience = targetAudience;
        this.prerequisites = prerequisites;
        this.mentorName = mentorName;
        this.courseTitle = courseTitle;
        this.designation = designation;
        this.projectTitle = projectTitle;
        this.projectOverview = projectOverview;
        this.projectObjectives = projectObjectives;
        this.projectScope = projectScope;
        this.projectTimeline = projectTimeline;
        this.projectMethodolgy = projectMethodolgy;
        this.basicInfo = basicInfo;
        this.videos = videos;
        this.enrolls = enrolls;
    }
    public CourseCard() {
        super();
    }

    @Override
    public String toString() {
        return "CourseCard{" +
                "courseId=" + courseId +
                ", courseImage=" + Arrays.toString(courseImage) +
                ", mentorImage=" + Arrays.toString(mentorImage) +
                ", title='" + title + '\'' +
                ", fee='" + fee + '\'' +
                ", duration='" + duration + '\'' +
                ", skills='" + skills + '\'' +
                ", courseType='" + courseType + '\'' +
                ", targetAudience='" + targetAudience + '\'' +
                ", prerequisites='" + prerequisites + '\'' +
                ", mentorName='" + mentorName + '\'' +
                ", courseTitle='" + courseTitle + '\'' +
                ", designation='" + designation + '\'' +
                ", projectTitle='" + projectTitle + '\'' +
                ", projectOverview='" + projectOverview + '\'' +
                ", projectObjectives='" + projectObjectives + '\'' +
                ", projectScope='" + projectScope + '\'' +
                ", projectTimeline='" + projectTimeline + '\'' +
                ", projectMethodolgy='" + projectMethodolgy + '\'' +
                ", basicInfo=" + basicInfo +
                ", videos=" + videos +
                ", enrolls=" + enrolls +
                ", ratings=" + ratings +
                '}';
    }


}