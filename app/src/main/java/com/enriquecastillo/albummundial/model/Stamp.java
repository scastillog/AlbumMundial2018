package com.enriquecastillo.albummundial.model;

import com.enriquecastillo.albummundial.app.MyApplication;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by szeph on 24/03/18.
 */

public class Stamp extends RealmObject {

    @PrimaryKey
    private int id;
    private int numOfStamp;
    private boolean gotIt;

    public Stamp() {
    }

    public Stamp(int numOfStamp, boolean gotIt) {
        this.id = MyApplication.StampId.incrementAndGet();
        this.numOfStamp = numOfStamp;
        this.gotIt = gotIt;
    }

    public int getId() {
        return id;
    }

    public int getNumOfStamp() {
        return numOfStamp;
    }

    public void setNumOfStamp(int numOfStamp) {
        this.numOfStamp = numOfStamp;
    }

    public boolean isGotIt() {
        return gotIt;
    }

    public void setGotIt(boolean gotIt) {
        this.gotIt = gotIt;
    }
}
