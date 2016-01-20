package org.zeropage.causcheduler.data;

/**
 * 한 식단의 정보를 가지고 있는 클래스입니다.
 * Created by Lumin on 2016-01-11.
 */
public class Meal {
    public final int price;
    public final float totalCalorie;
    public final String distributeTime;
    public final String name;
    public final String[] menu;

    /**
     * Meal 인스턴스를 초기화합니다.
     * @param price 해당 식단의 가격을 가리킵니다.
     * @param totalCalorie 해당 식단의 총 칼로리를 가리킵니다.
     * @param distributeTime 해당 식단의 배급 시간을 가리킵니다.
     * @param name 해당 식단의 이름을 가리킵니다.
     * @param menu 해당 식단의 구성 메뉴들을 가리킵니다.
     */
    public Meal(int price, float totalCalorie, String distributeTime, String name, String[] menu) {
        this.price = price;
        this.totalCalorie = totalCalorie;
        this.distributeTime = distributeTime;
        this.name = name;
        this.menu = menu;
    }
}