package org.zeropage.causcheduler.data.original;

/**
 * Created by Lumin on 2016-01-18.
 */
public class Homework extends AbstractHomework {
    public final boolean isSubmitted;
    public final String currentProgressStatus;
    public final int submitStudentNum;
    public final int totalStudentNum;
    public final int orderingNum;

    public Homework(String name, String startTime, String endTime, String extendEndTime, boolean isSubmitted, String currentProgressStatus, int submitStudentNum, int totalStudentNum, int orderingNum) {
        super(name, startTime, endTime, extendEndTime);
        this.isSubmitted = isSubmitted;
        this.currentProgressStatus = currentProgressStatus;
        this.submitStudentNum = submitStudentNum;
        this.totalStudentNum = totalStudentNum;
        this.orderingNum = orderingNum;
    }
}
