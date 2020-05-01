package com.example.ficus.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.ficus.R;


public class Tourist_Fragment1 extends Fragment {

    public Tourist_Fragment1() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_content, container, false);
        LinearLayout linearlayout=(LinearLayout) view.findViewById(R.id.tourist_paper);
        linearlayout.setBackgroundResource(R.drawable.i3);
        /*
        String imageUrl="http://gtd.alicdn.com/bao/uploaded///img.alicdn.com/bao/uploaded/i4/170070264899153448/TB2pk58uXXXXXcwXXXXXXXXXXXX_!!0-travel.jpg_600x600.jpg";
        Glide.with(getActivity())
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .override(500, 330)
                .into(linearlayout)
               ;*/
        TextView textView=(TextView)view.findViewById(R.id.paper_text);
        textView.setText("木兰草原");
        return view;
    }

}
