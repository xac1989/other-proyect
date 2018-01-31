package com.cencosud.middleware.bonus.model;

import java.math.BigInteger;

/**
 * 
 * 
 * <h1>BonusResponse</h1>
 * <p>
 * Objeto respuesta del servicio Web.
 * </p>
 * 
 * @author fernando.castro
 * @version 1.0
 * @since May 31, 2017
 */
public class BonusResponse {

	private String status;
	private String subStatus;
	private BigInteger points;

	public BonusResponse() {
	}

	public BonusResponse(String status, String subStatus, BigInteger points) {
		super();
		this.status = status;
		this.subStatus = subStatus;
		this.points = points;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the subStatus
	 */
	public String getSubStatus() {
		return subStatus;
	}

	/**
	 * @param subStatus the subStatus to set
	 */
	public void setSubStatus(String subStatus) {
		this.subStatus = subStatus;
	}

	/**
	 * @return the points
	 */
	public BigInteger getPoints() {
		return points;
	}

	/**
	 * @param points the points to set
	 */
	public void setPoints(BigInteger points) {
		this.points = points;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BonusResponse [status=");
		builder.append(status);
		builder.append(", subStatus=");
		builder.append(subStatus);
		builder.append(", points=");
		builder.append(points);
		builder.append("]");
		return builder.toString();
	}

	
}
