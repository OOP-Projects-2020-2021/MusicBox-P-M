package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ExplorePosts extends Stage {
    ExplorePosts(){
        this.resizableProperty().setValue(Boolean.FALSE);
        this.setWidth(400);
        this.setHeight(600);
        this.setTitle("Artists announcements");
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #e3b0dc, #63dafa);");
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setVgap(20);

        Text title1 = new Text("Artists ");
        Text title2 = new Text("announcements");

        StylingMethods.textStyle(title1,25);
        StylingMethods.textStyle(title2,25);

        gridPane.add(title1,0,0);
        gridPane.add(title2,1,0);

        int row=2;
        try {
            BufferedReader in = new BufferedReader(
                    new FileReader("posts.txt"));
            String line;
            while ((line = in.readLine()) != null) {
                String[] postInfo = line.split(",");
                Text artistName = new Text(postInfo[0]+": ");
                artistName.setFont(Font.font("Arial", FontWeight.BOLD, 15));
                Label post = new Label(postInfo[1]);
                post.setStyle("-fx-padding: 10;" +
                        "-fx-border-style: solid inside;" +
                        "-fx-border-width: 2;" +
                        "-fx-border-insets: 5;" +
                        "-fx-border-radius: 5;" +
                        "-fx-border-color: #005eff;"
                );
                Background background=new Background(new BackgroundFill(StylingMethods.generateRandomColor(),null,null));
                post.setBackground(background);
                post.setFont(Font.font("Arial", FontWeight.BOLD, 10));

                gridPane.add(artistName,0,row);
                gridPane.add(post,1,row);
                row++;
            }
        }
        catch (IOException e) {
            System.out.println("File Read Error");
        }

        Scene scene = new Scene(gridPane);
        this.setScene(scene);
        this.show();

    }


}
