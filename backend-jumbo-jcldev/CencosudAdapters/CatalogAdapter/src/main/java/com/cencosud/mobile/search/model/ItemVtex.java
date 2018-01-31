package com.cencosud.mobile.search.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * Item definition
 **/

@ApiModel(description = "Item definition")
@javax.annotation.Generated(value = "class com.github.mfpdev.adapters.swagger.codegen.MfpAdapterCodegen", date = "2017-01-09T16:45:59.781-03:00")
public class ItemVtex   {
  
  private String itemId = null;
  private List<SellerVtex> sellers = new ArrayList<SellerVtex>();
  private List<ImageVtex> images = new ArrayList<ImageVtex>();

  /**
   * Item Identification
   **/
  public ItemVtex itemId(String itemId) {
    this.itemId = itemId;
    return this;
  }

  
  @ApiModelProperty(example = "123123", value = "Item Identification")
  @JsonProperty("itemId")
  public String getItemId() {
    return itemId;
  }
  public void setItemId(String itemId) {
    this.itemId = itemId;
  }

  /**
   * Seller
   **/
  public ItemVtex sellers(List<SellerVtex> sellers) {
    this.sellers = sellers;
    return this;
  }

  
  @ApiModelProperty(value = "Seller")
  @JsonProperty("sellers")
  public List<SellerVtex> getSellers() {
    return sellers;
  }
  public void setSellers(List<SellerVtex> sellers) {
    this.sellers = sellers;
  }

  /**
   * URLs for images
   **/
  public ItemVtex images(List<ImageVtex> images) {
    this.images = images;
    return this;
  }

  
  @ApiModelProperty(value = "URLs for images")
  @JsonProperty("images")
  public List<ImageVtex> getImages() {
    return images;
  }
  public void setImages(List<ImageVtex> images) {
    this.images = images;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ItemVtex itemVtex = (ItemVtex) o;
    return Objects.equals(itemId, itemVtex.itemId) &&
        Objects.equals(sellers, itemVtex.sellers) &&
        Objects.equals(images, itemVtex.images);
  }

  @Override
  public int hashCode() {
    return Objects.hash(itemId, sellers, images);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ItemVtex {\n");
    
    sb.append("    itemId: ").append(toIndentedString(itemId)).append("\n");
    sb.append("    sellers: ").append(toIndentedString(sellers)).append("\n");
    sb.append("    images: ").append(toIndentedString(images)).append("\n");
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

