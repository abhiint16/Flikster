package com.flikster.HomeActivity.CommonFragments.MyStyleFragment.CustomStyleTypes;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.flikster.HomeActivity.CommonFragments.MyStyleFragment.SearchActivity;
import com.flikster.HomeActivity.PostRetrofit;
import com.flikster.R;
import com.flikster.Util.Common;
import com.flikster.Util.FileUtils;
import com.flikster.Util.SharedPrefsUtil;
import com.flikster.permission.DangerousPermResponseCallBack;
import com.flikster.permission.DangerousPermissionResponse;
import com.flikster.permission.DangerousPermissionUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;

/**
 * Created by abhishek on 12-10-2017.
 */

public class MyStyleWithProductOrImageUpload extends Fragment implements View.OnClickListener {
    View view;
    FragmentManager fragmentManager;
    ImageView captureimg, productthingimg, productimg, productthingextraimg;
    Bundle bundle = new Bundle();
    String styletype = "";

    private static int CAMERA_REQUES_CODEE = 101;
    boolean cameracaptured = false;
    final int ACTIVITY_SELECT_IMAGE = 2;
    private static final int IMG_SELECT = 777;
    Activity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try {
            bundle = getArguments();
            fragmentManager = getActivity().getSupportFragmentManager();
            styletype = bundle.getString("MY_STYLE_TYPE");
            Log.e("type_style", styletype + "Style");
            if (styletype.equals("FIRST_STYLE")) {
                SharedPrefsUtil.setStringPreference(getContext(), "CONTAINER_TYPE", "1");
                view = inflater.inflate(R.layout.forgment_my_style_one, container, false);
            } else if (styletype.equals("SECOND_STYLE")) {
                SharedPrefsUtil.setStringPreference(getContext(), "CONTAINER_TYPE", "2");
                view = inflater.inflate(R.layout.fragment_my_style_two, container, false);
            } else if (styletype.equals("THIRD_STYLE")) {
                SharedPrefsUtil.setStringPreference(getContext(), "CONTAINER_TYPE", "3");
                view = inflater.inflate(R.layout.fragment_my_style_three, container, false);
            } else if (styletype.equals("FOURTH_STYLE")) {
                SharedPrefsUtil.setStringPreference(getContext(), "CONTAINER_TYPE", "4");
                view = inflater.inflate(R.layout.fragment_my_style_four, container, false);
            } else if (styletype.equals("FIFTH_STYLE")) {
                SharedPrefsUtil.setStringPreference(getContext(), "CONTAINER_TYPE", "5");
                view = inflater.inflate(R.layout.fragment_my_style_five, container, false);
            }
            initializeViews();
            initializeRest();
        } catch (Exception e) {
            Log.e("Error", "Mystle");
        }
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
                String styleimgaccess = SharedPrefsUtil.getStringPreference(getContext(), "STYLE_IMAGE_CAPTURE_ACCESS");
                if (styleimgaccess != null && !styleimgaccess.isEmpty()) {
                    if (styleimgaccess.equals("ENABLE")) {
                        String styleObjNo = SharedPrefsUtil.getStringPreference(getContext(), "STYLE_OBJECT_NUMBER");
                        if (styleObjNo != null && !styleObjNo.isEmpty()) {
                            if (styleObjNo.equals("1")) {
                                productimg.setScaleType(ImageView.ScaleType.FIT_XY);
                                productimg.setImageBitmap(bitmap);
                                SharedPrefsUtil.setStringPreference(getContext(), "STYLE_IMG_CAPTURE_STR", Common.BitMapToString(bitmap));
                                SharedPrefsUtil.setStringPreference(getContext(), "PRODUCT_IMG", "");
                            } else if (styleObjNo.equals("2")) {
                                productthingimg.setScaleType(ImageView.ScaleType.FIT_XY);
                                productthingimg.setImageBitmap(bitmap);
                                SharedPrefsUtil.setStringPreference(getContext(), "STYLE_IMG_CAPTURE_STR", Common.BitMapToString(bitmap));
                                SharedPrefsUtil.setStringPreference(getContext(), "PRODUCT_IMG_TWO", "");
                            } else if (styleObjNo.equals("3")) {
                                productthingextraimg.setScaleType(ImageView.ScaleType.FIT_XY);
                                productthingextraimg.setImageBitmap(bitmap);
                                SharedPrefsUtil.setStringPreference(getContext(), "STYLE_IMG_CAPTURE_STR", Common.BitMapToString(bitmap));
                                SharedPrefsUtil.setStringPreference(getContext(), "PRODUCT_IMG_THREE", "");
                            }
                        }
                    } else {
                        profileImageSet(bitmap);
                    }
                }
//                profileImageSet(bitmap);
            } else {
                Toast.makeText(getContext(), "failed", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == IMG_SELECT && resultCode == RESULT_OK && data != null) {
            try {
                Uri path = data.getData();
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), path);
                String styleimgaccess = SharedPrefsUtil.getStringPreference(getContext(), "STYLE_IMAGE_CAPTURE_ACCESS");
                if (styleimgaccess != null && !styleimgaccess.isEmpty()) {
                    if (styleimgaccess.equals("ENABLE")) {
                        String styleObjNo = SharedPrefsUtil.getStringPreference(getContext(), "STYLE_OBJECT_NUMBER");
                        if (styleObjNo != null && !styleObjNo.isEmpty()) {
                            if (styleObjNo.equals("1")) {
                                productimg.setScaleType(ImageView.ScaleType.FIT_XY);
                                productimg.setImageBitmap(bitmap);
                                SharedPrefsUtil.setStringPreference(getContext(), "STYLE_IMG_CAPTURE_STR", Common.BitMapToString(bitmap));
                                SharedPrefsUtil.setStringPreference(getContext(), "PRODUCT_IMG", "");

                            } else if (styleObjNo.equals("2")) {
                                productthingimg.setScaleType(ImageView.ScaleType.FIT_XY);
                                productthingimg.setImageBitmap(bitmap);
                                SharedPrefsUtil.setStringPreference(getContext(), "STYLE_IMG_CAPTURE_STR", Common.BitMapToString(bitmap));
                                SharedPrefsUtil.setStringPreference(getContext(), "PRODUCT_IMG_TWO", "");
                            } else if (styleObjNo.equals("3")) {
                                productthingextraimg.setScaleType(ImageView.ScaleType.FIT_XY);
                                productthingextraimg.setImageBitmap(bitmap);
                                SharedPrefsUtil.setStringPreference(getContext(), "STYLE_IMG_CAPTURE_STR", Common.BitMapToString(bitmap));
                                SharedPrefsUtil.setStringPreference(getContext(), "PRODUCT_IMG_THREE", "");
                            }
                           // uploadPhoto(path);
                        }
                    } else {
//                        uploadPhoto(path);
                        profileImageSet(bitmap);
                    }
                }
                // profileImageSet(bitmap);
            } catch (Exception e) {
            }
        }
    }

    private void profileImageSet(Bitmap bitmap) {
        SharedPrefsUtil.setStringPreference(getContext(), "ImageString", Common.BitMapToString(bitmap));
        captureimg.setScaleType(ImageView.ScaleType.FIT_XY);
        captureimg.setImageBitmap(bitmap);
    }

    private void uploadPhoto(Uri fileUri) {
        RequestBody descritption = RequestBody.create(MultipartBody.FORM, "image from camera");
        try {
            File originalFile = FileUtils.getFile(getContext(), fileUri);
            RequestBody filepart = RequestBody.create(MediaType.parse(getActivity().getContentResolver().getType(fileUri)),
                    originalFile);
            MultipartBody.Part file = MultipartBody.Part.createFormData("photo", originalFile.getName(),
                    filepart);
            new PostRetrofit().uploadImageToServer(descritption, file, getContext());
        } catch (Exception e) {
        }
    }

    private void initializeRest() {
        String imgStr = SharedPrefsUtil.getStringPreference(getContext(), "ImageString");
        if (imgStr != null && !imgStr.isEmpty()) {
            Bitmap image = Common.StringToBitMap(imgStr);
            captureimg.setScaleType(ImageView.ScaleType.FIT_XY);
            captureimg.setImageBitmap(image);
        }
        String styleimgaccess = SharedPrefsUtil.getStringPreference(getContext(), "STYLE_IMAGE_CAPTURE_ACCESS");
        if (styleimgaccess != null && !styleimgaccess.isEmpty()) {
            if (styleimgaccess.equals("ENABLE")) {
                String stylecaptureStr = SharedPrefsUtil.getStringPreference(getContext(), "STYLE_IMG_CAPTURE_STR");
                if (stylecaptureStr != null && !stylecaptureStr.isEmpty()) {
                    Bitmap styleCaptureStr = Common.StringToBitMap(stylecaptureStr);
                    productimg.setScaleType(ImageView.ScaleType.FIT_XY);
                    productimg.setImageBitmap(styleCaptureStr);
                }
            }
        }
        String productpicUrl = SharedPrefsUtil.getStringPreference(getContext(), "PRODUCT_IMG");
        if (productpicUrl != null && !productpicUrl.isEmpty()) {
            productimg.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(getContext()).load(productpicUrl).asBitmap().into(productimg);
        }
        String otherimageUrl = SharedPrefsUtil.getStringPreference(getContext(), "PRODUCT_IMG_TWO");
        if (otherimageUrl != null && !otherimageUrl.isEmpty()) {
            productthingimg.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(getContext()).load(otherimageUrl).asBitmap().into(productthingimg);
        }
        if (styletype.equals("THIRD_STYLE") || styletype.equals("FOURTH_STYLE") || styletype.equals("FIFTH_STYLE")) {
            productthingextraimg.setOnClickListener(this);
            String otherimagetwoUrl = SharedPrefsUtil.getStringPreference(getContext(), "PRODUCT_IMG_THREE");
            if (otherimagetwoUrl != null && !otherimagetwoUrl.isEmpty()) {
                productthingextraimg.setScaleType(ImageView.ScaleType.FIT_XY);
                Glide.with(getContext()).load(otherimagetwoUrl).asBitmap().into(productthingextraimg);
            }
        }
        captureimg.setOnClickListener(this);
        productimg.setOnClickListener(this);
        productthingimg.setOnClickListener(this);
    }

    private void initializeViews() {
        captureimg = (ImageView) view.findViewById(R.id.captureimg);
        productimg = (ImageView) view.findViewById(R.id.productimg);
        productthingimg = (ImageView) view.findViewById(R.id.productthingimg);
        if (styletype.equals("THIRD_STYLE") || styletype.equals("FOURTH_STYLE") || styletype.equals("FIFTH_STYLE")) {
            productthingextraimg = (ImageView) view.findViewById(R.id.productthingextraimg);
        }
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
            SharedPrefsUtil.setStringPreference(getContext(), "STYLE_IMAGE_CAPTURE_ACCESS", "DISABLE");
            openCameraClickDialog();
        } else if (v.getId() == R.id.productimg) {
            SharedPrefsUtil.setStringPreference(getContext(), "STYLE_OBJECT_NUMBER", "1");
            openUploadOrtagDialog("1");
        } else if (v.getId() == R.id.productthingimg) {
            SharedPrefsUtil.setStringPreference(getContext(), "STYLE_OBJECT_NUMBER", "2");
            openUploadOrtagDialog("2");
        } else if (v.getId() == R.id.productthingextraimg) {
            SharedPrefsUtil.setStringPreference(getContext(), "STYLE_OBJECT_NUMBER", "3");
            openUploadOrtagDialog("3");
        }
    }

    private void searchActivity(String styletype, String categoryNo) {
        Intent i = new Intent(getContext(), SearchActivity.class);
        i.putExtra("IMAGE_ITEM_CLICK_NO", styletype);
        i.putExtra("CATEGORY_NO", categoryNo);
        startActivity(i);
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
                /*Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, ACTIVITY_SELECT_IMAGE);*/

                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(i, IMG_SELECT);

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

    private void openCategoriesDialog(final String styletype) {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_style_categories_click);
        final Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        LinearLayout dialog_product_style_layout = (LinearLayout) dialog.findViewById(R.id.dialog_product_style_layout);
        LinearLayout dialog_movie_style_layout = (LinearLayout) dialog.findViewById(R.id.dialog_movie_style_layout);
        LinearLayout dialog_celeb_style_layout = (LinearLayout) dialog.findViewById(R.id.dialog_celeb_style_layout);
        LinearLayout dialog_design_style_layout = (LinearLayout) dialog.findViewById(R.id.dialog_design_style_layout);
        LinearLayout dialog_brand_style_layout = (LinearLayout) dialog.findViewById(R.id.dialog_brand_style_layout);
        dialog_product_style_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchActivity(styletype, "1");
                dialog.dismiss();
            }
        });
        dialog_movie_style_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchActivity(styletype, "2");
                dialog.dismiss();
            }
        });
        dialog_celeb_style_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchActivity(styletype, "3");
                dialog.dismiss();
            }
        });
        dialog_design_style_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchActivity(styletype, "4");
                dialog.dismiss();
            }
        });
        dialog_brand_style_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchActivity(styletype, "5");
                dialog.dismiss();
            }
        });
        window.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.translucent)));
        dialog.show();
    }

    private void openUploadOrtagDialog(final String styletype) {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_style_upload_or_tagstyleclick);
        final Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        LinearLayout dialog_upload_layout = (LinearLayout) dialog.findViewById(R.id.dialog_upload_layout);
        LinearLayout dialog_tag_style_layout = (LinearLayout) dialog.findViewById(R.id.dialog_tag_style_layout);
        dialog_upload_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPrefsUtil.setStringPreference(getContext(), "STYLE_IMAGE_CAPTURE_ACCESS", "ENABLE");
                openCameraClickDialog();
                dialog.dismiss();
            }
        });
        dialog_tag_style_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCategoriesDialog(styletype);
                dialog.dismiss();
            }
        });

        window.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.translucent)));
        dialog.show();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }
}
