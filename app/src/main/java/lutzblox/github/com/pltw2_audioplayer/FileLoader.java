package lutzblox.github.com.pltw2_audioplayer;

import java.io.File;
import java.util.ArrayList;

//Still need to make default playlist and store song locations - Maybe just store location somehow?
public class FileLoader {
    public ArrayList<File> inFiles = new ArrayList<>();
    // dir is these: /storage/sdcard0/Music  && /storage/sdcard0/Downloads
    public void loadFiles(File dir)
    {
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                loadFiles(file);
            } else {
                if (file.getName().endsWith(".mp3")) {
                    inFiles.add(file);
                }
            }
        }
    }
}
