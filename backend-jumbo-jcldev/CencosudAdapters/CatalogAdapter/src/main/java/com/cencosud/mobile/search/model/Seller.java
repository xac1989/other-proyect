package com.cencosud.mobile.search.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * Seller
 **/

@ApiModel(description = "Seller")
@javax.annotation.Generated(value = "class com.github.mfpdev.adapters.swagger.codegen.MfpAdapterCodegen", date = "2017-01-09T16:45:59.781-03:00")
public class Seller   {
  
  private String addToCartLink = null;
  private CommertialOffer commertialOffer = null;

  /**
   * Add to cart link
   **/
  public Seller addToCartLink(String addToCartLink) {
    this.addToCartLink = addToCartLink;
    return this;
  }

  
  @ApiModelProperty(example = "https://wongqa.vtexcommercestable.com.br/checkout/cart/add?sku&#x3D;2000371&amp;qty&#x3D;1&amp;seller&#x3D;1&amp;sc&#x3D;1&amp;price&#x3D;19900&amp;cv&#x3D;900ab47aa9c6909beabc5b55ce8f1b06_geral:6526CBEB824751E6BAC7DCB9FEF12394&amp;sc&#x3D;1", value = "Add to cart link")
  @JsonProperty("addToCartLink")
  public String getAddToCartLink() {
    return addToCartLink;
  }
  public void setAddToCartLink(String addToCartLink) {
    this.addToCartLink = addToCartLink;
  }

  /**
   * Commertial Offer
   **/
  public Seller commertialOffer(CommertialOffer commertialOffer) {
    this.commertialOffer = commertialOffer;
    return this;
  }

  
  @ApiModelProperty(value = "Commertial Offer")
  @JsonProperty("commertialOffer")
  public CommertialOffer getCommertialOffer() {
    return commertialOffer;
  }
  public void setCommertialOffer(CommertialOffer commertialOffer) {
    this.commertialOffer = commertialOffer;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Seller seller = (Seller) o;
    return Objects.equals(addToCartLink, seller.addToCartLink) &&
        Objects.equals(commertialOffer, seller.commertialOffer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(addToCartLink, commertialOffer);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Seller {\n");
    
    sb.append("    addToCartLink: ").append(toIndentedString(addToCartLink)).append("\n");
    sb.append("    commertialOffer: ").append(toIndentedString(commertialOffer)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

