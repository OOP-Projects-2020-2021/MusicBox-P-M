package com.company;
import java.util.*;
import java.io.*;


public class Playlist {

    private String playlistName;
    private Set<Song>listOfSongs = new HashSet<Song>();
    private int minutes;
    private int seconds;

    public Playlist(String playlistName) {
        this.playlistName = playlistName;
        this.minutes = 0;
        this.seconds = 0;
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
