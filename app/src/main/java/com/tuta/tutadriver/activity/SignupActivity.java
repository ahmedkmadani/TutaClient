package com.tuta.tutadriver.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.tuta.tutadriver.databinding.ActivityRegisterBinding;

public class SignupActivity extends AppCompatActivity {
    ActivityRegisterBinding mBinding;
    String phoneNumber;
    String FullPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());


        mBinding.textviewTerms.setOnClickListener(v -> {
            Intent i = new Intent(SignupActivity.this, TermsActivity.class);
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
            Intent i = new Intent(SignupActivity.this, PinVerficationActivity.class);
            i.putExtra("PhoneNumber", FullPhoneNumber);
            startActivity(i);
        });
    }
}
