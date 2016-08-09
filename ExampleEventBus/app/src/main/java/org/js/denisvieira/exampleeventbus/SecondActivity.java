package org.js.denisvieira.exampleeventbus;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.js.denisvieira.exampleeventbus.eventbus.MessageEB;

/**
 * Created by denisvieira on 08/08/16.
 */


public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        // EventBus register
        EventBus.getDefault().register(SecondActivity.this);
    }



    @Override
    public void onDestroy(){
        super.onDestroy();

        // EventBus register
        EventBus.getDefault().unregister(SecondActivity.this);
    }


    // Listeners
    @Subscribe
    public void onEventMainThread(MessageEB mMessageEB){

        Log.i("LOG", "SecondActivity.this.onEventMainThread()");

        if(mMessageEB.getList() != null){
            Toast.makeText(SecondActivity.this,
                    "Name: " + mMessageEB.getList().get(0).getName() + "\nJob: " + mMessageEB.getList().get(0).getJob(),
                    Toast.LENGTH_SHORT).show();
        }
    }
}

