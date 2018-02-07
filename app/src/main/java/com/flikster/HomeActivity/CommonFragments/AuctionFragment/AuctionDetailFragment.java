package com.flikster.HomeActivity.CommonFragments.AuctionFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.flikster.Authentication.AuthenticationActivity;
import com.flikster.Authentication.SignUpActivity.SignupWithGmailOrFBData;
import com.flikster.HomeActivity.ApiClient;
import com.flikster.HomeActivity.ApiInterface;
import com.flikster.HomeActivity.CommonFragments.AuctionFragment.AuctionType.Current.AuctionCurrentFragment;
import com.flikster.HomeActivity.CommonFragments.AuctionFragment.AuctionType.Current.AuctionCurrentFragmentAdapter;
import com.flikster.HomeActivity.CommonFragments.AuctionFragment.AuctionType.Current.AuctionCurrentOrUpcomingData;
import com.flikster.HomeActivity.CommonFragments.AuctionFragment.AuctionType.Current.AuctionRelatedProductsViewHolder;
import com.flikster.HomeActivity.CommonFragments.AuctionFragment.AuctionType.Current.OnGoingBidData;
import com.flikster.HomeActivity.CommonFragments.ProductFragment.ProductImagesAdapter;
import com.flikster.CheckoutActivity.MyBagContinueOnClickActivity;
import com.flikster.HomeActivity.CommonFragments.CelebrityFragment.CelebrityBioAdapterImagesViewHolder;
import com.flikster.HomeActivity.FashionFragment.FashionLandingFragment.FashionLandingFragment;
import com.flikster.HomeActivity.FeedFragment.FeedFragment;
import com.flikster.HomeActivity.HomeActivity;
import com.flikster.R;
import com.flikster.Util.DateUtil;
import com.flikster.Util.SharedPrefsUtil;
import com.leo.simplearcloader.SimpleArcLoader;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by abhishek on 16-10-2017.
 */

public class AuctionDetailFragment extends Fragment implements View.OnClickListener {
    View view;
    Button placebidbtn, add, buy;
    ImageButton toolbar_back_navigation_btn;
    FragmentManager fragmentManager;
    TextView fragment_common_recyclerview_with_tv_title, infotxt, descrtxt;
    RecyclerView fragment_common_recyclerview_with_tv_recycler;
    ProductImagesAdapter myPrductImgAdapter;
    //    RecyclerView fragment_common_recyclerview_recycler;
    CelebrityBioAdapterImagesViewHolder myCeleAdapter;
    LinearLayout cartlayout;
    CarouselView carouselView;
    List<AuctionCurrentOrUpcomingData.AuctionInnerData> auctionDetails;
    List<String> GalleryData = new ArrayList<>();
    LinearLayout productqtylayout;

    TextView toolbar_frag_title, product_price, product_size_small, product_size_med, product_size_large, product_size_extra, product_size_extra_extra;
    TextView product_title, product_quantity_minus_btn, product_quanitity_plus_btn, product_quantity_txt;
    int sizeOfSize;
    String chosenSize = "";
    List<String> size = new ArrayList<>();
    TextView pricebidtxt, timelefttxt, enterbidtxt;
    String userChoose = "";
    //    boolean userChooseChoiceEnable = "";
    EditText et_createac_fullname;
    int positionvalue;

    RecyclerView ratingNowShowingRecycler;
    RecyclerView.LayoutManager ratingNowShowingLayoutManager;
    AuctionRelatedProductsViewHolder auctionCurrentFragmentAdapter;
    SimpleArcLoader mDialog;
    ApiInterface apiInterface;
    TextView ongoingbid;

    int ongoingbidAmount = 0;
    int ongoingbidAmountIncr = 0;

    AuctionPlaceBidData auctionPlaceBidData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_auction, container, false);
        Bundle bundle = getArguments();
        positionvalue = bundle.getInt("POSITION_VALUE");
        auctionDetails = (List<AuctionCurrentOrUpcomingData.AuctionInnerData>) bundle.getSerializable("AUCTION_DETAILS");
        Log.e("nameProfile", auctionDetails.get(positionvalue).getName());
//        GalleryData.add(auctionDetails.get(positionvalue).getProfilePic());
        GalleryData = auctionDetails.get(positionvalue).getGallery();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        initializeViews();
        initializeRest();
        onGoingBidServerData();
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.placebidbtn) {
            if (userChoose != null && !userChoose.isEmpty()) {
                if (et_createac_fullname.getText().toString() != null && !et_createac_fullname.getText().toString().isEmpty()) {
                    String enterbid = et_createac_fullname.getText().toString();
                    Integer enterbidValue = Integer.valueOf(enterbid);
                    Integer availbleBidvalue = Integer.valueOf(auctionDetails.get(positionvalue).getStartingPrice());
                    if (availbleBidvalue <= enterbidValue) {
                        if (enterbidValue >= ongoingbidAmountIncr) {
                            if (SharedPrefsUtil.getStringPreference(getContext(), "USER_ID") != null && !SharedPrefsUtil.getStringPreference(getContext(), "USER_ID").isEmpty()) {
                                placeBidServerData(String.valueOf(enterbidValue),
                                        auctionDetails.get(positionvalue).getId(),
                                        SharedPrefsUtil.getStringPreference(getContext(), "USER_ID"));
                                Toast.makeText(getActivity(), "Your Bid Placed Successfully..", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), "please login.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Entered bid less then On going bid.", Toast.LENGTH_SHORT).show();
                        }


                    } else {
                        Toast.makeText(getActivity(), "bid amount is not less then to the enter amount", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getActivity(), "please enter your bid", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getActivity(), "choose size before buying", Toast.LENGTH_SHORT).show();
            }

        } else if (view.getId() == R.id.toolbar_back_navigation_btn) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, new AuctionFeedFragment())
                    .addToBackStack("")
                    .commit();
        }
        if (view.getId() == R.id.ongoingbid) {
            onGoingBidServerData();
        }
        if (view.getId() == R.id.buy_now_btn) {
            if (chosenSize.trim().length() == 0 || chosenSize == null) {
                if (size.size() != 1) {
                    Toast.makeText(getActivity(), "choose size before buying", Toast.LENGTH_SHORT).show();
                    return;
                } else if (size.size() == 1) {
                    chosenSize = size.get(positionvalue);
                }
            }
            /*Intent intent = new Intent(getActivity(), MyBagContinueOnClickActivity.class);
            intent.putExtra("productId", productId);
            intent.putExtra("productSlug", productSlug);
            intent.putExtra("productTitle", productTitle);
            intent.putExtra("userId", userId);
            intent.putExtra("size", chosenSize);
            intent.putExtra("color", "RED");
            intent.putExtra("profilePic", profilePic);
            intent.putExtra("price", price);
            intent.putExtra("quantity", product_quantity_txt.getText().toString());
            startActivity(intent);*/
        } else if (view.getId() == R.id.toolbar_back_navigation_btn) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, new AuctionFeedFragment())
                    .addToBackStack("")
                    .commit();
        } else if (view.getId() == R.id.buy_click_product_quantity_minus_btn) {
            int i = Integer.parseInt(product_quantity_txt.getText().toString());
            if (i > 1)
                product_quantity_txt.setText("" + (i - 1));
        } else if (view.getId() == R.id.buy_click_product_quantity_plus_btn) {
            int i = Integer.parseInt(product_quantity_txt.getText().toString());
            product_quantity_txt.setText("" + (i + 1));
        } else if (view.getId() == R.id.buy_click_product_size_small) {
            product_size_small.setBackgroundColor(getActivity().getResources().getColor(R.color.colorAccent));
            product_size_med.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
            product_size_large.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
            product_size_extra.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
            product_size_extra_extra.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
            userChoose = "S";
//            chosenSize = size.get(positionvalue);
        } else if (view.getId() == R.id.buy_click_product_size_medium) {
            product_size_small.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
            product_size_med.setBackgroundColor(getActivity().getResources().getColor(R.color.colorAccent));
            product_size_large.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
            product_size_extra.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
            product_size_extra_extra.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
//            this.chosenSize = size.get(1);
            userChoose = "M";
        } else if (view.getId() == R.id.buy_click_product_size_large) {
            product_size_small.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
            product_size_med.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
            product_size_large.setBackgroundColor(getActivity().getResources().getColor(R.color.colorAccent));
            product_size_extra.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
            product_size_extra_extra.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
//            chosenSize = size.get(2);
            userChoose = "L";
        } else if (view.getId() == R.id.buy_click_product_size_extra_large) {
            product_size_small.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
            product_size_med.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
            product_size_large.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
            product_size_extra.setBackgroundColor(getActivity().getResources().getColor(R.color.colorAccent));
            product_size_extra_extra.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
//            chosenSize = size.get(3);
            userChoose = "XL";
        } else if (view.getId() == R.id.buy_click_product_size_extra_extra_large) {
            product_size_small.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
            product_size_med.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
            product_size_large.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
            product_size_extra.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
            product_size_extra_extra.setBackgroundColor(getActivity().getResources().getColor(R.color.colorAccent));
//            chosenSize = size.get(4);
            userChoose = "XLL";
        }

    }

    private void initializeRest() {
        toolbar_frag_title.setText("Auction");
        fragment_common_recyclerview_with_tv_title.setText("Recommended Product");
        //toolbar_more_icon.setVisibility(View.VISIBLE);
        cartlayout.setVisibility(View.GONE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        fragment_common_recyclerview_with_tv_recycler.setLayoutManager(layoutManager);
        auctionCurrentFragmentAdapter = new AuctionRelatedProductsViewHolder(getContext(),
                fragmentManager, auctionDetails);
        fragment_common_recyclerview_with_tv_recycler.setAdapter(auctionCurrentFragmentAdapter);

        toolbar_back_navigation_btn.setOnClickListener(this);
        placebidbtn.setOnClickListener(this);

        setAvailableText();

        carouselView.setPageCount(GalleryData.size());
        carouselView.setImageListener(imageListenersData);

        sizeOfSize = 0;// size.size();

        if (auctionDetails.get(positionvalue).getName() != null && !auctionDetails.get(positionvalue).getName().isEmpty()) {
            product_title.setText(Html.fromHtml(auctionDetails.get(positionvalue).getName()));
        }


        if (auctionDetails.get(positionvalue).getStartingPrice() != null && !auctionDetails.get(positionvalue).getStartingPrice().isEmpty()) {
            product_price.setText("Rs " + Html.fromHtml(auctionDetails.get(positionvalue).getStartingPrice() + "/-"));
        }

        if (auctionDetails.get(positionvalue).getStartingPrice() != null && !auctionDetails.get(positionvalue).getStartingPrice().isEmpty()) {
            pricebidtxt.setText("Rs " + Html.fromHtml(auctionDetails.get(positionvalue).getStartingPrice() + "/-"));
        }

       /* if (auctionDetails.get(positionvalue).getEndTime() != null && !auctionDetails.get(positionvalue).getEndTime().isEmpty()) {
//            timelefttxt.setText("Rs " + Html.fromHtml(auctionDetails.get(positionvalue).getStartingPrice() + "/"));
            try {
                JSONObject objtime = new JSONObject(auctionDetails.get(positionvalue).getEndTime());
                String hours = objtime.getString("hour");
                String minute = objtime.getString("minute");
                String second = objtime.getString("second");
                String completeTime = hours + "h " + minute + "m " + second + "s ";
                timelefttxt.setText(Html.fromHtml(completeTime));
            } catch (Exception e) {
            }
        }*/

        if (auctionDetails.get(0).getEndDate()
                != null && !auctionDetails.get(0).getEndDate().isEmpty()) {
            try {
                       /* JSONObject objtime = new JSONObject(auctionCurrentOrUpcomingData.get(0).getEndTime());
                        String hours = objtime.getString("hour");
                        String minute = objtime.getString("minute");
                        String second = objtime.getString("second");
                        String completeTime = "Time Left: " + hours + "h " + minute + "m " + second + "s ";
                        ((ViewHolder1) holder).timelefttxt.setText(Html.fromHtml(completeTime) + "");*/
//                        String enddateTime = DateUtil.serverSentTimeChange(auctionCurrentOrUpcomingData.get(0).getEndDate());

                String enddateTime = DateUtil.serverSentTimeChange(auctionDetails.get(0).getEndDate());
                SimpleDateFormat simDf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                Date enddate = simDf.parse(enddateTime);
                long endTimeMillSec = enddate.getTime();
                timelefttxt.setText(Html.fromHtml(DateUtil.getTimeLeft(endTimeMillSec)) + "");


            } catch (Exception e) {

            }
        }

        if (auctionDetails.get(positionvalue).getInfo() != null && !auctionDetails.get(positionvalue).getInfo().isEmpty()) {
            infotxt.setText("Information : " + Html.fromHtml(auctionDetails.get(positionvalue).getInfo()));
        }
        if (auctionDetails.get(positionvalue).getDescription() != null && !auctionDetails.get(positionvalue).getDescription().isEmpty()) {
            descrtxt.setText("Description : " + Html.fromHtml(auctionDetails.get(positionvalue).getDescription()));
        }
        product_size_small.setOnClickListener(this);
        product_size_med.setOnClickListener(this);
        product_size_large.setOnClickListener(this);
        product_size_extra.setOnClickListener(this);
        product_size_extra_extra.setOnClickListener(this);
        ongoingbid.setOnClickListener(this);
    }

    private void setAvailableText() {
        String infostr = "<font color=\"#030a0a\"><bold>"
                + "Information : \n"
                + "</bold></font>" + "Being Fab Men's Solid Formal Blue Shirt,Regular Fit, Full Sleeve, Being Fab has just the range of apparel";
        //
        infotxt.setText(Html.fromHtml(infostr));

        String descstr = "<font color=\"#030a0a\"><bold>"
                + "Description : \n"
                + "</bold></font>" + "If you are looking to be a trend setter when you go out to the fanciest of parties on a weekend, Being Fab has just the range of apparel that ensures you'd never miss that admirable glance or compliment. You can tuck the shirt inside for a formal look and also put it outside for casual look.";
        //Description :
        // If you are looking to be a trend setter when you go out to the fanciest of parties on a weekend, Being Fab has just the range of apparel that ensures you'd never miss that admirable glance or compliment. You can tuck the shirt inside for a formal look and also put it outside for casual look.
        descrtxt.setText(Html.fromHtml(descstr));
    }

    private void initializeViews() {
        toolbar_back_navigation_btn = (ImageButton) view.findViewById(R.id.toolbar_back_navigation_btn);
        toolbar_frag_title = (TextView) view.findViewById(R.id.toolbar_frag_title);
        fragment_common_recyclerview_with_tv_title = (TextView) view.findViewById(R.id.fragment_common_recyclerview_with_tv_title);
        infotxt = (TextView) view.findViewById(R.id.infotxt);
        descrtxt = (TextView) view.findViewById(R.id.descrtxt);
//        fragment_common_recyclerview_recycler = (RecyclerView) view.findViewById(R.id.fragment_common_recyclerview_recycler);
        fragment_common_recyclerview_with_tv_recycler = (RecyclerView) view.findViewById(R.id.fragment_common_recyclerview_with_tv_recycler);
        placebidbtn = (Button) view.findViewById(R.id.placebidbtn);
        //toolbar_more_icon = (ImageButton) view.findViewById(R.id.toolbar_more_icon);
        cartlayout = (LinearLayout) view.findViewById(R.id.cartlayout);
        fragmentManager = getActivity().getSupportFragmentManager();

        carouselView = (CarouselView) view.findViewById(R.id.carouselView);

        productqtylayout = (LinearLayout) view.findViewById(R.id.productqtylayout);
        productqtylayout.setVisibility(View.GONE);

        product_price = (TextView) view.findViewById(R.id.buy_click_product_price);
        product_title = (TextView) view.findViewById(R.id.buy_click_product_title);
        product_size_small = (TextView) view.findViewById(R.id.buy_click_product_size_small);
        product_size_med = (TextView) view.findViewById(R.id.buy_click_product_size_medium);
        product_size_large = (TextView) view.findViewById(R.id.buy_click_product_size_large);
        product_size_extra = (TextView) view.findViewById(R.id.buy_click_product_size_extra_large);
        product_size_extra_extra = (TextView) view.findViewById(R.id.buy_click_product_size_extra_extra_large);
        add = (Button) view.findViewById(R.id.add_to_cart_btn);
        buy = (Button) view.findViewById(R.id.buy_now_btn);

        timelefttxt = (TextView) view.findViewById(R.id.timelefttxt);
        pricebidtxt = (TextView) view.findViewById(R.id.pricebidtxt);
        enterbidtxt = (TextView) view.findViewById(R.id.enterbidtxt);

        et_createac_fullname = (EditText) view.findViewById(R.id.et_createac_fullname);

        mDialog = (SimpleArcLoader) view.findViewById(R.id.arc_loader);

        ongoingbid = (TextView) view.findViewById(R.id.ongoingbid);


    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

    ImageListener imageListenersData = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            Log.e("gallerydata", GalleryData.get(position));
            Glide.with(getContext()).load(GalleryData.get(position)).into(imageView);
        }
    };


    /*private void placeBidServerData() {
        mDialog.setVisibility(View.VISIBLE);
        mDialog.start();
        apiInterface = ApiClient.getClient(ApiClient.PLACE_BID_URL).create(ApiInterface.class);
        Call<AuctionCurrentOrUpcomingData> call = apiInterface.getAuctionData(ApiClient.PLACE_BID_URL);
        call.enqueue(new Callback<AuctionCurrentOrUpcomingData>() {
            @Override
            public void onResponse(Call<AuctionCurrentOrUpcomingData> call,
                                   Response<AuctionCurrentOrUpcomingData> response) {
                mDialog.setVisibility(View.GONE);
                mDialog.stop();
            }

            @Override
            public void onFailure(Call<AuctionCurrentOrUpcomingData> call, Throwable t) {
                mDialog.setVisibility(View.GONE);
                mDialog.stop();
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
    }*/


    private void placeBidServerData(final String bidAmount, final String auctionId, final String userId) {
        mDialog.setVisibility(View.VISIBLE);
        mDialog.start();
        auctionPlaceBidData = new AuctionPlaceBidData(bidAmount, auctionId, userId);
        apiInterface = ApiClient.getClient(ApiClient.PLACE_BID_URL)
                .create(ApiInterface.class);
        Call<AuctionPlaceBidData> call = apiInterface.auctionPlaceBidinServer(auctionPlaceBidData);
        call.enqueue(new Callback<AuctionPlaceBidData>() {
            @Override
            public void onResponse(Call<AuctionPlaceBidData> call,
                                   Response<AuctionPlaceBidData> response) {
                mDialog.setVisibility(View.GONE);
                mDialog.stop();
                if (response.body().getStatusCode() == 200) {
                    Toast.makeText(getContext(), "Congratulations Your Bid Placed Successfully", Toast.LENGTH_LONG).show();
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_container, new FashionLandingFragment())
                            .addToBackStack("")
                            .commit();
                } else {
                    if (response.body().getMessage() != null) {
                        Toast.makeText(getContext(), "Failed to Place Bid" + response.body().getMessage(), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getContext(), "Failed to Place Bid", Toast.LENGTH_LONG).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<AuctionPlaceBidData> call, Throwable t) {
                mDialog.setVisibility(View.GONE);
                mDialog.stop();
                Toast.makeText(getContext(), "Failed to Place Bid", Toast.LENGTH_LONG).show();
                Log.e("insied onfailure", "insied onfailre" + call + "bcbbc" + t);
            }
        });
    }


    private void onGoingBidServerData() {
        mDialog.setVisibility(View.VISIBLE);
        mDialog.start();
        apiInterface = ApiClient.getClient(ApiClient.ON_GOING_BID_URL
                + auctionDetails.get(positionvalue).getId() + "/").create(ApiInterface.class);
        Call<OnGoingBidData> call = apiInterface.getOngoingBid(ApiClient.ON_GOING_BID_URL
                + auctionDetails.get(positionvalue).getId() + "/");
        call.enqueue(new Callback<OnGoingBidData>() {
            @Override
            public void onResponse(Call<OnGoingBidData> call,
                                   Response<OnGoingBidData> response) {
                mDialog.setVisibility(View.GONE);
                mDialog.stop();
                if (response.body().getStatusCode() == 200) {
                    ongoingbidAmount = response.body().getHighestBid();
                    if (auctionDetails.get(positionvalue).getBidIncrement() !=null && !auctionDetails.get(positionvalue).getBidIncrement().isEmpty()){
                        ongoingbidAmountIncr = ongoingbidAmount + Integer.valueOf(auctionDetails.get(positionvalue).getBidIncrement());
                        enterbidtxt.setText("Enter Rs " + Html.fromHtml(ongoingbidAmountIncr+ " or more"));
                    }else {
                        enterbidtxt.setText("Enter Rs " + Html.fromHtml(response.body().getHighestBid()+ " or more"));
                    }

                    ongoingbid.setText("On going bid Rs " + response.body().getHighestBid() + " /- ");

                    ongoingbid.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(getContext(), "There is no going bid", Toast.LENGTH_SHORT).show();
                    ongoingbid.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<OnGoingBidData> call, Throwable t) {
                mDialog.setVisibility(View.GONE);
                mDialog.stop();
                Log.e("vvvvvvvvvv", "vv" + call + t);
            }
        });
    }

}
