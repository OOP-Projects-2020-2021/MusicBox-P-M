package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.io.FileInputStream;


/**
* Application start page with welcome message
*/

public class StartPage extends Stage {
    Button loginButton=new Button("Login");
    Button createButton = new Button("Create account");
    HBox hbox = new HBox(loginButton,createButton);
    StartPage() {

       this.resizableProperty().setValue(Boolean.FALSE);
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(100, 0, 30, 0));
        Image image = new Image("background.png");
        ImageView imageView=new ImageView(image);
        BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
        borderPane.setBackground(new Background(new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bSize)));
        Text text = new Text("Welcome to Music Box!");
        DropShadow shadow = new DropShadow();
        shadow.setOffsetY(2.0);
        loginButton.setTextFill(Color.WHITE);
        loginButton.setStyle("-fx-font: 22 arial; -fx-base: #1c1d1d;");
        createButton.setStyle("-fx-font: 22 arial; -fx-base: #1c1d1d;");
        loginButton.setEffect(shadow);
        createButton.setEffect(shadow);
        createButton.setTextFill(Color.WHITE);

        hbox.setSpacing(50);
        hbox.setAlignment(Pos.CENTER);
        StylingMethods.textStyle(text,30);
        text.setEffect(shadow);

        borderPane.setTop(text);
        borderPane.setCenter(hbox);

        BorderPane.setAlignment(text,Pos.TOP_CENTER);
        Scene scene = new Scene(borderPane, 400, 600, Color.ALICEBLUE);
        this.setScene(scene);
        this.show();

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                close();
                new Login();

            }

        });
        createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                close();
                new NewAccount();
            }
        });
    }
}
