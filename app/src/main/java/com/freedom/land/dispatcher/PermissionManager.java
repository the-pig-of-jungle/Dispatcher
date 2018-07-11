package com.freedom.land.dispatcher;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.util.SparseArray;

public class PermissionManager {

    private static SparseArray<PermissionCallback> sRequestedPermissions;

    public static void requestPermission(Activity activity, String permission, int requestId, PermissionCallback callback) {

        int result = ActivityCompat.checkSelfPermission(activity, permission);
        if (result == PackageManager.PERMISSION_GRANTED) {
            callback.onAccepted();
        } else {
            getRequestedPermissions().put(requestId, callback);
            boolean needExplain = ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
            if (true) {
                callback.showExplainUI(activity);
            }
            ActivityCompat.requestPermissions(activity, new String[]{permission}, requestId);
        }
    }


    public static void receiveRequestResult(int requestId, String permission, int result) {
        PermissionCallback callback = getRequestedPermissions().get(requestId, null);


        if (callback != null) {
            getRequestedPermissions().delete(requestId);
            if (result == PackageManager.PERMISSION_GRANTED) {
                callback.onAccepted();
            } else {
                callback.onDenied();
            }
        }
    }

    public interface PermissionCallback {

        void onAccepted();

        void showExplainUI(Activity activity);

        void onDenied();

    }


    private static SparseArray<PermissionCallback> getRequestedPermissions() {
        if (sRequestedPermissions == null) {
            sRequestedPermissions = new SparseArray<>();
        }
        return sRequestedPermissions;
    }
}
