package com.cencosud.middleware.login.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cencosud.middleware.login.dto.UserLoginDto;
import com.cencosud.middleware.login.model.UserMigration;
import com.cencosud.middleware.login.model.UserLogin;
import com.cencosud.middleware.login.repository.UserLoginRepository;
import com.cencosud.middleware.login.service.UserLoginService;

@Service("userLoginR2ServiceImpl")
public class UserLoginR2ServiceImpl implements UserLoginService {

	private static final String REGION_ID = "r2";

	@Autowired
	@Qualifier("userLoginR2Repository")
	UserLoginRepository userLoginRepository;

	private String token = "";

	private static String INVALID_TOKEN = "InvalidToken";

	@Override
	public UserLoginDto loginUser(String email, String password) throws Exception {

		String tmpToken = this.token;
		UserLogin response = null;
		/**
		 * Avoid login the first time because token is empty
		 */
		if (!StringUtils.isEmpty(tmpToken)) {
			response = userLoginRepository.loginUser(tmpToken, email, password);
		}

		if (response == null || INVALID_TOKEN.equals(response.getDescription())) {
			String newToken = requestToken(tmpToken);
			response = userLoginRepository.loginUser(newToken, email, password);
		}

		return new UserLoginDto(response);
	}

	/**
	 * 
	 * @param tmpToken
	 *            is a token used for getting user Login
	 * @return
	 */
	private String requestToken(String tmpToken) {
		if (tmpToken.equals(this.token)) {
			synchronized (this) {
				if (tmpToken.equals(this.token)) {
					String newToken = userLoginRepository.getToken();
					this.token = newToken;
					return newToken;
				}
			}
		}
		return tmpToken;
	}

	@Override
	public String getRegionId() {
		return REGION_ID;
	}

	@Override
	public UserMigration getMigrationInfo(String rut) {
		rut = rut.replaceAll("[.-]", "");
		return userLoginRepository.getMigrationInfo(rut);
	}
	
}
