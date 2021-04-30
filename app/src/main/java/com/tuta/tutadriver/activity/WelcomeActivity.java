package com.tuta.tutadriver.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.tuta.tutadriver.R;
import com.tuta.tutadriver.databinding.ActivityWelcomeBinding;

public class WelcomeActivity extends AppCompatActivity {
    ActivityWelcomeBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityWelcomeBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mBinding.LoginBtn.setOnClickListener(v -> {
            Intent i = new Intent(WelcomeActivity.this, LoginActivity.class);
            startActivity(i);
        });

        mBinding.JoinBtn.setOnClickListener(v -> {
            Intent i = new Intent(WelcomeActivity.this, SignupActivity.class);
            startActivity(i);
        });
    }
}
