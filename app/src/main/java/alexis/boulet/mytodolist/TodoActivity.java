package alexis.boulet.mytodolist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.util.ArraySet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Set;

public class TodoActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ListView lvTodoList;
    private Button btAddTodo;
    private Set<String> stTodoList;
    private static final int MON_ACTIVITE_REQUEST_CODE = 1;
    private static final String PREFS_NAME = "PreferencesFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_layout);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        stTodoList = settings.getStringSet("Test", new ArraySet<String>());

        btAddTodo = (Button) findViewById(R.id.btAdd);
        lvTodoList = (ListView) findViewById(R.id.lvTodoList);
        lvTodoList.setAdapter(new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,stTodoList.toArray()));

        btAddTodo.setOnClickListener(this);
        lvTodoList.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // go intent
        startMonActivite();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // some stuff
    }


    private void startMonActivite(){
        Intent myIntent = new Intent(getApplicationContext(), AddActivity.class);
        startActivityForResult(myIntent, MON_ACTIVITE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int
            resultCode, Intent data) {
        if (requestCode == MON_ACTIVITE_REQUEST_CODE)
        {
            if (resultCode==RESULT_OK)
            {
                String s = data.getStringExtra("etat");
            }
        }
    }
}
