package ru.gsdev.dontforgetthepassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class ForgotPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        EditText email = findViewById(R.id.email);
        MaterialButton sendButton = findViewById(R.id.send_button);
        TextView loginAccount = findViewById(R.id.have_account);

        loginAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity.class));
                finish();
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(email.getText())) {
                    Toast.makeText(ForgotPasswordActivity.this, "Email обязателен для заполнения", Toast.LENGTH_LONG).show();
                    return;
                }

                ForgotPasswordRequest forgotPasswordRequest = new ForgotPasswordRequest();
                forgotPasswordRequest.setEmail(email.getText().toString());

                userForgetPassword(forgotPasswordRequest);
            }
        });
    }

    public void userForgetPassword(ForgotPasswordRequest forgotPasswordRequest) {
        Call<ForgotPasswordResponse> forgotPasswordResponseCall = ApiClient.getService().userForgotPassword(forgotPasswordRequest);

        forgotPasswordResponseCall.enqueue(new Callback<ForgotPasswordResponse>() {
            @Override
            public void onResponse(Call<ForgotPasswordResponse> call, Response<ForgotPasswordResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(ForgotPasswordActivity.this, "Произошла ошибка, попробуйте позднее", Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(ForgotPasswordActivity.this, "Новый пароль отправлен на вашу почту", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ForgotPasswordResponse> call, Throwable t) {
                Toast.makeText(ForgotPasswordActivity.this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}