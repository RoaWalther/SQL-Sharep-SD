package com.example.walther.mynuevaaplicacin;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class BaeseDeDatos1 extends AppCompatActivity {

    private EditText et_codigo, et_descripcion,et_precio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baese_de_datos1);

        et_codigo = (EditText)findViewById(R.id.codigo);
        et_descripcion = (EditText)findViewById(R.id.descripcion);
        et_precio = (EditText)findViewById(R.id.precio);
    }
    //metodo para dar de alta alos productos
    public void registro (View v){
        //creamos un objeto de la misma clase creada en SQLite 1 el nombre que le dimos a nuestra clase
        // y luegos el nombre que queramos al objeto
       AdminSqlite Administrar = new AdminSqlite(this,"administracion",null,1); // el 1 es nuestra primera version
       //abrimos nuestra base de datos en modo lectura y escritura
        //creamos un odjeto de la clase SQLiteDatabase y le ponemos como sea "base_datos"
        // luego va hacer = a nuestro objeto creado anteriormente seguido del metodo getWritableDatabase
        //que nos servira para abrir nuestra base de datos en modo lectura y escritura
        SQLiteDatabase base_datos = Administrar.getWritableDatabase();
        String codigo = et_codigo.getText().toString();
        String descrip = et_descripcion.getText().toString();
        String precio = et_precio.getText().toString();
        //creamos una condicional para validar que los campos sean llenados por el usuario 1 nombre dado 2 .isEmpty
        //ejemplo que sea diferente de vacio "!codigo.isEmpty"
        if (!codigo.isEmpty() && !descrip.isEmpty()&& !precio.isEmpty()){
            // creamos un objeto de la clase COntentValues
            ContentValues registro = new ContentValues();
            //luego colocamos nuestro objeto + el metodo ".put" enter (el nombre de referencia)
            registro.put("codigo",codigo);
            registro.put("descripcion",descrip);
            registro.put("precio",precio);
            // luego que tenemos los datos los insertamos en la tabla creada en nuestra clase la cual se llama articulos
            //seguido el nombre puesto en mi caso "base_datos" + el metodo ".insert" y le enviamos los parametros como se muestra acontinuacion
            base_datos.insert("articulos",null,registro);
            //finalmente cerramos nuestra bd
            base_datos.close();
            //luego la limpiamos
            et_codigo.setText("");
            et_descripcion.setText("");
            et_precio.setText("");

            Toast.makeText(this,"Inserción exitosa",Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(this,"Debes llenar los campos",Toast.LENGTH_SHORT).show();

        }

    }

    // metodo para consultar un articulo
    public void buscar(View view){
        AdminSqlite admin_buscar = new AdminSqlite(this,"administracion",null,1);
        SQLiteDatabase base_datos2 = admin_buscar.getWritableDatabase();
        String codigo = et_codigo.getText().toString();
        String descrip = et_descripcion.getText().toString();
        if (!codigo.isEmpty()){
            // creamos un objeto de una clase llamada cursor + su nombre "fila" = a nuestro objeto SQLiteDatabase "base_datos2"
            // + .rawQuery que para hacer consultas esto es de base de datos
            Cursor fila = base_datos2.rawQuery("select descripcion, precio from articulos where codigo =" + codigo,null);
            // creamos otra condicion junto con el metodo moveToFirst que me ayudara a mover mi objeto fila

            if (fila.moveToFirst()){
                et_descripcion.setText(fila.getString(0));
                et_precio.setText(fila.getString(1));
                base_datos2.close();
                Toast.makeText(this,"Articulo encontrado",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this,"No existe el articulo",Toast.LENGTH_SHORT).show();
                base_datos2.close();
            }
         // para mas tarde hacer lo mkismo con descricion y precio
        }else{
            Toast.makeText(this,"Debes introducir el codigo del articulo para su busqueda",Toast.LENGTH_SHORT).show();
        }
    }
    // metodo para eliminar un articulo
    public void eliminar(View view){
        //siempre se pasan 4 parametros "new AdminSqlite(this,"administracion",null,1);"
        AdminSqlite admin_eliminar = new AdminSqlite(this,"administracion",null,1);
        SQLiteDatabase base_datos3 = admin_eliminar.getWritableDatabase();
        String codigo = et_codigo.getText().toString();
        if (!codigo.isEmpty()){
            //primero declaramos una variable tipo entero por retornara un valor entero
            long cantidad = base_datos3.delete("articulos","codigo=" + codigo,null);
            base_datos3.close();
            et_codigo.setText("");
            et_descripcion.setText("");
            et_precio.setText("");
            //se coloca si nuestra variable es == 1 porque siempre en android retorna un 1
            if (cantidad==1){
                Toast.makeText(this,"Articulo eliminado exitosamente",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this,"El articulo no existe",Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this,"Debes introducir el codigo del articulo para su eliminación",Toast.LENGTH_SHORT).show();
        }
    }
    //metodo para modificar mi base de datos
    public void modificar(View view){
        //nunca olvidar pasar los 4 parametros
        AdminSqlite admin_modificar = new AdminSqlite(this,"administracion",null,1);
        SQLiteDatabase base_datos4 = admin_modificar.getWritableDatabase();
        String codigo = et_codigo.getText().toString();
        String descrip = et_descripcion.getText().toString();
        String precio = et_precio.getText().toString();

        if (!codigo.isEmpty()&& !descrip.isEmpty()&& !precio.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("codigo",codigo);
            registro.put("descripcion",descrip);
            registro.put("precio",precio);
            // update para actualizar
            long cantida = base_datos4.update("articulos",registro,"codigo=" + codigo,null);
            base_datos4.close();
            //se coloca si nuestra variable es == 1 porque siempre en android retorna un 1
            if (cantida==1){
                Toast.makeText(this,"Articulo modificado exitosamente",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this,"El articulo no existe",Toast.LENGTH_SHORT).show();
            }

        }else {
            Toast.makeText(this,"Debes llenar todos los campos",Toast.LENGTH_SHORT).show();
        }

    }

    public void regresar(View v){
        Intent intent = new Intent(BaeseDeDatos1.this,MenuActivity.class);
        startActivity(intent);
        finish();
    }
}
