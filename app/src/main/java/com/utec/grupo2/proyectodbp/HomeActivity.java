package com.utec.grupo2.proyectodbp;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import com.utec.grupo2.proyectodbp.db.DbContactos;
import com.utec.grupo2.proyectodbp.Contactos;
import com.utec.grupo2.proyectodbp.db.DbHelper;
import com.utec.grupo2.proyectodbp.ListaContactosAdapter;

public class HomeActivity extends AppCompatActivity {
    RecyclerView listaContactos;
    ArrayList<Contactos> listaArrayContactos;
    private FirebaseAuth Auth;
    EditText txtNombre, txtTelefono, txtCorreo;
    Contactos contacto;
    int id = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Auth=FirebaseAuth.getInstance();
        listaContactos = findViewById(R.id.listaManga);
        listaContactos.setLayoutManager(new LinearLayoutManager(this));

        DbContactos dbContactos = new DbContactos(HomeActivity.this);

        listaArrayContactos = new ArrayList<>();
        ListaContactosAdapter adapter = new ListaContactosAdapter(dbContactos.mostrarContactos());
        listaContactos.setAdapter(adapter);
        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);


    }
    public void Agregar(View view){
    if(!txtNombre.getText().toString().equals("") && !txtTelefono.getText().toString().equals("")) {

        DbContactos dbContactos = new DbContactos(HomeActivity.this);
        long id = dbContactos.insertarContacto(txtNombre.getText().toString(), txtTelefono.getText().toString());

        if (id > 0) {
            Toast.makeText(HomeActivity.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
            limpiar();
            finish();
            startActivity(getIntent());
        } else {
            Toast.makeText(HomeActivity.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
        }
    } else {
        Toast.makeText(HomeActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
    }
}
    public void Logout(View view){
        Auth.signOut();
        startActivity(new Intent(HomeActivity.this, AuthActivity.class));
        finish();
    }


    private void limpiar() {
        txtNombre.setText("");
        txtTelefono.setText("");
    }
}