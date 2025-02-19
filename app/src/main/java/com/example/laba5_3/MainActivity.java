package com.example.laba5_3;

import static android.provider.Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.AspectRatio;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    final static int APP_STORAGE_ACCESS_REQUEST_CODE = 501; // Any value

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = new Intent(ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION, Uri.parse("package:" + "com.example.mobil_lab5"));
        startActivityForResult(intent, APP_STORAGE_ACCESS_REQUEST_CODE);


        View.OnClickListener btnClick=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("myLogs",v.getId()+"");
                Click(v.getId());
            }
        };
        ((Button)findViewById(R.id.bMedia)).setOnClickListener(btnClick);
        ((Button)findViewById(R.id.bCamera)).setOnClickListener(btnClick);
        ((Button)findViewById(R.id.bGallery)).setOnClickListener(btnClick);
        ((Button)findViewById(R.id.bGeo)).setOnClickListener(btnClick);
    }

    protected void Click(int view){
        Intent intent=null;
        Log.d("myLogs",view+"");

        if (view == R.id.bMedia) {
//            intent = new Intent(this, MediaActivity.class);
        } else if (view == R.id.bGallery) {
//            intent = new Intent(this, GalleryActivity.class);
        } else if (view == R.id.bCamera) {
            intent = new Intent(this, CameraActivity.class);
        } else if (view == R.id.bGeo) {
            intent = new Intent(this, GeoActivity.class);
        }
        if(intent!=null){
            Log.d("myLogs","Интент = "+intent.toString());
            startActivity(intent);
        }
    }
}