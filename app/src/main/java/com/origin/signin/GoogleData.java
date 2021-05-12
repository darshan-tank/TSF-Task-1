package com.origin.signin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

import io.alterac.blurkit.BlurLayout;

public class GoogleData extends AppCompatActivity {

    GoogleSignInClient mGoogleSignInClient;
    BlurLayout blurLayout;
    TextView name,email,id;
    ImageView profile;
    Button signout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        id = findViewById(R.id.id);
        signout = findViewById(R.id.signout);
        profile = findViewById(R.id.profile);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGoogleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(GoogleData.this,"Signout Successfully",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(GoogleData.this,MainActivity.class));
                    }
                });
            }
        });

        blurLayout = findViewById(R.id.blurLayout);
        blurLayout.setFPS(0);
        blurLayout.setDownscaleFactor((float) 0.12);
        blurLayout.setBlurRadius(3);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();

            name.setText(personName);
            email.setText(personEmail);
            id.setText(personId);
            Log.d("Error",personPhoto.toString());
            Picasso.with(this).load(personPhoto).placeholder(R.drawable.tsf).into(profile);
            //Glide.with(this).load(personPhoto).into(profile);
        }
    }
}