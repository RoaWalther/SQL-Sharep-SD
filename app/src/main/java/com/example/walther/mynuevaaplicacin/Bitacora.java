package com.example.walther.mynuevaaplicacin;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Bitacora extends AppCompatActivity {

    private EditText et_bitacora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitacora);
        et_bitacora =findViewById(R.id.bitacora);
        //el metodo fileList() devuelve un array con los ficheros almacenados por nuestra app
        String archivo [] = fileList();
        // creamos una condicion creando un metodo buleano al cual llamamos ArchivoExiste al cual le pasamos 2 parametros  el 1 parametro lo
        // llamamos archivo que es un vector que acavamos de crear luego entre comillas "bitacora.txt" el .txt es para indicar que es tipo texto
        if (ArchivoExiste(archivo,"bitacora.txt")){
            // la clase InputStreamReader me va a permitir abrir un archivo para leerlo
            // el metodo openFileInput(me va a permitir indicar cual es el archivo que queremos abrir)
            // tambien lo encerramos entre la sentencia  TRY {  }  y CATCH (IOException  xxx)
            try {
                InputStreamReader archivos = new InputStreamReader(openFileInput("bitacora.txt"));
                // la clase BufferedReader nos permite leer un archivo previamente abierto con la clase InputStreamReader
                BufferedReader br_archivo = new BufferedReader(archivos);
                //ahora indicamos donde guardar linea por linea creamos una variable tipo String
                String linea_archivo = br_archivo.readLine();
                // String linea_archivo = br_archivo.readLine(); esto me indica que va a leer la primer linea de texto y asi seguidamente
                // creamos otra variable donde guardamos todos los  textos
                String bitacoracompleta = "";
                // creamos un while donde vamos hacer un recorrido para ir leyendo linea por linea
                while (linea_archivo != null){
                    bitacoracompleta = bitacoracompleta + linea_archivo + "\n";
                    linea_archivo = br_archivo.readLine();
                }
                // ahora debemos indicarle al odjeto que esta leyyendo que deje de leer y se lo indicamos con el metodo ".close();"
                br_archivo.close();
                archivos.close();
                et_bitacora.setText(bitacoracompleta);


            }catch (IOException e){

            }
        }
    }
    // con metodos booleanos no es necesario las llaves de apertura y cierre de una decicion
    public boolean ArchivoExiste(String archivo [], String nombre_archivo){
        for (int i = 0; i < archivo.length; i++)
            if (nombre_archivo.equals(archivo[i]))
                return true;
        return false;
    }

    //metodo para el botton guardar
    public void guardar (View v){
        // el metodo OutputStreamWriter para indicar que mandaremos texto a un nuevo archivo al cual llamamos archivo2
        // el metodo openFileOutput el cual nos abre por file
        // tambien lo encerramos en un TRY y CATCH
        try {
            OutputStreamWriter archivo2 = new OutputStreamWriter(openFileOutput("bitacora.txt", Activity.MODE_PRIVATE));
            // ahora colocamos el metodo que nos permitira escribir dentro de nuestro archivo "bitacora.txt"
            // write es el metodo de escribir
            archivo2.write(et_bitacora.getText().toString());
            //ahora limpiamos lo guardado en el metodo BufferedReader con el metodo flush()
            archivo2.flush();
            archivo2.close();

        }catch (IOException d){

        }
        Toast.makeText(this,"archivo guardado correctamente",Toast.LENGTH_SHORT).show();

    }

    public void regresar(View v){
        Intent intent = new Intent(Bitacora.this,MenuActivity.class);
        startActivity(intent);
        finish();
    }
}
