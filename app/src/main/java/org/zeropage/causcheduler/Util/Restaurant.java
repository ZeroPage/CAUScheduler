package org.zeropage.causcheduler.Util;

/**
 * Created by Lumin on 2016-01-09.
 * 이용 가능한 식당의 목록을 가져옵니다.
 */
public enum Restaurant {
    /**
     * University Club 식당을 가리킵니다.
     */
    UniversityClub("11"),
    /**
     * 교직원 식당을 가리킵니다.
     */
    EmployeeRestourant("07"),
    /**
     * 신 기숙사 식당을 가리킵니다.
     */
    NewDormitory("12"),
    /**
     * 기존 기숙사 식당을 가리킵니다.
     */
    Dormitory("08"),
    /**
     * 법학관 식당을 가리킵니다.
     */
    RawBuilding("06"),
    /**
     * 참마루 식당을 가리킵니다.
     */
    CharmMaru("03"),
    /**
     * 슬기마루 식당을 가리킵니다.
     */
    SensibleMaru("02");

    public final String code;

    Restaurant(String code) {
        this.code = code;
    }
}
