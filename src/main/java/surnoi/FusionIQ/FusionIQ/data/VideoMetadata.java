package surnoi.FusionIQ.FusionIQ.data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class VideoMetadata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String s3Key;
    private String s3Url;
    private String description;
    private String watchedPercentage;
    private String lastWatchedTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
//    @JsonIgnore
    private CourseCard courseCard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "basic_info_id")
//    @JsonIgnore
    private BasicInfo basicInfo;
    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL)
    private List<Rating> ratings;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getS3Key() {
        return s3Key;
    }
    public void setS3Key(String s3Key) {
        this.s3Key = s3Key;
    }
    public String getS3Url() {
        return s3Url;
    }
    public void setS3Url(String s3Url) {
        this.s3Url = s3Url;
    }
    public String getWatchedPercentage() {
        return watchedPercentage;
    }
    public void setWatchedPercentage(String watchedPercentage) {
        this.watchedPercentage = watchedPercentage;
    }
    public String getLastWatchedTime() {
        return lastWatchedTime;
    }
    public void setLastWatchedTime(String lastWatchedTime) {
        this.lastWatchedTime = lastWatchedTime;
    }

    public CourseCard getCourseCard() {
        return courseCard;
    }

    public void setCourseCard(CourseCard courseCard) {
        this.courseCard = courseCard;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BasicInfo getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(BasicInfo basicInfo) {
        this.basicInfo = basicInfo;
    }

    @Override
    public String toString() {
        return "VideoMetadata{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", s3Key='" + s3Key + '\'' +
                ", s3Url='" + s3Url + '\'' +
                ", description='" + description + '\'' +
                ", watchedPercentage='" + watchedPercentage + '\'' +
                ", lastWatchedTime='" + lastWatchedTime + '\'' +
                ", courseCard=" + courseCard +
                ", basicInfo=" + basicInfo +
                ", ratings=" + ratings +
                '}';
    }

}
