package com.example.ficus.Hotel;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ficus.R;
import com.example.ficus.SetData;
import com.example.ficus.db.Hotel;

import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.ViewHolder> {
    private Context mContext;
    private List<SetData> mHotelList;
    private  List<Hotel> mHotelsList;
    public HotelAdapter(List<SetData> hotelList, List<Hotel> hotelsList) {
        mHotelList=hotelList;
        mHotelsList=hotelsList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView hotelImage;
        TextView hotelName;
        TextView hotelPrice;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            cardView=(CardView)itemView;
            hotelImage=(ImageView) itemView.findViewById(R.id.hotel_image);
            hotelName=(TextView)itemView.findViewById(R.id.hotel_name);
            hotelPrice=(TextView)itemView.findViewById(R.id.hotel_price);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext==null)
        {
            mContext=parent.getContext();
        }
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_item,parent,false);
        final HotelAdapter.ViewHolder holder= new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                int position=holder.getAdapterPosition();
                Hotel hotel =mHotelsList.get(position);
                Intent intent=new Intent(mContext,HotelHome.class);
                intent.putExtra(HotelHome.HOTEL_NAME,hotel.getHotelHostelName());
                intent.putExtra(HotelHome.hotelAddress,hotel.getHotelAddress());
                intent.putExtra(HotelHome.hotelCharm,hotel.getHotelCharm());
                intent.putExtra(HotelHome.hotelEvaluate,hotel.getHotelEvaluate());
                intent.putExtra(HotelHome.hotelImageUrl,hotel.getHotelImageUrl());
                intent.putExtra(HotelHome.hotelLow,hotel.getHotelLow());
                intent.putExtra(HotelHome.hotelPrice,hotel.getHotelPrice());
                intent.putExtra(HotelHome.hotelScore,hotel.getHotelScore());
                intent.putExtra(HotelHome.hotelStar,hotel.getHotelStar());
                intent.putExtra(HotelHome.hotelTag,hotel.getHotelTag());
                intent.putExtra(HotelHome.hotelUrl,hotel.getHotelUrl());
                intent.putExtra(HotelHome.hotelUser,hotel.getHotelUser());
                mContext.startActivity(intent);
            }
        });
        return holder;
}

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        SetData hotel=mHotelList.get(position);
        String imageUrl=hotel.getImageUrl();
        if (null == holder || null == imageUrl || imageUrl.equals("")) {
            return;
        }
        if(hotel.getName()==null)
        {
            holder.hotelName.setText("null");
        }else{
            holder.hotelName.setText(hotel.getName());
        }
        if(hotel.getImageUrl()==null)
        {
            holder.hotelPrice.setText("null");
        }else{
            holder.hotelPrice.setText("￥"+hotel.gerPrice()+"起");
        }
        Glide.with(mContext)
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.hotelImage);
    }
    @Override
    public int getItemCount(){
        return mHotelList.size();
    }
    @Override
    public void onViewRecycled(@NonNull ViewHolder holder)//这个方法是Adapter里面的
    {
        if (holder != null)
        {
            Glide.clear(holder.hotelImage);
        }
        super.onViewRecycled(holder);
    }
}
