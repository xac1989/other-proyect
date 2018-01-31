package com.cencosud.mobile.search.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class com.github.mfpdev.adapters.swagger.codegen.MfpAdapterCodegen", date = "2017-01-09T16:45:59.781-03:00")
public class ProductResponse   {
  
  private Product product = null;
  private Metadata metadata = null;

  /**
   **/
  public ProductResponse product(Product product) {
    this.product = product;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("product")
  public Product getProduct() {
    return product;
  }
  public void setProduct(Product product) {
    this.product = product;
  }

  /**
   * Response metadata
   **/
  public ProductResponse metadata(Metadata metadata) {
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductResponse productResponse = (ProductResponse) o;
    return Objects.equals(product, productResponse.product) &&
        Objects.equals(metadata, productResponse.metadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(product, metadata);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductResponse {\n");
    
    sb.append("    product: ").append(toIndentedString(product)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
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

