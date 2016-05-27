package org.js.denisvieira.criandonotificacoes;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int NOTIFICACAO_SIMPLES = 1;
    private static final int NOTIFICACAO_COMPLETA = 2;
    private static final int NOTIFICACAO_BIG = 3;

    EditText mEdtTexto;
    MeuReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEdtTexto = (EditText) findViewById(R.id.editText);

        mReceiver = new MeuReceiver();
        registerReceiver(mReceiver, new IntentFilter(NotificationUtils.ACAO_DELETE));
        registerReceiver(mReceiver, new IntentFilter(NotificationUtils.ACAO_NOTIFICACAO));
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    public void criarNotificacaoSimples(View v){
        NotificationUtils.criarNotificacaoSimples(this,mEdtTexto.getText().toString(),NOTIFICACAO_SIMPLES);
    }

    public void criarNotificacaoCompleta(View v){
        NotificationUtils.criarNotificacaoCompleta(this,mEdtTexto.getText().toString(),NOTIFICACAO_COMPLETA);
    }

    public void criarNotificacaoBig(View v){
        NotificationUtils.criarNotificacaoBig(this,NOTIFICACAO_BIG);
    }

    class MeuReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent){
            Toast.makeText(MainActivity.this,intent.getAction(),Toast.LENGTH_SHORT).show();
        }
    }
}
