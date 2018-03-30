package com.flikster.HomeActivity.FashionFragment.FashionNewDesign.FashionFragContainerClick;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flikster.HomeActivity.ApiClient;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.HomeActivity.CommonFragments.ProductFragment.ProductOnClick;
import com.flikster.HomeActivity.FashionFragment.FashionType.AllStoreFragment.AllStoreData;
import com.flikster.HomeActivity.FashionFragment.FashionType.AllStoreFragment.AllStoreInnerData;
import com.flikster.R;
import com.flikster.Util.Common;
import com.rohitarya.glide.facedetection.transformation.FaceCenterCrop;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abhishek on 07-03-2018.
 */

public class FashionContainerClickFragAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    AllStoreInnerData hits;
    List<String> carouselImg = new ArrayList<>();
    Context context;
    GridReyclerAdapter gridReyclerAdapter;
    RecyclerView.LayoutManager layoutManager;
    int j = -1;
    int k = 0;
    String url;
    FashionContainerClickFrag.FashionOnClickToProduct fashionOnClick;
    ApiInterface apiInterface;
    int count=10;

    public FashionContainerClickFragAdapter(AllStoreInnerData hits, Context context, FashionContainerClickFrag.FashionOnClickToProduct fashionOnClick,
                                            String url) {
        this.url = url;
        this.hits = hits;
        this.context = context;
        this.fashionOnClick = fashionOnClick;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.carouselview, parent, false);
            return new ViewHolder0(view);
        } else if (viewType == 2) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fashion_containerclick_recycler_item, parent, false);
            return new ViewHolder1(view);
        } else if (viewType == 100) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_no_comments, parent, false);
            return new ViewHolder100(view);
        } else if (viewType == 50) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hor_last_item_load_more, parent, false);
            return new ViewHolder50(view);
        }
        return null;
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            Glide.with(context).load(carouselImg.get(position).trim())
                    .into(imageView);
            //imageView.setImageURI(Uri.parse(carouselImg.get(position)));
        }
    };

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 1) {
            int j = 0;
            int k;
            if (hits.getHits().size() > 4) {
                k = 4;
            } else
                k = hits.getHits().size();
            for (int i = 0; i < k; i++) {
                if (hits.getHits().get(i).get_source().getImageGallery() != null && hits.getHits().get(i).get_source().getImageGallery().size() != 0) {
                    carouselImg.add(hits.getHits().get(i).get_source().getImageGallery().get(0));
                    j = ++j;
                }
            }
            ((ViewHolder0) holder).carouselView.setPageCount(j);
            ((ViewHolder0) holder).carouselView.setImageListener(imageListener);
        } else if (holder.getItemViewType() == 2) {
            if (hits.getHits().get(position - 1).get_source().getImageGallery() != null&&hits.getHits().get(position - 1).get_source().getImageGallery().size()!=0)
                Glide.with(context).load(hits.getHits().get(position - 1).get_source().getImageGallery().get(0))
                        .into(((ViewHolder1) holder).fashion_containerclick_recycler_item_image);
            if (hits.getHits().get(position - 1).get_source().getName() != null)
                ((ViewHolder1) holder).fashion_containerclick_recycler_item_title.setText(hits.getHits().get(position - 1).get_source().getName());
            if (hits.getHits().get(position - 1).get_source().getPrice() != null)
                ((ViewHolder1) holder).fashion_containerclick_recycler_item_price.setText("Rs. " + hits.getHits().get(position - 1).get_source().getPrice());
            /*Log.e("chcek for size","chec j&k"+(position+j)+"AND"+(position+k));
            if (!(hits.getHits().size()<=position+j))
            {
                Glide.with(context).load(hits.getHits().get(position+j).get_source().getImageGallery().get(0))
                        .into(((ViewHolder1)holder).fashion_grid_item_image1);
                j++;
            }
            if (!(hits.getHits().size()<=position+k))
            {
                Glide.with(context).load(hits.getHits().get(position+k).get_source().getImageGallery().get(0))
                        .into(((ViewHolder1)holder).fashion_grid_item_image2);
                k++;
            }
            Log.e("chcek beofre value","chec before"+j+"AND"+k);
            Log.e("chcek after value","chec after"+j+"AND"+k);*/
            /*layoutManager=new GridLayoutManager(context,2);
            //layoutManager=new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
            ((ViewHolder1)holder).fragment_common_recyclerview_recycler.setLayoutManager(layoutManager);
            gridReyclerAdapter=new GridReyclerAdapter(context,hits);
            ((ViewHolder1)holder).fragment_common_recyclerview_recycler.setAdapter(gridReyclerAdapter);*/
        } else if (holder.getItemViewType() == 100) {
            ((ViewHolder100) holder).activity_no_comments_tv.setText("No Content Available");
        }
        else if (holder.getItemViewType()==50)
        {
            String subStringInitial;
            String subStringLast;
            subStringInitial=url.substring(0,88);
            subStringLast=url.substring(89);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    (int) context.getResources().getDimension(R.dimen.fifty));
            ((ViewHolder50)holder).hor_last_item_load_more_container.setLayoutParams(params);
            loadMore(subStringInitial+count+subStringLast);
        }
    }

    private void loadMore(String urlFinal) {
        apiInterface = ApiClient.getClient("http://apiservice-ec.flikster.com/products/_search/").create(ApiInterface.class);
        Call<AllStoreData> call = apiInterface.getAllStore(urlFinal);
        call.enqueue(new Callback<AllStoreData>() {
            @Override
            public void onResponse(Call<AllStoreData> call, Response<AllStoreData> response) {
                count=count+10;
                hits.getHits().addAll(response.body().getHits().getHits());
                notifyItemRangeChanged(count-9,10);
            }

            @Override
            public void onFailure(Call<AllStoreData> call, Throwable t) {
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (hits.getHits() != null && hits.getHits().size() != 0) {
            if (hits.getHits().size() != hits.getTotal())
                return hits.getHits().size() + 2;
            else return hits.getHits().size() + 1;
        } else
            return 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (hits.getHits() != null && hits.getHits().size() != 0) {
            if (position == 0) {
                return 1;
            }
            if (position > hits.getHits().size())
                return 50;
            else return 2;
        } else return 100;
    }

    public class ViewHolder0 extends RecyclerView.ViewHolder {
        CarouselView carouselView;

        public ViewHolder0(View itemView) {
            super(itemView);
            carouselView = (CarouselView) itemView.findViewById(R.id.carouselView);
        }
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView fashion_containerclick_recycler_item_image;
        TextView fashion_containerclick_recycler_item_price, fashion_containerclick_recycler_item_title;
        CardView fashion_containerclick_recycler_item_card;
        ImageButton fashion_containerclick_recycler_item_sharebtn;

        public ViewHolder1(View itemView) {
            super(itemView);
            fashion_containerclick_recycler_item_card = (CardView) itemView.findViewById(R.id.fashion_containerclick_recycler_item_card);
            fashion_containerclick_recycler_item_image = (ImageView) itemView.findViewById(R.id.fashion_containerclick_recycler_item_image);
            fashion_containerclick_recycler_item_title = (TextView) itemView.findViewById(R.id.fashion_containerclick_recycler_item_title);
            fashion_containerclick_recycler_item_price = (TextView) itemView.findViewById(R.id.fashion_containerclick_recycler_item_price);
            fashion_containerclick_recycler_item_sharebtn=(ImageButton)itemView.findViewById(R.id.fashion_containerclick_recycler_item_sharebtn);
            fashion_containerclick_recycler_item_card.setOnClickListener(this);
            fashion_containerclick_recycler_item_sharebtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view.getId()==R.id.fashion_containerclick_recycler_item_card)
            {
                fashionOnClick.onBuyClick(hits.getHits().get(getAdapterPosition()-1).get_source().getId(),
                        hits.getHits().get(getAdapterPosition()-1).get_source().getSize(), "abhiint",
                        hits.getHits().get(getAdapterPosition()-1).get_source().getPrice(),
                        hits.getHits().get(getAdapterPosition()-1).get_source().getProfilePic(),
                        hits.getHits().get(getAdapterPosition()-1).get_source().getName(),
                        hits.getHits().get(getAdapterPosition()-1).get_source().getSlug(),
                        hits.getHits().get(getAdapterPosition()-1).get_source().getImageGallery(),
                        hits.getHits().get(getAdapterPosition()-1).get_source().getProductDescription(),
                        hits.getHits().get(getAdapterPosition()-1).get_source().getProductInfo(), new ProductOnClick());
            }
            else if (view.getId()==R.id.fashion_containerclick_recycler_item_sharebtn)
            {
                Common.shareClick(hits.getHits().get(getAdapterPosition()-1).get_source().getImageGallery().get(0),context);
            }
        }
    }

    public class ViewHolder100 extends RecyclerView.ViewHolder {
        TextView activity_no_comments_tv;

        public ViewHolder100(View itemView) {
            super(itemView);
            activity_no_comments_tv = (TextView) itemView.findViewById(R.id.activity_no_comments_tv);
        }
    }

    public class ViewHolder50 extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout hor_last_item_load_more_container;
        public ViewHolder50(View itemView) {
            super(itemView);
            hor_last_item_load_more_container=(LinearLayout) itemView.findViewById(R.id.hor_last_item_load_more_container);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
