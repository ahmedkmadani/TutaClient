package com.tuta.tutadriver.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.tuta.tutadriver.R;

public class Utility extends AppCompatActivity {

    //    get device name for user
    public String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }

    private String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }

    //    Show Snack bar to view message
    public  void ShowSnackBar(Context context, View view, String msg){
        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG);
        View snackbarview = snackbar.getView();
        snackbarview.setBackgroundColor(getResources().getColor(R.color.bg_register));
        snackbar.show();
    }

    //    Show Snack bar in case of there is no internet connection
    public void ShowSnackbBarNoInternet(Context context, View view){
        Snackbar snackbar = Snackbar.make(view, String.valueOf(R.string.error_no_internet), Snackbar.LENGTH_LONG);
        View snackbarview = snackbar.getView();
        snackbarview.setBackgroundColor(getResources().getColor(R.color.bg_register));
        snackbar.show();
    }

    //    function to check if user connected to internet
    public boolean isOnline(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected() && cm.getActiveNetworkInfo().isAvailable()
        && cm.getActiveNetworkInfo().isConnected()){
            return true;
        } else {
            return false;
        }
    }

}
