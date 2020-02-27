package com.example.ficus.Tourist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ficus.R;
import com.example.ficus.SetData;

import java.util.List;

public class TouristViewAdapter extends RecyclerView.Adapter<TouristViewAdapter.ViewHolder>{
    private List<SetData> mTouristViewList;

    private Context mContext;

    public TouristViewAdapter(List<SetData> hotelList) {
        mTouristViewList=hotelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.touristview_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SetData touristview =mTouristViewList.get(position);
        holder.touristViewImage.setBackgroundResource(touristview.getImageId());
    }

    @Override
    public int getItemCount() {
        return mTouristViewList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout touristViewImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            touristViewImage=(LinearLayout) itemView.findViewById(R.id.text4);
        }
    }
}
