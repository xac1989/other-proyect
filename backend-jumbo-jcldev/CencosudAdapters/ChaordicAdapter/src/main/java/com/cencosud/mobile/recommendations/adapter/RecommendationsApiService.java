package com.cencosud.mobile.recommendations.adapter;

import com.cencosud.mobile.recommendations.adapter.*;
import com.cencosud.mobile.recommendations.model.*;

import java.util.List;
import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class com.github.mfpdev.adapters.swagger.codegen.MfpAdapterCodegen", date = "2016-12-26T16:58:25.127-03:00")
public interface RecommendationsApiService {
      public Response recommendationsGet(String type,List<String> productId,List<String> categoryId,List<String> tagId,String deviceId,String userId,String useBoughtProducts,String useCartProducts,String useVisitedProducts,Integer size,SecurityContext securityContext)
      throws NotFoundException;
}
