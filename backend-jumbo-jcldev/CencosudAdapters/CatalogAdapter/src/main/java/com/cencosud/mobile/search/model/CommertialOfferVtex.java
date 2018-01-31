package com.cencosud.mobile.search.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * Commercial offer
 **/

@ApiModel(description = "Commercial offer")
@javax.annotation.Generated(value = "class com.github.mfpdev.adapters.swagger.codegen.MfpAdapterCodegen", date = "2017-01-09T16:45:59.781-03:00")
public class CommertialOfferVtex   {
  
  private Double availableQuantity = null;
  private String discountRate = null;
  private String price = null;
  private String listPrice = null;

  /**
   * Avaliable quantity
   **/
  public CommertialOfferVtex availableQuantity(Double availableQuantity) {
    this.availableQuantity = availableQuantity;
    return this;
  }

  
  @ApiModelProperty(example = "10000.0", value = "Avaliable quantity")
  @JsonProperty("AvailableQuantity")
  public Double getAvailableQuantity() {
    return availableQuantity;
  }
  public void setAvailableQuantity(Double availableQuantity) {
    this.availableQuantity = availableQuantity;
  }

  /**
   * Discount rate
   **/
  public CommertialOfferVtex discountRate(String discountRate) {
    this.discountRate = discountRate;
    return this;
  }

  
  @ApiModelProperty(example = "20", value = "Discount rate")
  @JsonProperty("discountRate")
  public String getDiscountRate() {
    return discountRate;
  }
  public void setDiscountRate(String discountRate) {
    this.discountRate = discountRate;
  }

  /**
   * Price
   **/
  public CommertialOfferVtex price(String price) {
    this.price = price;
    return this;
  }

  
  @ApiModelProperty(example = "12.3", value = "Price")
  @JsonProperty("Price")
  public String getPrice() {
    return price;
  }
  public void setPrice(String price) {
    this.price = price;
  }

  /**
   * List price
   **/
  public CommertialOfferVtex listPrice(String listPrice) {
    this.listPrice = listPrice;
    return this;
  }

  
  @ApiModelProperty(example = "24.34", value = "List price")
  @JsonProperty("ListPrice")
  public String getListPrice() {
    return listPrice;
  }
  public void setListPrice(String listPrice) {
    this.listPrice = listPrice;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CommertialOfferVtex commertialOfferVtex = (CommertialOfferVtex) o;
    return Objects.equals(availableQuantity, commertialOfferVtex.availableQuantity) &&
        Objects.equals(discountRate, commertialOfferVtex.discountRate) &&
        Objects.equals(price, commertialOfferVtex.price) &&
        Objects.equals(listPrice, commertialOfferVtex.listPrice);
  }

  @Override
  public int hashCode() {
    return Objects.hash(availableQuantity, discountRate, price, listPrice);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CommertialOfferVtex {\n");
    
    sb.append("    availableQuantity: ").append(toIndentedString(availableQuantity)).append("\n");
    sb.append("    discountRate: ").append(toIndentedString(discountRate)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    listPrice: ").append(toIndentedString(listPrice)).append("\n");
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

