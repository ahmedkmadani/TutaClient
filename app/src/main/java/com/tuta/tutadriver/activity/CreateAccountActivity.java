package com.tuta.tutadriver.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tuta.tutadriver.R;
import com.tuta.tutadriver.utils.UrLs;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class CreateAccountActivity extends AppCompatActivity {

    EditText inputFname, inputLname, inputEmail, inputPhonenumber, inputCity, inputPassword;
    Button btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        inputFname = findViewById(R.id.input_fname);
        inputLname = findViewById(R.id.input_lname);
        inputEmail = findViewById(R.id.input_email);
        inputPhonenumber = findViewById(R.id.input_phone);
        inputCity = findViewById(R.id.input_city);
        inputPassword = findViewById(R.id.input_password);
        btnContinue = findViewById(R.id.BtnContinue);

        TextView tv_terms = findViewById(R.id.textview_terms);

        tv_terms.setOnClickListener(v -> {
            Intent i = new Intent(CreateAccountActivity.this, TermsActivity.class);
            startActivity(i);
        });


        btnContinue.setOnClickListener(v -> {
//            Intent i = new Intent(CreateAccountActivity.this, AddVehicleActivity.class);
//            startActivity(i);
            CreateAccount();
        });
    }

    private void CreateAccount() {
        if (!validate()) {
            return;
        }
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("first_name", inputFname.getText().toString());
            jsonBody.put("last_name", inputLname.getText().toString());
            jsonBody.put("phone_number", inputPhonenumber.getText().toString());
            jsonBody.put("user_type", "driver");
            jsonBody.put("city_id", inputCity.getText().toString());
            jsonBody.put("password", inputPassword.getText().toString());
            jsonBody.put("email", inputEmail.getText().toString());

            final String mRequestBody = jsonBody.toString();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, UrLs.register, response -> Log.i("LOG_RESPONSE", response), error -> Log.e("LOG_RESPONSE", error.toString())) {
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
        if (inputFname.length() == 0) {
            inputFname.setError("Please enter valid first name");
            valid = false;
        } else {
            inputFname.setError(null);
        }

        if(inputLname.length() == 0){
            inputLname.setError("Please enter valid last name");
            valid = false;
        } else {
            inputLname.setError(null);
        }

        if(inputEmail.length() == 0 && !Patterns.EMAIL_ADDRESS.matcher(inputEmail.getText().toString()).matches()){
            inputEmail.setError("Please enter valid email address");
            valid = false;
        } else {
            inputEmail.setError(null);
        }

        if(inputPhonenumber.length() == 0 && !Patterns.PHONE.matcher(inputPhonenumber.getText().toString()).matches()) {
            inputPhonenumber.setError("Please enter valid phone number");
            valid = false;
        } else {
            inputPhonenumber.setError(null);
        }

        if (inputCity.length() == 0){
            inputCity.setError("Please enter valid city name");
            valid = false;
        } else {
            inputCity.setError(null);
        }

        if (inputPassword.getText().toString().length() < 4 && inputPassword.length() > 10){
            inputPassword.setError("Please enter valid password between 4 and 10 characters");
        } else {
            inputPassword.setError(null);
        }
        return valid;
    }
}
