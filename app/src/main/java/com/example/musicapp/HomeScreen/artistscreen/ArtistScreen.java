package com.example.musicapp.HomeScreen.artistscreen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.musicapp.Adapter.MusicAdapter;
import com.example.musicapp.ItuneApiPojo.ItuneApiPOJO;
import com.example.musicapp.R;
import com.example.musicapp.api.ApiService;
import com.example.musicapp.api.ItuneInterface;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ArtistScreen extends Fragment {

    View view;
   RecyclerView artistSongrecyclerView;
   RoundedImageView roundedImageView;
   TextView bestOf;
    String name;

    int image;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view= inflater.inflate(R.layout.fragment_artist_screen, container, false);

        init();

        image=getArguments().getInt("image");
        roundedImageView.setImageResource(image);

        name=getArguments().getString("singerName");
        bestOf.setText("Best of "+name);



        artistSongrecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        list.add(new ArtistModel("Bekhayali","Vishal",R.drawable.arjit));
//        list.add(new ArtistModel("Bekhayali","Vishal",R.drawable.arjit));
//        list.add(new ArtistModel("Bekhayali","Vishal",R.drawable.arjit));
//        list.add(new ArtistModel("Bekhayali","Vishal",R.drawable.arjit));
//        list.add(new ArtistModel("Bekhayali","Vishal",R.drawable.arjit));
//        list.add(new ArtistModel("Bekhayali","Vishal",R.drawable.arjit));
//        list.add(new ArtistModel("Bekhayali","Vishal",R.drawable.arjit));
//        list.add(new ArtistModel("Bekhayali","Vishal",R.drawable.arjit));
//        list.add(new ArtistModel("Bekhayali","Vishal",R.drawable.arjit));
//        list.add(new ArtistModel("Bekhayali","Vishal",R.drawable.arjit));

//        artistSongrecyclerView.setAdapter(new ArtistAdapter(list));
        doNetworkCall(name);
        return view;
    }

    private void init(){
        artistSongrecyclerView=view.findViewById(R.id.artistsong_recycler_view);
        roundedImageView=view.findViewById(R.id.singerImage);
        bestOf=view.findViewById(R.id.bestOfSingerText);

    }

    private void doNetworkCall(String search) {

        ItuneInterface retrofitInterface= (ItuneInterface) ApiService.getClient().create(ItuneInterface.class);
        Call<ItuneApiPOJO> call=retrofitInterface.getRecords(search);


        call.enqueue(new Callback<ItuneApiPOJO>() {
            @Override
            public void onResponse(Call<ItuneApiPOJO> call, Response<ItuneApiPOJO> response) {
                ItuneApiPOJO apiPOJO=response.body();
                ArtistAdapter artistAdapter=new ArtistAdapter(apiPOJO.getTracks(),getActivity());
                artistSongrecyclerView.setAdapter(artistAdapter);
                artistAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ItuneApiPOJO> call, Throwable t) {

                Log.e("TAG", "onFailure: ", t);
            }
        });

    }
}


