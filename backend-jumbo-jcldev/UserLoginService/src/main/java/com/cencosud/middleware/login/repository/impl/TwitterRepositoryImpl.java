package com.cencosud.middleware.login.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.cencosud.middleware.login.annotation.Loggable;
import com.cencosud.middleware.login.configuration.ApplicationConfig;
import com.cencosud.middleware.login.exception.LoginServiceException;
import com.cencosud.middleware.login.exception.UnauthorizedException;
import com.cencosud.middleware.login.model.LoggedUser;
import com.cencosud.middleware.login.repository.TwitterRepository;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

@Repository
public class TwitterRepositoryImpl implements TwitterRepository {

	@Autowired
	private ApplicationConfig applicationConfig;

	private static final Logger logger = LoggerFactory.getLogger(TwitterRepositoryImpl.class);

	@Loggable
	@Override
	public LoggedUser getUser(String oauthToken, String oauthSecretToken, String app)
			throws LoginServiceException, UnauthorizedException {
		try {
			Twitter twitter = getTwitterInstance(oauthToken, oauthSecretToken, app);
			User user = twitter.verifyCredentials();
			if (user != null) {
				logger.debug("User info from twitter: {}", user);
			}
			// no todas las apps te dan el mail del usuario, si se validan las
			// credenciales
			// y el email es nulo, se setea uno por defecto
			String email = user.getEmail() != null ? user.getEmail() : "";
			return new LoggedUser(Long.toString(user.getId()), user.getName(), user.getBiggerProfileImageURL(), email,
					user.isDefaultProfileImage());
		} catch (TwitterException e) {
			if (e.getStatusCode() == HttpStatus.UNAUTHORIZED.value()) {
				logger.error("Error: [" + this.getClass().getCanonicalName() + "]", e);
				throw new UnauthorizedException(e.getMessage(), e);
			}
			logger.error("Error: [" + this.getClass().getCanonicalName() + "]", e);
			throw new LoginServiceException(e.getMessage(), e);
		}
	}

	private Twitter getTwitterInstance(String oauthToken, String oauthSecretToken, String app) {
		// set android as default values
		String consumerKey = applicationConfig.getTwitter().getAndroid().getConsumerKey();
		String consumerSecretKey = applicationConfig.getTwitter().getAndroid().getConsumerSecretKey();
		// get ios values in case specified
		if (ApplicationConfig.APP_IOS.equalsIgnoreCase(app)) {
			consumerKey = applicationConfig.getTwitter().getIos().getConsumerKey();
			consumerSecretKey = applicationConfig.getTwitter().getIos().getConsumerSecretKey();
		}

		logger.debug("consumerKey: " + consumerKey);
		logger.debug("consumerSecretKey: " + consumerSecretKey);

		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey(consumerKey).setOAuthConsumerSecret(consumerSecretKey)
				.setOAuthAccessToken(oauthToken).setOAuthAccessTokenSecret(oauthSecretToken)
				.setIncludeEmailEnabled(true);
		TwitterFactory tf = new TwitterFactory(cb.build());
		return tf.getInstance();
	}
}
