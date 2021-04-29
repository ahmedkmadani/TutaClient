package com.tuta.tutadriver.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.tuta.tutadriver.R;

public class AddVehicleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehical);

        TextView tv_terms = findViewById(R.id.textview_terms);

        tv_terms.setOnClickListener(v -> {
            Intent i = new Intent(AddVehicleActivity.this, TermsActivity.class);
            startActivity(i);
        });


        Button BtnContinue = findViewById(R.id.BtnContinue);

        BtnContinue.setOnClickListener(v -> {
            Intent i = new Intent(AddVehicleActivity.this, AddDocumentActivity.class);
            startActivity(i);

        });

    }
}
