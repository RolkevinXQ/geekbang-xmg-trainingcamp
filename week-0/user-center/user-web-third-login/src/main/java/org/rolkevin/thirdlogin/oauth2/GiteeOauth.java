package org.rolkevin.thirdlogin.oauth2;

import org.rolkevin.thirdlogin.domain.ClientInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GiteeOauth implements BaseOauth {

    @Value("${gitee.client.id}")
    private String clientId;

    @Value("${gitee.client.secret}")
    private String clientSecret;

    @Value("${gitee.redirect.uri}")
    private String redirectUri;

    @Value("${gitee.authorize.url }")
    private String authorizeUrl;

    @Value("${gitee.access.url}")
    private String accessUrl;

    @Value("${gitee.userinfo.url}")
    private String userInfoUrl;

    public String authorize(){
        return authorizeUrl.replace("{client_id}",clientId)
                .replace("{redirect_uri}",redirectUri);
    }

    public String accessTokenUrl(String code){
        return accessUrl.replace("{code}",code)
                .replace("{client_id}",clientId)
                .replace("{redirect_uri}",redirectUri)
                .replace("{client_secret}",clientSecret);
    }

    public String getUserInfoUrl(String accessToken){
        return userInfoUrl.replace("{token}",accessToken);
    }

    public ClientInfo getClientInfo(){
        return new ClientInfo(clientId,clientSecret);
    }
}
