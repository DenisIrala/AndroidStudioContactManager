 package com.utec.grupo2.proyectodbp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.view.KeyEvent.Callback;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AuthActivity extends AppCompatActivity {

    private EditText Email;
    private EditText Password;
    private String email="";
    private String password="";
    DatabaseReference mDatabase;
    FirebaseAuth Auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        Email= (EditText) findViewById(R.id.Username);
        Password = (EditText) findViewById(R.id.Password);
        mDatabase=FirebaseDatabase.getInstance().getReference();
    }
   public void Register(View view){

       email=Email.getText().toString();
       password=Password.getText().toString();
       Auth=FirebaseAuth.getInstance();


       if (!password.isEmpty() && !email.isEmpty()){
            if(password.length()>=6){
                Auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Map<String,Object> map = new HashMap<>();
                            map.put("email", email);
                            map.put("password", password);
                            String id = Auth.getCurrentUser().getUid();
                            mDatabase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task2) {
                                    if(task2.isSuccessful()){
                                        startActivity(new Intent(AuthActivity.this, HomeActivity.class));
                                        finish();
                                    }
                                    else{
                                        Toast.makeText(AuthActivity.this, "No se pudieron añadir los datos a la database.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }
                        else{
                            Toast.makeText(AuthActivity.this, "No se pudo crear la cuenta", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            else Toast.makeText(AuthActivity.this, "La contraseña debe tener un mínimo de 6 caracteres.", Toast.LENGTH_SHORT).show();
       }
       else {
           Toast.makeText(AuthActivity.this, "Todos los campos deben ser rellenados.", Toast.LENGTH_SHORT).show();
       }

   }

   public void Login(View view){
        email = Email.getText().toString().trim();
        password=Password.getText().toString().trim();
        Auth=FirebaseAuth.getInstance();

        if(!email.isEmpty() && !password.isEmpty()){
            Auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task3) {
                    if(task3.isSuccessful()){
                        startActivity(new Intent(AuthActivity.this, HomeActivity.class));
                        finish();
                    }
                    else{
                        Log.w(this.getClass().getName(), "signInWithEmail:failure", task3.getException());
                        Toast.makeText(AuthActivity.this, "No se pudo iniciar sesión.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else{
            Toast.makeText(AuthActivity.this, "Todos los campos deben ser rellenados.", Toast.LENGTH_SHORT).show();

        }
   }

    @Override
    protected void onStart() {
        super.onStart();
        Auth=FirebaseAuth.getInstance();
        if(Auth.getCurrentUser()!=null){
            startActivity(new Intent(AuthActivity.this, HomeActivity.class));
            finish();
        }
    }
}