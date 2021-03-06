package com.flikster.Util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.flikster.R;

/**
 * Created by Logins on 27-11-2017.
 */

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private Drawable mDivider;

    public SpacesItemDecoration(Context context) {
        mDivider = context.getResources().getDrawable(R.drawable.linedivider);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();

            mDivider.setBounds(2, 2, 2, 2);
            mDivider.draw(c);
        }
    }
}
