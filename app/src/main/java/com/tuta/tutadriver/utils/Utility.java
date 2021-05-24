package com.tuta.tutadriver.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.snackbar.Snackbar;
import com.tuta.tutadriver.R;
import com.tuta.tutadriver.activity.App;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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
    public void ShowSnackBar(Context context, View view, String msg) {
        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG);
        View snackbarview = snackbar.getView();
        snackbarview.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        snackbar.show();
    }

    //    Show Snack bar in case of there is no internet connection
    public void ShowSnackbBarNoInternet(Context context, View view) {
        Snackbar snackbar = Snackbar.make(view, String.valueOf(R.string.error_no_internet), Snackbar.LENGTH_LONG);
        View snackbarview = snackbar.getView();
        snackbarview.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        snackbar.show();
    }

    //    function to check if user connected to internet
    public boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected() && cm.getActiveNetworkInfo().isAvailable()
                && cm.getActiveNetworkInfo().isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    //function to make request using Volley
    public void makeRawRequest(int method, String Url, JSONObject rawData, String s, final CustomRequest.VolleyResponseListener listener) {
        App app = new App();
        Log.d("Requested URL", Url);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(method, Url, rawData, response -> listener.onResponse(response), error -> listener.onError(error.toString())) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> rawData = new HashMap<String, String>();
                return rawData;
            }
        };

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                App.MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        jsonObjectRequest.setShouldCache(false);
        App.getInstance().addToRequestQueue(jsonObjectRequest);

    }

}
