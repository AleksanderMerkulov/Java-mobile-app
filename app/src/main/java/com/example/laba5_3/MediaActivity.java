package com.example.laba5_3;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.io.File;

public class MediaActivity extends AppCompatActivity {

    private RecyclerView recyclerViewPhotos;
    private PhotoAdapter photoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);

        recyclerViewPhotos = findViewById(R.id.recyclerViewPhotos);
        recyclerViewPhotos.setLayoutManager(new GridLayoutManager(this, 3));

        // Загрузка фотографий
        File directory = getExternalFilesDir(null);
        if (directory != null) {
            File[] files = directory.listFiles((dir, name) -> name.endsWith(".jpg"));
            if (files != null) {
                photoAdapter = new PhotoAdapter(this, files);
                recyclerViewPhotos.setAdapter(photoAdapter);
            }
        }
    }

    static class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {

        private final Context context;
        private final File[] files;

        public PhotoAdapter(Context context, File[] files) {
            this.context = context;
            this.files = files;
        }

        @NonNull
        @Override
        public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_photo, parent, false);
            return new PhotoViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
            File file = files[position];
            holder.imageView.setImageURI(android.net.Uri.fromFile(file));
        }

        @Override
        public int getItemCount() {
            return files.length;
        }

        static class PhotoViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;

            public PhotoViewHolder(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.imageViewPhoto);
            }
        }
    }
}