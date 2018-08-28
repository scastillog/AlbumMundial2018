package com.enriquecastillo.albummundial.fragments;


import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.enriquecastillo.albummundial.R;
import com.enriquecastillo.albummundial.adapter.SpacesItemDecoration;
import com.enriquecastillo.albummundial.adapter.StampAdapter;
import com.enriquecastillo.albummundial.model.Board;
import com.enriquecastillo.albummundial.model.Stamp;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class CheckedFragment extends Fragment implements RealmChangeListener<RealmResults<Board>> {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private Realm realm;
    private RealmResults<Stamp> stamps;

    private List<Stamp> list = new ArrayList<>();

    private static String MESSAGE_CHECK = "CHECK";
    private static Bundle mBundleRecyclerViewState;
    private final String KEY_RECYCLER_STATE = "recycler_state";

    private TextView txtQuantity;

    public CheckedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_checked, container, false);

        txtQuantity = (TextView) view.findViewById(R.id.txtQuantityChecked);

        realm = Realm.getDefaultInstance();
        stamps = realm.where(Stamp.class).findAll();

        for (int j = 0; j < stamps.size(); j++) {
            if (stamps.get(j).isGotIt()) {
                list.add(stamps.get(j));
            }
        }

        txtQuantity.setText("Tienes "+ list.size()+ " laminas de 669.");

        recyclerView = view.findViewById(R.id.recyclerViewCheck);

        layoutManager = new GridLayoutManager(getContext(), calculateNoOfColumns(getContext()), LinearLayoutManager.VERTICAL, false);

        adapter = new StampAdapter(getActivity(), R.layout.carview_stamp_little, list, new StampAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Stamp stamp, int position, boolean checked) {
                editStamps(stamp, checked, position);
            }
        }, MESSAGE_CHECK);

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing_little);
        recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int numColumns = (int) (dpWidth / 60);
        return numColumns;
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

    private void editStamps(Stamp stamp, boolean checked, int position) {
        realm.beginTransaction();
        stamp.setGotIt(checked);
        realm.copyToRealmOrUpdate(stamp);
        realm.commitTransaction();
    }
}
