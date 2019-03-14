package com.sinc.project.model.vo;

import java.io.Serializable;

public class MyBasket_VO implements Serializable {
 private String user_Id;
 private String product_Id;
 private int cnt;
 private int discountPrice;
 
 public MyBasket_VO() {
  super();
  // TODO Auto-generated constructor stub
 }
 
 public MyBasket_VO(String user_Id, String product_ID, int cnt, int discountPrice) {
  super();
  this.user_Id = user_Id;
  this.product_Id = product_ID;
  this.cnt = cnt;
  this.discountPrice = discountPrice;
 }
 
 public String getUser_Id() {
  return user_Id;
 }
 public void setUser_Id(String user_Id) {
  this.user_Id = user_Id;
 }
 public String getProduct_ID() {
  return product_Id;
 }
 public void setProduct_ID(String product_ID) {
  this.product_Id = product_ID;
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

@Override
public String toString() {
	return "MyBasketVO [user_Id=" + user_Id + ", product_Id=" + product_Id + ", cnt=" + cnt + ", discountPrice="
			+ discountPrice + "]";
}
 
 

}