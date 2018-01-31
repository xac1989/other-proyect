package com.cencosud.middleware.profile.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.cencosud.middleware.profile.client.VtexClient;
import com.cencosud.middleware.profile.dto.UserPreferencePostRequest;
import com.cencosud.middleware.profile.exception.UserProfileException;
import com.cencosud.middleware.profile.model.JumboUserProfile;
import com.cencosud.middleware.profile.model.UserPreference;
import com.cencosud.middleware.profile.model.enums.RequestProtocolEnum;
import com.cencosud.middleware.profile.utils.StringUtils;
import com.cencosud.middleware.profile.utils.VtexUtilClient;
import com.fasterxml.jackson.core.type.TypeReference;

@Repository
public class ProfileVtexRepositoryImpl implements ProfileRepository {

	static final Logger logger = LoggerFactory.getLogger(ProfileVtexRepositoryImpl.class);

	public static final String SEARCH_PRODUCTS_URL = "/dataentities/CL/search";
	public static final String FIELDS = "userId,firstName,lastName,email,documentType,document,homePhone,gender,birthDate";
	public static final String USER_PROFILE_URL = "/dataentities/UP/documents";
	private static final String PATH_SALES_CHANNEL = "/dataentities/UP/documents/";
	public static final String USER_PROFILE_URL_HOMOLOG = "/api/profile-system/pvt/profiles/";
	public static final String PERSONAL_DATA_HOMOLOG = "/personalData";

	@Autowired
	@Qualifier("vtexClientJumbo")
	VtexClient client;

	@Autowired
	@Qualifier("vTexUtilClient")
	private VtexUtilClient clientUtil;

	@Autowired
	@Qualifier("vTexUtilClientEcom")
	private VtexUtilClient clientUtilEcom;

	public void setClient(VtexClient client) {
		this.client = client;
	}

	@Override
	public JumboUserProfile findById(String id) {
		JumboUserProfile result = new JumboUserProfile();
		try {
			List<NameValuePair> nvps = new ArrayList<>();
			NameValuePair email = new BasicNameValuePair("email", id);
			NameValuePair fields = new BasicNameValuePair("_fields", FIELDS);
			nvps.add(email);
			nvps.add(fields);

			List<JumboUserProfile> preResult = client.execute(SEARCH_PRODUCTS_URL, nvps, RequestProtocolEnum.GET,
					new TypeReference<List<JumboUserProfile>>() {
					});
			if (!CollectionUtils.isEmpty(preResult)) {
				result = preResult.get(0);
			}
		} catch (UserProfileException e) {
			logger.error("Issues when deserializing VtexProfile.", e);
		}
		return result;
	}

	@Override
	public void postPreferences(UserPreference request) {
		clientUtil.executeService(USER_PROFILE_URL, request, UserPreferencePostRequest.class, HttpMethod.PUT);
	}

	@Override
	public UserPreference getUserPreference(String userId) {
		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("_fields", "id,defaultSalesChannel,deliveryMode,deliveryAddress");
		UserPreference userPreference = clientUtil.executeService(PATH_SALES_CHANNEL+userId, null, UserPreference.class,
				HttpMethod.GET, queryParams);
		return userPreference;
	}

	@Override
	public void updateUserProfile(JumboUserProfile request, String email) {

		StringBuilder path = new StringBuilder(USER_PROFILE_URL_HOMOLOG);
		path.append(email);
		path.append(PERSONAL_DATA_HOMOLOG);

		Map<String, Object> userMap = new HashMap<>();
		userMap.put("birthDate", StringUtils.convertToString(request.getBirthDate()));
		userMap.put("defaultSalesChannel", request.getDefaultSalesChannel());
		userMap.put("document", request.getDocument());
		userMap.put("documentType", request.getDocumentType());
		userMap.put("email", request.getEmail());
		userMap.put("firstName", request.getFirstName());
		userMap.put("gender", request.getGender());
		userMap.put("homePhone", request.getHomePhone());
		userMap.put("id", request.getId());
		userMap.put("lastName", request.getLastName());
		userMap.put("userId", request.getUserId());

		String response = clientUtilEcom.executeService(path.toString(), userMap, String.class, HttpMethod.POST);

	}

}