package com.anas.firebasegooglesignin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;

import javax.microedition.khronos.opengles.GL;

public class HomeActivity extends AppCompatActivity {

    Button btnLogout;
    ImageView imgUser_img;
    TextView txtUser_name,txtUser_email;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        btnLogout = findViewById(R.id.btnLogout);
        imgUser_img = findViewById(R.id.imgUser_img);
        txtUser_name = findViewById(R.id.txtUser_name);
        txtUser_email = findViewById(R.id.txtUser_email);


        GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);
        uri = googleSignInAccount.getPhotoUrl();

        Toast.makeText(this, FirebaseAuth.getInstance().getCurrentUser().getUid(), Toast.LENGTH_SHORT).show();
        
        txtUser_email.setText(googleSignInAccount.getDisplayName());
        txtUser_name.setText(googleSignInAccount.getEmail());
        Glide.with(this).load(uri).into(imgUser_img);


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //proper logout
                GoogleSignIn.getClient(HomeActivity.this, GoogleSignInOptions.DEFAULT_SIGN_IN).signOut();
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(HomeActivity.this,MainActivity.class));
                finish();
            }
        });
    }
}