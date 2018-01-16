package alexis.boulet.mytodolist;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.util.ArraySet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Set;

/**
 * Created by aboulet on 16/01/2018.
 */

public class AddActivity extends Activity implements View.OnClickListener{

    private Button btnValider;
    private Button btnEmpty;
    private Button btIntent;
    private EditText edtTodo;
    private Set<String> stTodoList;
    private static final String PREFS_NAME = "PreferencesFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_layout);

        btnValider = (Button) findViewById(R.id.btnValider);
        btnEmpty = (Button) findViewById(R.id.btnEmpty);
        btIntent = (Button) findViewById(R.id.btIntent);
        edtTodo = (EditText) findViewById(R.id.edtTodolList);

        btnValider.setOnClickListener(this);
        btnEmpty.setOnClickListener(this);
        btIntent.setOnClickListener(this);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        stTodoList = settings.getStringSet("Test", new ArraySet<String>());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btnValider.getId())
            this.validation();
        else if (v.getId() == btnEmpty.getId())
            this.vidage();
        else
            this.intent();
    }

    private void validation()
    {
        if (edtTodo.getText().length() > 0)
        {
            stTodoList.add(edtTodo.getText().toString());
            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putStringSet("Test", stTodoList);
            editor.commit();
        }
        edtTodo.setText("");
    }

    private void intent()
    {
        Intent myIntent = new Intent(getApplicationContext(), TodoActivity.class);
        startActivity(myIntent);
    }

    private void vidage()
    {
        edtTodo.setText("");
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putStringSet("Test", null);
        editor.commit();
        intent();
    }

    private void activiteTerminee(boolean resultat, boolean etatHyperactif){
        if (resultat){
            Intent fermetureMonActivite = new Intent();
            if (etatHyperactif){
                fermetureMonActivite.putExtra("etat","hyperactif");
            }else{
                fermetureMonActivite.putExtra("etat","calme");
            }
            setResult(RESULT_OK,fermetureMonActivite);
        }else{
            setResult(RESULT_CANCELED);
        }
        finish();
    }
}
