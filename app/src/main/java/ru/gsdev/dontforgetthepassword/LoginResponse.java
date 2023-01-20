package ru.gsdev.dontforgetthepassword;

import java.io.Serializable;

public class LoginResponse implements Serializable {

    private String accessToken;

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
