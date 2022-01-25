package com.example.walther.mynuevaaplicacin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
    public void webview(View v){
        Intent intent= new Intent(MenuActivity.this,MainActivity.class);
        startActivity(intent);
    }

    public void claseshared(View v){
        Intent intent = new Intent(MenuActivity.this,SharedMain.class);
        startActivity(intent);
    }
    public void claseshared2(View v){
        Intent intent = new Intent(MenuActivity.this,SharedMain2.class);
        startActivity(intent);
    }
    public void bitacora(View v){
        Intent intent = new Intent(MenuActivity.this,Bitacora.class);
        startActivity(intent);
    }

    public void sd(View v){
        Intent intent = new Intent(MenuActivity.this,SdActivity.class);
        startActivity(intent);
    }

    public void basededatos1(View v){
        Intent intent = new Intent(MenuActivity.this,BaeseDeDatos1.class);
        startActivity(intent);
    }

    public void cerrar(View v){
        finish();
    }
}

