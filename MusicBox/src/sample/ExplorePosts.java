package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
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
        this.setWidth(400);
        this.setHeight(600);
        this.setTitle("Artists announcements");


        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #e3b0dc, #63dafa);");
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setVgap(20);

        Text title1 = new Text("Artists ");
        Text title2 = new Text("announcements");

        textStyle(title1);
        textStyle(title2);

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
                Text post = new Text(postInfo[1]);
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

    public void textStyle(Text text){
        text.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        text.setFill(Color.BLACK);
        text.setStroke(Color.BLUEVIOLET);
        text.setStrokeWidth(0.5);
        text.setFontSmoothingType(FontSmoothingType.LCD);
    }
}
