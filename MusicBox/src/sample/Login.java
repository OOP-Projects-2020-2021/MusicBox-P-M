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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Login page for existing users
 */
public class Login extends Stage {
    Login() {
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

        //Styling
        DropShadow shadow = new DropShadow();
        shadow.setOffsetY(2.0);
        loginButton.setTextFill(Color.WHITE);
        loginButton.setStyle("-fx-font: 10 arial; -fx-base: #1c1d1d;");
        clearButton.setStyle("-fx-font: 10 arial; -fx-base: #1c1d1d;");
        loginButton.setEffect(shadow);
        clearButton.setEffect(shadow);
        clearButton.setTextFill(Color.WHITE);


        Text usernameAlert=new Text("Name required!");
        usernameAlert.setStyle("-fx-font: 10 arial; -fx-base: red;");
        usernameAlert.setVisible(false);

        Text passwordAlert=new Text("Password required!");
        passwordAlert.setStyle("-fx-font: 10 arial; -fx-base: red;");
        passwordAlert.setVisible(false);


        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(10, 10, 10, 10));

        gridPane.setVgap(5);
        gridPane.setHgap(5);

        gridPane.setAlignment(Pos.CENTER);


        gridPane.add(textUsername, 0, 0);
        gridPane.add(textFieldUsername, 1, 0);
        gridPane.add(usernameAlert,2,0);

        gridPane.add(textPassword, 0, 1);
        gridPane.add(textFieldPassword, 1, 1);
        gridPane.add(passwordAlert,2,1);

        gridPane.add(loginButton, 0, 10);
        gridPane.add(clearButton, 1, 10);
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
                                        RegularUser user = new RegularUser(userInfo[1],userInfo[2],userInfo[0],"",0);
                                        new RegularUserProfile(user);
                                    }
                                    else {
                                        if (userInfo[3].equals("1")) {
                                            Artist artist = new Artist(userInfo[1], userInfo[2], userInfo[0], "", 0, 0, 0, 1);
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
