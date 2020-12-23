package sample;

public class Song {
    private Artist artist;
    private String title;
    private String albumName;
    private String minutes;
    private String seconds;
    private String genre;
    private String filePath;


    public Song(Artist artistName,String title, String albumName, String minutes, String seconds, String genre, String filePath) {
        this.title = title;
        this.artist=artistName;
        this.albumName = albumName;
        this.minutes = minutes;
        this.seconds = seconds;
        this.genre = genre;
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

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getSeconds() {
        return seconds;
    }

    public void setSeconds(String seconds) {
        this.seconds = seconds;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
