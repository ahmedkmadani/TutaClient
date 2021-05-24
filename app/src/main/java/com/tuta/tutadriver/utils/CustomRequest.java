package com.tuta.tutadriver.utils;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class CustomRequest extends Request<JSONObject> {

      private Listener<JSONObject> listener;
      private Map<String, String> params;
      private JSONObject jsonObject ;
      String token = "" ;


    public interface VolleyResponseListener {
        void onResponse(JSONObject response);
        void onError(String message);
    }

    // raw with token
    // modified general purpose call
    public CustomRequest(int method, String url, JSONObject rawData, String token ,
                         Listener<JSONObject> reponseListener, ErrorListener errorListener) {
        super(method, url, errorListener);
        this.listener = reponseListener;
        this.jsonObject = rawData;
        this.token = token;
    }

    public CustomRequest(int method, String url, JSONObject rawData,
                         Listener<JSONObject> reponseListener, ErrorListener errorListener) {
        super(method, url, errorListener);
        this.listener = reponseListener;
        this.jsonObject = rawData;
    }

    public CustomRequest(int method, String url, String token , Map<String, String> params ,
                         Listener<JSONObject> reponseListener, ErrorListener errorListener) {
        super(method, url, errorListener);
        this.listener = reponseListener;
        this.token = token;
        this.params = params;
    }


    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
      return params;
    }


    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json; charset=UTF-8");
        headers.put("Accept", "application/json; charset=UTF-8");
        headers.put("Authorization", "Bearer "+token);

        return headers;
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        listener.onResponse(response);
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
         try {
             String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
             Log.e("originl resp" , jsonString);
                return Response.success(new JSONObject(jsonString),
                        HttpHeaderParser.parseCacheHeaders(response));
            } catch (UnsupportedEncodingException e) {
                return Response.error(new ParseError(e));
            } catch (JSONException je) {
                return Response.error(new ParseError(je));
            }
    }
    
    
    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError){
            if(volleyError.networkResponse != null && volleyError.networkResponse.data != null){
                    VolleyError error = new VolleyError(new String(volleyError.networkResponse.data));
                    volleyError = error;
                }
            return volleyError;
        }
}