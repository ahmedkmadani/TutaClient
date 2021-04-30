package com.tuta.tutadriver.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.tuta.tutadriver.R;
import com.tuta.tutadriver.databinding.ActivityOtpBinding;
import com.tuta.tutadriver.utils.UrLs;
import com.tuta.tutadriver.utils.Utility;

import org.json.JSONException;
import org.json.JSONObject;

public class PinVerficationActivity extends Utility {

    String StringPin;
    String FullPhoneNumber;

    ActivityOtpBinding mBinding;
    String msg;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityOtpBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        FullPhoneNumber = getIntent().getExtras().getString("PhoneNumber");
        view = findViewById(android.R.id.content);

        Log.d("FullPhoneNumber", FullPhoneNumber);
        if(isOnline(this)) {
            GetOTP(FullPhoneNumber);
        } else {
            ShowSnackbBarNoInternet(getApplicationContext(), view);
        }

        mBinding.tutaPinView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                StringPin = String.valueOf(s);
            }
        });

        mBinding.textResend.setOnClickListener(v -> {
            GetOTP(FullPhoneNumber);
        });

        mBinding.BtnVerify.setOnClickListener(v -> {
            if(isOnline(this)) {
                VerifyOTP(StringPin, FullPhoneNumber);
            } else {
                ShowSnackbBarNoInternet(getApplicationContext(), view);
            }
        });
    }

    private void GetOTP(String phoneNumber) {
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("phone_number", phoneNumber);
            JsonObjectRequest request_json = new JsonObjectRequest(Request.Method.POST, UrLs.otpRequest, jsonBody,
                    response -> {
                        Log.d("res", response.toString());
                        try {
                             msg = response.getString("message");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        ShowSnackBar(getApplicationContext(), view, msg);
                    }, error -> {
                        VolleyLog.e("Error: ", error.getMessage());
                       ShowSnackBar(getApplicationContext(), view, String.valueOf(R.string.error_msg_otp));
                    });

            requestQueue.add(request_json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

        private void VerifyOTP(String stringPin, String phoneNumber) {
            if (!validate()) {
                return;
            }
            try {
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                JSONObject jsonBody = new JSONObject();
                jsonBody.put("phone_number", phoneNumber);
                jsonBody.put("otp_code", stringPin);
                jsonBody.put("device_name", getDeviceName());
                JsonObjectRequest request_json = new JsonObjectRequest(Request.Method.POST, UrLs.otpVerification, jsonBody,
                        response -> {
                            try {
                                onSucceedActivity(phoneNumber);
                                JSONObject data = response.getJSONObject("data");
                                String verified = data.getString("verified");
                                String phonenumber = data.getString("phone_number");
                                msg = data.getString("message");
                                if(verified == "true"){
                                    ShowSnackBar(getApplicationContext(), view, msg);
                                    onSucceedActivity(phonenumber);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Log.d("res", response.toString());
                        }, error -> {
                            VolleyLog.e("Error: ", error.getMessage());
                            msg = getString(R.string.error_msg_verify_otp);
                            ShowSnackBar(getApplicationContext(), view, msg);
                        });

                requestQueue.add(request_json);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    private void onSucceedActivity(String phonenumber) {
        Intent i = new Intent(PinVerficationActivity.this, CreateAccountActivity.class);
        i.putExtra("PhoneNumber", phonenumber);
        startActivity(i);
    }

    private boolean validate() {
        boolean valid = true;
        if (mBinding.tutaPinView.getText().toString().isEmpty()) {
            valid = false;
        } else {

        }
        return valid;
    }
}
