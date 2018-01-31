package com.cencosud.mobile.login.adapter;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import com.cencosud.mobile.model.UserProfile;
import com.ibm.mfp.security.checks.base.UserAuthenticationSecurityCheck;
import com.ibm.mfp.server.registration.external.model.AuthenticatedUser;
import com.ibm.mfp.server.security.external.checks.AuthorizationResponse;
import com.ibm.mfp.server.security.external.checks.SecurityCheckConfiguration;

public class SocialLoginAdapter extends UserAuthenticationSecurityCheck {

	public static final String VENDOR_KEY = "vendor";
	public static final String TOKEN_KEY = "token";
	public static final String CLIENT_KEY = "key";
	public static final String CLIENT_SECRET_KEY = "secretKey";
	public static final String VENDOR_ATTRIBUTE = "socialLoginVendor";
	public static final String ORIGINAL_TOKEN_ATTRIBUTE = "originalToken";

	private static final Logger logger = Logger.getLogger(SocialLoginAdapter.class.getName());

	private transient String vendorName;
	private transient AuthenticatedUser user;

	@Override
	public SecurityCheckConfiguration createConfiguration(Properties properties) {
		return new SocialLoginConfiguration(properties);
	}

	@Override
	public void authorize(Set<String> scope, Map<String, Object> credentials, HttpServletRequest request,
			AuthorizationResponse response) {
		super.authorize(scope, credentials, request, response);
		if (response.getType() == AuthorizationResponse.ResponseType.FAILURE) {
			Map<String, Object> failure = new HashMap<>();
			if (vendorName != null) {
				failure.put(vendorName, "invalid token");
			} else {
				failure.put("invalid_vendor_name", "vendor name cannot be null");
			}
			response.addFailure(getName(), failure);
		}
	}

	@Override
	protected Map<String, Object> createChallenge() {
		Map<String, Object> res = new HashMap<>();
		res.put("vendorList", getConfiguration().getEnabledVendors().keySet().toArray());
		return res;
	}

	@Override
	protected boolean validateCredentials(Map<String, Object> credentials) {
		vendorName = (String) credentials.get(VENDOR_KEY);
		if (vendorName != null) {
			LoginVendor vendor = getConfiguration().getEnabledVendors().get(vendorName);
			if (vendor != null) {
				//This user comes from token validation
				AuthenticatedUser user = vendor.validateTokenAndCreateUser(credentials, getName());
				if (user != null) {
					Map<String, Object> attributes = new HashMap<>(user.getAttributes());
					attributes.put(VENDOR_ATTRIBUTE, vendorName);
					if (getConfiguration().isKeepOriginalToken()) {
						if (vendor.equals(SocialLoginConfiguration.TWITTER_PROVIDER)) {
							StringBuffer sb = new StringBuffer(110);
							sb.append(credentials.get(CLIENT_KEY));
							sb.append(":");
							sb.append(credentials.get(CLIENT_SECRET_KEY));
							attributes.put(ORIGINAL_TOKEN_ATTRIBUTE, sb.toString());
						} else {
							attributes.put(ORIGINAL_TOKEN_ATTRIBUTE, credentials.get(TOKEN_KEY));
						}
					}
					//This user is the one that gets attached on security context
					
					try {
						UserProfile up = getConfiguration().getUserProfileService().createProfile(
								new AuthenticatedUser(user.getId(), user.getDisplayName(), getName(), attributes));
						this.user = new AuthenticatedUser(user.getId(), up.getDisplayName(), getName(), attributes);
						System.out.println("Token verified");
						return true;
					} catch (Exception e) {
						logger.log(Level.SEVERE, e.getMessage(), e);
					}
				}
			}
		}
		return false;
	}
	
	
	@Override
	protected AuthenticatedUser createUser() {
		return user;
	}

    @Override
    protected SocialLoginConfiguration getConfiguration() {
    	return (SocialLoginConfiguration) super.getConfiguration();

    }
	
}
