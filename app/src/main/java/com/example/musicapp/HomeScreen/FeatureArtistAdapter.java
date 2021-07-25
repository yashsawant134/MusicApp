package com.example.musicapp.HomeScreen;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.HomeScreen.artistscreen.ArtistScreen;
import com.example.musicapp.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FeatureArtistAdapter extends RecyclerView.Adapter<FeatureArtistAdapter.ViewHolder> {

    List<FeatureArtistModel> list=new ArrayList<>();
    Context context;
    public FeatureArtistAdapter(List<FeatureArtistModel> list, Context context) {
        this.list = list;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.feature_artist_recycler_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeatureArtistAdapter.ViewHolder holder, int position) {
        holder.circleImageView.setImageResource(list.get(position).getImage());
        holder.name.setText(list.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArtistScreen artistScreen=new ArtistScreen();
                Bundle bundle=new Bundle();
                bundle.putString("singerName",list.get(position).getName());
                bundle.putInt("image",list.get(position).getImage());
                artistScreen.setArguments(bundle);

                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                activity.getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,artistScreen).addToBackStack(null).commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView circleImageView;
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            circleImageView=itemView.findViewById(R.id.featureartist_image);
            name=itemView.findViewById(R.id.artistnamefeature);
        }
    }
}
