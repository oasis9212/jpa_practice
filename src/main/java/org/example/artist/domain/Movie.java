package org.example.artist.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Movie extends  artistItem{


    private String Director;
    private Long Actor;

    public String getDirector() {
        return Director;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public Long getActor() {
        return Actor;
    }

    public void setActor(Long actor) {
        Actor = actor;
    }
}
