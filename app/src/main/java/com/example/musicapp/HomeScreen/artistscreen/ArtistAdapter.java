package com.example.musicapp.HomeScreen.artistscreen;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.ItuneApiPojo.ItuneApiPOJO;
import com.example.musicapp.MainActivity;
import com.example.musicapp.R;
import com.like.LikeButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ViewHolder> {

    List<ViewHolder> holders = new ArrayList<ViewHolder>();
    List<ItuneApiPOJO> list=new ArrayList<>();
    Context context;
    public ArtistAdapter(List<ItuneApiPOJO> list,Context context) {
        this.list = list;
        this.context=context;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_song_recycle_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        holders.add(viewHolder);
        return viewHolder;    }



    @Override
    public void onBindViewHolder(@NonNull ArtistAdapter.ViewHolder holder, int position) {

        final ItuneApiPOJO current = list.get(position);

        Picasso.get().load(current.getArtworkUrl100()).into(holder.image);
        holder.singername.setText(current.getArtistName());
        holder.songname.setText(current.getTrackName());
        holder.viewBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArtistBottomSheet artistBottomSheet=new ArtistBottomSheet();

                holder.likeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.likeButton.setEnabled(true);
                    }
                });
                Bundle bundle=new Bundle();
                bundle.putString("SongName",list.get(position).getTrackName());
                bundle.putString("ArtistName",list.get(position).getArtistName());
                bundle.putString("Image",list.get(position).getArtworkUrl100());
                artistBottomSheet.setArguments(bundle);
                artistBottomSheet.show(((FragmentActivity)holder.itemView.getContext()).getSupportFragmentManager(),artistBottomSheet.getTag());
            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context instanceof MainActivity) {
                    ((MainActivity)context).show(list.get(position).getTrackName(),list.get(position).getArtworkUrl100(),list.get(position).getPreviewUrl(),list.get(position).getTrackId());
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image,viewBottomSheet;
        TextView songname,singername;
        LikeButton likeButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.setIsRecyclable(false);

            image=itemView.findViewById(R.id.artistsong_recycler_view_image);
            songname=itemView.findViewById(R.id.artistsong_recycler_view_songname);
            singername=itemView.findViewById(R.id.artistsong_recycler_view_Singername);
            viewBottomSheet=itemView.findViewById(R.id.viewBottomSheet);
            likeButton=itemView.findViewById(R.id.likebtn);
        }
    }
}
