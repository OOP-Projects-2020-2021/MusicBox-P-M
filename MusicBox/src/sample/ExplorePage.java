package sample;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ExplorePage extends Stage {
    ExplorePage(HashMap<String,Song>songs, RegularUser regularUser, RegularUserProfile regularUserProfile){
        this.setWidth(650);
        this.setHeight(600);
        this.setTitle("Explore Page");
        ObservableList<Map.Entry<String,Song>> items = FXCollections.observableArrayList(songs.entrySet());
        TableView<Map.Entry<String,Song>> table = new TableView<>(items);
        TableColumn<Map.Entry<String, Song>, String> column1 = new TableColumn<>("Title");
        column1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, Song>, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, Song>, String> p) {
                return new SimpleStringProperty(p.getValue().getKey());
            }
        });
        TableColumn<Map.Entry<String, Song>, String> column2 = new TableColumn<>("Artist");
        column2.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, Song>, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, Song>, String> p) {
                return new SimpleStringProperty(p.getValue().getValue().getArtistName().getName());
            }
        });

        TableColumn<Map.Entry<String, Song>, String> column3 = new TableColumn<>("Album");
        column3.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, Song>, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, Song>, String> p) {
                return new SimpleStringProperty(p.getValue().getValue().getAlbumName());
            }
        });
        TableColumn<Map.Entry<String, Song>, String> column4 = new TableColumn<>("Genre");
        column4.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, Song>, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, Song>, String> p) {
                return new SimpleStringProperty(p.getValue().getValue().getGenre());
            }
        });
        TableColumn<Map.Entry<String, Song>, String> column5 = new TableColumn<>("Duration");
        column5.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, Song>, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, Song>, String> p) {
                return new SimpleStringProperty(p.getValue().getValue().getMinutes()+":"+p.getValue().getValue().getSeconds());
            }
        });
        TableColumn actionCol = new TableColumn("Add");
        Callback<TableColumn<Map.Entry<String, Song>, String>, TableCell<Map.Entry<String, Song>, String>> cellFactory
                = //
                new Callback<TableColumn<Map.Entry<String, Song>, String>, TableCell<Map.Entry<String, Song>, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Map.Entry<String, Song>, String> param) {
                        final TableCell<Map.Entry<String, Song>, String> cell = new TableCell<Map.Entry<String, Song>, String>() {

                           final Button btn = new Button("+");


                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        Map.Entry<String, Song> song = getTableView().getItems().get(getIndex());
                                        if(!regularUser.getUserLibrary().userLibrarySongs.containsKey(song.getKey()))
                                        {
                                            int foundArtist=0;
                                            for(Song userSongs:regularUser.getUserLibrary().userLibrarySongs.values())
                                            {
                                                if(userSongs.getArtistName().getName().equals(song.getValue().getArtistName().getName()))
                                                {
                                                    foundArtist = 1;
                                                }
                                            }
                                            if(foundArtist==0)
                                            {
                                                regularUser.setNrOfArtists(regularUser.getNrOfArtists()+1);
                                                regularUserProfile.nrOfArtists.setText("Artists: "+String.valueOf(regularUser.getNrOfArtists()));
                                            }
                                            regularUser.addSong(song.getValue());
                                            Button removeSong = new Button("-");
                                            removeSong.setTextFill(Color.WHITE);
                                            removeSong.setStyle("-fx-font: 10 arial; -fx-base: #1c1d1d;");
                                            Button songButton = new Button(song.getValue().getTitle());
                                            Text artistName = new Text(song.getValue().getArtistName().getName());
                                            Text albumName = new Text(song.getValue().getAlbumName());
                                            Text duration = new Text("            " + song.getValue().getMinutes() + ":" + song.getValue().getSeconds());
                                            songButton.setStyle("-fx-background-color: transparent;-fx-font: 13 arial; ");
                                            songButton.setTextFill(Color.DARKBLUE);
                                            HBox hBox = new HBox(removeSong, songButton);
                                            //System.out.println(regularUser.getNrOfSongs());
                                            if (regularUser.getNrOfSongs() - 1 == 0) {
                                                regularUserProfile.gridPane.add(hBox, 0, 8);
                                                regularUserProfile.gridPane.add(artistName, 2, 8);
                                                regularUserProfile.gridPane.add(albumName, 3, 8);
                                                regularUserProfile.gridPane.add(duration, 4, 8);
                                            } else {
                                                regularUserProfile.gridPane.add(hBox, 0, regularUser.getNrOfSongs() + 7);
                                                regularUserProfile.gridPane.add(artistName, 2, regularUser.getNrOfSongs() + 7);
                                                regularUserProfile.gridPane.add(albumName, 3, regularUser.getNrOfSongs() + 7);
                                                regularUserProfile.gridPane.add(duration, 4, regularUser.getNrOfSongs() + 7);
                                            }
                                            regularUserProfile.removeButtons.add(removeSong);
                                            regularUserProfile.songTitles.add(songButton);
                                            regularUserProfile.artistNames.add(artistName);
                                            regularUserProfile.albumsTitles.add(albumName);
                                            regularUserProfile.duration.add(duration);
                                            regularUserProfile.nrOfSongs.setText("Songs: " + String.valueOf(regularUser.getNrOfSongs()));
                                            songButton.setOnAction(new EventHandler<ActionEvent>() {
                                                @Override
                                                public void handle(ActionEvent t) {

                                                    String path = songButton.getText() + ".mp3";
                                                    Media media = new Media(new File(path).toURI().toString());
                                                    MediaPlayer mediaPlayer = new MediaPlayer(media);
                                                    MediaView mediaView = new MediaView(mediaPlayer);
                                                    mediaPlayer.play();
                                                    new AudioPlayer(mediaPlayer, mediaView, song.getValue());

                                                }
                                            });
                                            removeSong.setOnAction(new EventHandler<ActionEvent>() {
                                                @Override
                                                public void handle(ActionEvent t) {
                                                    removeSong.setVisible(false);
                                                    songButton.setVisible(false);
                                                    hBox.setVisible(false);
                                                    regularUserProfile.gridPane.getChildren().remove(removeSong);
                                                    regularUserProfile.gridPane.getChildren().remove(songButton);
                                                    regularUserProfile.gridPane.getChildren().remove(hBox);
                                                    regularUserProfile.gridPane.getChildren().remove(albumName);
                                                    regularUserProfile.gridPane.getChildren().remove(artistName);
                                                    regularUserProfile.gridPane.getChildren().remove(duration);

                                                    String artist=regularUser.getUserLibrary().userLibrarySongs.get(songButton.getText()).getArtistName().getName();
                                                    MusicManager.removeSong(songButton.getText(), regularUser.getUserLibrary());
                                                    regularUser.setNrOfSongs(regularUser.getNrOfSongs() - 1);
                                                    regularUserProfile.removeButtons.remove(removeSong);
                                                    regularUserProfile.songTitles.remove(songButton);
                                                    regularUserProfile.albumsTitles.remove(albumName);
                                                    regularUserProfile.artistNames.remove(artistName);
                                                    regularUserProfile.duration.remove(duration);
                                                    regularUserProfile.hBoxes.remove(hBox);

                                                    for (int i = 0; i < regularUser.getNrOfSongs(); i++) {
                                                        regularUserProfile.gridPane.getChildren().remove(regularUserProfile.removeButtons.get(i));
                                                        regularUserProfile.gridPane.getChildren().remove(regularUserProfile.songTitles.get(i));
                                                        regularUserProfile.gridPane.getChildren().remove(regularUserProfile.hBoxes.get(i));
                                                        regularUserProfile.gridPane.getChildren().remove(regularUserProfile.artistNames.get(i));
                                                        regularUserProfile.gridPane.getChildren().remove(regularUserProfile.albumsTitles.get(i));
                                                        regularUserProfile.gridPane.getChildren().remove(regularUserProfile.duration.get(i));
                                                    }

                                                    int pos = 8;
                                                    for (int j = 0; j < regularUser.getNrOfSongs(); j++) {
                                                        HBox box = new HBox(regularUserProfile.removeButtons.get(j), regularUserProfile.songTitles.get(j));
                                                        regularUserProfile.gridPane.add(box, 0, pos);
                                                        regularUserProfile.gridPane.add(regularUserProfile.artistNames.get(j), 2, pos);
                                                        regularUserProfile.gridPane.add(regularUserProfile.albumsTitles.get(j), 3, pos);
                                                        regularUserProfile.gridPane.add(regularUserProfile.duration.get(j), 4, pos);
                                                        pos++;
                                                    }

                                                    int foundArtist=0;
                                                    for(Song userSongs:regularUser.getUserLibrary().userLibrarySongs.values())
                                                    {
                                                        if(userSongs.getArtistName().getName().equals(artist))
                                                        {
                                                            foundArtist = 1;
                                                        }
                                                    }
                                                    if(foundArtist==0)
                                                    {
                                                        regularUser.setNrOfArtists(regularUser.getNrOfArtists()-1);
                                                        regularUserProfile.nrOfArtists.setText("Artists: "+String.valueOf(regularUser.getNrOfArtists()));
                                                    }

                                                    regularUserProfile.nrOfSongs.setText("Songs: " + String.valueOf(regularUser.getNrOfSongs()));
                                                }

                                            });
                                        }

                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

        actionCol.setCellFactory(cellFactory);

        table.getColumns().setAll(column1, column2,column3,column4,column5,actionCol);

        TextField songName=new TextField();
        TextField artistName=new TextField();
        TextField albumName=new TextField();
        TextField genre =new TextField();

        songName.setPromptText("Search song");
        artistName.setPromptText("Search artist");
        albumName.setPromptText("Search album");
        genre.setPromptText("Search genre");

        HBox hbox=new HBox(songName,artistName,albumName,genre);

        hbox.prefWidthProperty().bind(table.widthProperty());

        songName.prefWidthProperty().bind(column1.widthProperty());
        artistName.prefWidthProperty().bind(column2.widthProperty());
        albumName.prefWidthProperty().bind(column3.widthProperty());
        genre.prefWidthProperty().bind(column4.widthProperty());

        FilteredList filteredList = new FilteredList<>(items);
        filteredList.setPredicate(p -> true);

        table.itemsProperty().set(filteredList);
        showSearchedItems(songName,items,table);
        showSearchedItems(artistName,items,table);
        showSearchedItems(albumName,items,table);
        showSearchedItems(genre,items,table);
        column1.setGraphic(songName);
        column2.setGraphic(artistName);
        column3.setGraphic(albumName);
        column4.setGraphic(genre);


        table.prefHeightProperty().bind(this.heightProperty());
        table.prefWidthProperty().bind(this.widthProperty());
        table.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE,null,null)));
        column1.setStyle("-fx-background-color: lightblue;");
        column2.setStyle("-fx-background-color: lightblue;");
        column3.setStyle("-fx-background-color: lightblue;");
        column4.setStyle("-fx-background-color: lightblue;");
        column5.setStyle("-fx-background-color: lightblue;");
        actionCol.setStyle("-fx-background-color: lightblue;");


        Group group=new Group(table,hbox);
        Scene scene = new Scene(group);
        this.setScene(scene);
        this.show();
    }
   public void showSearchedItems(TextField textField, ObservableList<Map.Entry<String,Song>> items,TableView<Map.Entry<String,Song>> table)
   {

      textField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
           if (oldValue != null && (newValue.length() < oldValue.length())) {
               table.setItems(items);
           }
           String value = newValue.toLowerCase();
           ObservableList<Map.Entry<String,Song>> subentries = FXCollections.observableArrayList();
           long count = table.getColumns().stream().count();
           for (int i = 0; i < table.getItems().size(); i++) {
               for (int j = 0; j < count; j++) {
                   String entry = "" + table.getColumns().get(j).getCellData(i);
                   if (entry.toLowerCase().contains(value)) {
                       subentries.add(table.getItems().get(i));
                       break;
                   }
               }
           }
           table.setItems(subentries);
       });
   }
}
