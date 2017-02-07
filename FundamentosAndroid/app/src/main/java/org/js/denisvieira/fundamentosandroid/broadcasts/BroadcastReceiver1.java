package org.js.denisvieira.fundamentosandroid.broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by denisvieira on 07/02/17.
 */

public class BroadcastReceiver1 extends BroadcastReceiver {

    // tem 10s pra dar resposta pro android
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("BroadcastReceiver1");
    }
}
