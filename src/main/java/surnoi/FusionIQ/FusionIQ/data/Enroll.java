package surnoi.FusionIQ.FusionIQ.data;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
public class Enroll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long enrollId;
    private String courseTitle;
    private String fee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    @JsonBackReference
    private CourseCard courseCard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "basic_info_id")

    private BasicInfo basicInfo;

    @JsonProperty("courseId")
    public Long getCourseId() {
        return courseCard != null ? courseCard.getCourseId() : null;
    }
    public Long getEnrollId() {
        return enrollId;
    }

    public void setEnrollId(Long enrollId) {
        this.enrollId = enrollId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public CourseCard getCourseCard() {
        return courseCard;
    }

    public void setCourseCard(CourseCard courseCard) {
        this.courseCard = courseCard;
    }

    public BasicInfo getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(BasicInfo basicInfo) {
        this.basicInfo = basicInfo;
    }

    public Enroll(Long enrollId, String courseTitle, String fee) {
        this.enrollId = enrollId;
        this.courseTitle = courseTitle;
        this.fee = fee;
    }

    public Enroll() {
        super();
    }

    @Override
    public String toString() {
        return "Enroll{" +
                "enrollId=" + enrollId +
                ", courseTitle='" + courseTitle + '\'' +
                ", fee='" + fee + '\'' +
                ", courseCard=" + courseCard +
                ", basicInfo=" + basicInfo +
                '}';
    }
}

