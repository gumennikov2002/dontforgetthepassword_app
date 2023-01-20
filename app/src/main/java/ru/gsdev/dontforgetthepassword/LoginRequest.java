package ru.gsdev.dontforgetthepassword;

public class LoginRequest {

    private String email;
    private String password;
    private String device_name;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setDeviceName(String deviceName) {
        this.device_name = deviceName;
    }

    public String getDeviceName() {
        return device_name;
    }
}
