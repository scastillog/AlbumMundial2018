package com.enriquecastillo.albummundial.model;

import android.provider.ContactsContract;

import com.enriquecastillo.albummundial.app.MyApplication;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by szeph on 25/03/18.
 */

public class Board extends RealmObject {

    @PrimaryKey
    private int id;
    @Required
    private String title;
    private String description;
    private int image;
    private RealmList<Stamp> stamps;

    public Board() {
    }

    public Board(String title,String description ,int image) {
        this.id = MyApplication.BoardId.incrementAndGet();
        this.title = title;
        this.description = description;
        this.image = image;
        this.stamps = new RealmList<Stamp>();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public RealmList<Stamp> getStamps() {
        return stamps;
    }

    public void setStamps(RealmList<Stamp> stamps) {
        this.stamps = stamps;
    }
}
