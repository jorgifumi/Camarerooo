package es.jorgifumi.camarerooo.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import es.jorgifumi.camarerooo.R;
import es.jorgifumi.camarerooo.fragment.AddDishDialogFragment;
import es.jorgifumi.camarerooo.model.Dish;
import es.jorgifumi.camarerooo.model.Menu;

public class MenuActivity extends AppCompatActivity implements AddDishDialogFragment.OnAddDishDialogFragmentListener {
    private static final String TAG = "MenuActivity";
    public static final String EXTRA_CURRENT_ORDERS = "es.jorgifumi.camarerooo.EXTRA_CURRENT_ORDERS";

    private URL sUrl;
    private Menu mOrders;

    private Menu mMenu;
    private ArrayAdapter<Dish> mMenuArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            sUrl = new URL("http://www.mocky.io/v2/57127a92110000aa1c0e06c1");
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }

        mMenu = new Menu();
        mOrders = (Menu) getIntent().getSerializableExtra(EXTRA_CURRENT_ORDERS);
        
        downloadMenu();

        GridView gridMenu = (GridView) findViewById(R.id.gridView);

        mMenuArrayAdapter = new ArrayAdapter<Dish>(
                this,
                android.R.layout.simple_list_item_1,
                mMenu.getDishes()
        );

        gridMenu.setAdapter(mMenuArrayAdapter);

        gridMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Dish selectedDish = mMenu.getDishes().get(position);
                viewDish(selectedDish, position);
            }
        });
    }

    private void viewDish(Dish selectedDish, int position) {
      
        Bundle arguments = new Bundle();
        arguments.putSerializable("Dish", selectedDish);

        AddDishDialogFragment.newInstance(arguments).show(getSupportFragmentManager(), "NewDish");
    }

    private void downloadMenu() {
        AsyncTask<URL, Integer, String> menuDownloader = new AsyncTask<URL, Integer, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(URL... params) {

                URL url = params[0];
                InputStream input = null;
                String jsonString = new String("");

                try {
                    // Download data
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.connect();
                    int responseLength = connection.getContentLength();
                    byte data[] = new byte[1024];
                    long currentBytes = 0;
                    int downloadedBytes;
                    input = connection.getInputStream();
                    StringBuilder sb = new StringBuilder();
                    while ((downloadedBytes = input.read(data)) != -1) {
                        sb.append(new String(data, 0, downloadedBytes));
                        if (responseLength > 0) {
                            currentBytes += downloadedBytes;
                            // TODO: Update progress bar
                        }
                    }

                    Log.v(TAG, "JSON Downloaded");
                    jsonString = sb.toString();


                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                return jsonString;
            }

            @Override
            protected void onPostExecute(String jsonString) {
                super.onPostExecute(jsonString);

                try {
                    // Process JSON
                    JSONObject jsonRoot = new JSONObject(jsonString);
                    JSONArray dishes = jsonRoot.getJSONArray("dishes");

                    for (int i = 0; i < dishes.length(); i++) {
                        JSONObject dish = dishes.getJSONObject(i);
                        String name = dish.getString("name");
                        String description = dish.getString("description");
                        Float price = Float.valueOf(dish.getString("price"));
                        ArrayList<String> allergens = new ArrayList<>();
                        JSONArray jsonAllergens = dish.getJSONArray("allergens");
                        for (int j = 0; j < jsonAllergens.length(); j++) {
                            allergens.add(jsonAllergens.getString(j));
                        }

                        String imageURL = dish.getString("image");

                        mMenu.addDish(new Dish(name, description, price, allergens, imageURL));
                        mMenuArrayAdapter.notifyDataSetChanged();
                    }

                    Log.v(TAG, "JSON Parsed");

                } catch (JSONException ex) {
                    ex.printStackTrace();
                }

            }
        };

        menuDownloader.execute(sUrl);


    }

    @Override
    public void onAddDishButtonClick(Dish newDish) {
        mOrders.addDish(newDish);
        Intent returnIntent = new Intent();
        if (mOrders != null) {

            returnIntent.putExtra(TableActivity.EXTRA_CURRENT_TABLE, mOrders);
            setResult(RESULT_OK, returnIntent);

        } else {
            setResult(RESULT_CANCELED);
        }
        Snackbar.make(findViewById(android.R.id.content), newDish.getName(), Snackbar.LENGTH_LONG).show();

    }

    @Override
    public void onCancelButtonClick() {

    }
}
