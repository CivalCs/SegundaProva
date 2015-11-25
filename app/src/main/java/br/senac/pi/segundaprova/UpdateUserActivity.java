package br.senac.pi.segundaprova;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import br.senac.pi.segundaprova.domain.UserDb;

public class UpdateUserActivity extends AppCompatActivity {
    private UserDb userDb;
    private SQLiteDatabase db;
    private EditText edtUpNome, edtUpPass;
    private TextView txtIdUser;
    private String id;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        id = getIntent().getStringExtra("id");
        userDb = new UserDb(this);
        txtIdUser = (TextView) findViewById(R.id.txtIdUser);
        edtUpNome = (EditText) findViewById(R.id.edtUpNome);
        edtUpPass = (EditText) findViewById(R.id.edtUpPass);
        cursor = loadUser(Integer.parseInt(id));
        txtIdUser.setText(cursor.getString(cursor.getColumnIndexOrThrow("_id")));
        edtUpNome.setText(cursor.getString(cursor.getColumnIndexOrThrow("nome")));
        edtUpPass.setText(cursor.getString(cursor.getColumnIndexOrThrow("pass")));
        findViewById(R.id.btnUpUser).setOnClickListener(updateUser());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private Cursor loadUser(int id){
        db = userDb.getWritableDatabase();
        String[] campos = {"_id","nome","pass"};
        String whereArgs = String.valueOf(id);
        cursor = db.query("usuario", campos, whereArgs, null, null,null,null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
    private View.OnClickListener updateUser(){
        return new View.OnClickListener(){
            @Override
            public void onClick(View v){
                db = userDb.getWritableDatabase();
                ContentValues values = new ContentValues();
                String whereArgs = id;
                Log.i("curso", "ID capturado:" + id);
                values.put("nome", edtUpNome.getText().toString());
                values.put("pass", edtUpPass.getText().toString());
                db.update("usuario", values, "_id = "+ whereArgs, null);
                db.close();
            }
        };
    }

}
