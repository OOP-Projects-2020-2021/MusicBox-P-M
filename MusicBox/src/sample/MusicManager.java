package sample;

public interface MusicManager<T>{
    public  void addSong(T song);
    static void removeSong(String songTitle,UserLibrary library){
        library.userLibrarySongs.remove(songTitle);
    }
}
