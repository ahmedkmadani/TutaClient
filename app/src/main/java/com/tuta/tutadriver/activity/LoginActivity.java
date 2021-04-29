package com.tuta.tutadriver.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.tuta.tutadriver.R;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button SignUpBtn  = findViewById(R.id.SignupBtn);

        SignUpBtn.setOnClickListener(v -> {
            Intent i = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(i);
        });
    }
}
