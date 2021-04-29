package com.tuta.tutadriver.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.tuta.tutadriver.R;

public class PaymentDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up_payment);

        Button BtnSetupPayment = findViewById(R.id.BtnContinue);

        BtnSetupPayment.setOnClickListener(v -> {
                Intent i = new Intent(PaymentDetailsActivity.this, DashboardActivity.class);
                startActivity(i);
        });
    }
}
