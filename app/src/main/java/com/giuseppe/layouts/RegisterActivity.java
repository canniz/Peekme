package com.giuseppe.layouts;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.giuseppe.layouts.utils.NetworkConstants;
import com.giuseppe.layouts.utils.RandomConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class RegisterActivity extends AppCompatActivity {
    Button okButton;
    EditText email;
    EditText username;
    EditText password;
    JSONObject post = new JSONObject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.start_register_activity);
        Glide
                .with(this)
                .load(getIntent().getData())
                .placeholder(R.drawable.panorama)
                .centerCrop()
                .into(((ImageView) findViewById(R.id.backgroundRegisterImage)));
        okButton = (Button) findViewById(R.id.confirmRegistration);
        email = (EditText) findViewById(R.id.emailRegister);
        username = (EditText) findViewById(R.id.usernameRegister);
        password = (EditText) findViewById(R.id.passwordIdRegister);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO CHECK IF EVERYTHING IS OK AND LEVARE HARDCODING
                try {
                    post.put("nickname",username.getText());
                    post.put("email",email.getText());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                new AsyncCaller().execute();


            }
        });
    }
    OkHttpClient client = new OkHttpClient();
    String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(RandomConstants.JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
    private class AsyncCaller extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
        }
        @Override
        protected Void doInBackground(Void... params) {
            String response = null;
            try {
                response = post(NetworkConstants.REGISTRATION_HTTP_POST,post.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(response==null){
                System.out.println("ERROR IN REGISTRATION CONNECTION");
            }else{
                System.out.println("REGISTRATION SUCCESSFUL");
                System.out.println(response);
            }

            //this method will be running on background thread so don't update UI from here
            //do your long running http tasks here,you don't want to pass argument and u can access the parent class' variable url over here


            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            //this method will be running on UI thread
        }

    }
}

