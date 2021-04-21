package org.rolkevin.thirdlogin.oauth2;

import org.rolkevin.thirdlogin.domain.AccessToken;
import org.rolkevin.thirdlogin.domain.ClientInfo;
import org.rolkevin.thirdlogin.domain.OauthUserInfo;
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

    @Value("${gitee.authorize.url}")
    private String authorizeUrl;

    @Value("${gitee.access.url}")
    private String accessUrl;

    @Value("${gitee.userinfo.url}")
    private String userInfoUrl;

    private OauthUserInfo oauthUserInfo;

    public String authorize(){
        return authorizeUrl.replace("{client_id}",clientId)
                .replace("{redirect_uri}",redirectUri);
    }

    public String accessTokenUrl(String code){
        return accessUrl;
    }

    public String accessTokenUrl(){
        return accessUrl;
    }
    public String getUserInfoUrl(String accessToken){
        return userInfoUrl.replace("{token}",accessToken);
    }

    public ClientInfo getClientInfo(){
        return new ClientInfo(clientId,clientSecret);
    }

    public AccessToken getAccessToken(String code){
        AccessToken token = new AccessToken(code,clientId,redirectUri,clientSecret);
        return token;
    }

    public OauthUserInfo getOauthUserInfo() {
        return oauthUserInfo;
    }

    public void setOauthUserInfo(OauthUserInfo oauthUserInfo) {
        this.oauthUserInfo = oauthUserInfo;
    }
}
