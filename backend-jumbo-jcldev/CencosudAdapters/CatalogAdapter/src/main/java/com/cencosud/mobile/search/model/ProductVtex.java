package com.cencosud.mobile.search.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * Product definition
 **/

@ApiModel(description = "Product definition")
@javax.annotation.Generated(value = "class com.github.mfpdev.adapters.swagger.codegen.MfpAdapterCodegen", date = "2017-01-09T16:45:59.781-03:00")
public class ProductVtex   {
  
  private String brand = null;
  private List<String> allSpecifiactions = new ArrayList<String>();
  private String productName = null;
  private String productId = null;
  private String description = null;
  private String infoGeneral = null;
  private String productReference = null;
  private List<ItemVtex> items = new ArrayList<ItemVtex>();

  /**
   * Brand
   **/
  public ProductVtex brand(String brand) {
    this.brand = brand;
    return this;
  }

  
  @ApiModelProperty(value = "Brand")
  @JsonProperty("brand")
  public String getBrand() {
    return brand;
  }
  public void setBrand(String brand) {
    this.brand = brand;
  }

  /**
   * Specifications list
   **/
  public ProductVtex allSpecifiactions(List<String> allSpecifiactions) {
    this.allSpecifiactions = allSpecifiactions;
    return this;
  }

  
  @ApiModelProperty(value = "Specifications list")
  @JsonProperty("allSpecifiactions")
  public List<String> getAllSpecifiactions() {
    return allSpecifiactions;
  }
  public void setAllSpecifiactions(List<String> allSpecifiactions) {
    this.allSpecifiactions = allSpecifiactions;
  }

  /**
   * Product name
   **/
  public ProductVtex productName(String productName) {
    this.productName = productName;
    return this;
  }

  
  @ApiModelProperty(example = "camera", value = "Product name")
  @JsonProperty("productName")
  public String getProductName() {
    return productName;
  }
  public void setProductName(String productName) {
    this.productName = productName;
  }

  /**
   * Identification
   **/
  public ProductVtex productId(String productId) {
    this.productId = productId;
    return this;
  }

  
  @ApiModelProperty(example = "0001", value = "Identification")
  @JsonProperty("productId")
  public String getProductId() {
    return productId;
  }
  public void setProductId(String productId) {
    this.productId = productId;
  }

  /**
   * Product Description
   **/
  public ProductVtex description(String description) {
    this.description = description;
    return this;
  }

  
  @ApiModelProperty(example = "Panel LED de 48” 1920 x 1080", value = "Product Description")
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * General Info
   **/
  public ProductVtex infoGeneral(String infoGeneral) {
    this.infoGeneral = infoGeneral;
    return this;
  }

  
  @ApiModelProperty(example = "Vive la nueva era de ver televisión gracias a Samsung.", value = "General Info")
  @JsonProperty("Info General")
  public String getInfoGeneral() {
    return infoGeneral;
  }
  public void setInfoGeneral(String infoGeneral) {
    this.infoGeneral = infoGeneral;
  }

  /**
   * Product Reference
   **/
  public ProductVtex productReference(String productReference) {
    this.productReference = productReference;
    return this;
  }

  
  @ApiModelProperty(example = "471979", value = "Product Reference")
  @JsonProperty("productReference")
  public String getProductReference() {
    return productReference;
  }
  public void setProductReference(String productReference) {
    this.productReference = productReference;
  }

  /**
   * Items
   **/
  public ProductVtex items(List<ItemVtex> items) {
    this.items = items;
    return this;
  }

  
  @ApiModelProperty(value = "Items")
  @JsonProperty("items")
  public List<ItemVtex> getItems() {
    return items;
  }
  public void setItems(List<ItemVtex> items) {
    this.items = items;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductVtex productVtex = (ProductVtex) o;
    return Objects.equals(brand, productVtex.brand) &&
        Objects.equals(allSpecifiactions, productVtex.allSpecifiactions) &&
        Objects.equals(productName, productVtex.productName) &&
        Objects.equals(productId, productVtex.productId) &&
        Objects.equals(description, productVtex.description) &&
        Objects.equals(infoGeneral, productVtex.infoGeneral) &&
        Objects.equals(productReference, productVtex.productReference) &&
        Objects.equals(items, productVtex.items);
  }

  @Override
  public int hashCode() {
    return Objects.hash(brand, allSpecifiactions, productName, productId, description, infoGeneral, productReference, items);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductVtex {\n");
    
    sb.append("    brand: ").append(toIndentedString(brand)).append("\n");
    sb.append("    allSpecifiactions: ").append(toIndentedString(allSpecifiactions)).append("\n");
    sb.append("    productName: ").append(toIndentedString(productName)).append("\n");
    sb.append("    productId: ").append(toIndentedString(productId)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    infoGeneral: ").append(toIndentedString(infoGeneral)).append("\n");
    sb.append("    productReference: ").append(toIndentedString(productReference)).append("\n");
    sb.append("    items: ").append(toIndentedString(items)).append("\n");
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

