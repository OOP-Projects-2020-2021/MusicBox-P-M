package com.company;

public interface MusicManager<T>{
    public  void addSong(T song);
    static void removeSong(Song song,UserLibrary library){

    }
}
