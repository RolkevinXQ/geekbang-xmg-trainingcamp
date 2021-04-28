package org.rolkevin.thirdlogin.domain;

public class AccessTokenInfo {
    private String access_token;
    private String token_type;
    private long expires_in;
    private String refresh_token;
    private String scope;
    private long created_at;
    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
    public String getAccess_token() {
        return access_token;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }
    public String getToken_type() {
        return token_type;
    }

    public void setExpires_in(long expires_in) {
        this.expires_in = expires_in;
    }
    public long getExpires_in() {
        return expires_in;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }
    public String getRefresh_token() {
        return refresh_token;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
    public String getScope() {
        return scope;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }
    public long getCreated_at() {
        return created_at;
    }
}