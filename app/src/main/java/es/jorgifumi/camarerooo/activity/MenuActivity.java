package es.jorgifumi.camarerooo.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Set;

import es.jorgifumi.camarerooo.R;
import es.jorgifumi.camarerooo.model.Allergen;
import es.jorgifumi.camarerooo.model.Dish;
import es.jorgifumi.camarerooo.model.Menu;

public class MenuActivity extends AppCompatActivity {
    private static final String TAG = "MenuActivity";

    private Menu mMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        downloadMenu();

        //GridView gridMenu = new GridView(this, )
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

            for (int i=1; i<dishes.length(); i++) {
                JSONObject dish = dishes.getJSONObject(i);
                String name = dish.getString("name");
                String description = dish.getString("description");
                Float price = Float.valueOf(dish.getString("price"));
                Set<Allergen> allergens = (Set<Allergen>) dish.getJSONArray("allergens");
                String imageURL = dish.getString("image");

                mMenu.addDish(new Dish(name, description, price, allergens,imageURL));
                Log.v(TAG, name);
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
