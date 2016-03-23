package org.zeropage.causcheduler.data;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * DB에 저장할 강의 정보 객체입니다.
 * Created by Donghwan on 2016-02-04.
 */
public class Lecture extends RealmObject {
	@Required
	private String name;
	@PrimaryKey
	private int num;
	@Required
	private String professorName;
	@Required
	private String deptName;
	private int sectionNum;
	@Required
	private String studyPeriod;

	public Lecture() {
	}

	/**
	 * Lecture 인스턴스를 초기화합니다.
	 * @param name 과목의 이름을 가리킵니다.
	 * @param num 과목의 포탈 서버 내의 id 값을 가리킵니다.
	 * @param professorName 과목을 담당하는 교수님의 성함을 가리킵니다.
	 * @param deptName 과목이 소속된 학과를 가리킵니다.
	 * @param sectionNum 해당 과목의 분반 번호를 가리킵니다.
	 * @param studyPeriod 과목의 수강기간을 가리킵니다.
	 */
	public Lecture(String name, int num, String professorName, String deptName, int sectionNum, String studyPeriod) {
		this.name = name;
		this.num = num;
		this.professorName = professorName;
		this.deptName = deptName;
		this.sectionNum = sectionNum;
		this.studyPeriod = studyPeriod;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfessorName() {
		return professorName;
	}

	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
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
