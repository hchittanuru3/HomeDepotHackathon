package com.example.hemanthc98.homedepothackathon;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;


public class BarcodeScanning extends AppCompatActivity {

    SurfaceView camera;
    Button button;
    public static final String ALLOW_KEY = "ALLOWED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);
        button = (Button) findViewById(R.id.scan);
        camera = (SurfaceView) findViewById(R.id.surfaceView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCameraSource();
            }
        });
    }






    private void getCameraSource() {
        BarcodeDetector bd = new BarcodeDetector.Builder(this).build();
        final CameraSource cameraSource = new CameraSource.Builder(this, bd)
                .setAutoFocusEnabled(true)
                .setRequestedPreviewSize(1600, 1024)
                .build();
        camera.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if (ContextCompat.checkSelfPermission(BarcodeScanning.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    System.out.println("yo");
                }
                try {
                    cameraSource.start(camera.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        bd.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();
                if (barcodes.size() > 0) {
                    // Intent intent = new Intent();
                    // intent.putExtra("barcode", barcodes.valueAt(0));
                    // setResult(CommonStatusCodes.SUCCESS, intent);
                    // finish();
                    System.out.println(barcodes.valueAt(0).rawValue);
                }
            }
        });
    }
}
