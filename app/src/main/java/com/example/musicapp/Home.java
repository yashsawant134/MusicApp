package com.example.musicapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.musicapp.Adapter.MusicAdapter;
import com.example.musicapp.ItuneApiPojo.ItuneApiPOJO;

import com.example.musicapp.api.ApiService;
import com.example.musicapp.api.ItuneInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Home extends Fragment {

    RecyclerView recyclerView;
    TextView searchbar;
    LinearLayout searchbtn;
    LinearLayout lottieAnimationView;
    MusicAdapter musicAdapter;
    List<ItuneApiPOJO> list=new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        searchbar=view.findViewById(R.id.search_bar);
        searchbtn=view.findViewById(R.id.search_btn);
        lottieAnimationView=view.findViewById(R.id.lottieanim);

        musicAdapter=new MusicAdapter(list,getContext());

        recyclerView=view.findViewById(R.id.recyclerview);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);





        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lottieAnimationView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
               doNetworkCall(searchbar.getText().toString());

            }
        });
    }

    private void doNetworkCall(String search) {

        ItuneInterface retrofitInterface= (ItuneInterface) ApiService.getClient().create(ItuneInterface.class);
        Call<ItuneApiPOJO> call=retrofitInterface.getRecords(search);


        call.enqueue(new Callback<ItuneApiPOJO>() {
            @Override
            public void onResponse(Call<ItuneApiPOJO> call, Response<ItuneApiPOJO> response) {
                ItuneApiPOJO apiPOJO=response.body();

                recyclerView.setAdapter(new MusicAdapter(apiPOJO.getTracks(),getContext()));
            }

            @Override
            public void onFailure(Call<ItuneApiPOJO> call, Throwable t) {

                Log.e("TAG", "onFailure: ", t);
            }
        });

    }

//    public void setRecyclerView(List<ItuneApiPOJO> list){
//        recyclerView.setAdapter(new MusicAdapter(list,getContext()));
//    }
}