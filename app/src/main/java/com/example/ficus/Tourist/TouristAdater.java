package com.example.ficus.Tourist;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.ficus.Hotel.HotelAdapter;
import com.example.ficus.R;
import com.example.ficus.SetData;
import com.example.ficus.db.Tourist;

import java.util.List;

public class TouristAdater extends RecyclerView.Adapter<TouristAdater.ViewHolder> {
    private Context mContext;


    private List<SetData> mTouristList;
    private List<Tourist> mTouristsList;

    public TouristAdater(List<SetData> touristList,List<Tourist> hotelsList) {
        mTouristList=touristList;
        mTouristsList=hotelsList;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView TouristImage;
        TextView TouristName;
        CardView cardView;
        FrameLayout  linearLayout;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            cardView=(CardView)itemView;
            //cardView=(FrameLayout)itemView;
            TouristImage=(ImageView)itemView.findViewById(R.id.tourist_image);
            TouristName=(TextView)itemView.findViewById(R.id.tourist23_name);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext==null)
        {
            mContext=parent.getContext();
        }
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.touristlist_item,parent,false);
        final TouristAdater.ViewHolder holder= new TouristAdater.ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                int position=holder.getAdapterPosition();
                Tourist tourist=mTouristsList.get(position);
                Intent intent=new Intent(mContext,TouristView.class);
                intent.putExtra(TouristView.touristViewName,tourist.getTouristViewName());
                intent.putExtra(TouristView.touristImageView1,tourist.getTouristImageView1());
                intent.putExtra(TouristView.touristImageView2,tourist.getTouristImageView2());
                intent.putExtra(TouristView.touristImageView3,tourist.getTouristImageView3());
                intent.putExtra(TouristView.touristImageView4,tourist.getTouristImageView4());
                intent.putExtra(TouristView.touristImageView5,tourist.getTouristImageView5());
                intent.putExtra(TouristView.touristViewPrice,tourist.getTouristViewPrice());
                intent.putExtra(TouristView.touristTesturl,tourist.getTouristTesturl());
                intent.putExtra(TouristView.touristTouristGrade,tourist.getTouristTouristGrade());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        SetData tourist=mTouristList.get(position);
        String imageUrl =tourist.getImageUrl();
        if (null == holder || null == imageUrl || imageUrl.equals("")) {
            return;
        }
        if(tourist.getName()==null)
        {
            holder.TouristName.setText("null");
        }else{
            holder.TouristName.setText(tourist.getName());
        }
        Glide.with(mContext)
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.TouristImage);
        /*
        Glide.with(mContext)
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .into(new SimpleTarget<GlideDrawable>(){
                          @Override
                          public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                              Drawable drawable = new BitmapDrawable(String.valueOf(resource));
                              TouristImage.setBackground(drawable);
                          }
                      });*/
    }
    @Override
    public int getItemCount() {
        return mTouristList.size();
    }
/*
    @Override
    public void onViewRecycled(@NonNull ViewHolder holder)//这个方法是Adapter里面的
    {
        if (holder != null)
        {
            Glide.clear(holder.);
        }
        super.onViewRecycled(holder);
    }*/
}
