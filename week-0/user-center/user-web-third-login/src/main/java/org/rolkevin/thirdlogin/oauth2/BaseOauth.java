package org.rolkevin.thirdlogin.oauth2;

public interface BaseOauth {

    default String authorize() {
        return null;
    }

    default String accessTokenUrl(String authCode){
        return null;
    }

    default String getUserInfoUrl(String accessToken){
        return null;
    }

}
