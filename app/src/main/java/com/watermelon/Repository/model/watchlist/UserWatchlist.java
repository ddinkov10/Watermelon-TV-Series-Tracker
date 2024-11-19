package com.watermelon.Repository.model.watchlist;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;

import com.watermelon.Repository.model.userProfile.UserProfile;

//@Entity(foreignKeys = @ForeignKey(entity = UserProfile.class,
//        parentColumns = "id",
//        childColumns = "profileId",
//        onDelete = ForeignKey.CASCADE))
@Entity
public class UserWatchlist {
    @PrimaryKey(autoGenerate = true)
    private int id;
//    private int profileId; // Foreign key

    // Constructors, Getters, and Setters
//    public UserWatchlist(int profileId) {
//        this.profileId = profileId;
//    }

    public UserWatchlist() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public int getProfileId() {
//        return profileId;
//    }

//    public void setProfileId(int profileId) {
//        this.profileId = profileId;
//    }
}
