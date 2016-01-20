package org.zeropage.causcheduler.Util;

/**
 * Created by Lumin on 2016-01-18.
 */
public class DetailHomework extends AbstractHomework {
    public final boolean isSubmitted;
    public final String currentProgressStatus;
    public final int submitStudentNum;
    public final int totalStudentNum;
    public final int orderingNum;
    public final boolean isOpenTask;

    public DetailHomework(String name, String startTime, String endTime, String extendEndTime, boolean isSubmitted, String currentProgressStatus, int submitStudentNum, int totalStudentNum, int orderingNum, boolean isOpenTask) {
        super(name, startTime, endTime, extendEndTime);
        this.isSubmitted = isSubmitted;
        this.currentProgressStatus = currentProgressStatus;
        this.submitStudentNum = submitStudentNum;
        this.totalStudentNum = totalStudentNum;
        this.orderingNum = orderingNum;
        this.isOpenTask = isOpenTask;
    }
}
