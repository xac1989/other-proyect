package com.cencosud.mobile.delivery.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class com.github.mfpdev.adapters.swagger.codegen.MfpAdapterCodegen", date = "2017-01-26T10:53:29.306-03:00")
public class Metadata   {
  
  private String requestTime = null;
  private String region = null;
  private String version = null;

  /**
   * Api Request Time
   **/
  public Metadata requestTime(String requestTime) {
    this.requestTime = requestTime;
    return this;
  }

  
  @ApiModelProperty(example = "Thu, 24 Nov 2016 15:17:32 Z", value = "Api Request Time")
  @JsonProperty("requestTime")
  public String getRequestTime() {
    return requestTime;
  }
  public void setRequestTime(String requestTime) {
    this.requestTime = requestTime;
  }

  /**
   * API Region
   **/
  public Metadata region(String region) {
    this.region = region;
    return this;
  }

  
  @ApiModelProperty(example = "r1", value = "API Region")
  @JsonProperty("region")
  public String getRegion() {
    return region;
  }
  public void setRegion(String region) {
    this.region = region;
  }

  /**
   * API Version
   **/
  public Metadata version(String version) {
    this.version = version;
    return this;
  }

  
  @ApiModelProperty(example = "v1", value = "API Version")
  @JsonProperty("version")
  public String getVersion() {
    return version;
  }
  public void setVersion(String version) {
    this.version = version;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Metadata metadata = (Metadata) o;
    return Objects.equals(requestTime, metadata.requestTime) &&
        Objects.equals(region, metadata.region) &&
        Objects.equals(version, metadata.version);
  }

  @Override
  public int hashCode() {
    return Objects.hash(requestTime, region, version);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Metadata {\n");
    
    sb.append("    requestTime: ").append(toIndentedString(requestTime)).append("\n");
    sb.append("    region: ").append(toIndentedString(region)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
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

