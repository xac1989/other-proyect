package com.cencosud.middleware.feedback.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * 
 * <h1>Product</h1>
 * <p>
 * Modelo de objeto feedback utilizado para guardar en base de datos
 * El mismo se compone por: <BR> 
 * * value : valoracion dada por los iconos . <BR>
 * * email : email del usuario que crea el feedback. <BR>
 * * date : fecha del feedback. <BR>
 * * storeId : ID de la tienda donde se realizo la compra. <BR>
 * * telefono : telefono del contacto. <BR>
 * * idContent : ID del contenido relacionado al feedback. <BR>
 * </p>
 */
@Document(collection = "feedback")
public class Feedback {

	@Id
	private String id;
	private String value;
	private String email;
	private String description;
	private Date date;
	private String storeId;
	private String telefono;
	private String idContent;
	
	public Feedback() {
	}

	/**
	 * @param id
	 * @param value
	 * @param email
	 * @param description
	 * @param date
	 * @param storeId
	 * @param telefono
	 * @param idContent
	 */
	public Feedback(String id, String value, String email, String description, Date date, String storeId,
			String telefono, String idContent) {
		super();
		this.id = id;
		this.value = value;
		this.email = email;
		this.description = description;
		this.date = date;
		this.storeId = storeId;
		this.telefono = telefono;
		this.idContent = idContent;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getIdContent() {
		return idContent;
	}

	public void setIdContent(String idContent) {
		this.idContent = idContent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idContent == null) ? 0 : idContent.hashCode());
		result = prime * result + ((storeId == null) ? 0 : storeId.hashCode());
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Feedback other = (Feedback) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idContent == null) {
			if (other.idContent != null)
				return false;
		} else if (!idContent.equals(other.idContent))
			return false;
		if (storeId == null) {
			if (other.storeId != null)
				return false;
		} else if (!storeId.equals(other.storeId))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Feedback [id=" + id + ", value=" + value + ", email=" + email + ", description=" + description
				+ ", date=" + date + ", storeId=" + storeId + ", telefono=" + telefono + ", idContent=" + idContent
				+ "]";
	}

}