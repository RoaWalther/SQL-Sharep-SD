package com.example.walther.mynuevaaplicacin;
// primero importamos la clase sqlite
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// seguidamente agregamos extends SQLiteOpenHelper alt + enter y seleccionamos los metodos onCreate y onUpgrade
// despues otra ves alt + eneter para crear el constructor
//y seleccionamos la primera
public class AdminSqlite extends SQLiteOpenHelper{


    public AdminSqlite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
// colocamos el nombre que queramos en el metodo onCreate yo le puse BaseDeDatos
    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {

        // asi se crea una base de datos 1 el nombre que le pusimos + el ."execSQL"
        BaseDeDatos.execSQL("create table articulos(codigo int primary key, descripcion text, precio real)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
