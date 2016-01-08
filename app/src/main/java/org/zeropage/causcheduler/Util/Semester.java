package org.zeropage.causcheduler.Util;

/**
 * 학기 정보를 나열하고 있는 열거형입니다.
 * Created by Lumin on 2016-01-08.
 */
public enum Semester {
    Spring(1), SummerVacation(3), Fall(2), WinterVacation(4);

    private final int semesterCode;

    Semester(int semesterCode) {
        this.semesterCode = semesterCode;
    }

    public int getSemesterCode() {
        return this.semesterCode;
    }
}
