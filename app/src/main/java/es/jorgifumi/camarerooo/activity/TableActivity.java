package es.jorgifumi.camarerooo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import es.jorgifumi.camarerooo.R;
import es.jorgifumi.camarerooo.model.Dish;
import es.jorgifumi.camarerooo.model.Table;

public class TableActivity extends AppCompatActivity {
    private static final String TAG = "TableActivity";

    private Table mTable;
    private ArrayAdapter<Dish> mOrdersArrayAdapter;
    private int mPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView ordersList = (ListView) findViewById(R.id.list_orders);

        mTable = new Table("Prueba", 6);

        mOrdersArrayAdapter = new ArrayAdapter<Dish>(
                this,
                android.R.layout.simple_list_item_1,
                mTable.getOrders().getDishes()
        );

        ordersList.setAdapter(mOrdersArrayAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDish();
            }
        });

        FloatingActionButton totalButton = (FloatingActionButton) findViewById(R.id.button_total);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewTotal();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mTable = (Table) data.getSerializableExtra("table_selected");
        mPosition = data.getIntExtra("position", 0);
        mOrdersArrayAdapter.notifyDataSetChanged();
        Log.v(TAG, "Tabla recibida");

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent returnIntent = new Intent();
        if (mTable != null) {

            returnIntent.putExtra("table_updated", mTable);
            returnIntent.putExtra("position", mPosition);
            setResult(RESULT_OK, returnIntent);

        } else {
            setResult(RESULT_CANCELED);
        }
        finish();
    }

    private void addDish() {
        Snackbar.make(findViewById(android.R.id.content), "Add dish", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    private void viewTotal() {
        Snackbar.make(findViewById(android.R.id.content), "View total", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
