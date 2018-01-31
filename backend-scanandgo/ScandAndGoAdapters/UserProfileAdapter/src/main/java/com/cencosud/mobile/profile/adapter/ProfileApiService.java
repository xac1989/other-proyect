package com.cencosud.mobile.profile.adapter;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.cencosud.middleware.category.model.UserProfile;

@javax.annotation.Generated(value = "class com.github.mfpdev.adapters.swagger.codegen.MfpAdapterCodegen", date = "2017-01-26T10:53:29.306-03:00")
public interface ProfileApiService {
      public Response profileGet(String id,SecurityContext securityContext)
      throws NotFoundException;
      public Response profilePost(SecurityContext securityContext)
      throws NotFoundException;
      public Response profilePut(UserProfile profile,SecurityContext securityContext)
      throws NotFoundException;
}
