package org.zeropage.causcheduler.data;

import java.util.Calendar;
import java.util.GregorianCalendar;

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

    public final int semesterCode;

    Semester(int semesterCode) {
        this.semesterCode = semesterCode;
    }

    public static Semester toSemester(Calendar calendar){
        Calendar springSemester = new GregorianCalendar(calendar.get(Calendar.YEAR), Calendar.MARCH, 2);
        switch(springSemester.get(Calendar.DAY_OF_WEEK)){
            case Calendar.SATURDAY: springSemester.add(Calendar.DAY_OF_MONTH, 2); break;
            case Calendar.SUNDAY: springSemester.add(Calendar.DAY_OF_MONTH, 1); break;
        }
        if(calendar.before(springSemester)) return WinterVacation; // 봄학기 시작 이전이므로 겨울방학

        Calendar summerSemester = new GregorianCalendar(springSemester.get(Calendar.YEAR), springSemester.get(Calendar.MONTH), springSemester.get(Calendar.DAY_OF_MONTH));
        summerSemester.add(Calendar.WEEK_OF_YEAR, 16);
        if(calendar.before(summerSemester)) return Spring; // 여름방학 시작 이전이므로 봄학기

        Calendar fallSemester = new GregorianCalendar(calendar.get(Calendar.YEAR), Calendar.SEPTEMBER, 1);
        switch(fallSemester.get(Calendar.DAY_OF_WEEK)){
            case Calendar.SATURDAY: fallSemester.add(Calendar.DAY_OF_MONTH, 2); break;
            case Calendar.SUNDAY: fallSemester.add(Calendar.DAY_OF_MONTH, 1); break;
        }
        if(calendar.before(fallSemester)) return SummerVacation; // 가을학기 시작 이전이므로 여름방학

        Calendar winterSemester = new GregorianCalendar(fallSemester.get(Calendar.YEAR), fallSemester.get(Calendar.MONTH), fallSemester.get(Calendar.DAY_OF_MONTH));
        winterSemester.add(Calendar.WEEK_OF_YEAR, 16);
        if(calendar.before(winterSemester)) return Fall; // 겨울방학 시작 이전이므로 가을학기
        else return WinterVacation; // 겨울방학 시작
    }
}
