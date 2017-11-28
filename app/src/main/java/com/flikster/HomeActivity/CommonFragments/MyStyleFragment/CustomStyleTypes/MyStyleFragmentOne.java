package com.flikster.HomeActivity.CommonFragments.MyStyleFragment.CustomStyleTypes;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.flikster.HomeActivity.CommonFragments.MyStyleFragment.MyStyleAdapter;
import com.flikster.HomeActivity.CommonFragments.MyStyleFragment.MyStyleFragment;
import com.flikster.HomeActivity.FeedFragment.FeedFragment;
import com.flikster.HomeActivity.WatchFragment.Music.MusicGridOnClick.SongsList.MovieSongsListFragment;
import com.flikster.R;
import com.flikster.SharedPref.SharedPref;
import com.flikster.Util.Common;
import com.flikster.Util.SharedPrefsUtil;
import com.flikster.permission.DangerousPermResponseCallBack;
import com.flikster.permission.DangerousPermissionResponse;
import com.flikster.permission.DangerousPermissionUtils;

import java.io.ByteArrayOutputStream;

import static android.app.Activity.RESULT_OK;

/**
 * Created by abhishek on 12-10-2017.
 */

public class MyStyleFragmentOne extends Fragment implements View.OnClickListener {
    View view;
    FragmentManager fragmentManager;
    ImageView captureimg, productthingimg, productimg, productthingextraimg;
    Bundle bundle = new Bundle();
    String styletype = "";

    private static int CAMERA_REQUES_CODEE = 101;
    boolean cameracaptured = false;
    final int ACTIVITY_SELECT_IMAGE = 2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bundle = getArguments();
        fragmentManager = getActivity().getSupportFragmentManager();
        styletype = bundle.getString("MY_STYLE_TYPE");
        Log.e("type_style", styletype + "Style");
        if (styletype.equals("FIRST_STYLE")) {
            view = inflater.inflate(R.layout.forgment_my_style_one, container, false);
        } else if (styletype.equals("SECOND_STYLE")) {
            view = inflater.inflate(R.layout.fragment_my_style_two, container, false);
        } else if (styletype.equals("THIRD_STYLE")) {
            view = inflater.inflate(R.layout.fragment_my_style_three, container, false);
        } else if (styletype.equals("FOURTH_STYLE")) {
            view = inflater.inflate(R.layout.fragment_my_style_four, container, false);
        } else if (styletype.equals("FIFTH_STYLE")) {
            view = inflater.inflate(R.layout.fragment_my_style_five, container, false);
        }
        initializeViews();
        initializeRest();

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1234) {
            if (resultCode == Activity.RESULT_OK) {
                Bitmap bmp = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

                SharedPrefsUtil.setStringPreference(getContext(), "ImageString", Common.BitMapToString(bitmap));

                captureimg.setScaleType(ImageView.ScaleType.FIT_XY);
                captureimg.setImageBitmap(bitmap);
            } else {
                Toast.makeText(getContext(), "failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void initializeRest() {
        String imgStr = SharedPrefsUtil.getStringPreference(getContext(), "ImageString");
        if (imgStr != null && !imgStr.isEmpty()) {
            Bitmap image = Common.StringToBitMap(imgStr);
            captureimg.setScaleType(ImageView.ScaleType.FIT_XY);
            captureimg.setImageBitmap(image);
        }
        captureimg.setOnClickListener(this);
    }

    private void initializeViews() {
        captureimg = (ImageView) view.findViewById(R.id.captureimg);
        productimg = (ImageView) view.findViewById(R.id.productimg);
        productthingimg = (ImageView) view.findViewById(R.id.productthingimg);
        if (styletype.equals("FOURTH_STYLE") && styletype.equals("FIFTH_STYLE")) {
            productthingextraimg = (ImageView) view.findViewById(R.id.productthingextraimg);
        }


    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.captureimg) {
            openCameraClickDialog();
        } else {
            fragmentManager.beginTransaction()
                    .replace(R.id.main_container, new FeedFragment())
                    .addToBackStack("")
                    .commit();
        }

    }

    //Camera Access
    private void cameraAccessPermission(int requestCode) {
        DangerousPermissionUtils.getPermission(getContext(), new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, requestCode)
                .enqueue(new DangerousPermResponseCallBack() {
                    @Override
                    public void onComplete(final DangerousPermissionResponse permissionResponse) {
                        if (permissionResponse.isGranted()) {
                            if (permissionResponse.getRequestCode() == CAMERA_REQUES_CODEE) {
                                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED && ActivityCompat
                                        .checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                                    return;
                                }
                                cameracaptured = true;
                                openCamera();
                            }
                        }
                    }
                });
    }

    private void openCameraClickDialog() {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_camera_click);
        final Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        LinearLayout dialog_camera_click_click_photo = (LinearLayout) dialog.findViewById(R.id.dialog_camera_click_click_photo);
        LinearLayout dialog_camera_click_select_gallery = (LinearLayout) dialog.findViewById(R.id.dialog_camera_click_select_gallery);
        dialog_camera_click_select_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, ACTIVITY_SELECT_IMAGE);
                dialog.dismiss();
            }
        });
        dialog_camera_click_click_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameraAccessPermission(CAMERA_REQUES_CODEE);
                dialog.dismiss();
            }
        });
        window.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.translucent)));
        dialog.show();
    }

    private void openCamera() {
        SharedPrefsUtil.setStringPreference(getContext(), "ACCESS_FRAGMENT_CAPTURE", "ENABLE");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1234);
    }
}
