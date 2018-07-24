package com.example.user1.quizvolumn;

import android.content.Context;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button upbtn = findViewById(R.id.upbtn);

        upbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* 자바 컴포넌트 라고 불렸던  */
                AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
                int volume =  audioManager.getStreamVolume(AudioManager.STREAM_RING);
                if(volume<15){
                    audioManager.setStreamVolume(AudioManager.STREAM_RING,volume+1,AudioManager.FLAG_PLAY_SOUND);
                }else{
                    Toast.makeText(getApplicationContext(), "현재 최고음량입니다.", Toast.LENGTH_SHORT).show();

                }

            }
        });

        Button downbtn = findViewById(R.id.downbtn);

        downbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
                int volume =  audioManager.getStreamVolume(AudioManager.STREAM_RING);
                if(volume>0){
                    audioManager.setStreamVolume(AudioManager.STREAM_RING,volume-1,AudioManager.FLAG_PLAY_SOUND);
                }else{
                    Toast.makeText(getApplicationContext(), "현재 최저음량입니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
