package com.cencosud.mobile.order.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class OrderDetailResponse {
	private Metadata metadata = null;
	
	  @ApiModelProperty(value = "")
	  @JsonProperty("metadata")
	  public Metadata getMetadata() {
	    return metadata;
	  }
	  public void setMetadata(Metadata metadata) {
	    this.metadata = metadata;
	  }
	  
	  /**
	   * Convert the given object to string with each line indented by 4 spaces
	   * (except the first line).
	   */
	  protected String toIndentedString(Object o) {
	    if (o == null) {
	      return "null";
	    }
	    return o.toString().replace("\n", "\n    ");
	  }
}
