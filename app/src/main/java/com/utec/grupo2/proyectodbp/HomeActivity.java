package com.utec.grupo2.proyectodbp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    private Button mLogout;
    private FirebaseAuth Auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Auth=FirebaseAuth.getInstance();
        mLogout= (Button) findViewById(R.id.logout_button);
    }
    public void Logout(View view){
        Auth.signOut();
        startActivity(new Intent(HomeActivity.this, AuthActivity.class));
        finish();
    }
}