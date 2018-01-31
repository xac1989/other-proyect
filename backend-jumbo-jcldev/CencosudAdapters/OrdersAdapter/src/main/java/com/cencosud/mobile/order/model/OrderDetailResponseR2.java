package com.cencosud.mobile.order.model;

import java.util.Objects;

public class OrderDetailResponseR2 extends OrderDetailResponse  {
  private OrderDetailR2 order = null;
  

  public OrderDetailR2 getOrder() {
    return order;
  }
  public void setOrder(OrderDetailR2 order) {
    this.order = order;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderDetailResponseR2 ordersResponse = (OrderDetailResponseR2) o;
    return Objects.equals(order, ordersResponse.order) &&
        Objects.equals(super.getMetadata(), ordersResponse.getMetadata());
  }

  @Override
  public int hashCode() {
    return Objects.hash(order, super.getMetadata());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrdersResponse {\n");
    
    sb.append("    orders: ").append(toIndentedString(order)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(super.getMetadata())).append("\n");
    sb.append("}");
    return sb.toString();
  }

}

