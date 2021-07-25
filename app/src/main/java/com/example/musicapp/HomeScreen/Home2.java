package com.example.musicapp.HomeScreen;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.musicapp.R;

import java.util.ArrayList;
import java.util.List;


public class Home2 extends Fragment {

    View view;
    ViewPager2 viewPager2;
    Handler sliderHandler=new Handler();
    RecyclerView trendingsongs_recyclerview,featureArtistrecyclerview,popular_in_hindi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view= inflater.inflate(R.layout.fragment_home2, container, false);

        init();


        List<slideritem> list=new ArrayList<>();
        list.add(new slideritem(R.drawable.seventh));
        list.add(new slideritem(R.drawable.sixth));
        list.add(new slideritem(R.drawable.fifth));
        list.add(new slideritem(R.drawable.fourth));
        list.add(new slideritem(R.drawable.third));
        list.add(new slideritem(R.drawable.second));
        list.add(new slideritem(R.drawable.first));

        viewPager2.setAdapter(new SliderAdapter(list,viewPager2));
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer=new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r=1-Math.abs(position);
                page.setScaleY(0.85f+r*0.15f);
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable,3000);
            }
        });







        List<TrendingSongModel>trendingsonglist=new ArrayList<>();
        trendingsonglist.add(new TrendingSongModel(R.drawable.baarish,"Baarish","Payal dev","9M+ Plays"));
        trendingsonglist.add(new TrendingSongModel(R.drawable.paani_paani,"Baarish","Payal dev","9M+ Plays"));
        trendingsonglist.add(new TrendingSongModel(R.drawable.surror,"Baarish","Payal dev","9M+ Plays"));
        trendingsonglist.add(new TrendingSongModel(R.drawable.butter,"Baarish","Payal dev","9M+ Plays"));
        trendingsonglist.add(new TrendingSongModel(R.drawable.jalebi,"Baarish","Payal dev","9M+ Plays"));
        trendingsonglist.add(new TrendingSongModel(R.drawable.lostcause,"Baarish","Payal dev","9M+ Plays"));

        trendingsongs_recyclerview.setLayoutManager(new GridLayoutManager(getContext(),3));
        trendingsongs_recyclerview.setAdapter(new TrendingSongAdapter(trendingsonglist));



        List<FeatureArtistModel> featureArtistModelList=new ArrayList<>();
        featureArtistModelList.add(new FeatureArtistModel(R.drawable.arjit,"Arjit"));
        featureArtistModelList.add(new FeatureArtistModel(R.drawable.justienbiber,"Justin biber"));
        featureArtistModelList.add(new FeatureArtistModel(R.drawable.neha,"Neha kakar"));
        featureArtistModelList.add(new FeatureArtistModel(R.drawable.bts,"BTS"));
        featureArtistModelList.add(new FeatureArtistModel(R.drawable.badshah,"Badshah"));
        featureArtistModelList.add(new FeatureArtistModel(R.drawable.coldplay,"Coldplay"));
        featureArtistModelList.add(new FeatureArtistModel(R.drawable.armanmalik,"Arman Malik"));

        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        featureArtistrecyclerview.setLayoutManager(layoutManager);
        featureArtistrecyclerview.setAdapter(new FeatureArtistAdapter(featureArtistModelList,getActivity()));



        List<TrendingSongModel>popular_in_hindi_List=new ArrayList<>();
        popular_in_hindi_List.add(new TrendingSongModel(R.drawable.fillhal,"Baarish","Payal dev","9M+ Plays"));
        popular_in_hindi_List.add(new TrendingSongModel(R.drawable.bekhayali,"Baarish","Payal dev","9M+ Plays"));
        popular_in_hindi_List.add(new TrendingSongModel(R.drawable.garmi,"Baarish","Payal dev","9M+ Plays"));
        popular_in_hindi_List.add(new TrendingSongModel(R.drawable.muqabala,"Baarish","Payal dev","9M+ Plays"));
        popular_in_hindi_List.add(new TrendingSongModel(R.drawable.sakisaki,"Baarish","Payal dev","9M+ Plays"));
        popular_in_hindi_List.add(new TrendingSongModel(R.drawable.tumhiana,"Baarish","Payal dev","9M+ Plays"));

        popular_in_hindi.setLayoutManager(new GridLayoutManager(getContext(),3));
        popular_in_hindi.setAdapter(new TrendingSongAdapter(popular_in_hindi_List));


        return view;
    }

    private void init(){
        viewPager2=view.findViewById(R.id.viewpager2);
        trendingsongs_recyclerview=view.findViewById(R.id.home_trending_songs_recyclerview);
        featureArtistrecyclerview=view.findViewById(R.id.featuredRecyclerView);
        popular_in_hindi=view.findViewById(R.id.popularinhindi);
    }

    private Runnable sliderRunnable=new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
        }
    };
}