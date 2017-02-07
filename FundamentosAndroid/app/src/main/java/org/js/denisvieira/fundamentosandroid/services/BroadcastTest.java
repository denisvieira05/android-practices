package org.js.denisvieira.fundamentosandroid.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import org.js.denisvieira.fundamentosandroid.services.ServiceTest;

/**
 * Created by denisvieira on 07/02/17.
 */

public class BroadcastTest extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("Script", "onReceive()");
        intent = new Intent(context,ServiceTest.class);
        context.startService(intent);
    }

}