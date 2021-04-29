package com.tuta.tutadriver.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tuta.tutadriver.adapter.ViewPagerAdapter;
import com.tuta.tutadriver.databinding.ActivityIntroBinding;
import com.tuta.tutadriver.fragment.IntroFirstFragment;
import com.tuta.tutadriver.fragment.IntroSecondFragment;
import com.tuta.tutadriver.*;

public class IntroActivity extends AppCompatActivity {

    ActivityIntroBinding binding;
    ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupViewPager(binding.introViewPager);

        binding.introViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                setUpCurrentIndicator(position);
                if(position == 1) {

                    startActivity(new Intent(IntroActivity.this , LoginActivity.class));
                }
            }


        });

    }

    private void setupViewPager(ViewPager2 viewPager) {
        binding.introViewPager.setUserInputEnabled(true);
        adapter = new ViewPagerAdapter(this);

        IntroFirstFragment firstFragment = new IntroFirstFragment();
        IntroSecondFragment secondFragment = new IntroSecondFragment();

        adapter.addFragment(firstFragment, "first");
        adapter.addFragment(secondFragment, "second");

        setUpCardIndicators();

        viewPager.setAdapter(adapter);
    }

    private void setUpCardIndicators(){
        binding.viewpagerIndicator.removeAllViews();
        ImageView[] indicators = new ImageView[adapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(8,0,8,0);
        for(int i=0 ;i<indicators.length ; i++){
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.card_indicator_inactive));
            indicators[i].setLayoutParams(layoutParams);
            binding.viewpagerIndicator.addView(indicators[i]);
        }

        setUpCurrentIndicator(0);
    }

    private void setUpCurrentIndicator(int index){
        int childCount = binding.viewpagerIndicator.getChildCount();
        for(int i = 0 ; i<childCount ; i++) {
            ImageView imageView = (ImageView) binding.viewpagerIndicator.getChildAt(i);
            if(i == index){
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.mipmap.card_indicator_active));
            }else{
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.mipmap.card_indicator_inactive));
            }
        }
    }
}

