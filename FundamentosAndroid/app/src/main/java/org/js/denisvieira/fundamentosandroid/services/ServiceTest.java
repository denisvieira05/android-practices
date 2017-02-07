package org.js.denisvieira.fundamentosandroid.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by denisvieira on 07/02/17.
 */

public class ServiceTest extends Service{
    public List<Worker> threads = new ArrayList<>();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        Log.i("Script0","onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Log.i("Script0","onStartCommand");

        Worker worker = new Worker(startId);
        worker.start();
        threads.add(worker);

//        return (super.onStartCommand(intent,flags,startId));
//         return (START_NOT_STICKY); // não é pra restartar se acontecer algo
         return (START_STICKY); //  é pra restartar, mas não recupera a intent, intent vem nula
//         return (START_REDELIVER_INTENT); //  é pra restartar, com o valor da intent
    }

    @Override
    public void onDestroy(){
        super.onDestroy();

        for(Worker thread : threads){
            thread.ativo = false;
        }
    }

    class Worker extends Thread{
        public int count = 0;
        public int startId;
        public boolean ativo = true;

        public Worker(int startId){
            this.startId = startId;
        }

        public void run(){
            while (ativo && count < 100){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count++;
                Log.i("Script0","COUNT : "+count);
            }
            stopSelf(startId);
        }
    }
}
