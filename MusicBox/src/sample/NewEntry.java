package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

public class NewEntry extends Stage {
    NewEntry(Artist artist)
    {
        this.setWidth(650);
        this.setHeight(150);
        this.setTitle(artist.getUsername() + "new post");

        TextField newPost = new TextField();
        newPost.setPrefSize(600,85);

        Button enter = new Button("New Post");
        enter.setTextFill(Color.WHITE);
        enter.setStyle("-fx-font: 10 arial; -fx-base: #1c1d1d;");


        Group group = new Group(newPost,enter);
        enter.setLayoutX(530);
        enter.setLayoutY(90);

        enter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                try {
                    FileWriter postsWriter = new FileWriter("posts.txt",true);
                    postsWriter.write(artist.getName()+","+ newPost.getText()+"\n");
                    postsWriter.close();

                    Label post = new Label(newPost.getText());
                    post.setStyle("-fx-padding: 10;" +
                            "-fx-border-style: solid inside;" +
                            "-fx-border-width: 2;" +
                            "-fx-border-insets: 5;" +
                            "-fx-border-radius: 5;" +
                            "-fx-border-color: #005eff;");
                    post.setFont(Font.font("Arial", FontWeight.BOLD, 10));
                    artist.getFeed().getPost().add(newPost.getText());
                    artist.getFeed().getPersonalFeed().gridPane.add(post,0,2+artist.getFeed().getPost().size());

                    close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Scene scene = new Scene(group);
        scene.setFill(Color.LAVENDER);
        this.setScene(scene);
        this.show();

    }
}
