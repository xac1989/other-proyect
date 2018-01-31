package com.scanandgo.mobile.login.adapter;

import com.ibm.mfp.server.registration.external.model.AuthenticatedUser;

import javax.net.ssl.SSLSocketFactory;

import java.util.Map;
import java.util.Properties;

public interface LoginVendor {

    /**
     * Get the names of configuration properties.
     * Only the properties with these names will be passed to {@link #setConfiguration(Properties, SSLSocketFactory)} method
     *
     * @return the array of property names, not null
     */
    String[] getConfigurationPropertyNames();

    /**
     * Invoked on a newly created instance upon adapter deployment or configuration change
     *
     * @param properties       the configuration properties defined by the {@link #getConfigurationPropertyNames()} method, not null.
     * @param sslSocketFactory socket factory produced by the {@link SocialLoginConfiguration} class
     */
    void setConfiguration(Properties properties, SSLSocketFactory sslSocketFactory);

    /**
     * Returns true if the vendor is properly configured and is ready to validate tokens
     *
     * @return true if this vendor is enabled, false otherwise
     */
    boolean isEnabled();

    /**
     * Validate the given token, and if it is valid build an AuthenticatedUser and return it.
     * If the token is invalid, return null.
     *
     * @param tokenStr  the token string sent by the client
     * @param checkName the security check name for creation of AuthenticatedUser
     * @return the authenticated user represented by the token, or null if the token is invalid
     */
    AuthenticatedUser validateTokenAndCreateUser(Map<String, Object> credentials, String checkName);
}
