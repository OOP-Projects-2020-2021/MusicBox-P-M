package sample;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class StartPage extends Stage {
    Button loginButton=new Button("Login");
    Button createButton = new Button("Create account");
    HBox hbox = new HBox();
    StartPage(){
        hbox.getChildren().add(loginButton);
        hbox.getChildren().add(createButton);
        hbox.setSpacing(50);
        Group root = new Group(hbox);
        Scene scene = new Scene(root, 400, 400, Color.BROWN);
        hbox.setAlignment(Pos.CENTER);
        hbox.setLayoutX(100);
        hbox.setLayoutY(300);
        Text text = new Text();
        text.setFont(new Font(20));
        text.setX(50);
        text.setY(150);
        text.setText("Welcome to MusicBox!");
        root.getChildren().add(text);
        this.setScene(scene);
        this.show();

    }
}
