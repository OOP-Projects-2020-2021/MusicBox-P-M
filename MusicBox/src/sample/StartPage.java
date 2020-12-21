package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;


/**
* Application start page with welcome message
*/

public class StartPage extends Stage {
    Button loginButton=new Button("Login");
    Button createButton = new Button("Create account");
    HBox hbox = new HBox(loginButton,createButton);
    StartPage() {

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(100, 0, 30, 0));


        Image image = new Image("https://lh3.googleusercontent.com/proxy/9frRW8q5oujluFRBFta1k3Ro_HNwRTUSwxpneZqKXiR_x_2QkobLW59Hv0wuqsqCbzpkQkqHoL-Jgj758no8Cw-f5H0n8PawAUEmEBJP2Yx6qflGLjpWDUzD0q0-OgF8_-51");
        BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
        borderPane.setBackground(new Background(new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bSize)));


        Text text = new Text("Welcome to Music Box!");

        //Buttons styling
        //Creating the inner shadow effect
        //Creating the drop shadow effect
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

        text.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        text.setFill(Color.BLACK);
        text.setStroke(Color.BLUEVIOLET);
        text.setStrokeWidth(0.5);
        text.setFontSmoothingType(FontSmoothingType.LCD);
        //Setting the effect to the text
        text.setEffect(shadow);

        borderPane.setTop(text);
        borderPane.setCenter(hbox);

        BorderPane.setAlignment(text,Pos.TOP_CENTER);
        //borderPane.setStyle("-fx-background-color:transparent");
        //borderPane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #63dafa, #e3b0dc);-fx-background-radius: 90;");

        Scene scene = new Scene(borderPane, 400, 600, Color.ALICEBLUE);
        this.setScene(scene);
        this.show();

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                close();
                new Login();

            }//end action

        });
        createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                close();
                new NewAccount();
            }//end action
        });


    }
}


