package org.rolkevin.thirdlogin.domain;

public class AccessToken {
    private String grant_type;
    private String code;
    private String client_id;
    private String redirect_uri;
    private String client_secret;

    public AccessToken(String code, String clientId, String redirectUri, String clientSecret) {
        this.grant_type = "authorization_code";
        this.code = code;
        this.client_id = clientId;
        this.redirect_uri = redirectUri;
        this.client_secret = clientSecret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }
}
