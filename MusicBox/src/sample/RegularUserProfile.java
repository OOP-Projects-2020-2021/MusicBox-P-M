package sample;

import javafx.stage.Stage;

public class RegularUserProfile extends Stage{
    RegularUserProfile(RegularUser user)
    {
        this.setWidth(400);
        this.setHeight(600);
        this.setTitle(user.getUsername() + " Profile");
        this.show();
    }
}
