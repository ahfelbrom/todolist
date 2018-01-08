package alexis.boulet.mytodolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TodoActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnValider;
    private Button btnEmpty;
    private TextView tvList;
    private EditText edtTodo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_layout);

        btnValider = (Button) findViewById(R.id.btnValider);
        btnEmpty = (Button) findViewById(R.id.btnEmpty);
        tvList = (TextView) findViewById(R.id.tvTodoList);
        edtTodo = (EditText) findViewById(R.id.edtTodolList);

        btnValider.setOnClickListener(this);
        btnEmpty.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btnValider.getId())
            this.validation();
        else
            this.vidage();
    }

    private void validation()
    {
        Toast.makeText(getApplicationContext(),edtTodo.getText(), Toast.LENGTH_SHORT).show();
        if (edtTodo.getText().length() > 0)
                tvList.append("- " + edtTodo.getText() + "\r\n");
        edtTodo.setText("");
    }

    private void vidage()
    {
        Toast.makeText(getApplicationContext(),"Vider la liste", Toast.LENGTH_SHORT).show();
        tvList.setText("");
        edtTodo.setText("");
    }
}
