package sample;

public interface MusicManager<T,W>{
    public  void addSong(T song, W info);
    static void removeSong(String songTitle,UserLibrary library){
        library.userLibrarySongs.remove(songTitle);
    }
}
