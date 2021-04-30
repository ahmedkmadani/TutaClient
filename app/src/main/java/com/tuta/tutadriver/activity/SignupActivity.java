package com.tuta.tutadriver.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tuta.tutadriver.R;
import com.tuta.tutadriver.databinding.ActivityRegisterBinding;

import com.tuta.tutadriver.utils.UrLs;

import org.json.JSONException;
import org.json.JSONObject;

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
