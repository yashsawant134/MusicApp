package com.example.musicapp.HomeScreen.artistscreen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.musicapp.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;


public class ArtistBottomSheet extends BottomSheetDialogFragment {


    RoundedImageView image;
    TextView name,ArtistName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_artist_bottom_sheet, container, false);

        image=view.findViewById(R.id.bottomsheetDialogImage);
        name=view.findViewById(R.id.bottomsheetDialogSongName);
        ArtistName=view.findViewById(R.id.bottomsheetDialogArtistName);

        String icon=getArguments().getString("Image");
        Picasso.get().load(icon).into(image);

        ArtistName.setText(getArguments().getString("ArtistName"));
        name.setText(getArguments().getString("SongName"));

        return view;
    }
}