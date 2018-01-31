package com.cencosud.middleware.product.model;


/**
 * 
 * 
 * <h1>Image</h1>
 * <p>
 * Modelo de objeto Image utilizado para guardar en base de datos.
 * </p>
 * @author fernando.castro
 * @version 1.0
 * @since Mar 22, 2017
 */
public class Image{
	
	private String imageUrl;
	private String imageName;
	private Integer fileId;
	
	/**
	 * 
	 */
	public Image() {
	}

	/**
	 * 
	 * @param imageUrl {@link String}
	 * @param imageName {@link String}
	 * @param fileId  {@link Integer}
	 */
	public Image(String imageUrl, String imageName, Integer fileId) {
		super();
		this.imageUrl = imageUrl;
		this.imageName = imageName;
		this.fileId = fileId;
	}

	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * @param imageUrl the imageUrl to set
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
	 * @return the imageName
	 */
	public String getImageName() {
		return imageName;
	}

	/**
	 * @param imageName the imageName to set
	 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	/**
	 * @return the fileId
	 */
	public Integer getFileId() {
		return fileId;
	}

	/**
	 * @param fileId the fileId to set
	 */
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fileId == null) ? 0 : fileId.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Image other = (Image) obj;
		if (fileId == null) {
			if (other.fileId != null)
				return false;
		} else if (!fileId.equals(other.fileId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Image [imageUrl=");
		builder.append(imageUrl);
		builder.append(", imageName=");
		builder.append(imageName);
		builder.append(", fileId=");
		builder.append(fileId);
		builder.append("]");
		return builder.toString();
	}
		
}