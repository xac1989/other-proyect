package com.cencosud.middleware.enrollment.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * 
 * <h1>Enrollment</h1>
 * <p>
 * Modelo de objeto enrollment utilizado para guardar en base de datos.
 * <BR>
 * Un enrollment es un nuevo usuario que quiere usar la app de Scan&Go.
 * <BR>
 * El mismo se compone por: <BR> 
 * * name : nombre del nuevo usuario. <BR>
 * * lastname : apellido del nuevo usuario. <BR>
 * * rut : rut o identificacion del nuevo usuario. <BR>
 * * email : email del nuevo usuario. <BR>
 * * storeId : ID de la tienda donde puede usar la app. <BR>
 * * anotherStore : otra tienda donde puede usar la app. <BR>
 * * date : fecha del enrollment. <BR>
 * </p>
 */
@Document(collection = "enrollment")
public class Enrollment {

	@Id
	private String id;
	private String name;
	private String lastname;
	private String rut;
	private String email;
	private String storeId;
	private String anotherStore;
	private Date date;
	
	public Enrollment() {
	}

	/**
	 * @param id
	 * @param name
	 * @param lastname
	 * @param rut
	 * @param email
	 * @param storeId
	 * @param anotherStore
	 * @param date
	 */
	public Enrollment(String id, String name, String lastname, String rut, String email, String storeId,
			String anotherStore, Date date) {
		super();
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.rut = rut;
		this.email = email;
		this.storeId = storeId;
		this.anotherStore = anotherStore;
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getAnotherStore() {
		return anotherStore;
	}

	public void setAnotherStore(String anotherStore) {
		this.anotherStore = anotherStore;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((anotherStore == null) ? 0 : anotherStore.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((rut == null) ? 0 : rut.hashCode());
		result = prime * result + ((storeId == null) ? 0 : storeId.hashCode());
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
		Enrollment other = (Enrollment) obj;
		if (anotherStore == null) {
			if (other.anotherStore != null)
				return false;
		} else if (!anotherStore.equals(other.anotherStore))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
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
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (rut == null) {
			if (other.rut != null)
				return false;
		} else if (!rut.equals(other.rut))
			return false;
		if (storeId == null) {
			if (other.storeId != null)
				return false;
		} else if (!storeId.equals(other.storeId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Enrollment [id=" + id + ", name=" + name + ", lastname=" + lastname + ", rut=" + rut + ", email="
				+ email + ", storeId=" + storeId + ", anotherStore=" + anotherStore + ", date=" + date + "]";
	}

}