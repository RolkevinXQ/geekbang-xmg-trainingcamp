package org.rolkevin.thirdlogin.service;

import org.rolkevin.thirdlogin.oauth2.GiteeOauth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

@Service
public class GiteeOauthService {

    @Autowired
    GiteeOauth giteeOauth;

    public String authorizeUri() {
        return giteeOauth.authorize();
    }

    public String getAccessToken(String code){
        Client client = ClientBuilder.newClient();
        Response response = client
                .target(giteeOauth.accessTokenUrl(code))      // WebTarget
                .request() // Invocation.Builder
                .get();                                     //  Response
        String content = response.readEntity(String.class);
        return "";
    }

    public <O> O getUserInfo(String accessToken){

        O o = null;
        return o;
    }

}
