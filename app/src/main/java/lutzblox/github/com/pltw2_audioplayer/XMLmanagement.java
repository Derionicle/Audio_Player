package lutzblox.github.com.pltw2_audioplayer;

import android.content.Context;
import android.util.Log;

import com.github.lutzblox.easyxml.EasyXML;
import com.github.lutzblox.easyxml.trees.XMLTag;
import com.github.lutzblox.easyxml.trees.XMLTree;
import com.github.lutzblox.easyxml.writers.XMLWriter;

import java.io.InputStream;
import java.io.OutputStream;

public class XMLmanagement {
    public String[] retrievePlaylistNames(Context context) {
        try {
            InputStream in = context.openFileInput("music.xml");
            XMLTree tree = EasyXML.newXMLReader().read(in);
            XMLTag root = tree.getRoot();
            if (root.hasChild("playlists")){
                XMLTag[] playlists = root.getChildren("playlists")[0].getChildren();
                String[] names = new String[playlists.length];
                for(int x = 0; x < playlists.length; x++) {
                    names[x] = playlists[x].getAttribute("name");
                }
                return names;
            }
        } catch (Exception e){
            Log.e("FileLoading", "file loading failed");
        }
        return new String[0];
    }
    public void newPlaylist(Context context, String name){
        try {
            InputStream in = context.openFileInput("music.xml");
            XMLTree tree = EasyXML.newXMLReader().read(in);
            XMLTag root = tree.getRoot();
            if(!root.hasChild("playlists")){
                root.addChild(new XMLTag("playlists"));
            }
            XMLTag playlist = new XMLTag(name);
            root.getChildren("playlists")[0].addChild(playlist);//Shouldn't this be enough?
            root.getChildren("playlists")[0].getChildren(name)[0].addAttribute("name", name);
            OutputStream out = context.openFileOutput("music.xml", 0);
            XMLWriter toWrite = EasyXML.newXMLWriter();
            toWrite.write(tree);//Right?  -- Don't get purpose of writer
        } catch (Exception e){
            Log.e("FileLoading", "file loading failed");
        }
    }
    public String[] retrieveSongNames(Context context, String playlist){
        try {
            InputStream in = context.openFileInput("music.xml");
            XMLTree tree = EasyXML.newXMLReader().read(in);
            XMLTag root = tree.getRoot();
            if (root.hasChild("playlists")&& root.getChildren("playlists")[0].hasChild(playlist)){
                XMLTag[] songs = root.getChildren("playlists")[0].getChildren(playlist)[0].getChildren();
                String[] names = new String[songs.length];
                for(int x = 0; x < songs.length; x++) {
                    names[x] = songs[x].getAttribute("name");
                }
                return names;
            }
        } catch (Exception e){
            Log.e("FileLoading", "file loading failed");
        }
        return new String[0];
    }
    public void newSong(Context context, String name, String playlist, String path){
        try {
            InputStream in = context.openFileInput("music.xml");
            XMLTree tree = EasyXML.newXMLReader().read(in);
            XMLTag root = tree.getRoot();
            if(!root.hasChild("playlists")){
                root.addChild(new XMLTag("playlists"));
            }
            if (!root.getChildren("playlists")[0].hasChild(playlist)){
                root.getChildren("playlists")[0].addChild(new XMLTag(playlist));
            }
            XMLTag song = new XMLTag(name);
            root.getChildren("playlists")[0].getChildren(playlist)[0].addChild(song);//Shouldn't this be enough?
            root.getChildren("playlists")[0].getChildren(playlist)[0].getChildren(name)[0].addAttribute("name", name);
            root.getChildren("playlists")[0].getChildren(playlist)[0].getChildren(name)[0].addAttribute("path", path);
            OutputStream out = context.openFileOutput("music.xml", 0);
            XMLWriter toWrite = EasyXML.newXMLWriter();
            toWrite.write(tree);//Right?  -- Don't get purpose of writer
        } catch (Exception e){
            Log.e("FileLoading", "file loading failed");
        }
    }
}
