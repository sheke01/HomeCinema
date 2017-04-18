package com.example.sheke.homecinema;

/**
 * Created by SHEKE on 4/15/2017.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.PriorityQueue;


public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

    private static final String GED = RecycleAdapter.class.getSimpleName();
    public static ListItemClickListener mListClickListener;
    public List<Movie> modie;
    private Context cont;
    public ImageView immad;


    public RecycleAdapter(List<Movie> modie, ListItemClickListener listener) {
        this.modie = modie;
        this.mListClickListener = listener;

    }

        public interface ListItemClickListener {
            void onListItemClick(int clickedItemIndex);
        }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CardView cardview = holder.cardview;
        Movie modi = modie.get(position);
        holder.tedd.setText(modi.getTitle());
        Picasso.with(cont).load(modi.getThumbnail())
                .resize(185, 195).into(immad);

    }


        public static class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
            private CardView cardview;
            public ImageView immad;
            private TextView tedd;
            private ViewHolder(View v){
                super(v);
                cardview=(CardView)v.findViewById(R.id.card);
                    immad = (ImageView)v.findViewById(R.id.list);
                    tedd = (TextView)v.findViewById(R.id.desc);
                cardview.setOnClickListener(this);
            }


            @Override
            public void onClick(View v) {
                int itemClickedIndex = getAdapterPosition();
                mListClickListener.onListItemClick(itemClickedIndex);
            }
        }
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            int llla = R.layout.movie_list;
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(llla, viewGroup, false);
            ViewHolder viewHolder = new ViewHolder(view);

            return viewHolder;
        }

        @Override
        public int getItemCount() {

            return modie.size();
        }


    }


