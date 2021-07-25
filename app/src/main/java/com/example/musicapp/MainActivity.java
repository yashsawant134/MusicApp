package com.example.musicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.musicapp.Database.DatabaseHelper;
import com.example.musicapp.HomeScreen.Home2;
import com.example.musicapp.MusicScreen.MusicScreen;
import com.example.musicapp.MyLibrary.MyLibrary;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomnav;
    Fragment fragment=null;
    LinearLayout bottomMusicViewLinearLayout;
    int id=R.id.home;
    ImageView cancel,bottomsongImage,pause;
    TextView bottomsongname;
    String musicUrl;
    SeekBar PlayseekBar;
    MediaPlayer mediaPlayer;
    Handler handler=new Handler();
    boolean isPlayed=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomnav=findViewById(R.id.bottomnav);

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container,new Home2()).commit();



        bottomnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull  MenuItem item) {

                if(id!=item.getItemId()) {
                    id=item.getItemId();
                    switch (item.getItemId()) {

                        case R.id.home:
                            fragment = new Home2();
                            break;
                        case R.id.search:
                            fragment = new Home();

                            break;

                        case R.id.mylibrary:
                            fragment = new MyLibrary();

                            break;

                    }
                    if(fragment!=null) {
                        FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();

                        fragmentTransaction1.replace(R.id.fragment_container, fragment).commit();
                    }
                }

                return true;
            }
        });

        pause=findViewById(R.id.bottomsongpause);
        bottomsongImage=findViewById(R.id.bottomSongImage);
        bottomsongname=findViewById(R.id.bottomSongname);
        bottomMusicViewLinearLayout=findViewById(R.id.bottomMusicViewLinearLayout);
        cancel=findViewById(R.id.bottommusicviewCancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                bottomMusicViewLinearLayout.setVisibility(View.GONE);
            }
        });

        PlayseekBar=findViewById(R.id.bottomSeekbar);
        PlayseekBar.getThumb().mutate().setAlpha(0);
        PlayseekBar.setMax(100);
        mediaPlayer=new MediaPlayer();


        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isPlayed) {
                    isPlayed=false;
                    preparemediaPlayer(musicUrl);

                }
                if(mediaPlayer.isPlaying()){
                    pause.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                    handler.removeCallbacks(updater);
                    mediaPlayer.pause();
                }else{
                    mediaPlayer.start();
                    pause.setImageResource(R.drawable.ic_baseline_pause_24);
                    updateSeekBar();
                }
            }


        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                pause.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                PlayseekBar.setProgress(0);
                mediaPlayer.reset();
isPlayed=true;            }
        });

    }

    public void show(String name,String image,String musicUr,int id){
        mediaPlayer.reset();
        isPlayed=true;
        pause.setImageResource(R.drawable.ic_baseline_pause_24);
        bottomsongname.setText(name);
        Picasso.get().load(image).into(bottomsongImage);
        bottomMusicViewLinearLayout.setVisibility(View.VISIBLE);
        this.musicUrl=musicUr;
        preparemediaPlayer(musicUrl);
        DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
        databaseHelper.addInRecentPlays(name,"00:00", image, id);


    }


    private  void  preparemediaPlayer(String trackURL){
        try{
            mediaPlayer.setDataSource(trackURL);
            mediaPlayer.prepare();
            mediaPlayer.start();
            updateSeekBar();
        }catch (Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT);
        }
    }

    private Runnable updater=new Runnable() {
        @Override
        public void run() {
            updateSeekBar();
            long currentDuration=mediaPlayer.getCurrentPosition();
        }
    };

    private void updateSeekBar(){
        if(mediaPlayer.isPlaying()){
            PlayseekBar.setProgress((int) (((float) mediaPlayer.getCurrentPosition()/mediaPlayer.getDuration())*100));
            handler.postDelayed(updater,1000);
        }
    }



    private String milliSecondsToTimer(Long milliseconds){
        String timerString="";
        String secondString;

        int hours=(int)(milliseconds/(1000*60*60));
        int minutes=(int)(milliseconds%(1000*60*60))/(1000*60);
        int seconds=(int)((milliseconds%(1000*60*60))%(1000*60)/1000);

        if(hours>0){
            timerString=hours+":";
        }

        if(seconds<10){
            secondString="0"+seconds;
        }else {
            secondString=""+seconds;
        }

        timerString=timerString+minutes+":"+secondString;
        return timerString;
    }




}