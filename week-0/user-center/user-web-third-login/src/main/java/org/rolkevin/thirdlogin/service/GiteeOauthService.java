package org.rolkevin.thirdlogin.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.rolkevin.thirdlogin.domain.AccessToken;
import org.rolkevin.thirdlogin.domain.AccessTokenInfo;
import org.rolkevin.thirdlogin.domain.OauthUserInfo;
import org.rolkevin.thirdlogin.oauth2.GiteeOauth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Service
public class GiteeOauthService {

    final MediaType mediaType = MediaType.get("application/json; charset=utf-8");

    @Autowired
    GiteeOauth giteeOauth;

    public String authorizeUri() {
        return giteeOauth.authorize();
    }

    public String getAccessToken(String code) {
        Client client = ClientBuilder.newClient();
        AccessToken token = giteeOauth.getAccessToken(code);
        String content = null;
        try {
            content = new ObjectMapper().writeValueAsString(token);
        } catch (JsonProcessingException e) {

        }
        Entity entity = Entity.json(content);
        Response response = client
                .target(giteeOauth.accessTokenUrl(code))      // WebTarget
                .request() // Invocation.Builder
                .post(entity);                                     //  Response
        String result = response.readEntity(String.class);
        System.out.println(result);

        return "";
    }

    public String getAccessToken1(String code){
        AccessToken accessToken = giteeOauth.getAccessToken(code);
        String content = null;
        try {
            content = new ObjectMapper().writeValueAsString(accessToken);
        } catch (JsonProcessingException e) {
        }
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, content);
        Request request = new Request.Builder()
                .url(giteeOauth.accessTokenUrl(accessToken.getCode()))
                .post(body)
                .build();
        try (okhttp3.Response response = client.newCall(request).execute()) {
            String access = response.body().string();
            AccessTokenInfo tokenInfo = new ObjectMapper().readValue(access,AccessTokenInfo.class);
            //String token = access.split("&")[0].split("=")[1];
            return tokenInfo.getAccess_token();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    public <O> O getUserInfo(String accessToken){
        O o = null;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(giteeOauth.getUserInfoUrl(accessToken))
                .build();

        try (okhttp3.Response response = client.newCall(request).execute()) {
            String userResponse = response.body().string();
            OauthUserInfo userInfo = new ObjectMapper().readValue(userResponse,OauthUserInfo.class);
            return (O) userInfo;
            //return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

}
