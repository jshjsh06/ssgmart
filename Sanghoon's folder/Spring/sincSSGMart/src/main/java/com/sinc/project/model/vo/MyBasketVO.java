package com.sinc.project.model.vo;

import java.io.Serializable;

public class MyBasketVO implements Serializable {

    private String user_Id;
    private String product_Id;
    private int cnt;
    private int discountPrice;

    public String getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(String user_Id) {
        this.user_Id = user_Id;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getProduct_Id() {
        return product_Id;
    }

    public void setProduct_Id(String product_Id) {
        this.product_Id = product_Id;
    }

    @Override
    public String toString() {
        return "MyBasketVO{" +
                "user_Id='" + user_Id + '\'' +
                ", product_Id='" + product_Id + '\'' +
                ", cnt=" + cnt +
                ", discountPrice=" + discountPrice +
                '}';
    }
}

