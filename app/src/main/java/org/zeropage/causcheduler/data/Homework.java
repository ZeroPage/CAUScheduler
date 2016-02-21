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
    private Lecture lecture;
    @Required
    private String startTime;
    @Required
    private String endTime;
    @Required
    private String extendEndTime;

    private String content;

    private boolean isSubmitted;
    private boolean isOpenTask;
    @Required
    private String currentProgressStatus;
    private int submitStudentNum;
    private int totalStudentNum;
    @Ignore
    private int index; // 포탈 서버 내에서 과제의 순서를 분류하는 번호

    public Homework() {
    }

    /**
     * Homework 인스턴스를 초기화힙니다.
     * @param name 제목을 가리킵니다.
     * @param lecture 과제가 게시된 과목을 가리킵니다.
     * @param startTime 시작 시간을 가리킵니다.
     * @param endTime 마감 시간을 가리킵니다.
     * @param extendEndTime 연장된 마감 시간을 가리킵니다.
     * @param isSubmitted 제출 여부를 가리킵니다.
     * @param currentProgressStatus 현재 진행 상황을 가리킵니다.
     * @param submitStudentNum 제출 학생 수를 가리킵니다.
     * @param totalStudentNum 전체 학생 수를 가리킵니다.
     * @param index 해당 과제의 포탈 서버 내에서의 순서를 나타냅니다.
     */
    public Homework(String name, Lecture lecture, String startTime, String endTime, String extendEndTime, boolean isSubmitted, boolean isOpenTask, String currentProgressStatus, int submitStudentNum, int totalStudentNum, int index) {
        this.name = name;
        this.lecture = lecture;
        this.startTime = startTime;
        this.endTime = endTime;
        this.extendEndTime = extendEndTime;
        this.isSubmitted = isSubmitted;
        this.isOpenTask = isOpenTask;
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

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
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

    public boolean isOpenTask() {
        return isOpenTask;
    }

    public void setIsOpenTask(boolean isOpenTask) {
        this.isOpenTask = isOpenTask;
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
