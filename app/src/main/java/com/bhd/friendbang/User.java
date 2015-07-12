package com.bhd.friendbang;

import android.media.Image;

import com.parse.ParseUser;

import java.util.Date;

/**
 * Created by sdhond on 2015-07-06.
 */
public class User extends ParseUser {
    private String name;
    private Image profilePic;
    private Date dateOfBirth;

    public User(String name, Date dateOfBirth, String email ){
        this.name = name;
        super.setEmail(email);
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Image getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(Image profilePic) {
        this.profilePic = profilePic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
