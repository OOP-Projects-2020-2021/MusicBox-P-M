package sample;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class RegularUser extends User implements MusicManager<Song> {
    private Set<Artist> listOfArtists = new HashSet<Artist>();
    private UserLibrary userLibrary;
    private ArrayList<Playlist> listOfPlaylists=new ArrayList<Playlist>();
    private int nrOfSongs;
    private int nrOfPlaylists;
    private int nrOfArtists;

    public RegularUser(String username,String password, String name, String imagePath,int type,int nrOfSongs,int nrOfPlaylists,int nrOfArtists)
    {
        super(username,password,name,imagePath,type);
        this.nrOfSongs=nrOfSongs;
        this.nrOfPlaylists=nrOfPlaylists;
        this.nrOfArtists=nrOfArtists;

    }
    public Set<Artist> getListOfArtists() {
        return listOfArtists;
    }

    public void setListOfArtists(Set<Artist> listOfArtists) {
        this.listOfArtists = listOfArtists;
    }

    public int getNrOfSongs() {
        return nrOfSongs;
    }

    public void setNrOfSongs(int nrOfSongs) {
        this.nrOfSongs = nrOfSongs;
    }

    public int getNrOfPlaylists() {
        return nrOfPlaylists;
    }

    public void setNrOfPlaylists(int nrOfPlaylists) {
        this.nrOfPlaylists = nrOfPlaylists;
    }

    public int getNrOfArtists() {
        return nrOfArtists;
    }

    public void setNrOfArtists(int nrOfArtists) {
        this.nrOfArtists = nrOfArtists;
    }

    @Override
    public  void addSong(Song song) {

    }

}
