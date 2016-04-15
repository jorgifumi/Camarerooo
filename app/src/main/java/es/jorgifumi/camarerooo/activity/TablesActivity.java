package es.jorgifumi.camarerooo.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import es.jorgifumi.camarerooo.R;
import es.jorgifumi.camarerooo.fragment.AddTableDialog;
import es.jorgifumi.camarerooo.model.Table;
import es.jorgifumi.camarerooo.model.Tables;

public class TablesActivity extends AppCompatActivity implements AddTableDialog.OnAddTableDialogListener {
    private static final String TAG = "TablesActivity";
    private Tables tables;
    private ArrayAdapter<Table> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);

        ListView tablesList = (ListView) findViewById(R.id.list_tables);

        tables = new Tables();

        adapter = new ArrayAdapter<Table>(
                this,
                android.R.layout.simple_list_item_1,
                tables.getTables()
        );

        tablesList.setAdapter(adapter);

        FloatingActionButton addTable = (FloatingActionButton) findViewById(R.id.button_add);
        addTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTable();
            }
        });

    }

    private void addTable() {
//        Log.v(TAG, "Botón añadir pulsado");
//        Snackbar.make(findViewById(android.R.id.content), "Boton añadir mesa pulsado", Snackbar.LENGTH_LONG).show();

        new AddTableDialog().show(getSupportFragmentManager(), "AddTableDialog");
    }

    @Override
    public void onAddTableButtonClick() {
        tables.addTable(new Table("Mesa prueba add", 6));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCancelButtonClick() {

    }
}
