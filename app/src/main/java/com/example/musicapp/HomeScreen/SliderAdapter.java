package com.example.musicapp.HomeScreen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.musicapp.R;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder>{

    List<slideritem> list;
    ViewPager2 viewPager2;

    public SliderAdapter(List<slideritem> list, ViewPager2 viewPager2) {
        this.list = list;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_item,parent,false);
        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderAdapter.SliderViewHolder holder, int position) {
        holder.setImage(list.get(position));
        if(position==list.size()-2){
            viewPager2.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class SliderViewHolder extends RecyclerView.ViewHolder{
        private ImageView image;

        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);

            image=itemView.findViewById(R.id.bannerimg);
        }

        void setImage(slideritem Slideritem){
            image.setImageResource(Slideritem.getImage());
        }
    }

    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            list.addAll(list);
            notifyDataSetChanged();
        }
    };
}
