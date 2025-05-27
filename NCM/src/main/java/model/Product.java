package model;

import java.math.BigDecimal;

public class Product {
  private  String email;
  private String name;
  private BigDecimal price ;
  private BigDecimal discountOffer;
  private String imgurl;
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public BigDecimal getPrice() {
	return price;
}
public void setPrice(BigDecimal price) {
	this.price = price;
}
public BigDecimal getDiscountOffer() {
	return discountOffer;
}
public void setDiscountOffer(BigDecimal discountOffer) {
	this.discountOffer = discountOffer;
}
public String getImgurl() {
	return imgurl;
}
public void setImgurl(String imgurl) {
	this.imgurl = imgurl;
}
  
}
