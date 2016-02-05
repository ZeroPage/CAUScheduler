package org.zeropage.causcheduler.data;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by Donghwan on 2016-02-04.
 */
public class Lecture extends RealmObject {
	@PrimaryKey
	private String lectureName;
	private int lectureNum;
	@Required
	private String professorName;
	@Required
	private String lectureDeptName;
	private int sectionNum;
	@Required
	private String studyPeriod;

	public Lecture() {
	}

	public Lecture(String lectureName, int lectureNum, String professorName, String lectureDeptName, int sectionNum, String studyPeriod) {
		this.lectureName = lectureName;
		this.lectureNum = lectureNum;
		this.professorName = professorName;
		this.lectureDeptName = lectureDeptName;
		this.sectionNum = sectionNum;
		this.studyPeriod = studyPeriod;
	}

	public int getLectureNum() {
		return lectureNum;
	}

	public void setLectureNum(int lectureNum) {
		this.lectureNum = lectureNum;
	}

	public String getLectureName() {
		return lectureName;
	}

	public void setLectureName(String lectureName) {
		this.lectureName = lectureName;
	}

	public String getProfessorName() {
		return professorName;
	}

	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}

	public String getLectureDeptName() {
		return lectureDeptName;
	}

	public void setLectureDeptName(String lectureDeptName) {
		this.lectureDeptName = lectureDeptName;
	}

	public int getSectionNum() {
		return sectionNum;
	}

	public void setSectionNum(int sectionNum) {
		this.sectionNum = sectionNum;
	}

	public String getStudyPeriod() {
		return studyPeriod;
	}

	public void setStudyPeriod(String studyPeriod) {
		this.studyPeriod = studyPeriod;
	}
}
