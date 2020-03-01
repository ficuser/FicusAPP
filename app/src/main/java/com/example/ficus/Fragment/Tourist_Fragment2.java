package com.example.ficus.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.ficus.R;


public class Tourist_Fragment2 extends Fragment {

    public Tourist_Fragment2() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_content, container, false);
        LinearLayout linearlayout=(LinearLayout) view.findViewById(R.id.tourist_paper);
        TextView textView=(TextView)view.findViewById(R.id.paper_text);
        linearlayout.setBackgroundResource(R.drawable.fcg41);
        textView.setText("怪石滩");
        return view;
    }

}
