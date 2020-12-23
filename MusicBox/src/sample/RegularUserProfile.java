package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RegularUserProfile extends Stage{
    RegularUserProfile(RegularUser user)
    {
        this.setWidth(400);
        this.setHeight(600);
        this.setTitle(user.getUsername() + " Profile");
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        //gridPane.setAlignment(Pos.CENTER);
        Image image = new Image("GenericProfile.png");
        ImageView imageView=new ImageView(image);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        gridPane.add(imageView,0,0);
        Text name=new Text(user.getName());
        Text title=new Text("'s Music Box");
        textDesign(name,20);
        textDesign(title,10);
        gridPane.add(name, 2, 0);
        gridPane.add(title,3,0);

        Text nrOfSongs=new Text("Songs: "+String.valueOf(user.getNrOfSongs()));
        Text nrOfPlaylists=new Text("Playlists: "+String.valueOf(user.getNrOfPlaylists()));
        Text nrOfArtists=new Text("Artists: "+String.valueOf(user.getNrOfArtists()));
        textDesign(nrOfSongs,15);
        textDesign(nrOfPlaylists,15);
        textDesign(nrOfArtists,15);

        gridPane.add(nrOfSongs, 0, 1);
        gridPane.add(nrOfPlaylists,0,2);
        gridPane.add(nrOfArtists,0,3);
        gridPane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #63dafa, #e3b0dc);");
        Scene scene = new Scene(gridPane);
        this.setScene(scene);
        this.show();

    }
    public void textDesign(Text text, int font)
    {

        text.setFont(Font.font("Arial", FontWeight.BOLD, font));
        text.setFill(Color.BLACK);
        text.setStroke(Color.ROYALBLUE);
        text.setStrokeWidth(0.5);
        text.setFontSmoothingType(FontSmoothingType.LCD);
    }
}
