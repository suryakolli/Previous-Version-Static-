package com.mania.indiaelearn;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class CommunityRegisterActivity extends AppCompatActivity {

    RequestQueue mQueue;
    private EditText full_name,user_name,pass,email;
    private Button btn_reg,btn_link_Login;

    private final String url = "http://shubh.noads.biz/IndiaELearn/RegistrationIndiaELearn.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_register);
        full_name = (EditText)findViewById(R.id.community_register_full_name);
        user_name = (EditText)findViewById(R.id.community_register_user_name);
        pass = (EditText)findViewById(R.id.community_register_password);
        email = (EditText)findViewById(R.id.community_register_email);

        btn_reg = (Button)findViewById(R.id.community_btn_register);
        btn_link_Login = (Button)findViewById(R.id.community_link_login);

        mQueue = Volley.newRequestQueue(this);

        btn_link_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommunityRegisterActivity.this,CommunityLogin.class);
                startActivity(intent);
            }
        });

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = full_name.getText().toString();
                final String username = user_name.getText().toString();
                final String password = pass.getText().toString();
                final String Email = email.getText().toString();

                final ProgressDialog pd = new ProgressDialog(getApplicationContext());
                pd.setMessage("Registering....");
                if(name.equals("") || username.equals("") || password.equals("") || Email.equals("")){
                    Toast.makeText(getApplicationContext(),"Filling every Field is mandatory",Toast.LENGTH_LONG).show();
                    pd.dismiss();
                }
                else{
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                            pd.dismiss();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            pd.dismiss();
                            full_name.setText(null);
                            user_name.setText(null);
                            pass.setText(null);
                            email.setText(null);
                            Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> map = new HashMap<String, String>();
                            map.put("Name",name);
                            map.put("Username",username);
                            map.put("Password",password);
                            map.put("Email",Email);
                            return map;
                        }
                    };
                    mQueue.add(stringRequest);
                }
            }
        });
    }
}
