package com.cencosud.mobile.list.service;

import com.cencosud.mobile.list.dto.ListPostRequest;
import com.cencosud.mobile.list.dto.ListPostResponse;
import com.cencosud.mobile.list.dto.ListProductUpdateRequest;
import com.cencosud.mobile.list.dto.ListProductUpdateResponse;
import com.cencosud.mobile.list.dto.ListUpdateRequest;
import com.cencosud.mobile.list.dto.ShoppingListDto;
import com.cencosud.mobile.list.exception.NotFoundException;
import com.cencosud.mobile.list.model.ProductList;
import com.cencosud.mobile.list.model.UserShoppingList;

/**
 * 
 * 
 * <h1>ListApiService</h1>
 * <p>
 * Contrato del servicio para consultar el API
 * </p>
 * @version 1.0
 * @since Jun 30, 2017
 */
@javax.annotation.Generated(value = "class com.github.mfpdev.adapters.swagger.codegen.MfpAdapterCodegen", date = "2016-12-26T16:58:25.127-03:00")
public interface ListApiService {
      
      public UserShoppingList listsGet(String userId, String salesChannel,String region,String version) throws NotFoundException;
      
      /**
       * 
       * @param request {@link ListUpdateRequest} Request de actualizacion de servicio
       * @return {@link ListUpdateResponse} Response dela actualizacion del servicio
       * @throws NotFoundException
       */
      public ShoppingListDto updateList(ListUpdateRequest request, String region, String version) throws NotFoundException;
      
      public ListProductUpdateResponse updateProductList(ListProductUpdateRequest request, String region, String version) throws NotFoundException;
      
      public ListPostResponse createList(ListPostRequest request, String region, String version) throws NotFoundException;
      
      public void deleteList(String listId, String userId, String region, String version) throws NotFoundException;

      public ProductList getListProductGet(String userId, String skuId, String region, String version) throws NotFoundException;

}
