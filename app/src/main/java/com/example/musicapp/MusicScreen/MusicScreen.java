package com.example.musicapp.MusicScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.musicapp.Database.DatabaseHelper;
import com.example.musicapp.ItuneApiPojo.ItuneApiPOJO;
import com.example.musicapp.R;
import com.github.clans.fab.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MusicScreen extends AppCompatActivity {

    ImageView imageView,back;
    TextView name,currentTime,totalTime;
    SeekBar PlayseekBar;
    MediaPlayer mediaPlayer;
    Handler handler=new Handler();
    FloatingActionButton playpause,previous,next;
    String trackURL;
    int position;
    List<ItuneApiPOJO> list;
    boolean isPlayed=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_screen);

        imageView=findViewById(R.id.artisworkimage);
        name=findViewById(R.id.musicscreenname);
        currentTime=findViewById(R.id.musiccurrenttime);
        totalTime=findViewById(R.id.musictotaltime);
        PlayseekBar=findViewById(R.id.musicseekbar);
        playpause=findViewById(R.id.playpause);
        mediaPlayer=new MediaPlayer();
        playpause.setImageResource(R.drawable.ic_baseline_play_arrow_24);
        back=findViewById(R.id.back);
        previous=findViewById(R.id.previous);
        next=findViewById(R.id.next);




        PlayseekBar.setMax(100);
        position=getIntent().getIntExtra("position",0);

        list=(List<ItuneApiPOJO>) getIntent().getSerializableExtra("list");
        addData(position);


        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
//                mediaPlayer.reset();
                playpause.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                if(position>0) {
                    position = position - 1;
                    addData(position);

                }
                if(position==0){
                    position=list.size()-1;
                    addData(position);
                }
                PlayseekBar.setProgress(0);
                currentTime.setText("00:00");
            }

        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
//                mediaPlayer.reset();
                playpause.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                if(position<list.size()-1) {
                    position = position + 1;
                    addData(position);
                }
                if(position==list.size()-1){
                    position=0;
                    addData(position);
                }
                PlayseekBar.setProgress(0);
                currentTime.setText("00:00");
            }
        });

        playpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isPlayed) {
                    DatabaseHelper databaseHelper = new DatabaseHelper(MusicScreen.this);
                    databaseHelper.addInRecentPlays(list.get(position).getTrackName(), currentTime.getText().toString(), list.get(position).getArtworkUrl100(), list.get(position).getTrackId());
                    isPlayed=false;
                }
                    if(mediaPlayer.isPlaying()){
                        playpause.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                        handler.removeCallbacks(updater);
                        mediaPlayer.pause();
                    }else{
                        mediaPlayer.start();
                        playpause.setImageResource(R.drawable.ic_baseline_pause_24);
                        updateSeekBar();
                    }
                }

        });




        PlayseekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                SeekBar seekBar=(SeekBar) v;
                int playposition=(mediaPlayer.getDuration()/100)*seekBar.getProgress();
                mediaPlayer.seekTo(playposition);
                currentTime.setText(milliSecondsToTimer((long)mediaPlayer.getCurrentPosition()));
                return false;
            }
        });

        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                PlayseekBar.setSecondaryProgress(percent);
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                playpause.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                PlayseekBar.setProgress(0);
                currentTime.setText("00:00");
                mediaPlayer.reset();
                preparemediaPlayer(trackURL);
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private  void  preparemediaPlayer(String trackURL){
        try{
            mediaPlayer.setDataSource(trackURL);
            mediaPlayer.prepare();
            totalTime.setText(milliSecondsToTimer((long)mediaPlayer.getDuration()));

        }catch (Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT);
        }
    }

    private Runnable updater=new Runnable() {
        @Override
        public void run() {
            updateSeekBar();
            long currentDuration=mediaPlayer.getCurrentPosition();
            currentTime.setText(milliSecondsToTimer(currentDuration));
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
            secondString="0"+seconds
;
        }else {
            secondString=""+seconds;
        }

        timerString=timerString+minutes+":"+secondString;
        return timerString;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mediaPlayer.pause();

    }

    public void addData(int position){

        Picasso.get().load(list.get(position).getArtworkUrl100()).into(imageView);
        name.setText(list.get(position).getTrackName());
        trackURL=list.get(position).getPreviewUrl();
        preparemediaPlayer(trackURL);
    }
}