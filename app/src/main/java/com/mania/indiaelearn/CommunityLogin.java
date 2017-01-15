package com.mania.indiaelearn;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CommunityLogin extends AppCompatActivity {

    private CheckBox show_pass;
    private Button login,link_register;
    private EditText username,pass;

    private final String url = "http://shubh.noads.biz/IndiaELearn/LoginIndiaELearn.php";
    RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_login);

        show_pass = (CheckBox)findViewById(R.id.community_login_show_pass);
        login = (Button)findViewById(R.id.community_btn_login);
        link_register = (Button)findViewById(R.id.community_link_register);
        username = (EditText)findViewById(R.id.community_login_username_email);
        pass = (EditText)findViewById(R.id.community_login_password);

        mQueue = Volley.newRequestQueue(this);

        link_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CommunityRegisterActivity.class);
                startActivity(intent);
            }
        });

        show_pass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    pass.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);
                }
                else{
                    pass.setInputType(129);
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String user = username.getText().toString();
                final String passW = pass.getText().toString();
                final ProgressDialog pd = new ProgressDialog(getApplicationContext());
                pd.setMessage("Logging...In");
                pd.show();
                if(user.equals("") || passW.equals("")){
                    Toast.makeText(getApplicationContext(),"All the Columns are mandatory",Toast.LENGTH_LONG).show();
                    pd.dismiss();
                }
                else{
                    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            for(int i=0;i<response.length();i++){
                                try {
                                    JSONObject object = response.getJSONObject(i);
                                    String username = object.getString("Username");
                                    String password = object.getString("Password");
                                    String email = object.getString("Email");

                                    if((username.equals(user) || email.equals(user)) && passW.equals(password)){
                                        Intent intent = new Intent();
                                        startActivity(intent);
                                        pd.dismiss();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    pd.show();
                                }
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            pd.dismiss();
                            Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                        }
                    });

                    mQueue.add(jsonArrayRequest);
                }
            }
        });


    }
}
