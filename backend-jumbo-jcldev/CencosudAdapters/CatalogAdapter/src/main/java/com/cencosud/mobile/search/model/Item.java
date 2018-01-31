package com.cencosud.mobile.search.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * Product Item
 **/

@ApiModel(description = "Product Item")
@javax.annotation.Generated(value = "class com.github.mfpdev.adapters.swagger.codegen.MfpAdapterCodegen", date = "2017-01-09T16:45:59.781-03:00")
public class Item   {
  
  private List<Image> images = new ArrayList<Image>();
  private String itemId = null;
  private List<Seller> sellers = new ArrayList<Seller>();

  /**
   * Images
   **/
  public Item images(List<Image> images) {
    this.images = images;
    return this;
  }

  
  @ApiModelProperty(value = "Images")
  @JsonProperty("images")
  public List<Image> getImages() {
    return images;
  }
  public void setImages(List<Image> images) {
    this.images = images;
  }

  /**
   * Item Identification
   **/
  public Item itemId(String itemId) {
    this.itemId = itemId;
    return this;
  }

  
  @ApiModelProperty(example = "2001097", value = "Item Identification")
  @JsonProperty("itemId")
  public String getItemId() {
    return itemId;
  }
  public void setItemId(String itemId) {
    this.itemId = itemId;
  }

  /**
   * Sellers
   **/
  public Item sellers(List<Seller> sellers) {
    this.sellers = sellers;
    return this;
  }

  
  @ApiModelProperty(value = "Sellers")
  @JsonProperty("sellers")
  public List<Seller> getSellers() {
    return sellers;
  }
  public void setSellers(List<Seller> sellers) {
    this.sellers = sellers;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Item item = (Item) o;
    return Objects.equals(images, item.images) &&
        Objects.equals(itemId, item.itemId) &&
        Objects.equals(sellers, item.sellers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(images, itemId, sellers);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Item {\n");
    
    sb.append("    images: ").append(toIndentedString(images)).append("\n");
    sb.append("    itemId: ").append(toIndentedString(itemId)).append("\n");
    sb.append("    sellers: ").append(toIndentedString(sellers)).append("\n");
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

