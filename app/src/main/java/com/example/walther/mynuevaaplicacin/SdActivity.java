package com.example.walther.mynuevaaplicacin;

import android.app.Activity;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SdActivity extends AppCompatActivity {
    private EditText et_nombre, et_contenido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sd);
        et_nombre = (EditText)findViewById(R.id.nombre_sd);
        et_contenido = (EditText)findViewById(R.id.contenido);
    }

    public void guardar(View v){

        String nombre = et_nombre.getText().toString();
        String contenido = et_contenido.getText().toString();

        try{
            //primero indicamos la ruta sd donde se guardara nuestros archivos para ello utilizamos
            // un odjeto de la clase file el cual le ponemos como queramos que se llame
            // File nos permite guardar de manera temporar la ruta de la targeta sd
            //tambien debemos recuperar la ruta para ello utilizamos Environment.getExternalStorageDirectory(); que nos
            //permite guardarla en la clase File
            File targeta_sd = Environment.getExternalStorageDirectory();
            //utilizamos un Toast para que el usuario pueda ver donde queda guardado el archivo
            //el metodo getPath recupera lo que esta dentro del archivo file y colocarlo en el Toast
            Toast.makeText(this,targeta_sd.getPath(),Toast.LENGTH_LONG).show();
            File ruta_archivo = new File(targeta_sd.getPath(), nombre);
            OutputStreamWriter escribir_archivo = new OutputStreamWriter(openFileOutput(nombre, Activity.MODE_PRIVATE));
            escribir_archivo.write(contenido);
            escribir_archivo.flush();
            escribir_archivo.close();
            Toast.makeText(this,"Guardado Correctamente",Toast.LENGTH_LONG).show();
            et_nombre.setText("");
            et_contenido.setText("");
        }catch (IOException a){
            Toast.makeText(this,"no se pudo guardar el archivo",Toast.LENGTH_SHORT).show();
        }


    }

    //metodo para consultar
    public void consultar(View v){
        String nombre = et_nombre.getText().toString();

        try {
            File targeta_sd2 = Environment.getExternalStorageDirectory();
            File ruta_archivo = new File(targeta_sd2.getPath(), nombre);
            InputStreamReader abrir_archivo = new InputStreamReader(openFileInput(nombre));
            BufferedReader leer_archivo = new BufferedReader(abrir_archivo);
            String linea = leer_archivo.readLine();
            String contenidocompleto = "";

            while (linea != null){
                contenidocompleto = contenidocompleto + linea + "\n";
                linea = leer_archivo.readLine();
            }
            leer_archivo.close();
            abrir_archivo.close();
            et_contenido.setText(contenidocompleto);

        }catch (IOException e){
            Toast.makeText(this,"Error al leer el archivo",Toast.LENGTH_LONG).show();
        }
    }

    public void regresar(View v){
        Intent intent = new Intent(SdActivity.this,MenuActivity.class);
        startActivity(intent);
        finish();
    }
}
