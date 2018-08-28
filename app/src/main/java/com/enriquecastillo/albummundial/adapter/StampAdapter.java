package com.enriquecastillo.albummundial.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.enriquecastillo.albummundial.R;
import com.enriquecastillo.albummundial.model.Stamp;

import java.util.List;

import static com.enriquecastillo.albummundial.R.color.colorPrimaryLight;

/**
 * Created by szeph on 24/03/18.
 */

public class StampAdapter extends RecyclerView.Adapter<StampAdapter.ViewHolder> {

    private Activity activity;
    private int layout;
    private List<Stamp> stamps;
    private OnItemClickListener listener;
    private String message;

    private static String MESSAGE_STAMP = "STAMP";
    private static String MESSAGE_CHECK = "CHECK";
    private static String MESSAGE_NO_CHECK = "NOCHECK";

    public StampAdapter(Activity activity, int layout, List<Stamp> stamps, OnItemClickListener listener, String message) {
        this.activity = activity;
        this.layout = layout;
        this.stamps = stamps;
        this.listener = listener;
        this.message = message;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(layout, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(stamps.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return stamps.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public Button cardButton;


        public ViewHolder(View itemView) {
            super(itemView);
            cardButton = (Button) itemView.findViewById(R.id.buttonCard);

        }

        public void bind(final Stamp stamp, final OnItemClickListener listener) {
            if (stamp.isGotIt()) {
                this.cardButton.setText(stamp.getNumOfStamp() + "");
                this.cardButton.setBackgroundColor(itemView.getResources().getColor(R.color.cheched));

            } else {
                this.cardButton.setText(stamp.getNumOfStamp() + "");
                this.cardButton.setBackgroundColor(itemView.getResources().getColor(R.color.nocheched));

            }

            if (message.equals(MESSAGE_STAMP)) {
                this.cardButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (stamp.isGotIt()) {
                            cardButton.setBackgroundColor(itemView.getResources().getColor(R.color.nocheched));
                            listener.onItemClick(stamp, getAdapterPosition(), false);
                        } else {
                            cardButton.setBackgroundColor(itemView.getResources().getColor(R.color.cheched));
                            listener.onItemClick(stamp, getAdapterPosition(), true);
                        }
                    }
                });
            } else if (message.equals(MESSAGE_CHECK)) {
                this.cardButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (stamp.isGotIt()) {
                            cardButton.setBackgroundColor(itemView.getResources().getColor(R.color.nocheched));
                            listener.onItemClick(stamp, getAdapterPosition(), false);
                        } else {
                            cardButton.setBackgroundColor(itemView.getResources().getColor(R.color.cheched));
                            listener.onItemClick(stamp, getAdapterPosition(), true);
                        }
                    }
                });
            } else if (message.equals(MESSAGE_NO_CHECK)) {
                this.cardButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (stamp.isGotIt()) {
                            cardButton.setBackgroundColor(itemView.getResources().getColor(R.color.nocheched));
                            listener.onItemClick(stamp, getAdapterPosition(), false);
                        } else {
                            cardButton.setBackgroundColor(itemView.getResources().getColor(R.color.cheched));
                            listener.onItemClick(stamp, getAdapterPosition(), true);
                        }
                    }
                });
            }

        }
    }

    public interface OnItemClickListener {
        void onItemClick(Stamp stamp, int position, boolean checked);
    }
}
