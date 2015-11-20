package br.senac.pi.segundaprova;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import br.senac.pi.segundaprova.domain.UserDb;

public class SegundaProvaActivity extends AppCompatActivity {
    UserDb UserDb;
    SQLiteDatabase database;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_prova);
        //MyCode
        UserDb = new UserDb(this);

        findViewById(R.id.btnCadastra).setOnClickListener(cadastrarUsuario());
        findViewById(R.id.btnLista).setOnClickListener(listaUsuarios());

        //EndMyCode
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

    private View.OnClickListener cadastrarUsuario(){
        return new View.OnClickListener(){
            @Override
            public void onClick(View v){
                database = UserDb.getWritableDatabase();
                EditText edtUserName = (EditText) findViewById(R.id.edtUser);
                EditText edtPassUser = (EditText) findViewById(R.id.edtPass);
                String userName = edtUserName.getText().toString();
                String passUser = edtPassUser.getText().toString();
                ContentValues values = new ContentValues();
                values.put("usuario",userName);
                values.put("senha",passUser);
                long id = database.insert("usuario",null,values);
                if (id != 0 ){
                    Toast.makeText(getApplicationContext(),getString(R.string.confirm),Toast.LENGTH_LONG).show();
                    edtUserName.setText("");
                    edtPassUser.setText("");
                    edtUserName.requestFocus();
                }else{
                    Toast.makeText(getApplicationContext(),getString(R.string.erro),Toast.LENGTH_LONG).show();
                }
            }

        };
    }

    private View.OnClickListener listaUsuarios(){
        return  new View.OnClickListener(){
            @Override
            public  void  onClick(View v){
                Intent intent = new Intent(SegundaProvaActivity.this, ListarUserActivity.class);
                startActivity(intent);
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_segunda_prova, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
