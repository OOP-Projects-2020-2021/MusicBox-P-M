package sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
        userLibrary = new UserLibrary();
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

    public UserLibrary getUserSongs(String filepath, HashMap<String, Song> listOfSongs)
    {
        for(int i=0;i<nrOfSongs;i++)
        {
            try{
                BufferedReader in = new BufferedReader(
                        new FileReader(filepath));
                String line = in.readLine();   //skip first line
                while ((line = in.readLine())!= null) {
                    userLibrary.userLibrarySongs.put(line, listOfSongs.get(line));
                    //System.out.println(userLibrary.userLibrarySongs.get(line).getTitle());
                }
            }
            catch (IOException e) {
                System.out.println("File Read Error");
            }

        }
        return userLibrary;
    }
}
