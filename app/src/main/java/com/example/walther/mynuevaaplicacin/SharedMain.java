package com.example.walther.mynuevaaplicacin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SharedMain extends AppCompatActivity {

    private EditText et_email, et_nombre,et_datos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_main);

        et_email=(EditText)findViewById(R.id.etguardar);
        //cuando iniciamos la app lo primero que se dispara es el Oncreate y hay lo que contenga  el metodo
        // la clase SharedPreferences me permite crear archivos para guardar y mostrar
        // el metodo getSharedPreferences()me permite odtener la preferencia que se a guardado o creado
        SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        et_email.setText(preferences.getString("mail",""));
    }
    // metodo para el button guardar
    public  void  guaradar(View v){

        SharedPreferences preferences2 = getSharedPreferences("datos",Context.MODE_PRIVATE);
        //la clase extra Editor que se utiliza en conjunto con SharedPreferences
        // si no utilisamos editor sera imposible guardar un archivo
        SharedPreferences.Editor editor_odj = preferences2.edit();
        //metodo putString
        editor_odj.putString("mail",et_email.getText().toString());
        //confirmamos que lo queremos guardar
        //el metodo "commit" lo que hace es confirmar que lo que recuperamos con et.getText() en efecto lo queremos guardar
        editor_odj.commit();
        //ahora le damos la instruccion al programa que cuando el usuario presione guaradar la app se cierre
        finish();
    }
    public void regresar(View v){
        Intent intent = new Intent(SharedMain.this,MenuActivity.class);
        startActivity(intent);
        finish();
    }

}
