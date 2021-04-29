package com.tuta.tutadriver.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.tuta.tutadriver.R;

public class WelcomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Button LoginBtn = findViewById(R.id.LoginBtn);
        Button JoinBtn = findViewById(R.id.JoinBtn);

        LoginBtn.setOnClickListener(v -> {
            Intent i = new Intent(WelcomeActivity.this, LoginActivity.class);
            startActivity(i);
        });

        JoinBtn.setOnClickListener(v -> {
            Intent i = new Intent(WelcomeActivity.this, SignupActivity.class);
            startActivity(i);
        });
    }
}
