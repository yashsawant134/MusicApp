package com.example.musicapp.MyLibrary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.HomeScreen.FeatureArtistModel;
import com.example.musicapp.MainActivity;
import com.example.musicapp.R;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecentPlaysAdapter extends RecyclerView.Adapter<RecentPlaysAdapter.ViewHolder> {

    List<RecentPlayModel> list=new ArrayList<>();
    Context context;
    public RecentPlaysAdapter(List<RecentPlayModel> list,Context context) {
        this.list = list;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recent_plays_mylibrary,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentPlaysAdapter.ViewHolder holder, int position) {
        Picasso.get().load(list.get(position).getImage()).into(holder.image);
        holder.name.setText(list.get(position).getSongname());

    }

    @Override
    public int getItemCount() {
        if(list.size()<10){
            return list.size();
        }

        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RoundedImageView image;
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image=itemView.findViewById(R.id.recent_plays_roundedImageView);
            name=itemView.findViewById(R.id.recent_plays_name);

        }
    }
}
