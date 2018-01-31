package com.cencosud.mobile.search.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * Commercial Offer
 **/

@ApiModel(description = "Commercial Offer")
@javax.annotation.Generated(value = "class com.github.mfpdev.adapters.swagger.codegen.MfpAdapterCodegen", date = "2017-01-09T16:45:59.781-03:00")
public class CommertialOffer   {
  
  private Boolean available = null;
  private Double availableQuantity = null;
  private String price = null;
  private String listPrice = null;
  private String discountRate = null;

  /**
   * Available ins stock flag
   **/
  public CommertialOffer available(Boolean available) {
    this.available = available;
    return this;
  }

  
  @ApiModelProperty(example = "true", value = "Available ins stock flag")
  @JsonProperty("available")
  public Boolean getAvailable() {
    return available;
  }
  public void setAvailable(Boolean available) {
    this.available = available;
  }

  /**
   * Available quantity in stock
   **/
  public CommertialOffer availableQuantity(Double availableQuantity) {
    this.availableQuantity = availableQuantity;
    return this;
  }

  
  @ApiModelProperty(example = "10000.0", value = "Available quantity in stock")
  @JsonProperty("availableQuantity")
  public Double getAvailableQuantity() {
    return availableQuantity;
  }
  public void setAvailableQuantity(Double availableQuantity) {
    this.availableQuantity = availableQuantity;
  }

  /**
   * Product price
   **/
  public CommertialOffer price(String price) {
    this.price = price;
    return this;
  }

  
  @ApiModelProperty(example = "800", value = "Product price")
  @JsonProperty("price")
  public String getPrice() {
    return price;
  }
  public void setPrice(String price) {
    this.price = price;
  }

  /**
   * Product List Price
   **/
  public CommertialOffer listPrice(String listPrice) {
    this.listPrice = listPrice;
    return this;
  }

  
  @ApiModelProperty(example = "1000", value = "Product List Price")
  @JsonProperty("listPrice")
  public String getListPrice() {
    return listPrice;
  }
  public void setListPrice(String listPrice) {
    this.listPrice = listPrice;
  }

  /**
   * Discount Rate
   **/
  public CommertialOffer discountRate(String discountRate) {
    this.discountRate = discountRate;
    return this;
  }

  
  @ApiModelProperty(example = "20", value = "Discount Rate")
  @JsonProperty("discountRate")
  public String getDiscountRate() {
    return discountRate;
  }
  public void setDiscountRate(String discountRate) {
    this.discountRate = discountRate;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CommertialOffer commertialOffer = (CommertialOffer) o;
    return Objects.equals(available, commertialOffer.available) &&
        Objects.equals(availableQuantity, commertialOffer.availableQuantity) &&
        Objects.equals(price, commertialOffer.price) &&
        Objects.equals(listPrice, commertialOffer.listPrice) &&
        Objects.equals(discountRate, commertialOffer.discountRate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(available, availableQuantity, price, listPrice, discountRate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CommertialOffer {\n");
    
    sb.append("    available: ").append(toIndentedString(available)).append("\n");
    sb.append("    availableQuantity: ").append(toIndentedString(availableQuantity)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    listPrice: ").append(toIndentedString(listPrice)).append("\n");
    sb.append("    discountRate: ").append(toIndentedString(discountRate)).append("\n");
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

