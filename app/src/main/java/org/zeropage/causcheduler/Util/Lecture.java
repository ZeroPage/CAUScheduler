package org.zeropage.causcheduler.Util;

/**
 * 한 강의에 대한 정보를 담고 있는 클래스입니다.
 * Created by Lumin on 2016-01-09.
 */
public class Lecture {
    private String lectureName;
    private String professorName;
    private String lectureDeptName;
    private int sectionNum;

    // Member for testing.
    private String studyPeriod;

    public Lecture(String lectureName, String professorName, String lectureDeptName, int sectionNum, String studyPeriod) {
        this.lectureName = lectureName;
        this.professorName = professorName;
        this.lectureDeptName = lectureDeptName;
        this.sectionNum = sectionNum;

        // Testing
        this.studyPeriod = studyPeriod;
    }

    /**
     * 해당 강의의 이름을 가져옵니다.
     * @return 강의의 이름입니다.
     */
    public String getLectureName() {
        return this.lectureName;
    }

    /**
     * 해당 강의의 교수자 이름을 가져옵니다.
     * @return 교수자의 이름입니다.
     */
    public String getProfessorName() {
        return this.professorName;
    }

    /**
     * 해당 강의가 개설된 학과를 가져옵니다.
     * @return 강의가 개설된 학과입니다.
     */
    public String getLectureDeptName() {
        return this.lectureDeptName;
    }

    /**
     * 해당 강의의 분반을 가져옵니다.
     * @return 강의의 분반입니다.
     */
    public int getSectionNum() {
        return this.sectionNum;
    }

    public String getStudyPeriod() {
        return this.studyPeriod;
    }
}
