package org.js.denisvieira.exampleeventbus;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.js.denisvieira.exampleeventbus.eventbus.MessageEB;
import org.js.denisvieira.exampleeventbus.fragments.FragmentBottom;
import org.js.denisvieira.exampleeventbus.fragments.FragmentTop;
import org.js.denisvieira.exampleeventbus.services.ServiceTest;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // EventBus register
        EventBus.getDefault().register(MainActivity.this);

        // Calling Service
        Intent intent = new Intent(MainActivity.this, ServiceTest.class);
        startService(intent);
        Button callSecondActivityButton = (Button) findViewById(R.id.callSecondActivityButton);

        callSecondActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);

            }
        });

        // Fragments
        FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        FragmentTop mFragmentTop = new FragmentTop();
        FragmentBottom mFragmentBottom = new FragmentBottom();

        mFragmentTransaction.replace(R.id.llContainerFragmentTop, mFragmentTop);
        mFragmentTransaction.replace(R.id.llContainerFragmentBottom, mFragmentBottom);
        mFragmentTransaction.commit();

    }


    @Override
    public void onDestroy(){
        super.onDestroy();

        // Destroying Service
        Intent intent = new Intent(MainActivity.this, ServiceTest.class);
        stopService(intent);

        // EventBus unregister
        EventBus.getDefault().unregister(MainActivity.this);
    }


    // Listeners
    public void askAboutPerson(View view){
        MessageEB m = new MessageEB();
        m.setClassTester(ServiceTest.class+"");

        EventBus.getDefault().post(m);
    }


    @Subscribe
    public void onEventMainThread(MessageEB mMessageEB){
        if(!mMessageEB.getClassTester().equalsIgnoreCase(MainActivity.class+""))
            return;

        Log.i("LOG", "MainActivity.this.onEventMainThread()");

        if(mMessageEB.getList() != null){
            Toast.makeText(MainActivity.this,
                    "Name: "+mMessageEB.getList().get(0).getName()+"\nJob: "+mMessageEB.getList().get(0).getJob(),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Subscribe
    public void onEvent(MessageEB mMessageEB){
        if(!mMessageEB.getClassTester().equalsIgnoreCase(MainActivity.class+""))
            return;

        Log.i("LOG", "MainActivity.this.onEvent()");

        if(mMessageEB.getNumber() >= 0){
            Log.i("LOG", "MainActivity.this.onEvent().number: "+mMessageEB.getNumber());
        }

        if(mMessageEB.getText() != null){
            Log.i("LOG", "MainActivity.this.onEvent().text: "+mMessageEB.getText());
        }
    }
}
