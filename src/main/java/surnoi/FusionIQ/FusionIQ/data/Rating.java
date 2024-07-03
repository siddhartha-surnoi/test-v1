package surnoi.FusionIQ.FusionIQ.data;

import jakarta.persistence.*;

@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int value;
    private int stars;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private CourseCard courseCard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id")
    private VideoMetadata video;
    public Rating() {
        super();
    }
    public Rating(Long id, String name, int value,int stars) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.stars  = stars;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public CourseCard getCourseCard() {
        return courseCard;
    }

    public void setCourseCard(CourseCard courseCard) {
        this.courseCard = courseCard;
    }

    public VideoMetadata getVideo() {
        return video;
    }

    public void setVideo(VideoMetadata video) {
        this.video = video;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value=" + value +
                ", stars=" + stars +
                ", courseCard=" + courseCard +
                ", video=" + video +
                '}';
    }


}
