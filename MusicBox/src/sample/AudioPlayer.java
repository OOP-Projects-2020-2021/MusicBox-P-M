package sample;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AudioPlayer extends Stage {
    AudioPlayer(MediaPlayer mediaPlayer,MediaView mediaView, Song song){
        this.setWidth(500);
        this.setHeight(200);
        Text title=new Text("Playing: "+song.getTitle());
        StylingMethods.textStyle(title,30);
        Text artist=new Text(song.getArtistName().getName());
        artist.setStyle("-fx-font: 15 arial;");
        artist.setFill(Color.MIDNIGHTBLUE);
        GridPane gridPane=new GridPane();
        int duration=Integer.parseInt(song.getMinutes())*60+Integer.parseInt(song.getSeconds());
        Text songTime=new Text();
        gridPane.add(songTime,3,4);
        songTime.setStyle("-fx-font: 15 arial;");
        songTime.setFill(Color.MIDNIGHTBLUE);
        int enableTimeThread=1;
        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() {

                for (int i = 1; i < duration ; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int min=i/60;
                    int sec=i%60;
                    songTime.setText("  "+min+":"+sec);
                    updateProgress(i, duration);
                }
                return null;
            }
        };
        ProgressBar updProg = new ProgressBar();
        updProg.setMaxWidth(300);
        updProg.progressProperty().bind(task.progressProperty());
        updProg.setLayoutX(20);
        updProg.setLayoutY(30);
        updProg.setPrefWidth(Double.MAX_VALUE);
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();

        gridPane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #63dafa, #e3b0dc);-fx-background-radius: 90;");
        gridPane.add(title, 2,0);
        gridPane.add(artist, 2,1);
        gridPane.add(updProg,2,4);
        gridPane.add(mediaView,2,3);
        gridPane.setAlignment(Pos.CENTER);
        Scene scene = new Scene(gridPane);
        scene.setFill(Color.LIGHTBLUE);
        Button pause=new Button("Pause");
        Button stop=new Button("Stop");
        HBox hBox=new HBox(pause,stop);
        gridPane.add(hBox,2,7);
        pause.setTextFill(Color.WHITE);
        pause.setStyle("-fx-font: 10 arial; -fx-base: #1c1d1d;");
        stop.setTextFill(Color.WHITE);
        stop.setStyle("-fx-font: 10 arial; -fx-base: #1c1d1d;");
        pause.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if(pause.getText().equals("Pause")) {
                    mediaPlayer.pause();
                    pause.setText("Resume");
                   th.suspend();

                }
                else if(pause.getText().equals("Resume"))
                {
                    mediaPlayer.play();
                    pause.setText("Pause");
                    th.resume();
                }

            }
        });
        stop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                th.stop();
                mediaPlayer.stop();
                close();

            }
        });
        this.initStyle(StageStyle.UNDECORATED);
        this.setScene(scene);
        this.show();
    }

}

