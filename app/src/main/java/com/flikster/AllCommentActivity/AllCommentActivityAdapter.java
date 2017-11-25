package com.flikster.AllCommentActivity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.flikster.R;

/**
 * Created by abhishek on 25-11-2017.
 */

public class AllCommentActivityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    CommentsData.CommentsInnerData hits;

    public AllCommentActivityAdapter(CommentsData.CommentsInnerData hits) {
        this.hits=hits;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==1)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_all_comment_recycler_item,parent,false);
            return new ViewHolder1(view);
        }
        else
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_no_comments,parent,false);
            return new ViewHolder2(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder.getItemViewType()==1)
        {
            ((ViewHolder1)holder).activity_all_comment_main_comment.setText(hits.getHits().get(position).get_source().getCommentText());
            ((ViewHolder1)holder).activity_all_comment_username.setText(hits.getHits().get(position).get_source().getUserName());
            ((ViewHolder1)holder).activity_all_comment_time.setText(hits.getHits().get(position).get_source().getCreatedAt());
        }
    }

    @Override
    public int getItemCount() {
        if (hits.getHits().size()==0 || hits.getHits()==null)
            return 1;
        return hits.getHits().size();
    }

    @Override
    public int getItemViewType(int position) {
        if(hits.getHits().size()==0 || hits.getHits()==null)
            return 0;
        else
            return 1;
        //return super.getItemViewType(position);
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {
        ImageView activity_all_comment_user_img;
        TextView activity_all_comment_username,activity_all_comment_main_comment,activity_all_comment_time;

        public ViewHolder1(View itemView) {
            super(itemView);
            activity_all_comment_user_img=(ImageView)itemView.findViewById(R.id.activity_all_comment_user_img);
            activity_all_comment_username=(TextView)itemView.findViewById(R.id.activity_all_comment_username);
            activity_all_comment_main_comment=(TextView)itemView.findViewById(R.id.activity_all_comment_main_comment);
            activity_all_comment_time=(TextView)itemView.findViewById(R.id.activity_all_comment_time);
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder {

        public ViewHolder2(View itemView) {
            super(itemView);
        }
    }
}
