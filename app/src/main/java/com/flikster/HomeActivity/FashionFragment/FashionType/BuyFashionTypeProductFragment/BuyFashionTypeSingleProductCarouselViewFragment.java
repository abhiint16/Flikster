package com.flikster.HomeActivity.FashionFragment.FashionType.BuyFashionTypeProductFragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Trace;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.flikster.HomeActivity.CommonFragments.NotificationFragment.NotificationFragment;
import com.flikster.HomeActivity.FashionFragment.FashionType.CelebStoreFragment.CelebStoreFragmentAdapter;
import com.flikster.HomeActivity.FeedFragment.FeedFragment;
import com.flikster.MyBagActivity.MyBagActivity;
import com.flikster.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.w3c.dom.Text;

/**
 * Created by Logins on 21-11-2017.
 */

public class BuyFashionTypeSingleProductCarouselViewFragment extends Fragment implements View.OnClickListener {
    View view;
    CarouselView carouselView;
    RecyclerView.LayoutManager layoutManagerFashionFragment;
    BuyFashionTypeProductFragmentAdapter celebStoreFragmentAdapter;
    Toolbar toolbar_frag_multiicons_toolbar;
    FragmentManager fragmentManager;
    TextView toolbar_frag_multiicons_title;
    ImageButton toolbar_frag_multiicons_back_navigation;

    ImageView buyimg;
    int[] sampleImages = {R.drawable.shirtblue, R.drawable.shirtblue, R.drawable.shirtblue, R.drawable.shirtblue, R.drawable.shirtblue};
    Button buybtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.card_buy_product_carouselimgs, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        initializeViews();
        initializeRest();
        return view;
    }

    private void initializeRest() {
        toolbar_frag_multiicons_title.setText("BUY");
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);
        toolbar_frag_multiicons_back_navigation.setOnClickListener(this);
        //toolbar_frag_multiicons_notification.setOnClickListener(this);
        //toolbar_frag_multiicons_cart.setOnClickListener(this);
        buybtn.setOnClickListener(this);
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };


    private void initializeViews() {
        toolbar_frag_multiicons_title = (TextView)view.findViewById(R.id.toolbar_frag_multiicons_title);
        //toolbar_frag_multiicons_notification = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_notification);
        //toolbar_frag_multiicons_cart = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_cart);
        toolbar_frag_multiicons_back_navigation = (ImageButton) view.findViewById(R.id.toolbar_frag_multiicons_back_navigation);
        carouselView = (CarouselView) view.findViewById(R.id.carouselView);
        buybtn = (Button) view.findViewById(R.id.buybtn);
        fragmentManager = getActivity().getSupportFragmentManager();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.toolbar_frag_multiicons_back_navigation) {
            getFragmentManager().popBackStackImmediate();
        } else if (view.getId() == R.id.buybtn) {
            Intent intent = new Intent(getActivity(), MyBagActivity.class);
            startActivity(intent);
        } /*else if (view.getId() == R.id.toolbar_frag_multiicons_notification) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, new NotificationFragment())
                    .addToBackStack("")
                    .commit();
        }*/
    }
}
