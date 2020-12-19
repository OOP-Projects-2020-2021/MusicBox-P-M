package com.company;

public class Artist extends User {
    private int nrOfAlbums;
    private int nrOfSongs;
    private int nrOfSubscribers;
    public Artist(String username,String password, String name, String imagePath, int nrOfAlbums, int nrOfSongs, int nrOfSubscribers)
    {
        super(username,password,name,imagePath);
        this.nrOfAlbums=nrOfAlbums;
        this.nrOfSongs=nrOfSongs;
        this.nrOfSubscribers=nrOfSubscribers;
    }

    public int getNrOfAlbums() {
        return nrOfAlbums;
    }
    public void setNrOfAlbums(int nrOfAlbums) {
        this.nrOfAlbums = nrOfAlbums;
    }

    public int getNrOfSongs() {
        return nrOfSongs;
    }
    public void setNrOfSongs(int nrOfSongs) {
        this.nrOfSongs = nrOfSongs;
    }
    public int getNrOfSubscribers() {
        return nrOfSubscribers;
    }

    public void setNrOfSubscribers(int nrOfSubscribers) {
        this.nrOfSubscribers += nrOfSubscribers;
    }
}
