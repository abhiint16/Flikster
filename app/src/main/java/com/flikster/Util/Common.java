package com.flikster.Util;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.flikster.HomeActivity.FeedInnerData;
import com.flikster.HomeActivity.PostRetrofit;
import com.flikster.R;
import com.flikster.permission.DangerousPermResponseCallBack;
import com.flikster.permission.DangerousPermissionResponse;
import com.flikster.permission.DangerousPermissionUtils;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * Created by Logins on 10-11-2017.
 */

public class Common {

    public static void makeTextViewResizable(final TextView tv, final int maxLine, final String expandText, final boolean viewMore) {

        if (tv.getTag() == null) {
            tv.setTag(tv.getText());
        }
        ViewTreeObserver vto = tv.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {

                ViewTreeObserver obs = tv.getViewTreeObserver();
                obs.removeGlobalOnLayoutListener(this);
                if (maxLine == 0) {
                    int lineEndIndex = tv.getLayout().getLineEnd(0);
                    String text = tv.getText().subSequence(0, lineEndIndex - expandText.length() + 1) + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, maxLine, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                } else if (maxLine > 0 && tv.getLineCount() >= maxLine) {
                    int lineEndIndex = tv.getLayout().getLineEnd(maxLine - 1);
                    String text = tv.getText().subSequence(0, lineEndIndex - expandText.length() + 1) + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, maxLine, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                } else {
                    int lineEndIndex = tv.getLayout().getLineEnd(tv.getLayout().getLineCount() - 1);
                    String text = tv.getText().subSequence(0, lineEndIndex) + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, lineEndIndex, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                }
            }
        });

    }

    private static SpannableStringBuilder addClickablePartTextViewResizable(final Spanned strSpanned, final TextView tv,
                                                                            final int maxLine, final String spanableText, final boolean viewMore) {
        String str = strSpanned.toString();
        SpannableStringBuilder ssb = new SpannableStringBuilder(strSpanned);

        if (str.contains(spanableText)) {
            ssb.setSpan(new ClickableSpan() {

                @Override
                public void onClick(View widget) {

                    if (viewMore) {
                        tv.setLayoutParams(tv.getLayoutParams());
                        tv.setText(tv.getTag().toString(), TextView.BufferType.SPANNABLE);
                        tv.invalidate();
                        makeTextViewResizable(tv, -1, "View Less", false);
                    } else {
                        tv.setLayoutParams(tv.getLayoutParams());
                        tv.setText(tv.getTag().toString(), TextView.BufferType.SPANNABLE);
                        tv.invalidate();
                        makeTextViewResizable(tv, 3, "View More", true);
                    }

                }
            }, str.indexOf(spanableText), str.indexOf(spanableText) + spanableText.length(), 0);

        }
        return ssb;

    }


    public static String formatString(String name) {
        String s = name;
        if (s.length() >= 120) {
            s = s.substring(0, 115) + " ...";
        }
        return s;
    }

    public static String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    public static Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0,
                    encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public static void setKeyboardFocus(final EditText primaryTextField) {
        (new Handler()).postDelayed(new Runnable() {
            public void run() {
                primaryTextField.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, 0, 0, 0));
                primaryTextField.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, 0, 0, 0));
            }
        }, 100);
    }


    //Follow or UnFollow action
    public static void followOrUnFollow(Context context, final Button followbtn,
                                        final String userId, String entityId) {
        Log.e("TextData", followbtn.getText().toString() + "");
        if (followbtn.getText().toString().equals("follow")) {
            followcolorChange(followbtn, context);
            new PostRetrofit().postRetrofitFollowMethod("follow", userId, entityId, followbtn, context);
        } else {
            Toast.makeText(context, "You Unfollowing", Toast.LENGTH_LONG).show();
            unfollowcolorChange(followbtn, context);
            new PostRetrofit().postRetrofitFollowMethod("follow", userId, entityId, followbtn, context);
        }
    }


    public static void followcolorChange(Button followbtn, Context context) {
        followbtn.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
        followbtn.setBackground(context.getResources().getDrawable(R.drawable.corner_rounded_pink));
        followbtn.setText("Following");
        followbtn.setTextColor(context.getResources().getColor(R.color.white));
    }

    public static void unfollowcolorChange(Button followbtn, Context context) {
        followbtn.setBackgroundColor(context.getResources().getColor(R.color.black));
        followbtn.setBackground(context.getResources().getDrawable(R.drawable.corner_rounded));
        followbtn.setText("follow");
        followbtn.setTextColor(context.getResources().getColor(R.color.black));
    }

    //Like Event
    public static void likeAndUnLikeEvent(Context context, final ImageButton ib_like,
                                          final String userId, String entityId) {
        if (SharedPrefsUtil.getStringPreference(context, "IS_LOGGED_IN").equals("NOT_LOGGED_IN")) {
            Toast.makeText(context, "You need to first Login.", Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (ib_like.getDrawable().getConstantState().equals
                    (context.getResources().getDrawable(R.drawable.like_pink).getConstantState())) {
                Log.e("LikeEvent", "PINK_COLOR");
                likeImageChangeAndpostRequest(ib_like, "LIKED", userId, entityId, context);
            } else {
                Log.e("LikeEvent", "NORMAL_COLOR");
                likeImageChangeAndpostRequest(ib_like, "UNLIKED", userId, entityId, context);
            }
        }
    }

    private static void likeImageChangeAndpostRequest(ImageButton ib_like,
                                                      String likeStr, String userId,
                                                      String entityId, Context context) {
        if (SharedPrefsUtil.getStringPreference(context, "IS_LOGGED_IN").equals("NOT_LOGGED_IN")) {
            Toast.makeText(context, "You need to first Login.", Toast.LENGTH_SHORT).show();
            return;
        } else {
            ib_like.setImageResource(0);
            if (likeStr.equals("LIKED")) {
                ib_like.setImageResource(R.drawable.like_icon);
                Toast.makeText(context, userId + " UnLiked", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, userId + " Liked", Toast.LENGTH_SHORT).show();
                ib_like.setImageResource(R.drawable.like_pink);
            }
            new PostRetrofit().postRetrofitMethod("like", userId,
                    entityId, ib_like, context);
        }

    }

    //Book mark in Action Event
    public static void bookmarkAndUnBookmarkeEvent(Context context, final ImageButton bookmark,
                                                   final String userId, String entityId) {
        if (SharedPrefsUtil.getStringPreference(context, "IS_LOGGED_IN").equals("NOT_LOGGED_IN")) {
            Toast.makeText(context, "You need to first Login.", Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (bookmark.getDrawable().getConstantState().equals
                    (context.getResources().getDrawable(R.drawable.bookmark_yellow).getConstantState())) {
                Log.e("BookEvent", "YELLOW_COLOR");
                bookImageChangeAndpostRequest(bookmark, "UNBOOKMARKED", userId, entityId, context);
            } else {
                Log.e("BookEvent", "NORMAL_COLOR");
                bookImageChangeAndpostRequest(bookmark, "BOOKMARKED", userId, entityId, context);
            }
        }
    }

    private static void bookImageChangeAndpostRequest(ImageButton ib_like,
                                                      String likeStr, String userId,
                                                      String entityId, Context context) {
        ib_like.setImageResource(0);
        if (!likeStr.equals("BOOKMARKED")) {
            ib_like.setImageResource(R.drawable.bookmark_icon);
            Toast.makeText(context, userId + " Un Bookmarked", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, userId + " Bookmarked", Toast.LENGTH_SHORT).show();
            ib_like.setImageResource(R.drawable.bookmark_yellow);
        }
        new PostRetrofit().postRetrofitMethod("bookmark", userId,
                entityId, ib_like, context);

    }


    public static void shareClick(String shareableLink, Context context) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Flikster");
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareableLink + "\n\n\n" + "Download **Flikster**"
                + "https://play.google.com/store/apps/details?id=com.flikster&hl=en" +
                " and don't miss anything from movie industry. Stay connected to the world of Illusion.\n");
        shareIntent.setType("text/plain");
        context.startActivity(Intent.createChooser(shareIntent, "Complete action using ...."));
    }

}
