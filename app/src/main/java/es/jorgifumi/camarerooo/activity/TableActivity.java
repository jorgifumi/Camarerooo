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
import es.jorgifumi.camarerooo.model.Menu;
import es.jorgifumi.camarerooo.model.Table;

public class TableActivity extends AppCompatActivity {
    private static final String TAG = "TableActivity";
    public static final String EXTRA_CURRENT_TABLE = "es.jorgifumi.camarerooo.EXTRA_CURRENT_TABLE";
    public static final int VIEW_MENU = 1;
    private Table mTable;
    private ArrayAdapter<Dish> mOrdersArrayAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTable = (Table) getIntent().getSerializableExtra(EXTRA_CURRENT_TABLE);

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

    private void addDish() {
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra(MenuActivity.EXTRA_CURRENT_ORDERS, mTable.getOrders());
        startActivityForResult(intent, VIEW_MENU);
    }

    private void viewTotal() {
        Snackbar.make(findViewById(android.R.id.content), "View total", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ListView ordersList = (ListView) findViewById(R.id.list_orders);

        mOrdersArrayAdapter = new ArrayAdapter<Dish>(
                this,
                android.R.layout.simple_list_item_1,
                mTable.getOrders().getDishes()
        );

        ordersList.setAdapter(mOrdersArrayAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == VIEW_MENU) {
            if (resultCode == RESULT_OK) {
                Menu updatedOrders = (Menu) data.getSerializableExtra(EXTRA_CURRENT_TABLE);
                mTable.setOrders(updatedOrders);
                mOrdersArrayAdapter.notifyDataSetChanged();
                Intent returnIntent = new Intent();
                if (mTable != null) {

                    returnIntent.putExtra(TablesActivity.EXTRA_TABLE_UPDATED, mTable);
                    setResult(RESULT_OK, returnIntent);

                } else {
                    setResult(RESULT_CANCELED);
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.v(TAG, "On Destroy");
    }
}
