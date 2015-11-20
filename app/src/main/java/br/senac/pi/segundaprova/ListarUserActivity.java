package br.senac.pi.segundaprova;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import br.senac.pi.segundaprova.domain.UserDb;

public class ListarUserActivity extends AppCompatActivity {
    private CursorAdapter dataSource;
    private SQLiteDatabase database;
    private static final String campos[] = {"nome","pass","_id"};
    ListView listView;
    UserDb userDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_user);
        listView = (ListView) findViewById(R.id.listView);
        userDb = new UserDb(this);
        database = userDb.getWritableDatabase();
        findViewById(R.id.btnListar).setOnClickListener(listar());

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
    private View.OnClickListener listar(){
        return new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Cursor User = database.query("usuarios", campos, null, null, null,null,null);
                if (User.getCount() > 0){
                    dataSource = new SimpleCursorAdapter(ListarUserActivity.this,R.layout.row, User, campos,new int[] {R.id.edtUser, R.id.edtPass});
                    listView.setAdapter(dataSource);
                }else{
                    Toast.makeText(ListarUserActivity.this, getString(R.string.no), Toast.LENGTH_LONG).show();
                }
            }
        };

    }

}
