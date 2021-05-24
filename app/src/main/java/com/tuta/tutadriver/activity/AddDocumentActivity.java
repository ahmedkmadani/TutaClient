package com.tuta.tutadriver.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.tuta.tutadriver.R;
import com.tuta.tutadriver.databinding.ActivityCreateAccountBinding;
import com.tuta.tutadriver.databinding.ActivityUploadDocumentBinding;

public class AddDocumentActivity extends AppCompatActivity {
    ActivityUploadDocumentBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityUploadDocumentBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());


        mBinding.textviewTerms.setOnClickListener(v -> {
            Intent i = new Intent(AddDocumentActivity.this, TermsActivity.class);
            startActivity(i);
        });


        mBinding.BtnContinue.setOnClickListener(v -> {
            Intent i = new Intent(AddDocumentActivity.this, PaymentDetailsActivity.class);
            startActivity(i);
        });

    }
}
