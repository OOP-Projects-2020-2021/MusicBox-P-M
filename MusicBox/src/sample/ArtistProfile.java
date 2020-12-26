package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

public class ArtistProfile extends Stage {
    int song;
    Text nrOfSongs;
    Text nrOfAlbums;
    ArrayList<Button> removeButtons = new ArrayList<>();
    ArrayList<Button> songTitles = new ArrayList<Button>();
    ArrayList<Text> albumsTitles = new ArrayList<>();
    ArrayList<Text> duration = new ArrayList<>();
    ArrayList<HBox> hBoxes = new ArrayList<>();
    ArtistProfile(Artist artist)
    {
        this.setWidth(400);
        this.setHeight(600);
        this.setTitle(artist.getUsername() + " Profile");


       // user.setRegularUserProfile(this);
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        //gridPane.setAlignment(Pos.CENTER);
        Image image = new Image("GenericProfile.png");
        ImageView imageView=new ImageView(image);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        gridPane.add(imageView,0,0);
        Text name=new Text(artist.getName());
        Text title=new Text("'s Music Box");
        textDesign(name,15);
        textDesign(title,10);
        gridPane.add(name, 2, 0);
        gridPane.add(title,3,0);



        gridPane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #e3b0dc, #63dafa);");
        Scene scene = new Scene(gridPane);
        Button logout = new Button("Logout");
        logout.setTextFill(Color.WHITE);
        logout.setStyle("-fx-font: 10 arial; -fx-base: #1c1d1d;");
        gridPane.add(logout,3,1);

        nrOfSongs=new Text("Songs: "+String.valueOf(artist.getNrOfSongs()));
        nrOfAlbums=new Text("Albums: "+String.valueOf(artist.getNrOfAlbums()));
        textDesign(nrOfSongs,15);
        textDesign(nrOfAlbums,15);

        gridPane.add(nrOfSongs, 0, 1);
        gridPane.add(nrOfAlbums,0,2);


        Set<Artist> listOfArtists = new HashSet<Artist>();
        try {
            BufferedReader in = new BufferedReader(
                    new FileReader("users.txt"));
            String line;
            while ((line = in.readLine()) != null) {
                String[] userInfo = line.split(",");
                if(userInfo[3].equals("1"))
                {
                    Scanner scanner=new Scanner(new File(userInfo[1]+".txt"));
                    int nrOfAlbums = scanner.nextInt();
                    int nrOfArtistSongs=scanner.nextInt();
                    int nrOfSubscribers=scanner.nextInt();
                    Artist newArtist = new Artist(userInfo[1],userInfo[2],userInfo[0],"",nrOfAlbums,nrOfArtistSongs,nrOfSubscribers,1);
                    listOfArtists.add(newArtist);
                }
            }
        }
        catch (IOException e) {
            System.out.println("File Read Error");
        }

        Map<String, Song> listOfSongs = new HashMap<String, Song>();

        try{
            BufferedReader in = new BufferedReader(
                    new FileReader("songs1.txt"));
            String line;
            while ((line = in.readLine())!= null) {
                String[] songInfo = line.split(",");
                Artist newArtist = findSongArtist((HashSet<Artist>) listOfArtists,songInfo[0]);
                Song song = new Song(newArtist,songInfo[1],songInfo[2],songInfo[3],songInfo[4],songInfo[5],songInfo[1]+".mp3");
                listOfSongs.put(songInfo[1],song);
                if(songInfo[0].equals(artist.getName()))
                {
                    artist.getArtistLibrary().userLibrarySongs.put(songInfo[1],song);
                }
            }

        }
        catch (IOException e) {
            System.out.println("File Read Error");
        }


        Text header1 = new Text("Song Title");
        textDesign(header1,16);
        Text header3 = new Text("Album");
        textDesign(header3,16);
        Text header4 = new Text("Duration");
        textDesign(header4,16);

        gridPane.add(header1,0,6);
        gridPane.add(header3,2,6);
        gridPane.add(header4,3,6);

        int position = 8;
        song = 0;
        for (String keys : artist.getArtistLibrary().userLibrarySongs.keySet()) {

            Button removeSong = new Button("-");
            Button newSong = new Button(artist.getArtistLibrary().userLibrarySongs.get(keys).getTitle());
            songTitles.add(newSong);
            removeButtons.add(removeSong);
            HBox newHbox = new HBox(removeSong,newSong);
            hBoxes.add(newHbox);

            Text artistName = new Text(artist.getArtistLibrary().userLibrarySongs.get(keys).getArtistName().getName());
            Text albumTitle = new Text(artist.getArtistLibrary().userLibrarySongs.get(keys).getAlbumName());
            Text durationSong = new Text("            " + artist.getArtistLibrary().userLibrarySongs.get(keys).getMinutes() + ":" + artist.getArtistLibrary().userLibrarySongs.get(keys).getSeconds());
            albumsTitles.add(albumTitle);
            duration.add(durationSong);

            songTitles.get(song).setStyle("-fx-background-color: transparent;-fx-font: 13 arial; ");
            removeButtons.get(song).setTextFill(Color.WHITE);
            removeButtons.get(song).setStyle("-fx-font: 10 arial; -fx-base: #1c1d1d;");
            songTitles.get(song).setTextFill(Color.DARKBLUE);


            gridPane.add(hBoxes.get(song), 0, position);
            //gridPane.add(removeButtons.get(song),0,position);
            //gridPane.add(songTitles.get(song),2,position);
            gridPane.add(albumsTitles.get(song), 2, position);
            gridPane.add(duration.get(song), 3, position);
            position++;


            newSong.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {

                    String path = artist.getArtistLibrary().userLibrarySongs.get(keys).getTitle() + ".mp3";
                    Media media = new Media(new File(path).toURI().toString());
                    MediaPlayer mediaPlayer = new MediaPlayer(media);
                    MediaView mediaView = new MediaView(mediaPlayer);
                    //gridPane.add(mediaView,0,10);
                    // mediaPlayer.setAutoPlay(true);
                    mediaPlayer.play();


                    new AudioPlayer(mediaPlayer, mediaView, artist.getArtistLibrary().userLibrarySongs.get(keys));

                }
            });

            removeSong.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    removeSong.setVisible(false);
                    newSong.setVisible(false);
                    newHbox.setVisible(false);
                    gridPane.getChildren().remove(removeSong);
                    gridPane.getChildren().remove(newSong);
                    gridPane.getChildren().remove(newHbox);
                    gridPane.getChildren().remove(albumTitle);
                    gridPane.getChildren().remove(durationSong);

                    MusicManager.removeSong(newSong.getText(), artist.getArtistLibrary());
                    listOfSongs.remove(newSong.getText());


                    artist.setNrOfSongs(artist.getNrOfSongs()-1);
                    removeButtons.remove(removeSong);
                    songTitles.remove(newSong);
                    albumsTitles.remove(albumTitle);
                    duration.remove(durationSong);

                    deleteAll(gridPane,artist.getNrOfSongs());

                    int pos = 8;
                    for(int j=0;j<artist.getNrOfSongs();j++)
                    {
                        HBox box= new HBox(removeButtons.get(j),songTitles.get(j));
                        gridPane.add(box, 0, pos);
                        gridPane.add(albumsTitles.get(j), 2, pos);
                        gridPane.add(duration.get(j), 3, pos);
                        pos++;
                    }

                    int foundAlbum=0;
                    for(Song userSongs:artist.getArtistLibrary().userLibrarySongs.values())
                    {
                        if(userSongs.getAlbumName().equals(albumTitle.getText()))
                        {
                            foundAlbum = 1;
                        }
                    }
                    if(foundAlbum==0)
                    {
                        artist.setNrOfAlbums(artist.getNrOfAlbums()-1);
                        nrOfAlbums.setText("Albums: "+String.valueOf(artist.getNrOfAlbums()));
                    }

                    nrOfSongs.setText("Songs: "+String.valueOf(artist.getNrOfSongs()));
                }
            });
            song++;
        }
        /*exploreButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                new ExplorePage((HashMap<String, Song>) listOfSongs,user,user.getRegularUserProfile());
            }
        });*/

        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                FileWriter myWriter = null;
                try {
                    myWriter = new FileWriter(artist.getUsername()+".txt");
                    myWriter.write(artist.getNrOfAlbums() + " " + artist.getNrOfSongs()+" "+0+"\n");

                    FileWriter songsWriter;
                    songsWriter = new FileWriter("songs1.txt");
                    for(Song songs: listOfSongs.values()) {
                        songsWriter.write(songs.getArtistName().getName()+ "," + songs.getTitle() +"," + songs.getAlbumName()+","+ songs.getMinutes()+","+ songs.getSeconds()+","+songs.getGenre() + "\n");
                    }
                    songsWriter.close();
                    myWriter.close();
                    new StartPage();
                    close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        this.setScene(scene);
        this.setTitle(artist.getUsername() + " Profile");
        this.show();
    }

    public void textDesign(Text text, int font)
    {

        text.setFont(Font.font("Arial", FontWeight.BOLD, font));
        text.setFill(Color.BLACK);
        text.setStroke(Color.ROYALBLUE);
        text.setStrokeWidth(0.5);
        text.setFontSmoothingType(FontSmoothingType.LCD);
    }

    public Artist findSongArtist(HashSet<Artist> listOfArtists,String name)
    {
        for(Artist artist:listOfArtists)
        {
            if(artist.getName().equals(name))
                return artist;
        }
        return null;
    }
    public  void deleteAll(GridPane gridpane, int nrSongs)
    {
        for(int i=0;i<nrSongs;i++)
        {
            gridpane.getChildren().remove(removeButtons.get(i));
            gridpane.getChildren().remove(songTitles.get(i));
            gridpane.getChildren().remove(hBoxes.get(i));
            gridpane.getChildren().remove(albumsTitles.get(i));
            gridpane.getChildren().remove(duration.get(i));
        }
    }
}
