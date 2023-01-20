package ru.gsdev.dontforgetthepassword;

public class RegistrationRequest {

    private String email;
    private String password;
    private String password_confirmation;

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

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.password_confirmation = passwordConfirmation;
    }

    public String getPasswordConfirmation() {
        return password_confirmation;
    }
}
