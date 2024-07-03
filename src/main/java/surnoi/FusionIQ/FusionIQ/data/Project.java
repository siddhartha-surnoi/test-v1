package surnoi.FusionIQ.FusionIQ.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String projectTitle;
    private String projectOverview;
    private String objectives;
    private String scope;
    private String timeLine;
    private String methodology;
    private String courseTitle;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public String getObjectives() {
        return objectives;
    }
    public void setObjectives(String objectives) {
        this.objectives = objectives;
    }
    public String getScope() {
        return scope;
    }
    public void setScope(String scope) {
        this.scope = scope;
    }
    public String getTimeLine() {
        return timeLine;
    }
    public void setTimeLine(String timeLine) {
        this.timeLine = timeLine;
    }
    public String getMethodology() {
        return methodology;
    }
    public void setMethodology(String methodology) {
        this.methodology = methodology;
    }
    public String getCourseTitle() {
        return courseTitle;
    }
    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }
    public Project(Long id, String projectTitle, String projectOverview, String objectives, String scope, String timeLine, String methodology, String courseTitle) {
        this.id = id;
        this.projectTitle = projectTitle;
        this.projectOverview = projectOverview;
        this.objectives = objectives;
        this.scope = scope;
        this.timeLine = timeLine;
        this.methodology = methodology;
        this.courseTitle = courseTitle;
    }
    public Project() {
        super();
    }
    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", projectTitle='" + projectTitle + '\'' +
                ", projectOverview='" + projectOverview + '\'' +
                ", objectives='" + objectives + '\'' +
                ", scope='" + scope + '\'' +
                ", timeLine='" + timeLine + '\'' +
                ", methodology='" + methodology + '\'' +
                ", courseTitle='" + courseTitle + '\'' +
                '}';
    }
}