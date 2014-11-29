package us.proximal.spellwithme.model.dto;

/**
 * Created by b on 11/26/14.
 */
public class AnswerDTO {

    private int answerId;
    private int questionId;
    private int studentId;
    private int mkoId;
    private String date;
    private String grade;

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getMkoId() {
        return mkoId;
    }

    public void setMkoId(int mkoId) {
        this.mkoId = mkoId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }


}
