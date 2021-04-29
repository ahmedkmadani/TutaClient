package com.tuta.tutadriver.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.tuta.tutadriver.R;

public class AddDocumentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_document);

        TextView tv_terms = findViewById(R.id.textview_terms);

        tv_terms.setOnClickListener(v -> {
            Intent i = new Intent(AddDocumentActivity.this, TermsActivity.class);
            startActivity(i);
        });

        Button BtnSetupPayment= findViewById(R.id.BtnContinue);

        BtnSetupPayment.setOnClickListener(v -> {
            Intent i = new Intent(AddDocumentActivity.this, PaymentDetailsActivity.class);
            startActivity(i);
        });

    }
}
