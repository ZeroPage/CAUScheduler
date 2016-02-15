package org.zeropage.causcheduler.data;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by Donghwan on 2016-02-04.
 */
public class Lecture extends RealmObject {
	@Required
	private int lectureNum;
	@Required
	private String lectureName;
	@Required
	private String professorName;
	@Required
	private String lectureDeptName;
	private int sectionNum;
	@Required
	private String studyPeriod;

	public Lecture() {
	}

	/**
	 * Lecture 인스턴스를 초기화합니다.
	 * @param lectureName 과목의 이름을 가리킵니다.
	 * @param lectureNum 과목의 포탈 서버 내의 id 값을 가리킵니다.
	 * @param professorName 과목을 담당하는 교수님의 성함을 가리킵니다.
	 * @param lectureDeptName 과목이 소속된 학과를 가리킵니다.
	 * @param sectionNum 해당 과목의 분반 번호를 가리킵니다.
	 * @param studyPeriod 과목의 수강기간을 가리킵니다.
	 */
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
