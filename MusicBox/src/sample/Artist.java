package sample;

public class Artist extends User implements MusicManager<String> {
    private int nrOfAlbums;
    private int nrOfSongs;
    private int nrOfSubscribers;
    private ArtistFeed feed;
    private UserLibrary artistLibrary = new UserLibrary();
    public Artist(String username,String password, String name, String imagePath, int nrOfAlbums, int nrOfSongs, int nrOfSubscribers, int type)
    {
        super(username,password,name,imagePath,type);
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

    @Override
    public void addSong(String filePath) {

    }

    public ArtistFeed getFeed() {
        return feed;
    }

    public UserLibrary getArtistLibrary() {
        return artistLibrary;
    }

    public void setFeed(ArtistFeed feed) {
        this.feed = feed;
    }
}
