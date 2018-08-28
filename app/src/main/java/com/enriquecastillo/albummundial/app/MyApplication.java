package com.enriquecastillo.albummundial.app;


import android.app.Application;

import com.enriquecastillo.albummundial.model.Board;
import com.enriquecastillo.albummundial.model.Stamp;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;


/**
 * Created by szeph on 24/03/18.
 */

public class MyApplication extends Application {

    public static AtomicInteger StampId = new AtomicInteger();
    public static AtomicInteger BoardId = new AtomicInteger();


    @Override
    public void onCreate() {
        super.onCreate();

        setUpDBRealm();

        Realm realm = Realm.getDefaultInstance();
        BoardId = setItemId(realm, Board.class);
        StampId = setItemId(realm, Stamp.class);
        realm.close();

    }

    private void setUpDBRealm() {
        Realm.init(getApplicationContext());
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

    private <T extends RealmObject> AtomicInteger setItemId(Realm realm, Class<T> anyClass){
        RealmResults<T> results = realm.where(anyClass).findAll();
        return (results.size() > 0)? new AtomicInteger(results.max("id").intValue()) : new AtomicInteger();
    }
}
