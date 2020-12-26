package sample;

import java.util.ArrayList;

public class ArtistFeed {
    private ArrayList<String> post=new ArrayList<>();
    private PersonalFeed personalFeed;
    public ArrayList<String> getPost() {
        return post;
    }
    public void setPost(ArrayList<String> post) {
        this.post = post;
    }

    public PersonalFeed getPersonalFeed() {
        return personalFeed;
    }

    public void setPersonalFeed(PersonalFeed personalFeed) {
        this.personalFeed = personalFeed;
    }
}
