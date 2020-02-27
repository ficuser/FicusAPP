package com.example.ficus.Tourist;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ficus.Hotel.HotelAdapter;
import com.example.ficus.R;
import com.example.ficus.SetData;

import java.util.List;

public class TouristAdater extends RecyclerView.Adapter<TouristAdater.ViewHolder> {
    private Context mContext;

    private List<SetData> mTouristList;

    public TouristAdater(List<SetData> touristList) {
        mTouristList=touristList;

    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout TouristImage;
        TextView TouristName;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView=(CardView)itemView;
            TouristImage=(LinearLayout) itemView.findViewById(R.id.tourist_image);
            TouristName=(TextView)itemView.findViewById(R.id.tourist_name);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.tourist_item,parent,false);
        final TouristAdater.ViewHolder holder= new TouristAdater.ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                int position=holder.getAdapterPosition();
                SetData hotel=mTouristList.get(position);
                Intent intent=new Intent(mContext,TouristView.class);
                //intent.putExtra(HotelHome.FRUIT_NAME,hotel.getName());
                //intent.putExtra(HotelHome.FRUIT_IMAGE_ID,hotel.getImageId());
                mContext.startActivity(intent);
            }
        });

        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SetData hotel=mTouristList.get(position);
        holder.TouristImage.setBackgroundResource(hotel.getImageId());
        holder.TouristName.setText(hotel.getName());
    }
    @Override
    public int getItemCount() {
        return mTouristList.size();
    }
}
