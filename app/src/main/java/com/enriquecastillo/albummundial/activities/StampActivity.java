package com.enriquecastillo.albummundial.activities;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;

import com.enriquecastillo.albummundial.R;
import com.enriquecastillo.albummundial.adapter.SpacesItemDecoration;
import com.enriquecastillo.albummundial.adapter.StampAdapter;
import com.enriquecastillo.albummundial.model.Board;
import com.enriquecastillo.albummundial.model.Stamp;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;

public class StampActivity extends AppCompatActivity implements RealmChangeListener<Board> {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private Realm realm;
    private RealmList<Stamp> stamps;

    private Board board;
    private  int idBoard;

    private static String MESSAGE_STAMP = "STAMP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stamp);

        realm = Realm.getDefaultInstance();

        recyclerView = findViewById(R.id.recyclerView);

        if (getIntent().getExtras() != null){
            idBoard = getIntent().getExtras().getInt("id");
        }

        board = realm.where(Board.class).equalTo("id", idBoard).findFirst();
        stamps = board.getStamps();

        layoutManager = new GridLayoutManager(this, calculateNoOfColumns(this), LinearLayoutManager.VERTICAL, false);

        adapter = new StampAdapter(this, R.layout.cardview_stamp, stamps, new StampAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Stamp stamp, int position, boolean checked) {
                editStamps(stamp, checked, position);
            }
        }, MESSAGE_STAMP);

        setIconOnActivity();

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        board.addChangeListener(this);
    }

    private void editStamps(Stamp stamp, boolean checked, int position) {
        realm.beginTransaction();
        stamp.setGotIt(checked);
        realm.copyToRealmOrUpdate(stamp);
        realm.commitTransaction();
    }

    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int numColumns = (int) (dpWidth / 80);
        return numColumns;
    }

    @Override
    public void onChange(Board board) {
        adapter.notifyDataSetChanged();
    }

    private void setIconOnActivity() {
        getSupportActionBar().setTitle(board.getTitle());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
