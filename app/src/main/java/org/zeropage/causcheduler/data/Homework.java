package org.zeropage.causcheduler.data;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.Required;

/**
 * Created by Donghwan on 2016-01-20.
 */
public class Homework extends RealmObject {
    @Required
    private String name;
    @Required
    private String startTime;
    @Required
    private String endTime;
    @Required
    private String extendEndTime;

    private String content;

    private boolean isSubmitted;
    @Required
    private String currentProgressStatus;
    private int submitStudentNum;
    private int totalStudentNum;
    @Ignore
    private int index; // 포탈 서버 내에서 과제의 순서를 분류하는 번호

    public Homework() {
    }

    public Homework(String name, String startTime, String endTime, String extendEndTime, boolean isSubmitted, String currentProgressStatus, int submitStudentNum, int totalStudentNum, int index) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.extendEndTime = extendEndTime;
        this.isSubmitted = isSubmitted;
        this.currentProgressStatus = currentProgressStatus;
        this.submitStudentNum = submitStudentNum;
        this.totalStudentNum = totalStudentNum;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getExtendEndTime() {
        return extendEndTime;
    }

    public void setExtendEndTime(String extendEndTime) {
        this.extendEndTime = extendEndTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isSubmitted() {
        return isSubmitted;
    }

    public void setIsSubmitted(boolean isSubmitted) {
        this.isSubmitted = isSubmitted;
    }

    public String getCurrentProgressStatus() {
        return currentProgressStatus;
    }

    public void setCurrentProgressStatus(String currentProgressStatus) {
        this.currentProgressStatus = currentProgressStatus;
    }

    public int getSubmitStudentNum() {
        return submitStudentNum;
    }

    public void setSubmitStudentNum(int submitStudentNum) {
        this.submitStudentNum = submitStudentNum;
    }

    public int getTotalStudentNum() {
        return totalStudentNum;
    }

    public void setTotalStudentNum(int totalStudentNum) {
        this.totalStudentNum = totalStudentNum;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
