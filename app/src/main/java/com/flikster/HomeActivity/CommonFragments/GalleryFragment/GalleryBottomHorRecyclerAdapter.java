package com.flikster.HomeActivity.CommonFragments.GalleryFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.HomeActivity.CommonFragments.MyStyleFragment.CustomStyleTypes.MyStyleFragmentOne;
import com.flikster.R;
import com.leo.simplearcloader.SimpleArcLoader;


/**
 * Created by abhishek on 13-10-2017.
 */

public class GalleryBottomHorRecyclerAdapter extends RecyclerView.Adapter<GalleryBottomHorRecyclerAdapter.ViewHolder> {
    Context context;
    int Count;
    //    GalleryCardClick.Galleryimageclick testing;
    SimpleArcLoader mDialog;
    ApiInterface apiInterface;
    GalleryInnerData outerHits;
    int positionValue;
    GalleryCardClick galleryCardClickFrament;
    Bundle bundle;
    FragmentManager fragmentManager;


    public GalleryBottomHorRecyclerAdapter(Context context, FragmentManager fragmentManager, GalleryInnerData outerHits, Integer Count) {
//                                           GalleryCardClick.Galleryimageclick testing) {
        this.context = context;
        this.outerHits = outerHits;
        this.Count = Count;
//        this.testing = testing;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_celebrity_bio_images_recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        if (outerHits.getHits().get(position).get_source().getMovie() != null && outerHits.getHits().get(position).get_source().getMovie().size() != 0) {
            Glide.with(context).load(outerHits.getHits().get(position).get_source().getMovie().get(0).getProfilePic()).into(holder.carousel_image);
            holder.carousel_title.setText(outerHits.getHits().get(position).get_source().getMovie().get(0).getName());
        } else if (outerHits.getHits().get(position).get_source().getCeleb() != null && outerHits.getHits().get(position).get_source().getCeleb().size() != 0) {
            Glide.with(context).load(outerHits.getHits().get(position).get_source().getCeleb().get(0).getProfilePic()).into(holder.carousel_image);
            holder.carousel_title.setText(outerHits.getHits().get(position).get_source().getCeleb().get(0).getName());
        }
    }

    @Override
    public int getItemCount() {
        return Count;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView carousel_image;
        TextView carousel_title;

        public ViewHolder(View itemView) {
            super(itemView);
            carousel_image = (ImageView) itemView.findViewById(R.id.carousel_image);
            carousel_title = (TextView) itemView.findViewById(R.id.carousel_title);
            carousel_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v.getId() == R.id.carousel_image) {
                        if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().size() != 0) {
                            relatedgalleryitemClick(getAdapterPosition());
                        } else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null && outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().size() != 0) {
                            relatedgalleryitemClick(getAdapterPosition());
                        } else {
                            Toast.makeText(context, "There is no gallery data available", Toast.LENGTH_SHORT).show();
                        }



                        /*if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null) {
                            testing.galleryCardOnItemEvent(
                                    outerHits.getHits().get(getAdapterPosition()).get_source().getMedia().getGallery(),
                                    outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getName(),
                                    outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getProfilePic(),
                                    outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getType(),
                                    outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(), new GalleryCardClick());
                        } else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null) {

                            if (outerHits.getHits().get(getAdapterPosition()).get_source().getMedia().getGallery() != null) {
                                Log.e("GALLERY", outerHits.getHits().get(getAdapterPosition()).get_source().getMedia().getGallery() + "SHIVA!");
                                Log.e("NAME", outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getName() + "SHIVA1");
                                Log.e("PROFILEPIC", outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getProfilePic() + "SHIVA2");
                                Log.e("TYPE", outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getType() + "SHIVA3");
                                Log.e("TITLE", outerHits.getHits().get(getAdapterPosition()).get_source().getTitle() + "SHIVA4");*/

                                /*testing.galleryCardOnItemEvent(
                                        outerHits.getHits().get(getAdapterPosition()).get_source().getMedia().getGallery(),
                                        outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getName(),
                                        outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getProfilePic(),
                                        outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getType(),
                                        outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
                                        new GalleryCardClick());*/


                    }
//                } else

//                {
                            /*testing.galleryCardOnItemEvent(outerHits.getHits().get(getAdapterPosition()).get_source().getMedia().getGallery(),
                                    "",
                                    "", "", outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(), new GalleryCardClick());*/
//                }
                }
//            }
            });
        }

        @Override
        public void onClick(View view) {
//            if (view.getId() == R.id.carousel_image) {
//                if (outerHits.getHits().get(getAdapterPosition()).get_source().getMovie() != null) {
//                    testing.galleryCardOnItemEvent(outerHits.getHits().get(getAdapterPosition()).get_source().getMedia().getGallery(),
//                            outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getName(),
//                            outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getProfilePic(),
//                            outerHits.getHits().get(getAdapterPosition()).get_source().getMovie().get(0).getType(),
//                            outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(), new GalleryCardClick());
//                } else if (outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb() != null) {
//                    if (outerHits.getHits().get(getAdapterPosition()).get_source().getMedia().getGallery() != null) {
//                        testing.galleryCardOnItemEvent(
//                                outerHits.getHits().get(0).get_source().getMedia().getGallery(),
//                                outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getName(),
//                                outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getProfilePic(),
//                                outerHits.getHits().get(getAdapterPosition()).get_source().getCeleb().get(0).getType(),
//                                outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(),
//                                new GalleryCardClick());
//                        return;
//                    }
//                } else {
//                    testing.galleryCardOnItemEvent(outerHits.getHits().get(getAdapterPosition()).get_source().getMedia().getGallery(),
//                            "",
//                            "", "", outerHits.getHits().get(getAdapterPosition()).get_source().getTitle(), new GalleryCardClick());
//                }
//            }

        }
    }

    private void relatedgalleryitemClick(int getAdapterPositionValue) {
        galleryCardClickFrament = new GalleryCardClick();
        bundle = new Bundle();
        bundle.putString("RELATED_GALLERY_IMG", "ENABLE");
        bundle.putInt("IMG_POSITION_VALUE", getAdapterPositionValue);
        bundle.putSerializable("GALLERY_DATA", outerHits);
        galleryCardClickFrament.setArguments(bundle);
        fragmentManager.beginTransaction()
                .replace(R.id.main_container, galleryCardClickFrament)
                .addToBackStack("")
                .commit();
    }


}
