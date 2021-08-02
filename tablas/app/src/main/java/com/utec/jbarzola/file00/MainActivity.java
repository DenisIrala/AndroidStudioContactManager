package com.utec.jbarzola.file00;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    FragmentManager manejador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manejador = getSupportFragmentManager();
        pantalla1Fragment fragmentoUno = new pantalla1Fragment();
        manejador.beginTransaction().replace(R.id.area, fragmentoUno).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.opcion1:
                pantalla1Fragment fragmentoUno = new pantalla1Fragment();
                //Estaríamos reemplazado en cada uno de los fragmentos
                manejador.beginTransaction().replace(R.id.area, fragmentoUno).commit();
                return true;
            case R.id.opcion2:
                pantalla2Fragment fragmentoDos = new pantalla2Fragment();
                manejador.beginTransaction().replace(R.id.area, fragmentoDos).commit();
                return true;
            case R.id.opcion3:
                pantalla3Fragment fragmentoTres = new pantalla3Fragment();
                manejador.beginTransaction().replace(R.id.area, fragmentoTres).commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}






































