package org.zeropage.causcheduler.data;

import io.realm.RealmObject;
import io.realm.annotations.Required;

import java.util.Date;

/**
 * 한 식단의 정보를 가지고 있는 클래스입니다.
 * Created by Lumin on 2016-01-11.
 */
///**
// * Meal 인스턴스를 초기화합니다.
// * @param price 해당 식단의 가격을 가리킵니다.
// * @param totalCalorie 해당 식단의 총 칼로리를 가리킵니다.
// * @param distributeTime 해당 식단의 배급 시간을 가리킵니다.
// * @param name 해당 식단의 이름을 가리킵니다.
// * @param menu 해당 식단의 구성 메뉴들을 가리킵니다.
// */
public class Meal extends RealmObject{
    @Required
    private String name;
    private int restaurantCode;
    @Required
    private Date date;
    private int price;
    private float totalCalorie;
    @Required
    private String distributeTime;
    private String[] menu;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public float getTotalCalorie() {
        return totalCalorie;
    }

    public void setTotalCalorie(float totalCalorie) {
        this.totalCalorie = totalCalorie;
    }

    public String getDistributeTime() {
        return distributeTime;
    }

    public void setDistributeTime(String distributeTime) {
        this.distributeTime = distributeTime;
    }

    public String[] getMenu() {
        return menu;
    }

    public void setMenu(String[] menu) {
        this.menu = menu;
    }
}