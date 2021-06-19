package com.lastbug.firstbook.member.model.dto;

import java.io.Serializable;

public class FaqDTO implements Serializable{
	
	private static final long serialVersionUID = -7685458380084706512L;
	private int postNum;
	private String faqPostTitle;
	private String faqPostContent;
	
	public FaqDTO() {
		
	}

	public FaqDTO(int postNum, String faqPostTitle, String faqPostContent) {
		this.postNum = postNum;
		this.faqPostTitle = faqPostTitle;
		this.faqPostContent = faqPostContent;
	}

	public int getPostNum() {
		return postNum;
	}

	public void setPostNum(int postNum) {
		this.postNum = postNum;
	}

	public String getFaqPostTitle() {
		return faqPostTitle;
	}

	public void setFaqPostTitle(String faqPostTitle) {
		this.faqPostTitle = faqPostTitle;
	}

	public String getFaqPostContent() {
		return faqPostContent;
	}

	public void setFaqPostContent(String faqPostContent) {
		this.faqPostContent = faqPostContent;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "FaqDTO [postNum=" + postNum + ", faqPostTitle=" + faqPostTitle + ", faqPostContent=" + faqPostContent
				+ "]";
	}
	

}
