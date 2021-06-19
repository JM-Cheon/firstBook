package com.lastbug.firstbook.novel.model.dto;

import java.io.Serializable;

import com.lastbug.firstbook.webnovel.model.dto.GenreCategoryDTO;

public class NovelDTO implements Serializable{
	
	private static final long serialVersionUID = -5222562669052161541L;
	
	private int novNum;
	private String categoryCode;
	private String novTitle;
	private String novAuthor;
	private int price;
	private String publisher;
	private String inform;
	private String imgLocation;
	private String orderableYn;
	private int novAmount;
	private GenreCategoryDTO categoryName;
	
	public NovelDTO() {
		super();
	}

	public NovelDTO(int novNum, String categoryCode, String novTitle, String novAuthor, int price, String publisher,
			String inform, String imgLocation, String orderableYn, int novAmount, GenreCategoryDTO categoryName) {
		super();
		this.novNum = novNum;
		this.categoryCode = categoryCode;
		this.novTitle = novTitle;
		this.novAuthor = novAuthor;
		this.price = price;
		this.publisher = publisher;
		this.inform = inform;
		this.imgLocation = imgLocation;
		this.orderableYn = orderableYn;
		this.novAmount = novAmount;
		this.categoryName = categoryName;
	}
	
	public int getNovNum() {
		return novNum;
	}

	public void setNovNum(int novNum) {
		this.novNum = novNum;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getNovTitle() {
		return novTitle;
	}

	public void setNovTitle(String novTitle) {
		this.novTitle = novTitle;
	}

	public String getNovAuthor() {
		return novAuthor;
	}

	public void setNovAuthor(String novAuthor) {
		this.novAuthor = novAuthor;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getInform() {
		return inform;
	}

	public void setInform(String inform) {
		this.inform = inform;
	}

	public String getImgLocation() {
		return imgLocation;
	}

	public void setImgLocation(String imgLocation) {
		this.imgLocation = imgLocation;
	}

	public String getOrderableYn() {
		return orderableYn;
	}

	public void setOrderableYn(String orderableYn) {
		this.orderableYn = orderableYn;
	}

	public int getNovAmount() {
		return novAmount;
	}

	public void setNovAmount(int novAmount) {
		this.novAmount = novAmount;
	}

	public GenreCategoryDTO getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(GenreCategoryDTO categoryName) {
		this.categoryName = categoryName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "NovelDTO [novNum=" + novNum + ", categoryCode=" + categoryCode + ", novTitle=" + novTitle
				+ ", novAuthor=" + novAuthor + ", price=" + price + ", publisher=" + publisher + ", inform=" + inform
				+ ", imgLocation=" + imgLocation + ", orderableYn=" + orderableYn + ", novAmount=" + novAmount
				+ ", categoryName=" + categoryName + "]";
	}





	
	
	
	
}
