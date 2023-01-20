package ru.gsdev.dontforgetthepassword;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("login/")
    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);

    @POST("registration/")
    Call<RegistrationResponse> userRegistration(@Body RegistrationRequest registrationRequest);

    @POST("user/forgot_password")
    Call<ForgotPasswordResponse> userForgotPassword(@Body ForgotPasswordRequest forgotPasswordRequest);

}
