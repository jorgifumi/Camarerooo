package es.jorgifumi.camarerooo.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import es.jorgifumi.camarerooo.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

            // Process JSON

            JSONObject jsonRoot = new JSONObject(sb.toString());
            

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
