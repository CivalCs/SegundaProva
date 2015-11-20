package br.senac.pi.segundaprova.domain;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    /*
    private static final String TAG = "sql";
    //Nome do banco
    private static final String NOME_BANCO = "cursoandroid.sqlite";
    private static final int VERSAO_BANCO = 1;

    public CarrosDb(Context context){
        //context, nome do banco, factory, versão
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override*/
}
