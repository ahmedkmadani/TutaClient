package com.tuta.tutadriver.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.tuta.tutadriver.R;
import com.tuta.tutadriver.databinding.ActivityAddVehicalBinding;

public class AddVehicleActivity extends AppCompatActivity {
    ActivityAddVehicalBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityAddVehicalBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());


        mBinding.textviewTerms.setOnClickListener(v -> {
            Intent i = new Intent(AddVehicleActivity.this, TermsActivity.class);
            startActivity(i);
        });


        mBinding.BtnContinue.setOnClickListener(v -> {
            Intent i = new Intent(AddVehicleActivity.this, AddDocumentActivity.class);
            startActivity(i);

        });

    }
}
