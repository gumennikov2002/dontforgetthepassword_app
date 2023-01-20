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

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);
        EditText passwordConfirmation = findViewById(R.id.password_confirmation);
        TextView haveAnAccount = findViewById(R.id.have_account);
        MaterialButton registrationButton = findViewById(R.id.reg_btn);

        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(email.getText())) {
                    Toast.makeText(RegistrationActivity.this, "Email обязателен для заполнения", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(password.getText())) {
                    Toast.makeText(RegistrationActivity.this, "Пароль обязателен для заполнения", Toast.LENGTH_LONG).show();
                    return;
                }
                if (password.getText().length() < 6) {
                    Toast.makeText(RegistrationActivity.this, "Пароль должен содержать больше 6 символов", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!TextUtils.equals(passwordConfirmation.getText(), password.getText())) {
                    Toast.makeText(RegistrationActivity.this, "Пароли не совпадают", Toast.LENGTH_LONG).show();
                    return;
                }

                RegistrationRequest registrationRequest = new RegistrationRequest();

                registrationRequest.setEmail(email.getText().toString());
                registrationRequest.setPassword(password.getText().toString());
                registrationRequest.setPasswordConfirmation(passwordConfirmation.getText().toString());

                registerUser(registrationRequest);
            }
        });

        haveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    public void registerUser(RegistrationRequest registrationRequest) {

        Call<RegistrationResponse> registrationResponseCall = ApiClient.getService().userRegistration(registrationRequest);
        registrationResponseCall.enqueue(new Callback<RegistrationResponse>() {
            @Override
            public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(RegistrationActivity.this, "Произошла ошибка при регистрации. Попробуйте позднее", Toast.LENGTH_LONG).show();
                    return;
                }

                Toast.makeText(RegistrationActivity.this, "Регистрация прошла успешно", Toast.LENGTH_LONG).show();
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                finish();
            }

            @Override
            public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                Toast.makeText(RegistrationActivity.this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

}