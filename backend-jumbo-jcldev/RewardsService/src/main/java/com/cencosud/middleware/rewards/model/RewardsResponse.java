package com.cencosud.middleware.rewards.model;

public class RewardsResponse {
	
	private Long balancePoints;
	private String documentNumber;
	private Document document;
	private String cardNumber;
	private Boolean linkedUser;
			
	public RewardsResponse() {
		super();
	}

	public RewardsResponse(Long balancePoints, String documentNumber, Document document, String cardNumber,
			Boolean linkedUser) {
		super();
		this.balancePoints = balancePoints;
		this.documentNumber = documentNumber;
		this.document = document;
		this.cardNumber = cardNumber;
		this.linkedUser = linkedUser;
	}

	public Long getBalancePoints() {
		return balancePoints;
	}

	public void setBalancePoints(Long balancePoints) {
		this.balancePoints = balancePoints;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Boolean getLinkedUser() {
		return linkedUser;
	}

	public void setLinkedUser(Boolean linkedUser) {
		this.linkedUser = linkedUser;
	}

	public static class Document{
		private String id;
		private String description;
		
		public Document() {
			super();
		}

		public Document(String id, String description) {
			super();
			this.id = id;
			this.description = description;
		}
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		
		
	}
}
