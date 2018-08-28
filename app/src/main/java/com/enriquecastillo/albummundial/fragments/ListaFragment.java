package com.enriquecastillo.albummundial.fragments;


import android.content.Intent;
import android.os.Bundle;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.enriquecastillo.albummundial.R;
import com.enriquecastillo.albummundial.activities.StampActivity;
import com.enriquecastillo.albummundial.adapter.BoardAdapter;
import com.enriquecastillo.albummundial.model.Board;
import com.enriquecastillo.albummundial.model.Stamp;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;


public class ListaFragment extends Fragment implements RealmChangeListener<RealmResults<Board>>   {

    private Realm realm;
    private RealmResults<Board> boards;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager layoutManager;

    private static Bundle mBundleRecyclerViewState;
    private final String KEY_RECYCLER_STATE = "recycler_state";

    public ListaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        realm = Realm.getDefaultInstance();
        boards = realm.where(Board.class).findAll();

        recyclerView = view.findViewById(R.id.recyclerViewList);


        adapter = new BoardAdapter(getActivity(), R.layout.cardview_board, boards, new BoardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Board board, int position) {
                Intent intent = new Intent(getActivity(), StampActivity.class);
                intent.putExtra("id", board.getId());
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        boards.addChangeListener(this);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        // save RecyclerView state
        mBundleRecyclerViewState = new Bundle();
        Parcelable listState = recyclerView.getLayoutManager().onSaveInstanceState();
        mBundleRecyclerViewState.putParcelable(KEY_RECYCLER_STATE, listState);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mBundleRecyclerViewState != null) {
            Parcelable listState = mBundleRecyclerViewState.getParcelable(KEY_RECYCLER_STATE);
            recyclerView.getLayoutManager().onRestoreInstanceState(listState);
        }
    }

    @Override
    public void onChange(RealmResults<Board> boards) {
        adapter.notifyDataSetChanged();
    }
}
