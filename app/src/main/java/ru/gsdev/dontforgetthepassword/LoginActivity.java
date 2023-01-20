package ru.gsdev.dontforgetthepassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);
        TextView registration = findViewById(R.id.registration);
        TextView forgotPassword = findViewById(R.id.forgot_password);
        MaterialButton loginButton = findViewById(R.id.loginbtn);

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
                finish();
            }
        });

        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
                finish();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(email.getText())) {
                    Toast.makeText(LoginActivity.this, "Email обязателен для заполнения", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(password.getText())) {
                    Toast.makeText(LoginActivity.this, "Пароль обязателен для заполнения", Toast.LENGTH_LONG).show();
                    return;
                }

                LoginRequest loginRequest = new LoginRequest();

                loginRequest.setEmail(email.getText().toString());
                loginRequest.setPassword(password.getText().toString());
                loginRequest.setDeviceName(Build.DEVICE);

                userLogin(loginRequest);
            }
        });
    }

    public void userLogin(LoginRequest loginRequest) {
        Call<LoginResponse> loginResponseCall = ApiClient.getService().userLogin(loginRequest);

        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Неверный логин или пароль", Toast.LENGTH_LONG).show();
                    return;
                }
                LoginResponse loginResponse = response.body();
                Toast.makeText(LoginActivity.this, "Идем дальше", Toast.LENGTH_LONG).show();
                //Разобраться и доработать
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}