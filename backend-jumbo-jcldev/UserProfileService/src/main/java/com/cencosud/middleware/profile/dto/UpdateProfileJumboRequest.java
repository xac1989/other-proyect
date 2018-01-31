package com.cencosud.middleware.profile.dto;

public class UpdateProfileJumboRequest{

	protected String id;
	protected String vtexId;
	protected String document;
	protected String documentType;
	protected String firstName;
	protected String lastName;
	protected String fullName;
	protected String phone;
	protected String birthDate;
	protected String sex;
	
	private Integer defaultSalesChannel;
	
	public UpdateProfileJumboRequest(){
		super();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVtexId() {
		return vtexId;
	}

	public void setVtexId(String vtexId) {
		this.vtexId = vtexId;
	}

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

	public Integer getDefaultSalesChannel() {
		return defaultSalesChannel;
	}

	public void setDefaultSalesChannel(Integer defaultSalesChannel) {
		this.defaultSalesChannel = defaultSalesChannel;
	}

	
}
