//package com.cencosud.mobile.model.profile.wong;
//
//import java.util.Objects;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.annotation.JsonCreator;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import java.util.ArrayList;
//import java.util.List;
//
//
//
//
//
//@javax.annotation.Generated(value = "class com.github.mfpdev.adapters.swagger.codegen.MfpAdapterCodegen", date = "2017-01-26T10:53:29.306-03:00")
//public class Favorites   {
//  
//  private List<String> categories = new ArrayList<String>();
//
//  /**
//   **/
//  public Favorites categories(List<String> categories) {
//    this.categories = categories;
//    return this;
//  }
//
//  
//  @ApiModelProperty(value = "")
//  @JsonProperty("categories")
//  public List<String> getCategories() {
//    return categories;
//  }
//  public void setCategories(List<String> categories) {
//    this.categories = categories;
//  }
//
//
//  @Override
//  public boolean equals(Object o) {
//    if (this == o) {
//      return true;
//    }
//    if (o == null || getClass() != o.getClass()) {
//      return false;
//    }
//    Favorites favorites = (Favorites) o;
//    return Objects.equals(categories, favorites.categories);
//  }
//
//  @Override
//  public int hashCode() {
//    return Objects.hash(categories);
//  }
//
//  @Override
//  public String toString() {
//    StringBuilder sb = new StringBuilder();
//    sb.append("class Favorites {\n");
//    
//    sb.append("    categories: ").append(toIndentedString(categories)).append("\n");
//    sb.append("}");
//    return sb.toString();
//  }
//
//  /**
//   * Convert the given object to string with each line indented by 4 spaces
//   * (except the first line).
//   */
//  private String toIndentedString(Object o) {
//    if (o == null) {
//      return "null";
//    }
//    return o.toString().replace("\n", "\n    ");
//  }
//}
//
