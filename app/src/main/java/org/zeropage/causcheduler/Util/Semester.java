package org.zeropage.causcheduler.Util;

/**
 * 학기 정보를 나열하고 있는 열거형입니다.
 * Created by Lumin on 2016-01-08.
 */
public enum Semester {
    /**
     * 1학기를 나타냅니다.
     */
    Spring(1),
    /**
     * 여름계절학기를 나타냅니다.
     */
    SummerVacation(3),
    /**
     * 2학기를 나타냅니다.
     */
    Fall(2),
    /**
     * 겨울계절학기를 나타냅니다.
     */
    WinterVacation(4);

    private final int semesterCode;

    Semester(int semesterCode) {
        this.semesterCode = semesterCode;
    }

    /**
     * 지정된 학기에 부여된 고유 코드를 가져옵니다.
     * @return 현재 지정된 학기에 부여된 고유 코드입니다.
     */
    public int getSemesterCode() {
        return this.semesterCode;
    }
}
