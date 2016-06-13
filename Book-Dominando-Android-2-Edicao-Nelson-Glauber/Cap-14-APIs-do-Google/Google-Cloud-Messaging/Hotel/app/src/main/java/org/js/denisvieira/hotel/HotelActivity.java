package org.js.denisvieira.hotel;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import org.js.denisvieira.hotel.GCM.HotelIdListenerService;

/**
 * Created by denisvieira on 31/05/16.
 */
public class HotelActivity extends AppCompatActivity{
    private static final String TAG = "TAG";
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        iniciarGooglePLayServices();
    }

    private void iniciarGooglePLayServices(){
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int resultCode = api.isGooglePlayServicesAvailable(this);
        if(resultCode != ConnectionResult.SUCCESS){
            if(api.isUserResolvableError(resultCode)){
                Dialog dialog = api.getErrorDialog(this, resultCode,REQUEST_PLAY_SERVICES);
                dialog.setOnCancelListener(new DialogInterface.OnCancelListener(){
                    @Override
                    public void onCancel(DialogInterface dialogInterface){
                        finish();
                    }
                });
                dialog.show();
            }else{
                Toast.makeText(this, R.string.gcm_nao_suportado,Toast.LENGTH_SHORT).show();
                finish();
            }
        }else{
            Intent it = new Intent(this, HotelIdListenerService.class);
            it.putExtra(HotelIdListenerService.EXTRA_REGISTRAR,true);
            startService(it);
        }
    }

    @Override
    protected void onActivity Result(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == REQUEST_EDITAR_HOTEL && resultCode == RESULT_OK){
            mListFragment.limparBusca();
        }else if(requestCode == REQUEST_PLAY_SERVICES){
            iniciarGooglePLayServices();
        }
    }
}
