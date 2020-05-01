package com.example.ficus.Tourist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ficus.R;
import com.example.ficus.SetData;
import com.example.ficus.db.Hotel;
import com.example.ficus.db.Tourist;

import java.util.List;

public class TouristViewAdapter extends RecyclerView.Adapter<TouristViewAdapter.ViewHolder>{
    private List<String> mTouristViewList;



    private Context mContext;

    private List<Tourist> mTouristsViewList;

    public TouristViewAdapter(List<String> touristList) {
        mTouristViewList=touristList;
        //mTouristsViewList=hotelsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext==null)
        {
            mContext=parent.getContext();
        }
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.touristview_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String imageUrl=mTouristViewList.get(position);

        Glide.with(mContext)
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.touristViewImage);
    }

    @Override
    public void onViewRecycled(@NonNull ViewHolder holder)//这个方法是Adapter里面的
    {
        if (holder != null)
        {
            Glide.clear(holder.touristViewImage);
        }
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        return mTouristViewList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView touristViewImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            touristViewImage=(ImageView) itemView.findViewById(R.id.TouristList_ImageView);
        }
    }
}
