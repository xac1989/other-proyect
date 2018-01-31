package com.scanandgo.mobile.login.adapter;

import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import com.ibm.mfp.security.checks.base.UserAuthenticationSecurityCheckConfig;
import com.scanandgo.mobile.service.CencosudSupport;
import com.scanandgo.mobile.service.FacebookSupport;
import com.scanandgo.mobile.service.GoogleSupport;
import com.scanandgo.mobile.service.TwitterSupport;
import com.scanandgo.mobile.service.UserProfileService;

public class SocialLoginConfiguration extends UserAuthenticationSecurityCheckConfig {

	public static final String KEEP_ORIGINAL_TOKEN = "keepOriginalToken";

	public static final String GOOGLE_PROVIDER = "google";
	public static final String FACEBOOK_PROVIDER = "facebook";
	public static final String TWITTER_PROVIDER = "twitter";
	public static final String CENCOSUD_PROVIDER = "cencosud";
	public static final String API_BASE_URL = "api_base_url";
	public static final String API_CLIENT_ID = "api_client_id";
	public static final String API_CLIENT_SECRET = "api_secret";
	public static final String VALIDATION_WHITELIST = "login.cencosud.whitelist";

	private static final Logger logger = Logger.getLogger(SocialLoginConfiguration.class.getName());

	private boolean keepOriginalToken;
	private Map<String, LoginVendor> vendors;
	private UserProfileService userProfileService;
	private SSLSocketFactory sslSocketFactory;
	private String validationWhiteList;

	/**
	 * Create the vendors each with its relevant properties
	 *
	 * @param properties
	 *            security check configuration includes the properties of all
	 *            vendors
	 */
	public SocialLoginConfiguration(Properties properties) {
		super(properties);
		blockedStateExpirationSec = 1;
		keepOriginalToken = Boolean.parseBoolean(getStringProperty(KEEP_ORIGINAL_TOKEN, properties, "false"));
		try {
			TrustManagerFactory factory = TrustManagerFactory.getInstance("PKIX");
			factory.init((KeyStore) null);
			SSLContext ctx = SSLContext.getInstance("TLS");
			ctx.init(null, factory.getTrustManagers(), null);
			sslSocketFactory = ctx.getSocketFactory();
			// create user profile service
			String apiBaseurl = getStringProperty(API_BASE_URL, properties, "");
			String apiClientId = getStringProperty(API_CLIENT_ID, properties, "");
			String apiClientSecret = getStringProperty(API_CLIENT_SECRET, properties, "");
			setValidationWhiteList(getStringProperty(VALIDATION_WHITELIST, properties, ""));
			userProfileService = new UserProfileService(apiBaseurl, apiClientId, apiClientSecret);
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new RuntimeException(e);
		}

		createVendors();
		for (LoginVendor vendor : vendors.values()) {
			Properties vendorConfig = new Properties();
			for (String property : vendor.getConfigurationPropertyNames()) {
				String value = getStringProperty(property, properties, null);
				vendorConfig.setProperty(property, value);
			}
			vendor.setConfiguration(vendorConfig, sslSocketFactory);
		}

		setLogLevel();
	}

	/**
	 * Get only the vendors that are enabled according to their configurations
	 *
	 * @return map with vendor name as a key and the vendor as a value
	 */
	public Map<String, LoginVendor> getEnabledVendors() {
		Map<String, LoginVendor> res = new HashMap<>();
		for (Map.Entry<String, LoginVendor> entry : vendors.entrySet()) {
			if (entry.getValue().isEnabled())
				res.put(entry.getKey(), entry.getValue());
		}
		return res;
	}

	public UserProfileService getUserProfileService() {
		return this.userProfileService;
	}

	public boolean isKeepOriginalToken() {
		return keepOriginalToken;
	}

	private void createVendors() {
		vendors = new HashMap<>();
		vendors.put(GOOGLE_PROVIDER, new GoogleSupport());
		vendors.put(FACEBOOK_PROVIDER, new FacebookSupport());
		vendors.put(TWITTER_PROVIDER, new TwitterSupport());
		vendors.put(CENCOSUD_PROVIDER, new CencosudSupport());
	}

	private void setLogLevel() {
		Logger rootLogger = Logger.getLogger("");
		try {
			rootLogger.setLevel(Level.FINEST);
		} catch (IllegalArgumentException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	/**
	 * @return the validationWhiteList
	 */
	public String getValidationWhiteList() {
		return validationWhiteList;
	}

	/**
	 * @param validationWhiteList
	 *            the validationWhiteList to set
	 */
	public void setValidationWhiteList(String validationWhiteList) {
		this.validationWhiteList = validationWhiteList;
	}
}
