package com.freedom.land.dispatcher;

import android.Manifest;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PermissionManager.requestPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE, 1, new PermissionManager.PermissionCallback() {
            @Override
            public void onAccepted() {
                //dev
            }

            @Override
            public void showExplainUI(Activity activity) {

            }

            @Override
            public void onDenied() {

            }
        });

        PermissionManager.requestPermission(this, Manifest.permission.ACCESS_FINE_LOCATION, 1, new PermissionManager.PermissionCallback() {
            @Override
            public void onAccepted() {

            }

            @Override
            public void showExplainUI(Activity activity) {

            }

            @Override
            public void onDenied() {

            }
        });

        PermissionManager.requestPermission(this, Manifest.permission.CAMERA, 1, new PermissionManager.PermissionCallback() {
            @Override
            public void onAccepted() {

            }

            @Override
            public void showExplainUI(Activity activity) {

            }

            @Override
            public void onDenied() {

            }
        });
    }
}
