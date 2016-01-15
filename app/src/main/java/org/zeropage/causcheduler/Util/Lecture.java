package org.zeropage.causcheduler.Util;

/**
 * 한 강의에 대한 정보를 담고 있는 클래스입니다.
 * Created by Lumin on 2016-01-09.
 */
public class Lecture {
    public final String lectureName;
    public final String professorName;
    public final String lectureDeptName;
    public final int sectionNum;
    public final String studyPeriod;

    public Lecture(String lectureName, String professorName, String lectureDeptName, int sectionNum, String studyPeriod) {
        this.lectureName = lectureName;
        this.professorName = professorName;
        this.lectureDeptName = lectureDeptName;
        this.sectionNum = sectionNum;
        this.studyPeriod = studyPeriod;
    }
}
