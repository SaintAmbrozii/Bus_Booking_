package com.example.busbooking.security.oauth2;


import com.example.busbooking.domain.AuthProvider;
import com.example.busbooking.exception.OAuth2AuthentificationProcessingException;

import java.util.Map;

public class Oauth2UserInfoFactory {

    public static Oauth2userInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if (registrationId.equalsIgnoreCase(AuthProvider.GOOGLE.toString())) {
            return new GoogleOauth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthentificationProcessingException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}
