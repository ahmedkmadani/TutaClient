package com.tuta.tutadriver.activity;

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
import com.tuta.tutadriver.utils.UrLs;

import org.json.JSONException;
import org.json.JSONObject;

public class SignupActivity extends AppCompatActivity {
    String phoneNumber;
    EditText inputPhone;
    Button BtnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView tv_terms = findViewById(R.id.textview_terms);
        inputPhone = findViewById(R.id.input_phone);

        tv_terms.setOnClickListener(v -> {
            Intent i = new Intent(SignupActivity.this, TermsActivity.class);
            startActivity(i);
        });

        BtnContinue = findViewById(R.id.BtnContinue);
        BtnContinue.setOnClickListener(v -> {
            phoneNumber = inputPhone.getText().toString();
            GetOTP(phoneNumber);
        });
    }

    private void GetOTP(String phonenumber) {
        if (!validate()) {
            return;
        }

    }

    private void onFailResponse(VolleyError error) {
    }

    private void OnSuccedResponse(String phonenumber) {
        Intent i = new Intent(SignupActivity.this, PinVerficationActivity.class);
        i.putExtra("PhoneNumber", phonenumber);
        startActivity(i);
    }

    private boolean validate() {
        boolean valid = true;
        if (phoneNumber.isEmpty() && !Patterns.PHONE.matcher(phoneNumber).matches()) {
            inputPhone.setError("Please enter valid phone number");
            valid = false;
        } else {
            inputPhone.setError(null);
        }
        return valid;
    }
}
