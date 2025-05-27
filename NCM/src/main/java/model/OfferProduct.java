package model;

import java.math.BigDecimal;

public class OfferProduct {
	private int id;
	private String name;
	  private BigDecimal price ;
	  private BigDecimal discountOffer;
	  private String imgurl;
	  private BigDecimal discountprice;
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
	public BigDecimal getDiscountprice() {
		return discountprice;
	}
	public void setDiscountprice(BigDecimal discountprice) {
		this.discountprice = discountprice;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	  
}
