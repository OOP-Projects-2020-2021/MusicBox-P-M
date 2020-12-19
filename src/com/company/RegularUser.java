package com.company;

import java.util.HashSet;
import java.util.Set;

public class RegularUser extends User{
    private Set<Artist> listOfArtists = new HashSet<Artist>();
    public RegularUser(String username,String password, String name, String imagePath)
    {
        super(username,password,name,imagePath);

    }
    public Set<Artist> getListOfArtists() {
        return listOfArtists;
    }
    public void setListOfArtists(Set<Artist> listOfArtists) {
        this.listOfArtists = listOfArtists;
    }

}
