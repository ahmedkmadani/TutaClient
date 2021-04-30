package com.tuta.tutadriver.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tuta.tutadriver.R;
import com.tuta.tutadriver.databinding.ActivityCreateAccountBinding;
import com.tuta.tutadriver.utils.UrLs;
import com.tuta.tutadriver.utils.Utility;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class CreateAccountActivity extends Utility {

    ActivityCreateAccountBinding mBinding;
    String PhoneNumber ;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityCreateAccountBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        PhoneNumber = getIntent().getExtras().getString("PhoneNumber");
        view = findViewById(android.R.id.content);
        mBinding.inputPhone.setEnabled(false);

        mBinding.textviewTerms.setOnClickListener(v -> {
            Intent i = new Intent(CreateAccountActivity.this, TermsActivity.class);
            startActivity(i);
        });

        mBinding.inputPhone.setText(PhoneNumber);
        mBinding.BtnContinue.setOnClickListener(v -> {
            if(isOnline(this)) {
                CreateAccount();
            } else {
                ShowSnackbBarNoInternet(getApplicationContext(), view);
            }
        });
    }

    private void CreateAccount() {
        if (!validate()) {
            return;
        }
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("first_name", "Ahmed");
            jsonBody.put("last_name", "Kamal");
            jsonBody.put("phone_number", PhoneNumber);
            jsonBody.put("user_type", "driver");
            jsonBody.put("city_id", "1");
            jsonBody.put("password", "pass");
            jsonBody.put("email", "email");

            JsonObjectRequest request_json = new JsonObjectRequest(Request.Method.POST, UrLs.register, jsonBody,
                    response -> {

                        Log.d("res", response.toString());
                    }, error -> {
                VolleyLog.e("Error: ", error.getMessage());

            });

            requestQueue.add(request_json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    private boolean validate() {
        boolean valid = true;
        if (mBinding.inputFname.length() == 0) {
            mBinding.inputFname.setError("Please enter valid first name");
            valid = false;
        } else {
            mBinding.inputFname.setError(null);
        }

        if(mBinding.inputLname.length() == 0){
            mBinding.inputLname.setError("Please enter valid last name");
            valid = false;
        } else {
            mBinding.inputLname.setError(null);
        }

        if(mBinding.inputEmail.length() == 0 && !Patterns.EMAIL_ADDRESS.matcher(mBinding.inputEmail.getText().toString()).matches()){
            mBinding.inputEmail.setError("Please enter valid email address");
            valid = false;
        } else {
            mBinding.inputEmail.setError(null);
        }

        if (mBinding.inputCity.length() == 0){
            mBinding.inputCity.setError("Please enter valid city name");
            valid = false;
        } else {
            mBinding.inputCity.setError(null);
        }

        if (mBinding.inputPassword.getText().toString().length() < 4 && mBinding.inputPassword.length() > 10){
            mBinding.inputPassword.setError("Please enter valid password between 4 and 10 characters");
        } else {
            mBinding.inputPassword.setError(null);
        }
        return valid;
    }
}
