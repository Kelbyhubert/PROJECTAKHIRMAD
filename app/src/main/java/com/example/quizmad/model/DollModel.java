package com.example.quizmad.model;


import android.widget.ImageView;

import java.io.Serializable;

public class DollModel implements Serializable {

    private int dollImage;
    private String dollName , description , dollUID;
    private UserModel dollOwner;

    public DollModel(int dollImage, String dollName, UserModel dollOwner, String description, String dollUID) {
        this.dollImage = dollImage;
        this.dollName = dollName;
        this.dollOwner = dollOwner;
        this.description = description;
        this.dollUID = dollUID;
    }

    public int getDollImage() {
        return dollImage;
    }

    public void setDollImage(int dollImage) {
        this.dollImage = dollImage;
    }

    public String getDollName() {
        return dollName;
    }

    public void setDollName(String dollName) {
        this.dollName = dollName;
    }

    public UserModel getDollOwner() {
        return dollOwner;
    }

    public void setCreatorName(UserModel dollOwner) {
        this.dollOwner = dollOwner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDollUID() {
        return dollUID;
    }

    public void setDollUID(String dollUID) {
        this.dollUID = dollUID;
    }

    @Override
    public String toString() {
        return "DollModel{" +
                "dollImage=" + dollImage +
                ", dollName='" + dollName + '\'' +
                ", creatorName='" + dollOwner + '\'' +
                ", description='" + description + '\'' +
                ", dollUID='" + dollUID + '\'' +
                '}';
    }
}
