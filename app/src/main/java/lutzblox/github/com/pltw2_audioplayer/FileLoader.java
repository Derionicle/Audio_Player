package lutzblox.github.com.pltw2_audioplayer;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

public class FileLoader {
    public ArrayList<String> fileLocations = new ArrayList<>();
    public ArrayList<String> fileNames = new ArrayList<>();
    // dir is these: /storage/sdcard0/Music  && /storage/sdcard0/Downloads
    public void loadFiles(File dir)
    {
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                loadFiles(file);
            } else {
                if (file.getName().endsWith(".mp3")) {
                    fileLocations.add(file.getAbsolutePath());
                    fileNames.add(file.getName().substring(0,file.getName().lastIndexOf(".")-1));
                }
            }
        }
        if (fileNames.isEmpty() || fileLocations.isEmpty()){
            Log.e("File Loading:", "No files found");
        }
    }
    public void initializeDefaultPlaylist(Context context){
        if (!fileNames.isEmpty() && !fileLocations.isEmpty() && fileNames.size() == fileLocations.size()){
            XMLmanagement newXML = new XMLmanagement();
            newXML.newPlaylist(context, "default");
            for (int x = 0; x < fileNames.size(); x++) {
                newXML.newSong(context, fileNames.get(x), "default", fileLocations.get(x));
            }
        }else{
            Log.e("Default Playlist:", "initialization failed, no songs and/or paths or there is not an equal number");
        }
    }
}