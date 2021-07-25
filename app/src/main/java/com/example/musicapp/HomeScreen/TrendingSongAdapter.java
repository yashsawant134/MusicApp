package com.example.musicapp.HomeScreen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.R;

import java.util.ArrayList;
import java.util.List;

public class TrendingSongAdapter extends RecyclerView.Adapter<TrendingSongAdapter.ViewHolder> {

    List<TrendingSongModel>list=new ArrayList<>();

    public TrendingSongAdapter(List<TrendingSongModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public TrendingSongAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.trending_songs_recyclerview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrendingSongAdapter.ViewHolder holder, int position) {
        holder.imageView.setImageResource(list.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.trendingsongimage);
        }
    }
}
