package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.sql.rowset.spi.SyncFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Form for creating a new account
 */

public class NewAccount extends Stage {
   NewAccount() {

       this.setWidth(400);
       this.setHeight(600);
       Text textName = new Text("Name:");
       Text textUsername = new Text("Username:");
       Text textPassword = new Text("Password:");
       Text textConfirmPassword = new Text("Confirm password:");

       TextField textFieldName = new TextField();
       TextField textFieldUsername = new TextField();
       PasswordField textFieldPassword = new PasswordField();
      PasswordField textFieldConfirmPassword = new PasswordField();

       Button createButton = new Button("Create");
       Button clearButton = new Button("Clear");

       RadioButton regularUserType = new RadioButton();
       regularUserType.setText("Regular User");
       RadioButton artistType = new RadioButton("Artist");

       final ToggleGroup group = new ToggleGroup();
       regularUserType.setToggleGroup(group);
       regularUserType.setSelected(true);
       artistType.setToggleGroup(group);

       Text nameAlert=new Text("Name required!");
       nameAlert.setStyle("-fx-font: 10 arial; -fx-base: red;");
       nameAlert.setVisible(false);
       Text usernameAlert=new Text("Username required!");
       usernameAlert.setStyle("-fx-font: 10 arial; -fx-base: RED;");
       usernameAlert.setVisible(false);
       Text passwordAlert=new Text("Password required!");
       passwordAlert.setStyle("-fx-font: 10 arial; -fx-base: RED;");
       passwordAlert.setVisible(false);
       Text confirmPasswordAlert=new Text("Confirmation required!");
      confirmPasswordAlert.setStyle("-fx-font: 10 arial; -fx-base: RED;");
      confirmPasswordAlert.setVisible(false);
       GridPane gridPane = new GridPane();
       gridPane.setPadding(new Insets(10, 10, 10, 10));
       gridPane.setVgap(5);
       gridPane.setHgap(5);
       gridPane.setAlignment(Pos.CENTER);


       gridPane.add(textName, 0, 0);
       gridPane.add(textFieldName, 1, 0);
       gridPane.add(nameAlert,2,0);

       gridPane.add(textUsername, 0, 1);
       gridPane.add(textFieldUsername, 1, 1);
       gridPane.add(usernameAlert,2,1);

       gridPane.add(textPassword, 0, 2);
       gridPane.add(textFieldPassword, 1, 2);
       gridPane.add(passwordAlert,2,2);

       gridPane.add(textConfirmPassword, 0, 3);
       gridPane.add(textFieldConfirmPassword, 1, 3);
       gridPane.add(confirmPasswordAlert,2,3);

       gridPane.add(regularUserType, 0, 5);
       gridPane.add(artistType, 1, 5);

       gridPane.add(createButton, 1, 10);
       gridPane.add(clearButton, 1, 13);
       textFieldName.setPromptText("Name");
       textFieldUsername.setPromptText("Username");
       textFieldPassword.setPromptText("Password");
       textFieldConfirmPassword.setPromptText("Password");
       DropShadow shadow = new DropShadow();
       shadow.setOffsetY(2.0);
       createButton.setTextFill(Color.WHITE);
       clearButton.setStyle("-fx-font: 10 arial; -fx-base: #1c1d1d;");
       createButton.setStyle("-fx-font: 10 arial; -fx-base: #1c1d1d;");
       clearButton.setEffect(shadow);
       createButton.setEffect(shadow);
       clearButton.setTextFill(Color.WHITE);
       gridPane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #63dafa, #e3b0dc);");
       Scene scene = new Scene(gridPane);
       this.setScene(scene);
       this.show();
       createButton.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent t) {
               String name=textFieldName.getText();
               String username=textFieldUsername.getText();
               String password=textFieldPassword.getText();
               RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
               String value = selectedRadioButton.getText();
               int existingUser=0;
               if(validation(textFieldName,textFieldUsername,textFieldPassword,textFieldConfirmPassword,nameAlert,usernameAlert,passwordAlert,confirmPasswordAlert)) {
                   if (textFieldPassword.getText().equals(textFieldConfirmPassword.getText())) {
                       if (value.equals("Regular User")) {
                           //RegularUser newUser = new RegularUser(username, password, name, "", 0);
                           //add array

                           try {
                               File myObj = new File(textFieldUsername.getText()+".txt");
                               if (myObj.createNewFile()) {
                                   System.out.println("File created: " + myObj.getName());
                               } else {
                                   Alert fail= new Alert(Alert.AlertType.INFORMATION);
                                   fail.setHeaderText("Existing Username");
                                   fail.setContentText("This account already exists!");
                                   fail.showAndWait();
                                   existingUser=1;
                               }
                           } catch (IOException e) {
                               System.out.println("An error occurred.");
                               e.printStackTrace();
                           }
                           try {
                               FileWriter myWriter = new FileWriter(username+".txt", true);
                               myWriter.write("0 0 0\n");
                               myWriter.close();

                           } catch (IOException e) {
                               System.out.println("Error!");
                               e.printStackTrace();
                           }

                           if(existingUser==0) {
                               try {
                                   FileWriter myWriter = new FileWriter("users.txt", true);
                                   myWriter.write(name + "," + username + "," + password + "," + "0\n");
                                   myWriter.close();

                               } catch (IOException e) {
                                   System.out.println("Error!");
                                   e.printStackTrace();
                               }
                           }

                       } else if (value.equals("Artist")) {
                           //Artist newArtist = new Artist(username, password, name, "", 0, 0, 0, 1);

                           try {
                               File myObj = new File(textFieldUsername.getText()+".txt");
                               if (myObj.createNewFile()) {
                                   System.out.println("File created: " + myObj.getName());
                               } else {
                                   Alert fail= new Alert(Alert.AlertType.INFORMATION);
                                   fail.setHeaderText("Existing Username");
                                   fail.setContentText("This account already exists!");
                                   fail.showAndWait();
                                   existingUser=1;
                               }
                           } catch (IOException e) {
                               System.out.println("An error occurred.");
                               e.printStackTrace();
                           }

                           try {
                               FileWriter myWriter = new FileWriter(username+".txt", true);
                               myWriter.write("0 0 0\n");
                               myWriter.close();

                           } catch (IOException e) {
                               System.out.println("Error!");
                               e.printStackTrace();
                           }

                           if(existingUser==0)
                           {
                               try {
                                   FileWriter myWriter = new FileWriter("users.txt", true);
                                   myWriter.write(name + "," + username + "," + password + "," + "1\n");
                                   myWriter.close();

                               } catch (IOException e) {
                                   System.out.println("Error!");
                                   e.printStackTrace();
                               }
                           }
                       }
                       if(existingUser==0) {
                           new Login();
                           close();
                       }

                   }else{
                       Alert fail= new Alert(Alert.AlertType.INFORMATION);
                       fail.setHeaderText("Password Failure");
                       fail.setContentText("Confirm password mismatch!");
                       fail.showAndWait();
                   }
               }
               // new NewAccount();
           }//end action
       });
       clearButton.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent t) {
               textFieldName.clear();
               textFieldUsername.clear();
               textFieldPassword.clear();
               textFieldConfirmPassword.clear();
               nameAlert.setVisible(false);
               usernameAlert.setVisible(false);
               passwordAlert.setVisible(false);
               confirmPasswordAlert.setVisible(false);

           }
       });
   }
    public boolean validation(TextField name, TextField username, PasswordField password, PasswordField confirmPassword,Text nameAlert, Text usernameAlert,
    Text passwordAlert,Text confirmPasswordAlert){
        int ok=0;
        if(name.getText().trim().isEmpty() ) {
            nameAlert.setVisible(true);
            ok=1;
        }else{
            nameAlert.setVisible(false);
        }
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
        if(confirmPassword.getText().trim().isEmpty() ) {
            confirmPasswordAlert.setVisible(true);
            ok=1;
        }else{
            confirmPasswordAlert.setVisible(false);
        }
        return ok == 0;
    }
}
