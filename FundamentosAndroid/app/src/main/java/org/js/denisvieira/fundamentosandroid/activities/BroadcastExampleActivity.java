package org.js.denisvieira.fundamentosandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.js.denisvieira.fundamentosandroid.R;
import org.js.denisvieira.fundamentosandroid.broadcasts.BroadcastReceiver1;
import org.js.denisvieira.fundamentosandroid.services.ServiceTest;

/**
 * Created by denisvieira on 07/02/17.
 */

public class BroadcastExampleActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button broadReceiverXmlButton = (Button) findViewById(R.id.broad_receiver_xml);
        Button broadReceiverApiButton =  (Button)findViewById(R.id.broad_receiver_api);
        Button broadReceiverStartActivityButton =  (Button)findViewById(R.id.broad_receiver_start_activity);
        Button broadReceiverOutroProjeto =  (Button)findViewById(R.id.broad_receiver_outro_projeto);

        broadReceiverXmlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBroadcat(view);
            }
        });

        broadReceiverApiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBroadcat(view);
            }
        });

        broadReceiverStartActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBroadcat(view);
            }
        });

        broadReceiverOutroProjeto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBroadcat(view);
            }
        });
    }

    public void callBroadcat(View view){
        Button bt = (Button) view;
        switch (bt.getText().toString()){
            case "BROADCAST_RECEIVER_XML":
                Intent it = new Intent(getApplicationContext(),BroadcastReceiver1.class);
                sendBroadcast(it);
                break;
        }

    }
}
