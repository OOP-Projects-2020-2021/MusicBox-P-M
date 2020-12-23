package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class RegularUserProfile extends Stage{
    RegularUserProfile(RegularUser user)
    {
        this.setWidth(400);
        this.setHeight(600);
        this.setTitle(user.getUsername() + " Profile");
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
        Text name=new Text(user.getName());
        Text title=new Text("'s Music Box");
        textDesign(name,20);
        textDesign(title,10);
        gridPane.add(name, 2, 0);
        gridPane.add(title,3,0);

        Text nrOfSongs=new Text("Songs: "+String.valueOf(user.getNrOfSongs()));
        Text nrOfPlaylists=new Text("Playlists: "+String.valueOf(user.getNrOfPlaylists()));
        Text nrOfArtists=new Text("Artists: "+String.valueOf(user.getNrOfArtists()));
        textDesign(nrOfSongs,15);
        textDesign(nrOfPlaylists,15);
        textDesign(nrOfArtists,15);

        gridPane.add(nrOfSongs, 0, 1);
        gridPane.add(nrOfPlaylists,0,2);
        gridPane.add(nrOfArtists,0,3);
        gridPane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #63dafa, #e3b0dc);");
        Scene scene = new Scene(gridPane);

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
                    new FileReader("songs.txt"));
            String line;
            while ((line = in.readLine())!= null) {
                String[] songInfo = line.split(",");
                Artist artist = findSongArtist((HashSet<Artist>) listOfArtists,songInfo[0]);
                Song song = new Song(artist,songInfo[1],songInfo[2],songInfo[3],songInfo[4],songInfo[5],songInfo[1]+".mp3");
                listOfSongs.put(songInfo[1],song);
            }

        }
        catch (IOException e) {
            System.out.println("File Read Error");
        }

        Text header1 = new Text("Song Title");
        textDesign(header1,16);
        Text header2 = new Text("Artist");
        textDesign(header2,16);
        Text header3 = new Text("Album");
        textDesign(header3,16);
        Text header4 = new Text("Duration");
        textDesign(header4,16);

        gridPane.add(header1,0,6);
        gridPane.add(header2,2,6);
        gridPane.add(header3,3,6);
        gridPane.add(header4,4,6);

        UserLibrary library = user.getUserSongs(user.getUsername()+".txt", (HashMap<String, Song>) listOfSongs);
        int position = 8;
        for (String keys : library.userLibrarySongs.keySet())
        {
            //System.out.println(keys + ":"+ library.userLibrarySongs.get(keys));
            Text text1 = new Text(library.userLibrarySongs.get(keys).getTitle());
            textDesign(text1,15);
            Text text2 = new Text(library.userLibrarySongs.get(keys).getArtistName().getName());
            Text text3 = new Text(library.userLibrarySongs.get(keys).getAlbumName());
            Text text4 = new Text("            " + library.userLibrarySongs.get(keys).getMinutes()+":"+library.userLibrarySongs.get(keys).getSeconds());

            gridPane.add(text1,0,position);
            gridPane.add(text2,2,position);
            gridPane.add(text3,3,position);
            gridPane.add(text4,4,position);
            position++;
        }

        this.setScene(scene);
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
}
