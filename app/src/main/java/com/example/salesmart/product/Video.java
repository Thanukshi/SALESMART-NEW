package com.example.salesmart.product;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import com.example.salesmartnew.R;

public class Video extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        Button btn = (Button)findViewById(R.id.btnvideo);

        getWindow().setFormat(PixelFormat.UNKNOWN);

        VideoView videoView = (VideoView)findViewById(R.id.video);
      // String videopath = "andrid.resource://"+ getPackageName() +"/" + R.raw.video;
        //Uri uri = Uri.parse(videopath);
        //videopath.setVideoURi


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);

            }
        });

     }
}
