package entity;

import model.CourseType;

import java.util.Date;

public class CourseEvent extends Event {
    private String courseType;


    public CourseEvent(String category, Date stamp, Date lastModified, String uid, Date start, Date end, String summary, String location, String description) {
        super(category, stamp, lastModified, uid, start, end, summary, location, description);
        setCourseType();
    }

    public void setCourseType() {
        String[] words = super.getSummary().split(" ");
        String typeInSummary = words[words.length - 1];
        switch (typeInSummary) {
            case "CM":
                this.courseType = CourseType.CM.toString();
                return;
            case "TD":
                this.courseType = CourseType.TD.toString();
                return;
            case "TP":
                this.courseType = CourseType.TP.toString();
                return;
            case "Evaluation":
                this.courseType = CourseType.EVALUATION.toString();
                return;
        }
    }
}
