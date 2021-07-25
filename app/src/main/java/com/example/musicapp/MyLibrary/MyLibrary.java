package com.example.musicapp.MyLibrary;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.musicapp.Database.DatabaseHelper;
import com.example.musicapp.HomeScreen.FeatureArtistModel;
import com.example.musicapp.R;

import java.util.ArrayList;
import java.util.List;


public class MyLibrary extends Fragment {

    View view;
    RecyclerView recentPlays;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_my_library, container, false);

        init();


//        list.add(new FeatureArtistModel(R.drawable.bekhayali,"Bekhayali"));
//        list.add(new FeatureArtistModel(R.drawable.fillhal,"Fillhal"));
//        list.add(new FeatureArtistModel(R.drawable.muqabala,"Muqabala"));
//        list.add(new FeatureArtistModel(R.drawable.garmi,"Garmi"));
//        list.add(new FeatureArtistModel(R.drawable.tumhiana,"Tum hi ana"));
//        list.add(new FeatureArtistModel(R.drawable.bekhayali,"Bekhayali"));



        getRecentPlays();

        return view;

    }

    private void init(){
        recentPlays=view.findViewById(R.id.recent_plays_recyclerView);
    }

    private void getRecentPlays(){
        List<RecentPlayModel> list=new ArrayList<>();

        DatabaseHelper databaseHelper=new DatabaseHelper(getContext());
        list= databaseHelper.getRecentPlays();

        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recentPlays.setLayoutManager(layoutManager);
        recentPlays.setAdapter(new RecentPlaysAdapter(list,getActivity()));
    }
}