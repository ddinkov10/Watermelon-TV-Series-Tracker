package com.watermelon.Repository.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

//Not used now
@Entity
public class Movie extends MediaItem {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String director;

    // Constructors, Getters, and Setters
    public Movie(String title, String description, Date releaseDate, float rating, String director) {
        setTitle(title);
        setDescription(description);
        setReleaseDate(releaseDate);
        setRating(rating);
        this.director = director;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}

