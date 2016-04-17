package es.jorgifumi.camarerooo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import es.jorgifumi.camarerooo.R;
import es.jorgifumi.camarerooo.model.Dish;
import es.jorgifumi.camarerooo.model.Menu;

public class MenuActivity extends AppCompatActivity {
    private static final String TAG = "MenuActivity";

    private Menu mMenu;
    private ArrayAdapter<Dish> mMenuArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        mMenu = new Menu();
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
        Intent intent = new Intent(this, DishActivity.class);
        intent.putExtra(DishActivity.EXTRA_CURRENT_DISH, selectedDish);
        startActivity(intent);
        finish();
    }

    private void downloadMenu() {
        URL url = null;
        InputStream input = null;

        try {
            // Download data
            url = new URL("http://www.mocky.io/v2/57127a92110000aa1c0e06c1");
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

            // Process JSON
            JSONObject jsonRoot = new JSONObject(sb.toString());
            JSONArray dishes = jsonRoot.getJSONArray("dishes");

            for (int i=0; i<dishes.length(); i++) {
                JSONObject dish = dishes.getJSONObject(i);
                String name = dish.getString("name");
                String description = dish.getString("description");
                Float price = Float.valueOf(dish.getString("price"));
                ArrayList<String> allergens = new ArrayList<>();
                JSONArray jsonAllergens = dish.getJSONArray("allergens");
                for (int j=0;j<jsonAllergens.length();j++) {
                    allergens.add(jsonAllergens.getString(j));
                }

                String imageURL = dish.getString("image");

                mMenu.addDish(new Dish(name, description, price, allergens, imageURL));
            }

            Log.v(TAG, "JSON Parsed");


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
