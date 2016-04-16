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
    public static final String EXTRA_CURRENT_TABLE = "es.jorgifumi.camarerooo.EXTRA_CURRENT_TABLE";

    private Table mTable;
    private ArrayAdapter<Dish> mOrdersArrayAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView ordersList = (ListView) findViewById(R.id.list_orders);

        mTable = (Table) getIntent().getSerializableExtra(EXTRA_CURRENT_TABLE);
        Log.v(TAG, "Tabla recibida");

        mOrdersArrayAdapter = new ArrayAdapter<Dish>(
                this,
                android.R.layout.simple_list_item_1,
                mTable.getOrders().getDishes()
        );

        ordersList.setAdapter(mOrdersArrayAdapter);

        FloatingActionButton addDishButton = (FloatingActionButton) findViewById(R.id.button_add_dish);
        addDishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDish();
            }
        });

        FloatingActionButton totalButton = (FloatingActionButton) findViewById(R.id.button_total);
        totalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewTotal();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Intent returnIntent = new Intent();
        if (mTable != null) {

            returnIntent.putExtra(TablesActivity.EXTRA_TABLE_UPDATED, mTable);
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
