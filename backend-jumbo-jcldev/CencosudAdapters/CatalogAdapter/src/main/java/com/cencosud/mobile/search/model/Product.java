package com.cencosud.mobile.search.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * Product
 **/

@ApiModel(description = "Product")
@javax.annotation.Generated(value = "class com.github.mfpdev.adapters.swagger.codegen.MfpAdapterCodegen", date = "2017-01-09T16:45:59.781-03:00")
public class Product   {
  
  private String brand = null;
  private String categoryId = null;
  private String generalInfo = null;
  private List<String> specifications = new ArrayList<String>();
  private String description = null;
  private String productReference = null;
  private String productId = null;
  private String productName = null;
  private List<Item> items = new ArrayList<Item>();

  /**
   **/
  public Product brand(String brand) {
    this.brand = brand;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("brand")
  public String getBrand() {
    return brand;
  }
  public void setBrand(String brand) {
    this.brand = brand;
  }

  /**
   **/
  public Product categoryId(String categoryId) {
    this.categoryId = categoryId;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("categoryId")
  public String getCategoryId() {
    return categoryId;
  }
  public void setCategoryId(String categoryId) {
    this.categoryId = categoryId;
  }

  /**
   * General info
   **/
  public Product generalInfo(String generalInfo) {
    this.generalInfo = generalInfo;
    return this;
  }

  
  @ApiModelProperty(value = "General info")
  @JsonProperty("generalInfo")
  public String getGeneralInfo() {
    return generalInfo;
  }
  public void setGeneralInfo(String generalInfo) {
    this.generalInfo = generalInfo;
  }

  /**
   * Specifications
   **/
  public Product specifications(List<String> specifications) {
    this.specifications = specifications;
    return this;
  }

  
  @ApiModelProperty(value = "Specifications")
  @JsonProperty("specifications")
  public List<String> getSpecifications() {
    return specifications;
  }
  public void setSpecifications(List<String> specifications) {
    this.specifications = specifications;
  }

  /**
   * Product Description
   **/
  public Product description(String description) {
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
   * Product Reference
   **/
  public Product productReference(String productReference) {
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
   * Product Identification
   **/
  public Product productId(String productId) {
    this.productId = productId;
    return this;
  }

  
  @ApiModelProperty(example = "2001081", value = "Product Identification")
  @JsonProperty("productId")
  public String getProductId() {
    return productId;
  }
  public void setProductId(String productId) {
    this.productId = productId;
  }

  /**
   * Product Name
   **/
  public Product productName(String productName) {
    this.productName = productName;
    return this;
  }

  
  @ApiModelProperty(example = "Samsung Televisor LED Full HD Smart 48 UN48H5500AG", value = "Product Name")
  @JsonProperty("productName")
  public String getProductName() {
    return productName;
  }
  public void setProductName(String productName) {
    this.productName = productName;
  }

  /**
   * Items
   **/
  public Product items(List<Item> items) {
    this.items = items;
    return this;
  }

  
  @ApiModelProperty(value = "Items")
  @JsonProperty("items")
  public List<Item> getItems() {
    return items;
  }
  public void setItems(List<Item> items) {
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
    Product product = (Product) o;
    return Objects.equals(brand, product.brand) &&
        Objects.equals(categoryId, product.categoryId) &&
        Objects.equals(generalInfo, product.generalInfo) &&
        Objects.equals(specifications, product.specifications) &&
        Objects.equals(description, product.description) &&
        Objects.equals(productReference, product.productReference) &&
        Objects.equals(productId, product.productId) &&
        Objects.equals(productName, product.productName) &&
        Objects.equals(items, product.items);
  }

  @Override
  public int hashCode() {
    return Objects.hash(brand, categoryId, generalInfo, specifications, description, productReference, productId, productName, items);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Product {\n");
    
    sb.append("    brand: ").append(toIndentedString(brand)).append("\n");
    sb.append("    categoryId: ").append(toIndentedString(categoryId)).append("\n");
    sb.append("    generalInfo: ").append(toIndentedString(generalInfo)).append("\n");
    sb.append("    specifications: ").append(toIndentedString(specifications)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    productReference: ").append(toIndentedString(productReference)).append("\n");
    sb.append("    productId: ").append(toIndentedString(productId)).append("\n");
    sb.append("    productName: ").append(toIndentedString(productName)).append("\n");
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

