package com.example.khalipso.WebModels;

import java.util.Date;

public class UsersWebModel {
	
	
	private Integer id;
	private String userName;
	private String email;
	private String password;
	private String profileImage;
	private Date createdAt;
	private Date updatedAt;
	private String bio;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	@Override
	public String toString() {
		return "UsersWebModel [id=" + id + ", userName=" + userName + ", email=" + email + ", password=" + password
				+ ", profileImage=" + profileImage + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", bio="
				+ bio + "]";
	}
	
	

}
