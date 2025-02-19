package com.example.laba5_3;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

public class VideoActivity extends AppCompatActivity {

    private VideoView videoView;
    private Button buttonPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        // Инициализация элементов интерфейса
        videoView = findViewById(R.id.videoView);
        buttonPlay = findViewById(R.id.buttonPlay);

        // Установка пути к видео (из ресурсов)
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sample);

        // Настройка VideoView
        videoView.setVideoURI(videoUri);
        videoView.setMediaController(new MediaController(this));
//        videoView.requestFocus();


        // Обработка событий VideoView
        videoView.setOnPreparedListener(mp -> {
            Log.d("VideoActivity", "Video is ready to play");
            Toast.makeText(VideoActivity.this, "Video is ready to play", Toast.LENGTH_SHORT).show();
            videoView.start(); // Автоматически запускаем видео
        });

        videoView.setOnErrorListener((mp, what, extra) -> {
            Log.e("VideoActivity", "Error occurred while playing video: " + what + ", " + extra);
            Toast.makeText(VideoActivity.this, "Error occurred while playing video", Toast.LENGTH_SHORT).show();
            return true;
        });

        // Настройка кнопки Play
        buttonPlay.setOnClickListener(v -> {
            if (!videoView.isPlaying()) {
                videoView.start();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (videoView.isPlaying()) {
            videoView.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoView.stopPlayback();
    }
}