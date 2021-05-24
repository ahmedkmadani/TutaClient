package com.tuta.tutadriver.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.tuta.tutadriver.R;
import com.tuta.tutadriver.databinding.ActivityDashboardBinding;
import com.tuta.tutadriver.fragment.HomeFragment;
import com.tuta.tutadriver.fragment.ProfileFragment;

public class DashboardActivity extends AppCompatActivity {
    ActivityDashboardBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        LoadFragment(new HomeFragment());

        mBinding.bottomNavigationView.setSelectedItemId(R.id.home);
        mBinding.bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home :
                    LoadFragment(new HomeFragment());
                    break;
                case  R.id.nav_trasnsction :
                    break;
                case R.id.profile :
                    LoadFragment(new ProfileFragment());
                    break;
            }
            return false;
        });
    }

    //    Load Selected fragment by user
    private boolean LoadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
