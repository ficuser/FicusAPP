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

import com.example.ficus.R;
import com.example.ficus.SetData;

import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.ViewHolder> {
    private Context mContext;

    private List<SetData> mHotelList;

    public HotelAdapter(List<SetData> hotelList) {
        mHotelList=hotelList;
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
                SetData hotel=mHotelList.get(position);
                Intent intent=new Intent(mContext,HotelHome.class);
                intent.putExtra(HotelHome.FRUIT_NAME,hotel.getName());
                intent.putExtra(HotelHome.FRUIT_IMAGE_ID,hotel.getImageId());
                mContext.startActivity(intent);
            }
        });
        return holder;
}

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        SetData hotel=mHotelList.get(position);
        holder.hotelImage.setImageResource(hotel.getImageId());
        holder.hotelName.setText(hotel.getName());
        holder.hotelPrice.setText(hotel.gerPrice());
    }
    @Override
    public int getItemCount() {
        return mHotelList.size();
    }
}
