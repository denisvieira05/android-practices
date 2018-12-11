package org.js.denisvieira.fundamentosandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.js.denisvieira.fundamentosandroid.activities.HomeActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private CameraCaptureSession mSession;
    private CaptureRequest.Builder mBuilder;
    public Integer MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    public Integer PICK_CONTACT_REQUEST = 22;

    TextView contactNameTextView;
    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        Switch flashlightSwitch = (Switch) view.findViewById(R.id.flashlight_switch);

        flashlightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    turnOnFlashLight();
                    System.out.println("ligado");
                }else{
                    turnOffFlashLight();
                    System.out.println("desligado");
                }
            }
        });


        Button checkPermissionsButton = (Button) view.findViewById(R.id.check_permissions_button);
        contactNameTextView = (TextView) view.findViewById(R.id.contact_name_text_view);
        Button pickContactButton = (Button) view.findViewById(R.id.pick_contact_button);
        Button goToHomeActivity = (Button) view.findViewById(R.id.go_to_home_activity_button);
        goToHomeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), HomeActivity.class);
                intent.putExtra("MESSAGE", "MENSAGEM");
                startActivity(intent);
            }
        });
        pickContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickContact();
            }
        });
        checkPermissionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermissions();
            }
        });

        return view;
    }

    private void checkPermissions() {
        int PERMISSION_ALL = 5;
        String[] PERMISSIONS = {android.Manifest.permission.READ_PHONE_STATE, android.Manifest.permission.INTERNET
                , android.Manifest.permission.CAMERA};

//        if(!hasPermissions(getActivity(), PERMISSIONS)){
            ActivityCompat.requestPermissions(getActivity(), PERMISSIONS, PERMISSION_ALL);
//        }
    }

    private static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    private void pickContact() {
        // Create an intent to "pick" a contact, as defined by the content provider URI
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, PICK_CONTACT_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == PICK_CONTACT_REQUEST) {
            Cursor cursor = getActivity().getContentResolver().query(data.getData(),
                    new String[] {ContactsContract.Contacts.DISPLAY_NAME}, null, null, null);
            if (cursor.moveToFirst()) { // True if the cursor is not empty
                int columnIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                String name = cursor.getString(columnIndex);
                contactNameTextView.setText(name);
                // Do something with the selected contact's name...
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(getContext(), "Permissão para ler contatos concedida .", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getContext(), "Permissão para ler contatos recusada .", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    public void turnOnFlashLight() {
        try {
            CameraManager camManager = (CameraManager) getContext().getSystemService(Context.CAMERA_SERVICE);
            String cameraId = camManager.getCameraIdList()[0]; // Usually front camera is at 0 position.
            camManager.setTorchMode(cameraId, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void turnOffFlashLight() {
        try {
            CameraManager camManager = (CameraManager) getContext().getSystemService(Context.CAMERA_SERVICE);
            String cameraId = camManager.getCameraIdList()[0]; // Usually front camera is at 0 position.
            camManager.setTorchMode(cameraId, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
