package com.example.walther.mynuevaaplicacin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
private EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText)findViewById(R.id.webtex);
    }

    public void webviw ( View v){
        Intent intent= new Intent(MainActivity.this,ActivityWeb.class);
        intent.putExtra("sitioWeb",et.getText().toString());
        startActivity(intent);
    }

    public void primero (View v){
        Intent intent = new Intent(MainActivity.this,MenuActivity.class);
        startActivity(intent);
        finish();
    }

}
