package org.js.denisvieira.fundamentosandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.js.denisvieira.fundamentosandroid.R;
import org.js.denisvieira.fundamentosandroid.services.ServiceTest;

/**
 * Created by denisvieira on 07/02/17.
 */

public class HomeActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button startServiceButton = (Button) findViewById(R.id.start_service_button);
        Button stopServiceButton =  (Button)findViewById(R.id.stop_service_button);

        startServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(view);
            }
        });

        stopServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(view);
            }
        });
    }

    public void startService(View v){

        Intent serviceIntent = new Intent(getApplicationContext(),ServiceTest.class);
        getApplicationContext().startService(serviceIntent);
    }
    public void stopService(View v){
        Intent serviceIntent = new Intent(getApplicationContext(),ServiceTest.class);
        getApplicationContext().stopService(serviceIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
