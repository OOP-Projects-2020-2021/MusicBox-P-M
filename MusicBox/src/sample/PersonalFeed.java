package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PersonalFeed extends Stage {
    GridPane gridPane;
    PersonalFeed(Artist artist){
        this.setWidth(400);
        this.setHeight(600);
        this.setTitle(artist.getUsername() + " Feed");

        gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #e3b0dc, #63dafa);");
        gridPane.setVgap(20);

        artist.getFeed().setPersonalFeed(this);
        Button newPost = new Button("New Post");
        newPost.setTextFill(Color.WHITE);
        newPost.setStyle("-fx-font: 10 arial; -fx-base: #1c1d1d;");
        gridPane.add(newPost,0,0);
        newPost.setAlignment(Pos.CENTER);
        newPost.setPrefSize(70,30);


        int row = 2;
        try {
            BufferedReader in = new BufferedReader(
                    new FileReader("posts.txt"));
            String line;
            while ((line = in.readLine()) != null) {
                String[] postInfo = line.split(",");
                if(postInfo[0].equals(artist.getName()))
                {
                    Label post = new Label(postInfo[1]);
                    post.setStyle("-fx-padding: 10;" +
                            "-fx-border-style: solid inside;" +
                            "-fx-border-width: 2;" +
                            "-fx-border-insets: 5;" +
                            "-fx-border-radius: 5;" +
                            "-fx-border-color: #005eff;");
                    post.setFont(Font.font("Arial", FontWeight.BOLD, 10));
                    gridPane.add(post,0,row);
                    row++;
                    artist.getFeed().getPost().add(postInfo[1]);
                }
            }
        }
        catch (IOException e) {
                System.out.println("File Read Error");
            }


        newPost.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                new NewEntry(artist);
            }
        });

        Scene scene = new Scene(gridPane);
        this.setScene(scene);
        this.show();

    }
}
