package com.example.walther.mynuevaaplicacin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SharedMain2 extends AppCompatActivity {
    private EditText et_email, et_nombre,et_datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_main2);

        et_datos=(EditText)findViewById(R.id.datos);
        et_nombre=(EditText)findViewById(R.id.nombre);

    }

    public void guardar(View v){

        String nombre = et_nombre.getText().toString();
        String datos = et_datos.getText().toString();
        SharedPreferences nombrepre = getSharedPreferences("agenda", Context.MODE_PRIVATE);
        SharedPreferences.Editor edi_nombre = nombrepre.edit();
        edi_nombre.putString(nombre, datos);
        edi_nombre.commit();
        Toast.makeText(this,"El contacto ha sido guardado",Toast.LENGTH_LONG).show();
    }

    public void buscar (View view){
        String nom = et_nombre.getText().toString();

        SharedPreferences nombrepre = getSharedPreferences("agenda",Context.MODE_PRIVATE);
        String datos = nombrepre.getString(nom, "");

        if (datos.length()== 0){
            Toast.makeText(this,"No se encontro ningun valor",Toast.LENGTH_LONG).show();
        }else{
            et_datos.setText(datos);
        }

    }

    public void regresar(View v){
        Intent intent = new Intent(SharedMain2.this,MenuActivity.class);
        startActivity(intent);
        finish();
    }
}
