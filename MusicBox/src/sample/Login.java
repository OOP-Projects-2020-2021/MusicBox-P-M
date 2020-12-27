package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.Scanner;

/**
 * Login page for existing users
 */
public class Login extends Stage {
    Login() {
        this.resizableProperty().setValue(Boolean.FALSE);
        this.setTitle("Login");
        this.setWidth(400);
        this.setHeight(600);


        Text textUsername = new Text("Username:");
        Text textPassword = new Text("Password:");
        TextField textFieldUsername = new TextField();
        PasswordField textFieldPassword = new PasswordField();
        textFieldPassword.setPromptText("Password");
        textFieldUsername.setPromptText("Username");


        Button loginButton = new Button("Log in");
        Button clearButton = new Button("Clear");
        Button backButton = new Button("Back");

        //Styling
        DropShadow shadow = new DropShadow();
        shadow.setOffsetY(2.0);
        loginButton.setTextFill(Color.WHITE);
        loginButton.setStyle("-fx-font: 10 arial; -fx-base: #1c1d1d;");
        clearButton.setStyle("-fx-font: 10 arial; -fx-base: #1c1d1d;");
        loginButton.setEffect(shadow);
        clearButton.setEffect(shadow);
        clearButton.setTextFill(Color.WHITE);
        backButton.setTextFill(Color.WHITE);
        backButton.setStyle("-fx-font: 10 arial; -fx-base: #1c1d1d;");
       backButton.setEffect(shadow);


        Text usernameAlert=new Text("Name required!");
        usernameAlert.setStyle("-fx-font: 10 arial; -fx-base: red;");
        usernameAlert.setVisible(false);

        Text passwordAlert=new Text("Password required!");
        passwordAlert.setStyle("-fx-font: 10 arial; -fx-base: red;");
        passwordAlert.setVisible(false);

        Text title=new Text("Music Box Login");
        title.setUnderline(true);
        StylingMethods.textStyle(title,25);

        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(0, 10, 10, 70));

        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.add(title,1,0);
        title.setTextAlignment(TextAlignment.CENTER);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(textUsername, 0, 10);
        gridPane.add(textFieldUsername, 1, 10);
        gridPane.add(usernameAlert,2,10);

        gridPane.add(textPassword, 0, 11);
        gridPane.add(textFieldPassword, 1, 11);
        gridPane.add(passwordAlert,2,11);

        HBox hBox=new HBox(clearButton,backButton);
        hBox.setSpacing(15);
        gridPane.add(loginButton, 0, 20);
        gridPane.add(hBox,1,20);
        gridPane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #63dafa, #e3b0dc);-fx-background-radius: 90;");

        Scene scene = new Scene(gridPane);
        this.setScene(scene);
        this.show();

        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                textFieldUsername.clear();
                textFieldPassword.clear();
                usernameAlert.setVisible(false);
                passwordAlert.setVisible(false);
            }
        });
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                new StartPage();
                close();
            }
        });

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if(validation(textFieldUsername,textFieldPassword,usernameAlert,passwordAlert))
                {
                    int foundUser = 0;
                    try {
                        BufferedReader in = new BufferedReader(
                                new FileReader("users.txt"));
                        String line;
                        while ((line = in.readLine())!= null) {
                            String[] userInfo=line.split(",");
                            if(userInfo[1].equals(textFieldUsername.getText()))
                            {
                                foundUser = 1;
                                if(userInfo[2].equals(textFieldPassword.getText()))
                                {
                                    if(userInfo[3].equals("0"))
                                    {

                                        Scanner scanner=new Scanner(new File(textFieldUsername.getText()+".txt"));
                                        int nrOfSongs = scanner.nextInt();
                                        int nrOfPlaylists=scanner.nextInt();
                                        int nrOfArtists=scanner.nextInt();
                                        RegularUser user = new RegularUser(userInfo[1],userInfo[2],userInfo[0],"",0,nrOfSongs,nrOfPlaylists,nrOfArtists);
                                        new RegularUserProfile(user);
                                    }
                                    else {
                                        if (userInfo[3].equals("1")) {
                                            Scanner scanner=new Scanner(new File(textFieldUsername.getText()+".txt"));
                                            int nrOfAlbums = scanner.nextInt();
                                            int nrOfSongs=scanner.nextInt();
                                            int nrOfSubscribers=scanner.nextInt();
                                            Artist artist = new Artist(userInfo[1], userInfo[2], userInfo[0], "", nrOfAlbums, nrOfSongs, nrOfSubscribers, 1);
                                            new ArtistProfile(artist);
                                        }
                                    }
                                    close();
                                    //open user profile
                                }
                                else
                                {
                                    Alert fail= new Alert(Alert.AlertType.INFORMATION);
                                    fail.setHeaderText("Password Failure");
                                    fail.setContentText("Incorrect password!");
                                    fail.showAndWait();
                                }
                            }
                        }
                        if(foundUser==0)
                        {
                            Alert fail= new Alert(Alert.AlertType.INFORMATION);
                            fail.setHeaderText("Username Failure");
                            fail.setContentText("Username doesn't exist!");
                            fail.showAndWait();
                        }
                        in.close();
                    } catch (IOException e) {
                        System.out.println("File Read Error");
                    }
                }
            }
        });

    }

    public boolean validation(TextField username, PasswordField password, Text usernameAlert, Text passwordAlert){
        int ok=0;
        if(username.getText().trim().isEmpty() ) {
            usernameAlert.setVisible(true);
            ok=1;
        }else{
            usernameAlert.setVisible(false);
        }
        if(password.getText().trim().isEmpty() ) {
            passwordAlert.setVisible(true);
            ok=1;
        }else{
            passwordAlert.setVisible(false);
        }
        return ok == 0;
    }

}
