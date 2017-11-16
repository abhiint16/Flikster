package com.flikster.HomeActivity.CommonFragments.MyStyleFragment;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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


    public MyStyleAdapterViewHolder(Context context, FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        type.add(1);
        type.add(2);
        type.add(3);
        type.add(4);
        type.add(5);
        imag.add("http://img.youtube.com/vi/MeH346YHUIE/0.jpg");
        imag.add("http://img.youtube.com/vi/CUYcVfVt88I/0.jpg");
        imag.add("http://img.youtube.com/vi/IkIqgTt8Xsk/0.jpg");
        imag.add("http://img.youtube.com/vi/nwJ0tL8Fi-E/0.jpg");
        imag.add("http://img.youtube.com/vi/lhwfWm-m7tw/0.jpg");
        imag.add("http://img.youtube.com/vi/-0XiiT5dR_Q/0.jpg");
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
            if (v.getId() == R.id.mystyle_layout_one) {
//                fragmentJump(item);
            }

        }

      /*  private void fragmentJump(Feed mItemSelected) {
            mFragment = new Fragment2();
            mBundle = new Bundle();
            mBundle.putParcelable("item_selected_key", mItemSelected);
            fragmentManager.setArguments(mBundle);
            switchContent(R.id.frag1, fragmentManager);
        }*/

    }

    public class ViewHolder2 extends RecyclerView.ViewHolder {

        public ViewHolder2(View itemView) {
            super(itemView);
        }
    }

    public class ViewHolder3 extends RecyclerView.ViewHolder {

        public ViewHolder3(View itemView) {
            super(itemView);
        }
    }

    public class ViewHolder4 extends RecyclerView.ViewHolder {

        public ViewHolder4(View itemView) {
            super(itemView);
        }
    }

    public class ViewHolder5 extends RecyclerView.ViewHolder {

        public ViewHolder5(View itemView) {
            super(itemView);
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


}
