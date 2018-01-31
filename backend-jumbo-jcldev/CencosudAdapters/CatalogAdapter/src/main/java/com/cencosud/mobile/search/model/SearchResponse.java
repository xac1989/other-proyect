package com.cencosud.mobile.search.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * API Search response
 **/

@ApiModel(description = "API Search response")
@javax.annotation.Generated(value = "class com.github.mfpdev.adapters.swagger.codegen.MfpAdapterCodegen", date = "2017-01-09T16:45:59.781-03:00")
public class SearchResponse   {
  
  private Metadata metadata = null;
  private Products products = null;

  /**
   * Response metadata
   **/
  public SearchResponse metadata(Metadata metadata) {
    this.metadata = metadata;
    return this;
  }

  
  @ApiModelProperty(value = "Response metadata")
  @JsonProperty("metadata")
  public Metadata getMetadata() {
    return metadata;
  }
  public void setMetadata(Metadata metadata) {
    this.metadata = metadata;
  }

  /**
   * Product list
   **/
  public SearchResponse products(Products products) {
    this.products = products;
    return this;
  }

  
  @ApiModelProperty(value = "Product list")
  @JsonProperty("products")
  public Products getProducts() {
    return products;
  }
  public void setProducts(Products products) {
    this.products = products;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SearchResponse searchResponse = (SearchResponse) o;
    return Objects.equals(metadata, searchResponse.metadata) &&
        Objects.equals(products, searchResponse.products);
  }

  @Override
  public int hashCode() {
    return Objects.hash(metadata, products);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SearchResponse {\n");
    
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
    sb.append("    products: ").append(toIndentedString(products)).append("\n");
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

