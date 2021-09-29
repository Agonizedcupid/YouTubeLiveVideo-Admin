package com.aariyan.youtubevideoadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.aariyan.youtubevideoadmin.Model.VideoModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private EditText videoName,videoDescription,videoId;
    private RadioButton allBtn,liveBtn;
    private Button postBtn,deleteBtn;

    private DatabaseReference videoRef;
    private String type = "All";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoRef = FirebaseDatabase.getInstance().getReference().child("Video");
        initUI();
    }

    private void initUI() {
        videoName = findViewById(R.id.videoTitle);
        videoDescription = findViewById(R.id.videoDescription);
        videoId = findViewById(R.id.videoId);
        allBtn = findViewById(R.id.allBtn);
        liveBtn = findViewById(R.id.liveBtn);
        postBtn = findViewById(R.id.postBtn);
        deleteBtn = findViewById(R.id.deleteBtn);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Delete.class));
            }
        });

        allBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "All";
                allBtn.setChecked(true);
                liveBtn.setChecked(false);
            }
        });

        liveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "Live";
                allBtn.setChecked(false);
                liveBtn.setChecked(true);
            }
        });

        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = UUID.randomUUID().toString();
                VideoModel model = new VideoModel(
                        id,
                        videoDate(),
                        videoDescription.getText().toString(),
                        videoName.getText().toString(),
                        "",
                        videoId.getText().toString(),
                        type,"0"
                );

                videoRef.child(id).setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(MainActivity.this, "Uploaded!!", Toast.LENGTH_SHORT).show();
                        videoDescription.setText("");
                        videoId.setText("");
                        videoName.setText("");
                    }
                });
            }
        });
    }

    private String videoDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}