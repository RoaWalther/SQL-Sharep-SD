package com.example.walther.mynuevaaplicacin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ActivityWeb extends AppCompatActivity {
    WebView  web1;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        web1=(WebView)findViewById(R.id.webVi);
        // getInten me sirve para recuperar parametros o datos que vienen desde otra activity mas el metodo
        // getStringExtra con el nombre dado "sitioWeb"   (primero recuperamos parametros)
        url= getIntent().getStringExtra("sitioWeb");
        // metodo setWebViewClient me permite entrar desde mi kisma aplicación sin recurrir a otra aplicación
        //no permitimos navegar fuera de nuestra aplicación
        web1.setWebViewClient(new WebViewClient());
        //el metodo loadUrl le indicamos que nos habra el sitio solicitado
        web1.loadUrl("http://" + url);
    }
    public void cerrar (View v){
        finish();
    }
}
