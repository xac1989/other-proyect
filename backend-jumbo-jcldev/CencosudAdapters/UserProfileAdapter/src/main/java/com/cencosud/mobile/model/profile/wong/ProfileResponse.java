package com.cencosud.mobile.model.profile.wong;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.cencosud.mobile.model.profile.wong.Metadata;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class com.github.mfpdev.adapters.swagger.codegen.MfpAdapterCodegen", date = "2017-01-26T10:53:29.306-03:00")
public class ProfileResponse   {
  
  private Metadata metadata = null;
  private Object profile = null;

  /**
   * API Metadata
   **/
  public ProfileResponse metadata(Metadata metadata) {
    this.metadata = metadata;
    return this;
  }

  
  @ApiModelProperty(value = "API Metadata")
  @JsonProperty("metadata")
  public Metadata getMetadata() {
    return metadata;
  }
  public void setMetadata(Metadata metadata) {
    this.metadata = metadata;
  }

  /**
   * UserProfile
   **/
  public ProfileResponse profile(Object profile) {
    this.profile = profile;
    return this;
  }

  
  @ApiModelProperty(value = "UserProfile")
  @JsonProperty("profile")
  public Object getProfile() {
    return profile;
  }
  public void setProfile(Object profile) {
    this.profile = profile;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProfileResponse profileResponse = (ProfileResponse) o;
    return Objects.equals(metadata, profileResponse.metadata) &&
        Objects.equals(profile, profileResponse.profile);
  }

  @Override
  public int hashCode() {
    return Objects.hash(metadata, profile);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProfileResponse {\n");
    
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
    sb.append("    profile: ").append(toIndentedString(profile)).append("\n");
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

