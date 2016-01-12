package lutzblox.github.com.pltw2_audioplayer;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by MNM on 1/12/16.
 */
public class BasicMusic {
    public static MediaPlayer test;
    public static void play(Context context) {
        test = MediaPlayer.create(context, R.raw.happy);
        test.start();
    }
}
