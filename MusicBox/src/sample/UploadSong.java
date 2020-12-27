package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class UploadSong extends Stage {
    UploadSong( Map<String, Song> listOfSongs, Artist artist)
    {
        this.resizableProperty().setValue(Boolean.FALSE);
        this.setWidth(600);
        this.setHeight(400);
        Text textSongName = new Text("Song Name:");
        Text textAlbumName = new Text("Album Name:");
        Text textDuration = new Text("Duration:");
        Text separator=new Text(":");
        Text textGenre=new Text("Genre:");
        Text textFilePath=new Text("File path (songName.mp3):");


        TextField textFieldSongName= new TextField();
        TextField textFieldAlbumName = new TextField();
        TextField textFieldMinutes = new TextField();
        TextField textFieldSeconds= new TextField();
        TextField textFieldGenre = new TextField();
        TextField textFieldFilePath = new TextField();

        HBox hBox=new HBox(textFieldMinutes,separator,textFieldSeconds);

        Button uploadButton = new Button("Upload");
        Button clearButton = new Button("Clear");

        Text songNameAlert=new Text("Song name required!");
       songNameAlert.setStyle("-fx-font: 10 arial; -fx-base: red;");
       songNameAlert.setVisible(false);
        Text albumAlert=new Text("Album name required!");
        albumAlert.setStyle("-fx-font: 10 arial; -fx-base: RED;");
        albumAlert.setVisible(false);
        Text minutesAlert=new Text("Duration required!");
       minutesAlert.setStyle("-fx-font: 10 arial; -fx-base: RED;");
       minutesAlert.setVisible(false);
        Text secondsAlert=new Text("Duration required!");
        secondsAlert.setStyle("-fx-font: 10 arial; -fx-base: RED;");
       secondsAlert.setVisible(false);
        Text genreAlert=new Text("Genre required!");
        genreAlert.setStyle("-fx-font: 10 arial; -fx-base: RED;");
       genreAlert.setVisible(false);
        Text pathAlert=new Text("File path required!");
        pathAlert.setStyle("-fx-font: 10 arial; -fx-base: RED;");
        pathAlert.setVisible(false);

        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #e3b0dc, #63dafa);");
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);


        gridPane.add(textSongName, 0, 0);
        gridPane.add(textFieldSongName, 1, 0);
        gridPane.add(songNameAlert,2,0);

        gridPane.add(textAlbumName, 0, 1);
        gridPane.add(textFieldAlbumName, 1, 1);
        gridPane.add(albumAlert,2,1);

        gridPane.add(textDuration, 0, 2);
        gridPane.add(hBox, 1, 2);
        gridPane.add(minutesAlert,2,2);
        gridPane.add(secondsAlert,2,2);

        gridPane.add(textGenre, 0, 3);
        gridPane.add(textFieldGenre, 1, 3);
        gridPane.add(genreAlert,2,3);

        gridPane.add(textFilePath, 0, 4);
        gridPane.add(textFieldFilePath, 1, 4);
        gridPane.add(pathAlert,2,4);


        gridPane.add(uploadButton, 1, 10);
        gridPane.add(clearButton, 1, 13);
      uploadButton.setTextFill(Color.WHITE);
        uploadButton.setStyle("-fx-font: 10 arial; -fx-base: #1c1d1d;");
        clearButton.setTextFill(Color.WHITE);
       clearButton.setStyle("-fx-font: 10 arial; -fx-base: #1c1d1d;");

        textFieldSongName.setPromptText("Song Name");
        textFieldAlbumName.setPromptText("Album Name");
       textFieldMinutes.setPromptText("Min");
       textFieldSeconds.setPromptText("Sec");
       textFieldGenre.setPromptText("Genre");
       textFieldFilePath.setPromptText("songName.mp3");

        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                textFieldSongName.clear();
                textFieldAlbumName.clear();
                textFieldMinutes.clear();
                textFieldSeconds.clear();
                textFieldGenre.clear();
               textFieldFilePath.clear();
                songNameAlert.setVisible(false);
                albumAlert.setVisible(false);
                minutesAlert.setVisible(false);
               secondsAlert.setVisible(false);
               genreAlert.setVisible(false);
               pathAlert.setVisible(false);
            }
        });
       uploadButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if (validation(textFieldSongName, textFieldAlbumName, textFieldMinutes, textFieldSeconds, textFieldGenre, textFieldFilePath, songNameAlert, albumAlert, minutesAlert, secondsAlert, genreAlert, pathAlert)) {
                    Song newSong = new Song(artist, textFieldSongName.getText(), textFieldAlbumName.getText(), textFieldMinutes.getText(), textFieldSeconds.getText(), textFieldGenre.getText(), textFieldFilePath.getText());
                    listOfSongs.put(textFieldSongName.getText(), newSong);
                    int foundAlbum = 0;
                    for (Song artistSongs : artist.getArtistLibrary().userLibrarySongs.values()) {
                        if (artistSongs.getAlbumName().equals(textFieldAlbumName.getText())) {
                            foundAlbum = 1;
                        }
                    }
                    if (foundAlbum == 0) {
                        artist.setNrOfAlbums(artist.getNrOfAlbums() + 1);
                        artist.getArtistProfile().nrOfAlbums.setText("Albums: " + String.valueOf(artist.getNrOfAlbums()));
                    }
                    artist.addSong(textFieldFilePath.getText(), newSong);
                    try {
                        FileWriter songsWriter;
                        songsWriter = new FileWriter("songs.txt", true);
                        songsWriter.write(newSong.getArtistName().getName() + "," + newSong.getTitle() + "," + newSong.getAlbumName() + "," + newSong.getMinutes() + "," + newSong.getSeconds() + "," + newSong.getGenre() + "\n");
                        songsWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Button removeSong = new Button("-");
                    removeSong.setTextFill(Color.WHITE);
                    removeSong.setStyle("-fx-font: 10 arial; -fx-base: #1c1d1d;");
                    Button songButton = new Button(textFieldSongName.getText());
                    Text albumName = new Text(textFieldAlbumName.getText());
                    Text duration = new Text("            " + textFieldMinutes.getText() + ":" + textFieldSeconds.getText());
                    songButton.setStyle("-fx-background-color: transparent;-fx-font: 13 arial; ");
                    songButton.setTextFill(Color.DARKBLUE);
                    HBox hBox = new HBox(removeSong, songButton);
                    if (artist.getNrOfSongs() - 1 == 0) {
                        artist.getArtistProfile().gridPane.add(hBox, 0, 8);
                        artist.getArtistProfile().gridPane.add(albumName, 2, 8);
                        artist.getArtistProfile().gridPane.add(duration, 3, 8);
                    } else {
                        artist.getArtistProfile().gridPane.add(hBox, 0, artist.getNrOfSongs() + 7);
                        artist.getArtistProfile().gridPane.add(albumName, 2, artist.getNrOfSongs() + 7);
                        artist.getArtistProfile().gridPane.add(duration, 3, artist.getNrOfSongs() + 7);

                    }
                    artist.getArtistProfile().removeButtons.add(removeSong);
                    artist.getArtistProfile().songTitles.add(songButton);
                    artist.getArtistProfile().albumsTitles.add(albumName);
                    artist.getArtistProfile().duration.add(duration);
                    artist.getArtistProfile().nrOfSongs.setText("Songs: " + String.valueOf(artist.getNrOfSongs()));

                    songButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent t) {

                            String path = songButton.getText() + ".mp3";
                            Media media = new Media(new File(path).toURI().toString());
                            MediaPlayer mediaPlayer = new MediaPlayer(media);
                            MediaView mediaView = new MediaView(mediaPlayer);
                            mediaPlayer.play();
                            new AudioPlayer(mediaPlayer, mediaView, newSong);

                        }
                    });
                    removeSong.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent t) {
                            removeSong.setVisible(false);
                            songButton.setVisible(false);
                            hBox.setVisible(false);
                            artist.getArtistProfile().gridPane.getChildren().remove(removeSong);
                            artist.getArtistProfile().gridPane.getChildren().remove(songButton);
                            artist.getArtistProfile().gridPane.getChildren().remove(hBox);
                            artist.getArtistProfile().gridPane.getChildren().remove(albumName);
                            ;
                            artist.getArtistProfile().gridPane.getChildren().remove(duration);

                            MusicManager.removeSong(songButton.getText(), artist.getArtistLibrary());
                            artist.setNrOfSongs(artist.getNrOfSongs() - 1);
                            artist.getArtistProfile().removeButtons.remove(removeSong);
                            artist.getArtistProfile().songTitles.remove(songButton);
                            artist.getArtistProfile().albumsTitles.remove(albumName);
                            artist.getArtistProfile().duration.remove(duration);
                            artist.getArtistProfile().hBoxes.remove(hBox);

                            for (int i = 0; i < artist.getNrOfSongs(); i++) {
                                artist.getArtistProfile().gridPane.getChildren().remove(artist.getArtistProfile().removeButtons.get(i));
                                artist.getArtistProfile().gridPane.getChildren().remove(artist.getArtistProfile().songTitles.get(i));
                                //artist.getArtistProfile().gridPane.getChildren().remove(artist.getArtistProfile().hBoxes.get(i));
                                artist.getArtistProfile().gridPane.getChildren().remove(artist.getArtistProfile().albumsTitles.get(i));
                                artist.getArtistProfile().gridPane.getChildren().remove(artist.getArtistProfile().duration.get(i));
                            }

                            int pos = 8;
                            for (int j = 0; j < artist.getNrOfSongs(); j++) {
                                HBox box = new HBox(artist.getArtistProfile().removeButtons.get(j), artist.getArtistProfile().songTitles.get(j));
                                artist.getArtistProfile().gridPane.add(box, 0, pos);
                                artist.getArtistProfile().gridPane.add(artist.getArtistProfile().albumsTitles.get(j), 2, pos);
                                artist.getArtistProfile().gridPane.add(artist.getArtistProfile().duration.get(j), 3, pos);
                                pos++;
                            }

                            int foundAlbum = 0;
                            for (Song userSongs : artist.getArtistLibrary().userLibrarySongs.values()) {
                                if (userSongs.getAlbumName().equals(albumName.getText())) {
                                    foundAlbum = 1;
                                }
                            }
                            if (foundAlbum == 0) {
                                artist.setNrOfAlbums(artist.getNrOfAlbums() - 1);
                                artist.getArtistProfile().nrOfAlbums.setText("Albums: " + String.valueOf(artist.getNrOfAlbums()));
                            }

                            artist.getArtistProfile().nrOfSongs.setText("Songs: " + String.valueOf(artist.getNrOfSongs()));
                        }

                    });
                    close();
                }

            }
            });

       Scene scene= new Scene(gridPane);
       this.setScene(scene);
       this.setTitle("Upload Song");
       this.show();

    }
    public boolean validation(TextField name, TextField album, TextField minutes, TextField seconds,TextField genre,TextField filePath,Text nameAlert, Text albumAlert,
                              Text minutesAlert,Text secondsAlert,Text genreAlert,Text pathAlert){
        int ok=0;
        if(name.getText().trim().isEmpty() ) {
            nameAlert.setVisible(true);
            ok=1;
        }else{
            nameAlert.setVisible(false);
        }
        if(album.getText().trim().isEmpty() ) {
            albumAlert.setVisible(true);
            ok=1;
        }else{
            albumAlert.setVisible(false);
        }
        if(minutes.getText().trim().isEmpty() ) {
            minutesAlert.setVisible(true);
            ok=1;
        }else{
            minutesAlert.setVisible(false);
        }
        if(seconds.getText().trim().isEmpty() ) {
            secondsAlert.setVisible(true);
            ok=1;
        }else{
            secondsAlert.setVisible(false);
        }
        if(genre.getText().trim().isEmpty() ) {
           genreAlert.setVisible(true);
            ok=1;
        }else{
            genreAlert.setVisible(false);
        }
        if(filePath.getText().trim().isEmpty() || !filePath.getText().equals(name.getText()+".mp3")) {
            pathAlert.setVisible(true);
            ok=1;
        }else{
            pathAlert.setVisible(false);
        }

        return ok == 0;
    }
}
