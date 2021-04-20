package org.rolkevin.thirdlogin.domain;

public class ClientInfo {
    private String clientId;
    private String clientSecret;

    public ClientInfo (String clientId,String clientSecret){
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
