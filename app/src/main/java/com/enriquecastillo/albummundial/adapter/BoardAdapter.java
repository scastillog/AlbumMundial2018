package com.enriquecastillo.albummundial.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.enriquecastillo.albummundial.R;
import com.enriquecastillo.albummundial.model.Board;
import com.enriquecastillo.albummundial.model.Stamp;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by szeph on 25/03/18.
 */

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    private Activity activity;
    private int layout;
    private List<Board> boards;
    private OnItemClickListener listener;

    public BoardAdapter(Activity activity, int layout, List<Board> boards, OnItemClickListener listener) {
        this.activity = activity;
        this.layout = layout;
        this.boards = boards;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(layout, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        holder.bind(boards.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return boards.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView txtTitle;
        public TextView txtDescription;
        public TextView txtStamp;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            txtTitle = (TextView) itemView.findViewById(R.id.txtViewBoardTitle);
            txtDescription = (TextView) itemView.findViewById(R.id.txtViewBoardQuanty);
            txtStamp = (TextView) itemView.findViewById(R.id.txtViewBoardStamp);
        }

        public void bind(final Board board, final OnItemClickListener listener) {

            Picasso.get().load(board.getImage()).fit().into(this.imageView);
            List<Stamp> list = board.getStamps();
            int counter = 0;
            for (int i = 0; i < list.size(); i++) {
                Stamp stamp = list.get(i);
                if (stamp.isGotIt()) counter++;
            }
            int numberOfStamps = board.getStamps().size();
            this.txtStamp.setText(counter + "/" + numberOfStamps + "");
            this.txtTitle.setText(board.getTitle());
            this.txtDescription.setText(board.getDescription());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(board, getAdapterPosition());
                }
            });

        }
    }

    public interface OnItemClickListener {
        void onItemClick(Board board, int position);
    }
}
