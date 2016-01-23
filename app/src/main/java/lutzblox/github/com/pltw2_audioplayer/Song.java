package lutzblox.github.com.pltw2_audioplayer;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import java.io.IOException;

public class Song {
    private MediaPlayer music;
    private String state;
    //public String playlist;//holder, will be a classx
    Song(Context context, int location){
        music = MediaPlayer.create(context, location);
    }
    public void play() {
        if (state == "stop"){
            try {
                music.prepare();
                state = "prepared";
            }catch(IOException prepareFailed) {
                Log.println(30000,"Prepare", "Preparation failed, IO error");
            }
        }
        music.start();
        state = "play";
    }
    public void pause(){
        music.pause();
        state = "pause";
    }
    public void stop(){
        music.stop();
        state = "stop";
    }
    public int getDuration(){
        return music.getDuration()/1000;
    }
    public int getCurrentPosition(){
        return music.getCurrentPosition()/1000;
    }
}