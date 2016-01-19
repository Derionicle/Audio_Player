package lutzblox.github.com.pltw2_audioplayer;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import java.io.IOException;

/**
 * Created by MNM on 1/12/16.
 */
public class Song {
    private MediaPlayer music;
    private String state;
    public String playlist;//holder, will be a class
    Song(Context context, int location){
        music = MediaPlayer.create(context, location);
    }
    public void play() {
        if (state == "stop"){
            try {
                music.prepare();
            }catch(java.io.IOException prepareFailed) {
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
}