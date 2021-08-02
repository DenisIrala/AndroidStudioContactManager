package com.utec.grupo2.proyectodbp;

import android.content.Context;
import android.media.MediaParser;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link pantalla1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class pantalla1Fragment extends Fragment {

    EditText txtGuardar;
    TextView txtLeer;
    Button btnGuardar;
    Button btnLeer;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public pantalla1Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment pantalla1Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static pantalla1Fragment newInstance(String param1, String param2) {
        pantalla1Fragment fragment = new pantalla1Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_pantalla1, container, false);

        //Controlar y referencia botones
        txtGuardar = (EditText) vista.findViewById(R.id.txtGuardarInterna);
        txtLeer = (TextView) vista.findViewById(R.id.txtLeerExterna);
        btnGuardar = (Button) vista.findViewById(R.id.btnGuardarInterna);
        btnLeer = (Button) vista.findViewById(R.id.btnLeerInterna);

        btnLeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    BufferedReader lector = new BufferedReader(new InputStreamReader(getContext().openFileInput("interna.txt")));
                    //Obteniendo la información para leer
                    txtLeer.setText(lector.readLine());
                    lector.close();
                } catch(Exception ex){
                    Log.e("file00",ex.getMessage(),ex);
                }
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Gestión de errores
                try {
                    OutputStreamWriter manejador = new OutputStreamWriter(getContext().openFileOutput("interna.txt", Context.MODE_PRIVATE));
                    manejador.write(txtGuardar.getText().toString()); //Le pasamos lo que guardo en edit para mostrar
                    manejador.close();
                }catch (Exception ex){
                    Log.e("file00",ex.getMessage(),ex);
                }
            }
        });



        return vista;
    }
}








