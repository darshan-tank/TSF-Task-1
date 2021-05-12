package com.origin.signin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.squareup.picasso.Picasso;

public class FacebookData extends AppCompatActivity {

    TextView name,email,id;
    ImageView profile;
    Button signout;
    LoginManager mLoginManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_data);

        Intent intent = getIntent();

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        id = findViewById(R.id.id);
        signout = findViewById(R.id.signout);
        profile = findViewById(R.id.profile);

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginManager = LoginManager.getInstance();
                mLoginManager.logOut();
                startActivity(new Intent(FacebookData.this, MainActivity.class));
            }
        });

        String personName = intent.getStringExtra("name");
        String personEmail = intent.getStringExtra("email");
        String personId = intent.getStringExtra("id");
        String personPhoto = "https://graph.facebook.com/" + personId + "/picture?return_ssl_resources=1";

        name.setText(personName);
        email.setText(personEmail);
        id.setText(personId);
        Log.d("Error",personPhoto);
        Picasso.with(this).load(personPhoto).placeholder(R.drawable.tsf).into(profile);
    }


}