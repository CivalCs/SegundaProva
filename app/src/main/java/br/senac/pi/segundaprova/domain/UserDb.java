package br.senac.pi.segundaprova.domain;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aluno on 20/11/2015.
 */
public class UserDb extends SQLiteOpenHelper {
    private static final String TAG = "sql";
    private static final String BANCO_DADOS = "usuarios.sqlite";
    private static int VERSAO = 1;

    public UserDb(Context context){
        super( context, BANCO_DADOS, null, VERSAO);
    }


    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE usuario (_id INTEGER PRIMARY KEY, nome TEXT, pass VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){}

    public int delete(User usuario){
        SQLiteDatabase db = getWritableDatabase();
        try{
            int count = db.delete("usuario","_id=?",new String[]{String.valueOf(usuario.getId())});
            Log.i(TAG, "Deletou [" + count + "] registro.");
            return count;
        }finally {
            db.close();
        }
    }

    public List<User> findAll(){
        SQLiteDatabase db = getWritableDatabase();
        try{
            Cursor cursor = db.query("usuario", null, null, null, null, null, null, null);
            return toList(cursor);
        }finally {
            db.close();
        }
    }

    public List<User> toList(Cursor cursor){
        List<User> usuario = new ArrayList<User>();
        if (cursor.moveToFirst()){
            do {
                User user = new User();
                usuario.add(user);

                user.setId(cursor.getLong(cursor.getColumnIndex("_id")));
                user.setUsuario(cursor.getString(cursor.getColumnIndex("nome")));
                user.setPass(cursor.getString(cursor.getColumnIndex("pass")));

            }while (cursor.moveToNext());
        }
        return usuario;
    }

}
