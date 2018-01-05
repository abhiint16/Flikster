package com.flikster.Util;

/**
 * Created by Logins on 02-01-2018.
 */

import java.io.InputStream;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.util.AttributeSet;
import android.view.View;
import com.flikster.R;

public class GIFView extends View {

    private Movie mMovie;
    private long movieStart;

    public GIFView(Context context) {
        super(context);
        initializeView();
    }

    public GIFView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeView();
    }

    public GIFView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initializeView();
    }

    private void initializeView() {
        @SuppressLint("ResourceType") InputStream is = getContext().getResources().openRawResource(
                R.drawable.styleimagegif);
        mMovie = Movie.decodeStream(is);
    }

    protected void onDraw(Canvas canvas) {
//        canvas.drawColor(Color.BLACK);
        super.onDraw(canvas);
        long now = android.os.SystemClock.uptimeMillis();

        if (movieStart == 0) {
            movieStart = (int) now;
        }
        if (mMovie != null) {
            int relTime = (int) ((now - movieStart) % mMovie.duration());
            mMovie.setTime(relTime);
            mMovie.draw(canvas, getWidth() - mMovie.width(), getHeight()- mMovie.height());
            this.invalidate();
        }
    }
}
