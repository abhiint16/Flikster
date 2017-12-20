package com.flikster.HomeActivity.CommonFragments.MyStyleFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.flikster.HomeActivity.CommonFragments.MyStyleFragment.CustomStyleTypes.MyStyleFragmentOne;
import com.flikster.HomeActivity.WatchFragment.Music.MusicGridFragment;
import com.flikster.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 12-10-2017.
 */

public class MyStyleAdapterViewHolder extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    FragmentManager fragmentManager;
    List<Integer> type = new ArrayList<>();
    List<String> imag = new ArrayList<>();
    MyStyleFragmentOne fragment = new MyStyleFragmentOne();
    Bundle bundle = new Bundle();


    public MyStyleAdapterViewHolder(Context context, FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        type.add(1);
        type.add(2);
        type.add(3);
        type.add(4);
        type.add(5);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_my_style_one, parent, false);
            return new MyStyleAdapterViewHolder.ViewHolder1(view);
        } else if (viewType == 2) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_my_style_two, parent, false);
            return new MyStyleAdapterViewHolder.ViewHolder2(view);
        } else if (viewType == 3) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_my_style_three, parent, false);
            return new MyStyleAdapterViewHolder.ViewHolder3(view);
        } else if (viewType == 4) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_my_style_four, parent, false);
            return new MyStyleAdapterViewHolder.ViewHolder4(view);
        } else if (viewType == 5) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_my_style_five, parent, false);
            return new MyStyleAdapterViewHolder.ViewHolder5(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_my_style_five, parent, false);
            return new MyStyleAdapterViewHolder.ViewHolder5(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 1) {
        } else if (holder.getItemViewType() == 2) {
        } else if (holder.getItemViewType() == 3) {
        } else if (holder.getItemViewType() == 4) {
        } else if (holder.getItemViewType() == 5) {
        } else {
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    @Override
    public int getItemViewType(int position) {
        return type.get(position);
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout mystyle_layout_one;

        public ViewHolder1(View itemView) {
            super(itemView);
            mystyle_layout_one = (LinearLayout) itemView.findViewById(R.id.mystyle_layout_one);
            mystyle_layout_one.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            customizingStyle("FIRST_STYLE");
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout mystyle_layout_two;

        public ViewHolder2(View itemView) {
            super(itemView);
            mystyle_layout_two = (LinearLayout) itemView.findViewById(R.id.mystyle_layout_two);
            mystyle_layout_two.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            customizingStyle("SECOND_STYLE");
        }
    }

    public class ViewHolder3 extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout mystyle_layout_three;

        public ViewHolder3(View itemView) {
            super(itemView);
            mystyle_layout_three = (LinearLayout) itemView.findViewById(R.id.mystyle_layout_three);
            mystyle_layout_three.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            customizingStyle("THIRD_STYLE");
        }
    }

    public class ViewHolder4 extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout mystyle_layout_four;

        public ViewHolder4(View itemView) {
            super(itemView);
            mystyle_layout_four = (LinearLayout) itemView.findViewById(R.id.mystyle_layout_four);
            mystyle_layout_four.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            customizingStyle("FOURTH_STYLE");
        }
    }

    public class ViewHolder5 extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout mystyle_layout_five;

        public ViewHolder5(View itemView) {
            super(itemView);
            mystyle_layout_five = (LinearLayout) itemView.findViewById(R.id.mystyle_layout_five);
            mystyle_layout_five.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            customizingStyle("FIFTH_STYLE");
        }
    }


    public class ViewHolder6 extends RecyclerView.ViewHolder {

        public ViewHolder6(View itemView) {
            super(itemView);
        }
    }

    public class ViewHolder7 extends RecyclerView.ViewHolder {

        public ViewHolder7(View itemView) {
            super(itemView);
        }
    }

    private void customizingStyle(String type) {
        fragment = new MyStyleFragmentOne();
        bundle = new Bundle();
        bundle.putString("MY_STYLE_TYPE", type);
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction()
                .replace(R.id.mystyle_container, fragment)
                .addToBackStack("")
                .commit();
    }

}
