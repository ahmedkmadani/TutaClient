package com.tuta.tutadriver.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.chaos.view.PinView;
import com.tuta.tutadriver.R;
import com.tuta.tutadriver.utils.UrLs;
import com.tuta.tutadriver.utils.Utility;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class PinVerficationActivity extends AppCompatActivity {

    PinView mPinView;
    String StringPin;
    String PhoneNumber;
    Button BtnVerfiy;

    Utility mUtility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        Intent i = getIntent();
        PhoneNumber = i.getExtras().getString("PhoneNumber");

        mUtility = new Utility();

        BtnVerfiy = findViewById(R.id.BtnContinue);
        mPinView = findViewById(R.id.tutaPinView);

        mPinView.addTextChangedListener(new TextWatcher() {
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

        BtnVerfiy.setOnClickListener(v -> {
            VerifyOTP(StringPin, PhoneNumber);
        });
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
                jsonBody.put("device_name", mUtility.getDeviceName());

                final String mRequestBody = jsonBody.toString();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, UrLs.otpVerification, response -> {
                    Log.i("LOG_RESPONSE", response);
                }, error -> Log.d("error", error.toString())) {
                    @Override
                    public String getBodyContentType() {
                        return "application/json; charset=utf-8";
                    }

                    @Override
                    public byte[] getBody() {
                        try {
                            return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                        } catch (UnsupportedEncodingException uee) {
                            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
                            return null;
                        }
                    }

                    @Override
                    protected Response<String> parseNetworkResponse(NetworkResponse response) {
                        String responseString = "";
                        if (response != null) {
                            responseString = String.valueOf(response.statusCode);
                        }
                        return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                    }
                };

                requestQueue.add(stringRequest);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    private boolean validate() {
        boolean valid = true;
        if (mPinView.getText().toString().isEmpty()) {
            valid = false;
        } else {

        }
        return valid;
    }
}
