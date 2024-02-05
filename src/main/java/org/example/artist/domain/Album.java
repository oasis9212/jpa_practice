package org.example.artist.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
public class Album extends artistItem {

    private String artist;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
