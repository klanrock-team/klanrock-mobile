package com.klanrock.klanrock;

import android.app.ProgressDialog;
<<<<<<< HEAD
import android.content.Intent;
=======
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
>>>>>>> 5c11de07931b40cf7449c995808fdce2b42a2973
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
<<<<<<< HEAD
=======
import android.widget.Toast;
>>>>>>> 5c11de07931b40cf7449c995808fdce2b42a2973

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    public boolean status_register = false;
    EditText Nama_depan,Nama_belakang, No_telepon, Username, Password;
    private Snackbar snackbar;
    private Handler handler = new Handler();
    private ProgressDialog pd;
<<<<<<< HEAD
=======
    private ConnectivityManager cekConnection;
>>>>>>> 5c11de07931b40cf7449c995808fdce2b42a2973
    private static String URL_REGIST = "http://192.168.43.7/klanrock/pelanggan/register";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        pd = new ProgressDialog(this);
        Nama_depan = findViewById(R.id.nm_depan);
        Nama_belakang = findViewById(R.id.nm_belakang);
        No_telepon = findViewById(R.id.no_telp);
        Username = findViewById(R.id.username);
        Password = findViewById(R.id.password);
<<<<<<< HEAD
=======
        //cek koneksi internet
        cekConnection = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cekConnection.getActiveNetworkInfo()!=null && cekConnection.getActiveNetworkInfo().isAvailable() && cekConnection.getActiveNetworkInfo().isConnected()){

        }else{
            Toast.makeText(this,"No Internet Connection!!",Toast.LENGTH_SHORT).show();
        }
>>>>>>> 5c11de07931b40cf7449c995808fdce2b42a2973
    }

    public void openLogin(View view) {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
    private void kirim_data_register() {
        pd = new ProgressDialog(this);
        pd.setMessage("Mendaftarkan ...");
        pd.show();
        RequestQueue queue = Volley.newRequestQueue(this);
        String response = null;
        final String finalResponse = response;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.hide();
                        showSnackbar(response);
                        if(response.equals("Berhasil register,Silahkan login")){
                            handler.postDelayed(runAfterDelay,3000);
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
<<<<<<< HEAD
                        Log.d("ErrorResponse", finalResponse);

=======
                        Log.d("ErrorResponse", error.getMessage());
>>>>>>> 5c11de07931b40cf7449c995808fdce2b42a2973

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();

                params.put("nama_depan", Nama_depan.getText().toString());
                params.put("nama_belakang", Nama_belakang.getText().toString());
                params.put("no_telp", No_telepon.getText().toString());
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

    public void register(View view) {
<<<<<<< HEAD
        if (Nama_depan.getText().toString().length()==0){
            Nama_depan.setError("Required field!");
        }
        else if (Nama_belakang.getText().toString().length()==0){
            Nama_belakang.setError("Required field!");
        }
        else if (No_telepon.getText().toString().length()==0){
            No_telepon.setError("Required field!");
        }
        else if (No_telepon.getText().toString().length()<11 || No_telepon.getText().toString().length()>13){
            No_telepon.setError("Masukkan no telepon yang valid!");
        }

        else if (Username.getText().toString().length()==0){
            Username.setError("Required field!");
        }
        else if (Password.getText().toString().length()==0){
            Password.setError("Required Field!");
        }else if(Password.getText().toString().length()<8){
            Password.setError("Password minimal 8 karakter!");
        }else{
            try{
                kirim_data_register();
            }catch (Exception e) {
                e.printStackTrace();
            }
=======
        if (cekConnection.getActiveNetworkInfo()!=null && cekConnection.getActiveNetworkInfo().isAvailable() && cekConnection.getActiveNetworkInfo().isConnected()){
            if (Nama_depan.getText().toString().length()==0){
                Nama_depan.setError("Required field!");
            }
            else if (Nama_belakang.getText().toString().length()==0){
                Nama_belakang.setError("Required field!");
            }
            else if (No_telepon.getText().toString().length()==0){
                No_telepon.setError("Required field!");
            }
            else if (No_telepon.getText().toString().length()<11 || No_telepon.getText().toString().length()>13){
                No_telepon.setError("Masukkan no telepon yang valid!");
            }

            else if (Username.getText().toString().length()==0){
                Username.setError("Required field!");
            }
            else if (Password.getText().toString().length()==0){
                Password.setError("Required Field!");
            }else if(Password.getText().toString().length()<8){
                Password.setError("Password minimal 8 karakter!");
            }else{
                try{
                    kirim_data_register();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }else{
            Toast.makeText(this,"No Internet Connection!!",Toast.LENGTH_SHORT).show();
>>>>>>> 5c11de07931b40cf7449c995808fdce2b42a2973
        }
    }

    private Runnable runAfterDelay = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        }
    };
}
