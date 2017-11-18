package com.flikster.HomeActivity.FeedFragment;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;

import com.flikster.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 18-11-2017.
 */

public class JukeBoxRecyclerViewHolder extends RecyclerView.Adapter<JukeBoxRecyclerViewHolder.ViewHolder> {
    Context context;
    List<String> audio=new ArrayList<>();
    MediaPlayer mediaPlayer;
    Boolean isAudioPlaying=false;

    public JukeBoxRecyclerViewHolder(Context context,List<String> audio) {
        this.context=context;
        this.audio=audio;
    }

    @Override
    public JukeBoxRecyclerViewHolder.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_audio_clip, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(JukeBoxRecyclerViewHolder.ViewHolder holder, int position) {
        Log.e("audiofile","audiofile"+audio+"c  "+audio.get(position));
        mediaPlayer=MediaPlayer.create(context, Uri.parse(audio.get(position)));
        //mediaPlayer=MediaPlayer.create(context,audio.get(position));
    }

    @Override
    public int getItemCount() {
        return audio.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageButton card_audio_clip_play_btn;
        SeekBar card_audio_clip_seekbar;
        public ViewHolder(View itemView) {
            super(itemView);
            card_audio_clip_play_btn=(ImageButton)itemView.findViewById(R.id.card_audio_clip_play_btn);
            card_audio_clip_seekbar=(SeekBar)itemView.findViewById(R.id.card_audio_clip_seekbar);
            card_audio_clip_play_btn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(isAudioPlaying==false)
            {
                mediaPlayer.start();
                card_audio_clip_play_btn.setImageDrawable(context.getResources().getDrawable(R.drawable.pausebtn));
                isAudioPlaying=true;
            }
            else if(isAudioPlaying==true)
            {
                mediaPlayer.pause();
                card_audio_clip_play_btn.setImageDrawable(context.getResources().getDrawable(R.drawable.playicon));
                isAudioPlaying=false;
            }

        }
    }
}
