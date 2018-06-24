package com.klanrock.klanrock;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private EditText Username,Password;
    private Handler handler = new Handler();
    private static String URL_LOGIN = ServerUrl.URL+"pelanggan/login";
    private Snackbar snackbar;
    private ProgressDialog pd;
    private ConnectivityManager cekConnection;

    //key json response
    final static String TAG = LoginActivity.class.getSimpleName();
    final static String TAG_MESSAGE = "message";
    final static String TAG_ID = "id";
    final static String TAG_USERNAME = "username";
    final static String TAG_SUCCESS = "success";
    final static String TAG_NAMA = "nama";
    final static String TAG_ID_LOGIN = "id_login";
    final static String TAG_ID_PELANGGAN = "id_pelanggan";

    final static String session_status = "session_status";
    final static String my_session = "my_session";

    SharedPreferences simpan_login;
    private boolean session=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Username = (EditText)findViewById(R.id.username);
        Password = (EditText)findViewById(R.id.password);
        cekConnection = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cekConnection.getActiveNetworkInfo()!=null && cekConnection.getActiveNetworkInfo().isAvailable() && cekConnection.getActiveNetworkInfo().isConnected()){

        }else{
            Toast.makeText(LoginActivity.this,"No Internet Connection!!",Toast.LENGTH_SHORT).show();
        }
        //cek session
        simpan_login = getSharedPreferences(my_session,Context.MODE_PRIVATE);
        session = simpan_login.getBoolean(session_status,false);
        String username =simpan_login.getString(TAG_USERNAME,null);
        String nama = simpan_login.getString(TAG_NAMA,null);
        String id_login = simpan_login.getString(TAG_ID_LOGIN,null);
        String id_pelanggan = simpan_login.getString(TAG_ID_PELANGGAN,null);
        if (session){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            intent.putExtra(TAG_USERNAME,username);
            intent.putExtra(TAG_NAMA,nama);
            intent.putExtra(TAG_ID_LOGIN,id_login);
            intent.putExtra(TAG_ID_PELANGGAN,id_pelanggan);
            finish();
            startActivity(intent);
        }

    }

    public void openRegister(View view) {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }
    private void cek_login() {
        pd = new ProgressDialog(this);
        pd.setMessage("Verifikasi Login...");
        pd.show();
        RequestQueue queue = Volley.newRequestQueue(this);
//        String response = null;
//        final String finalResponse = response;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.hide();
                        try {
                            JSONObject data = new JSONObject(response);
                            int success = data.getInt(TAG_SUCCESS);
                            String message = data.getString(TAG_MESSAGE);
                            showSnackbar(message);
                            if (success==1){
                                String username = data.getString(TAG_USERNAME);
                                String nama = data.getString(TAG_NAMA);
                                String id_login = data.getString(TAG_ID_LOGIN);
                                String id_pelanggan = data.getString(TAG_ID_PELANGGAN);
                                //save session login
                                SharedPreferences.Editor editor = simpan_login.edit();
                                editor.putBoolean(session_status,true);
                                editor.putString(TAG_USERNAME,username);
                                editor.putString(TAG_NAMA,nama);
                                editor.putString(TAG_ID_LOGIN,id_login);
                                editor.putString(TAG_ID_PELANGGAN,id_pelanggan);
                                editor.commit();
                                //jika berhasil login,pindah ke home
                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                intent.putExtra(TAG_USERNAME,username);
                                intent.putExtra(TAG_NAMA,nama);
                                intent.putExtra(TAG_ID_LOGIN,id_login);
                                intent.putExtra(TAG_ID_PELANGGAN,id_pelanggan);
                                finish();
                                startActivity(intent);
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
//                        if(response.equals("Berhasil Login")){
//                            handler.postDelayed(runAfterDelay,1000);
//                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d(TAG,"Login Error : "+error.getMessage());

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("username", Username.getText().toString());
                params.put("password", Password.getText().toString());

                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);

    }

    public void showSnackbar(String stringSnackbar){
        snackbar.make(findViewById(android.R.id.content), stringSnackbar.toString(), Snackbar.LENGTH_SHORT)
                .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                .show();
    }
//
//    private Runnable runAfterDelay = new Runnable() {
//        @Override
//        public void run(){
//            startActivity(new Intent(getApplicationContext(), MainActivity.class));
//            finish();
//        }
//    };

    public void login(View view) {
        if (cekConnection.getActiveNetworkInfo()!=null && cekConnection.getActiveNetworkInfo().isAvailable() && cekConnection.getActiveNetworkInfo().isConnected()){
            if (Username.getText().toString().length()==0){
                Username.setError("Required Field!");
            }
            else if (Password.getText().toString().length()==0){
                Password.setError("Required Field!");
            }else{
                try{
                    cek_login();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }else{
            Toast.makeText(LoginActivity.this,"No Internet Connection!!",Toast.LENGTH_SHORT).show();
        }
            }
}
