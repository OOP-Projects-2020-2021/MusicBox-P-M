package sample;
import java.util.*;
import java.io.*;


public class Playlist {

    private String playlistName;
    private Set<Song>listOfSongs = new HashSet<Song>();
    private int minutes;
    private int seconds;
    private RegularUser playlistOwner;

    public Playlist(String playlistName, RegularUser playlistOwner) {
        this.playlistName = playlistName;
        this.minutes = 0;
        this.seconds = 0;
        this.playlistOwner = playlistOwner;
    }

    public RegularUser getPlaylistOwner() {
        return playlistOwner;
    }

    public void setPlaylistOwner(RegularUser playlistOwner) {
        this.playlistOwner = playlistOwner;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void set(int minutes) {
        this.minutes = minutes;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }
}
