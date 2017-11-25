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

public class AllCommentActivityAdapter extends RecyclerView.Adapter<AllCommentActivityAdapter.ViewHolder> {

    CommentsData.CommentsInnerData hits;

    public AllCommentActivityAdapter(CommentsData.CommentsInnerData hits) {
        this.hits=hits;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_all_comment_recycler_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.activity_all_comment_main_comment.setText(hits.getHits().get(position).get_source().getCommentText());
        holder.activity_all_comment_username.setText(hits.getHits().get(position).get_source().getUserName());
        holder.activity_all_comment_time.setText(hits.getHits().get(position).get_source().getCreatedAt());
    }

    @Override
    public int getItemCount() {
        return hits.getHits().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView activity_all_comment_user_img;
        TextView activity_all_comment_username,activity_all_comment_main_comment,activity_all_comment_time;

        public ViewHolder(View itemView) {
            super(itemView);
            activity_all_comment_user_img=(ImageView)itemView.findViewById(R.id.activity_all_comment_user_img);
            activity_all_comment_username=(TextView)itemView.findViewById(R.id.activity_all_comment_username);
            activity_all_comment_main_comment=(TextView)itemView.findViewById(R.id.activity_all_comment_main_comment);
            activity_all_comment_time=(TextView)itemView.findViewById(R.id.activity_all_comment_time);
        }
    }
}
