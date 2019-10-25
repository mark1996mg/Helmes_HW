package com.helmes.model;

import java.util.List;

public class User {
	
	private String userId;
	private String fullName;
	private List<Sector> sectors;
	private Boolean hasAgreedToTerms;
	
	public User() {
		
	}
	
	public String getUserId() {
		return userId;
	}
	public String getFullName() {
		return fullName;
	}
	public List<Sector> getSectors() {
		return sectors;
	}
	public Boolean getHasAgreedToTerms() {
		return hasAgreedToTerms;
	}
	
	
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public void setSectors(List<Sector> sectors) {
		this.sectors = sectors;
	}
	public void setHasAgreedToTerms(Boolean hasAgreedToTerms) {
		this.hasAgreedToTerms = hasAgreedToTerms;
	}
	
	

}
