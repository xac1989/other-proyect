package com.cencosud.mobile.dto.profile.jumbo;

public class UpdateProfileGenericRequest {

//	private String id;
//	private String vtexId;
	private String document;
	private String documentType;
	private String firstName;
	private String lastName;
	private String fullName;
	private String phone;
	private String birthDate;
	private String sex;
	
//	public String getId() {
//		return id;
//	}
//	public void setId(String id) {
//		this.id = id;
//	}
//	public String getVtexId() {
//		return vtexId;
//	}
//	public void setVtexId(String vtexId) {
//		this.vtexId = vtexId;
//	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Override
	public String toString() {
		return "[UserGenericUpdateRequest] :: firstName::"+this.getFirstName()
		+":: lastName::"+this.getLastName();
	}
	
}
