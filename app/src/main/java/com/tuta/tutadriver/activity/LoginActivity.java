package com.tuta.tutadriver.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.tuta.tutadriver.R;
import com.tuta.tutadriver.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding mBinding;
    String phoneNumber;
    String FullPhoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mBinding.SignupBtn.setOnClickListener(v -> {
            Intent i = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(i);
        });

        mBinding.BtnContinue.setOnClickListener(v -> {
            mBinding.ccp.registerPhoneNumberTextView(mBinding.inputPhone);
            mBinding.ccp.enablePhoneAutoFormatter(true);
            mBinding.ccp.enableHint(false);

            if(mBinding.ccp.isValid()){
                phoneNumber = mBinding.inputPhone.getText().toString();
                if(phoneNumber.startsWith("0")){
                    FullPhoneNumber = mBinding.ccp.getSelectedCountryCode()+phoneNumber.substring(1);
                }else{
                    FullPhoneNumber = mBinding.ccp.getFullNumber();
                }
            }
            Intent i = new Intent(LoginActivity.this, PinVerficationActivity.class);
            i.putExtra("PhoneNumber", FullPhoneNumber);
            startActivity(i);
        });
    }
}
