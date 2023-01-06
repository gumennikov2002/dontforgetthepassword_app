package ru.gsdev.dontforgetthepassword;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    //https://www.youtube.com/watch?v=suVgcrPwYKQ&t=0s

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView email = (TextView) findViewById(R.id.email);
        TextView password = (TextView) findViewById(R.id.password);
        MaterialButton loginButton = (MaterialButton) findViewById(R.id.loginbtn);
//
//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (usercode.getText().toString().equals("root")) {
//                    //correct
//                    return;
//                }
//
//                Toast.makeText(MainActivity.this, "Неверный код", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}