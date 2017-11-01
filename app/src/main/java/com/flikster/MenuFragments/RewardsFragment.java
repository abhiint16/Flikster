package com.flikster.MenuFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flikster.R;

/**
 * Created by abhishek on 17-10-2017.
 */

public class RewardsFragment extends Fragment {
    View view;
    TextView textView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_common,container,false);
        textView=(TextView)view.findViewById(R.id.tvf);
        textView.setText("Rewards is working fine");
        return view;
    }
}
