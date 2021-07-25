package com.example.musicapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.ItuneApiPojo.ItuneApiPOJO;

import com.example.musicapp.MusicScreen.MusicScreen;
import com.example.musicapp.R;
import com.github.clans.fab.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder> {

    List<ItuneApiPOJO> list=new ArrayList<>();
    Context context;
    public MusicAdapter(List<ItuneApiPOJO> lis,Context context) {
        list.clear();
        this.list = lis;
        this.context=context;
    }

    @NonNull

    @Override
    public MusicAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.music_recycler_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull  MusicAdapter.ViewHolder holder, int position) {
        Picasso.get().load(list.get(position).getArtworkUrl100().toString()).into(holder.imageView);
        holder.songname.setText(list.get(position).getTrackName());
        holder.artistname.setText(list.get(position).getArtistName());
        holder.moviename.setText(list.get(position).getTrackCensoredName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, MusicScreen.class);

                intent.putExtra("position",position);
                intent.putExtra("list", (Serializable) list);


                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation((Activity) context,holder.imageView,"image");


                context.startActivity(intent,options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void getAllSongs(List<ItuneApiPOJO> list){
        this.list=list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView songname,moviename,artistname;
        FloatingActionButton floatingActionButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.artisworkimage);
            songname=itemView.findViewById(R.id.songname);
            moviename=itemView.findViewById(R.id.moviename);
            artistname=itemView.findViewById(R.id.artistname);
        }
    }
}
