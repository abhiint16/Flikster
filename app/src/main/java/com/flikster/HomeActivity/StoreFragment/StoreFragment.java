package com.flikster.HomeActivity.StoreFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flikster.R;
import com.flikster.Util.Common;

/**
 * Created by abhishek on 17-10-2017.
 */

public class StoreFragment extends Fragment {
    View view;
    TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_common, container, false);
        textView = (TextView) view.findViewById(R.id.tvf);
        textView.setText("Android ButterKnife library is a view injection library that injects views into android activity / fragments using annotations. For example, @BindView annotation avoids using findViewById() method by automatically type casting the view element.\n" +
                "\n" +
                "Not just view binding, butterknife provides lot of other useful options like binding strings, dimens, drawables, click events and lot more. We’ll see brief about every component offered in this article.");

        Common.makeTextViewResizable(textView, 3, "View More", true);
        return view;
    }
}
