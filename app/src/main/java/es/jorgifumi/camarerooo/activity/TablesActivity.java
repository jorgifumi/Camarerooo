package es.jorgifumi.camarerooo.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import es.jorgifumi.camarerooo.R;
import es.jorgifumi.camarerooo.fragment.AddTableDialog;
import es.jorgifumi.camarerooo.model.Table;
import es.jorgifumi.camarerooo.model.Tables;

public class TablesActivity extends AppCompatActivity implements AddTableDialog.OnAddTableDialogListener {
    private static final String TAG = "TablesActivity";
    public static final String EXTRA_TABLE_UPDATED = "es.jorgifumi.camarerooo.EXTRA_TABLE_UPDATED";
    public static int VIEW_TABLE = 1;

    private Tables mTables;
    private ArrayAdapter<Table> mTableArrayAdapter;
    private int mSelectedTableIdx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);

        mTables = new Tables();

        FloatingActionButton addTable = (FloatingActionButton) findViewById(R.id.button_add);
        addTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTable();
            }
        });

    }

    private void addTable() {
        new AddTableDialog().show(getSupportFragmentManager(), "AddTableDialog");
    }

    private void viewTable(Table table, int position) {
        Intent intent = new Intent(this, TableActivity.class);
        intent.putExtra(TableActivity.EXTRA_CURRENT_TABLE, table);
        mSelectedTableIdx = position;
        startActivityForResult(intent, VIEW_TABLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ListView tablesList = (ListView) findViewById(R.id.list_tables);

        mTableArrayAdapter = new ArrayAdapter<Table>(
                this,
                android.R.layout.simple_list_item_1,
                mTables.getTables()
        );

        tablesList.setAdapter(mTableArrayAdapter);

        tablesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Table selectedTable = mTables.getTables().get(position);
                viewTable(selectedTable, position);
            }
        });
    }

    @Override
    public void onAddTableButtonClick(Table newTable) {
        mTables.addTable(newTable);
        mTableArrayAdapter.notifyDataSetChanged();
        Snackbar.make(findViewById(android.R.id.content), "Mesa añadida", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onCancelButtonClick() {
        Snackbar.make(findViewById(android.R.id.content), "Añadir mesa cancelado", Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == VIEW_TABLE) {

            if (resultCode == RESULT_OK) {
                Table selectedTable = (Table) data.getSerializableExtra(EXTRA_TABLE_UPDATED);
                mTables.setTable(mSelectedTableIdx, selectedTable);
                Log.v(TAG, selectedTable.getOrders().toString());
            } else {

            }

        }
    }
}
