package proyecto.kotions.BDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BaseDatos extends SQLiteOpenHelper{

    public static final String BDNom = "Login.db";

    public BaseDatos(@Nullable Context context) {
        super(context,"Login.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table usuarios(usuario TEXT primary key, contrasenya TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop Table if exists usuarios");
    }

    public boolean insertarDatos(String usuario, String contrasenya){//Método que se encarga de la inserción de los datos en la base de datos
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("usuario",usuario);
        contentValues.put("contrasenya",contrasenya);
        long resultado = MyDB.insert("usuarios",null,contentValues);
        if(resultado == -1) return false;
        else
            return  true;
    }

    public boolean comprobarNombre(String usuario){//Método que consulta si el usuario existe en la base de datos
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * from usuarios where usuario = ?",new String [] {usuario});

        if(cursor.getCount()>0)
            return  true;
            else
                return false;
    }

    public boolean comprobarUsuarioContrasenya(String usuario, String contrasenya){//comprobar ambos
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from usuarios where usuario = ? and contrasenya = ?", new String[] {usuario,contrasenya});
        if(cursor.getCount()>0)
            return  true;
        else
            return false;
    }
}
