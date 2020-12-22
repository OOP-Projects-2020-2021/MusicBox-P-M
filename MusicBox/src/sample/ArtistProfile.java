package sample;

import javafx.stage.Stage;

public class ArtistProfile extends Stage {
    ArtistProfile(Artist artist)
    {
        this.setWidth(400);
        this.setHeight(600);
        this.setTitle(artist.getUsername() + " Profile");
        this.show();
    }
}
