package es.jorgifumi.camarerooo.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import es.jorgifumi.camarerooo.R;
import es.jorgifumi.camarerooo.model.Table;
import es.jorgifumi.camarerooo.model.Tables;

public class TablesActivity extends AppCompatActivity {
    private static final String TAG = "TablesActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);

        ListView tablesList = (ListView) findViewById(R.id.list_tables);

        final Tables tables = new Tables();

        ArrayAdapter<Table> adapter = new ArrayAdapter<Table>(
                this,
                android.R.layout.simple_list_item_1,
                tables.getTables()
        );

        tablesList.setAdapter(adapter);

        FloatingActionButton addTable = (FloatingActionButton) findViewById(R.id.button_add);
        addTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "Botón añadir pulsado");
            }
        });

    }
}
