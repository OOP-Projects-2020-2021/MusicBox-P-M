package com.company;

public class Song {
    private Artist artist;
    private String title;
    private String albumName;
    private int minutes;
    private int seconds;
    private String genre;
    private String fileName;
    private String filePath;


    public Song(Artist artistName,String title, String albumName, int minutes, int seconds, String genre, String fileName, String filePath) {
        this.title = title;
        this.artist=artistName;
        this.albumName = albumName;
        this.minutes = minutes;
        this.seconds = seconds;
        this.genre = genre;
        this.fileName = fileName;
        this.filePath = filePath;
    }

    public Artist getArtistName() {
        return artist;
    }

    public void setArtistName(Artist artistName) {
        this.artist= artistName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
