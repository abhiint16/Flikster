package com.flikster.HomeActivity.SearchViewFragment;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flikster.HomeActivity.GlobalSearchGetData;
import com.flikster.R;
import com.flikster.Util.SharedPrefsUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 03-01-2018.
 */

public class SearchViewRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    GlobalSearchGetData globalSearchGetData;
    SearchViewFragment.SearchViewToFrag searchViewToFrag;
    Context context;
    Boolean movie=false,celeb=false,content=false,product=false,designer=false,brand=false;
    List<String> type=new ArrayList<>();
    String userId="null";
    RecyclerView.LayoutManager layoutManager;
    public SearchViewRecyclerAdapter(GlobalSearchGetData globalSearchGetData, SearchViewFragment.SearchViewToFrag searchViewToFrag,
                                     Context context) {
        this.globalSearchGetData = globalSearchGetData;
        this.searchViewToFrag=searchViewToFrag;
        this.context=context;
        if (this.globalSearchGetData!=null)
        {
            if (this.globalSearchGetData.getMovie()!=null&&this.globalSearchGetData.getMovie().size()!=0)
                type.add("movie");
            if (this.globalSearchGetData.getCeleb()!=null&&this.globalSearchGetData.getCeleb().size()!=0)
                type.add("celeb");
            if (this.globalSearchGetData.getContent()!=null&&this.globalSearchGetData.getContent().size()!=0)
                type.add("content");
            if (this.globalSearchGetData.getProducts()!=null&&this.globalSearchGetData.getProducts().size()!=0)
                type.add("product");
            if (this.globalSearchGetData.getDesigner()!=null&&this.globalSearchGetData.getDesigner().size()!=0)
                type.add("designer");
            if (this.globalSearchGetData.getBrand()!=null&&this.globalSearchGetData.getBrand().size()!=0)
                type.add("brand");
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==1)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.searchview_recycler_item,parent,false);
            return new ViewHolder1(view);
        }
        else if(viewType==2)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.searchview_recycler_item,parent,false);
            return new ViewHolder2(view);
        }
        else if(viewType==3)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.searchview_recycler_item,parent,false);
            return new ViewHolder3(view);
        }
        else if(viewType==4)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.searchview_recycler_item,parent,false);
            return new ViewHolder4(view);
        }
        else if(viewType==5)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.searchview_recycler_item,parent,false);
            return new ViewHolder5(view);
        }
        else if(viewType==6)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.searchview_recycler_item,parent,false);
            return new ViewHolder6(view);
        }
        else {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.search_nodata,parent,false);
            return new ViewHolder0(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (SharedPrefsUtil.getStringPreference(context, "USER_ID") != null && !SharedPrefsUtil.getStringPreference(context, "USER_ID").isEmpty()) {
            userId = SharedPrefsUtil.getStringPreference(context, "USER_ID");
            Log.e("LoginUserId", userId);
        }
        if (holder.getItemViewType()==1)
        {
            ((ViewHolder1)holder).searchview_recycleritem_topname.setText("Movies");
            layoutManager=new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
            ((ViewHolder1)holder).recyclerView.setLayoutManager(layoutManager);
            SearchInnerItemMovieRecyclerViewHolder searchInnerItemMovieRecyclerViewHolder =new SearchInnerItemMovieRecyclerViewHolder(context,globalSearchGetData.getMovie());
            ((ViewHolder1)holder).recyclerView.setAdapter(searchInnerItemMovieRecyclerViewHolder);
        }
        else if (holder.getItemViewType()==2)
        {
            ((ViewHolder2)holder).searchview_recycleritem_topname.setText("Celebrity");
            layoutManager=new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
            ((ViewHolder2)holder).recyclerView.setLayoutManager(layoutManager);
            SearchInnerItemCelebRecyclerViewHolder searchInnerItemCelebRecyclerViewHolder =new SearchInnerItemCelebRecyclerViewHolder(context,globalSearchGetData.getCeleb());
            ((ViewHolder2)holder).recyclerView.setAdapter(searchInnerItemCelebRecyclerViewHolder);
        }
        else if (holder.getItemViewType()==3)
        {
            ((ViewHolder3)holder).searchview_recycleritem_topname.setText("Content");
            layoutManager=new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
            ((ViewHolder3)holder).recyclerView.setLayoutManager(layoutManager);
            SearchInnerItemContentRecyclerViewHolder searchInnerItemContentRecyclerViewHolder =new SearchInnerItemContentRecyclerViewHolder(context,globalSearchGetData.getContent());
            ((ViewHolder3)holder).recyclerView.setAdapter(searchInnerItemContentRecyclerViewHolder);
        }
        else if (holder.getItemViewType()==4)
        {
            ((ViewHolder4)holder).searchview_recycleritem_topname.setText("Products");
            layoutManager=new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
            ((ViewHolder4)holder).recyclerView.setLayoutManager(layoutManager);
            SearchInnerItemProductRecyclerViewHolder searchInnerItemProductRecyclerViewHolder =new SearchInnerItemProductRecyclerViewHolder(context,globalSearchGetData.getProducts());
            ((ViewHolder4)holder).recyclerView.setAdapter(searchInnerItemProductRecyclerViewHolder);
        }
        else if (holder.getItemViewType()==5)
        {
            ((ViewHolder5)holder).searchview_recycleritem_topname.setText("Designer");
            layoutManager=new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
            ((ViewHolder5)holder).recyclerView.setLayoutManager(layoutManager);
            SearchInnerItemDesignerRecyclerViewHolder searchInnerItemDesignerRecyclerViewHolder =new SearchInnerItemDesignerRecyclerViewHolder(context,globalSearchGetData.getDesigner());
            ((ViewHolder5)holder).recyclerView.setAdapter(searchInnerItemDesignerRecyclerViewHolder);
        }
        else if (holder.getItemViewType()==6)
        {
            ((ViewHolder6)holder).searchview_recycleritem_topname.setText("Brand");
            layoutManager=new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
            ((ViewHolder6)holder).recyclerView.setLayoutManager(layoutManager);
            SearchInnerItemBrandRecyclerViewHolder searchInnerItemBrandRecyclerViewHolder =new SearchInnerItemBrandRecyclerViewHolder(context,globalSearchGetData.getBrand());
            ((ViewHolder6)holder).recyclerView.setAdapter(searchInnerItemBrandRecyclerViewHolder);
        }
        else if(holder.getItemViewType()==0)
        {

        }
    }

    @Override
    public int getItemCount() {
        Log.e("hhhhh",""+type.size()+type);
        if (globalSearchGetData!=null)
        {
            /*int a=0;
            if (globalSearchGetData.getMovie()!=null&&globalSearchGetData.getMovie().size()!=0)
                a=++a;
            if (globalSearchGetData.getCeleb()!=null&&globalSearchGetData.getCeleb().size()!=0)
                a=++a;
            if (globalSearchGetData.getBrand()!=null&&globalSearchGetData.getBrand().size()!=0)
                a=++a;
            if (globalSearchGetData.getContent()!=null&&globalSearchGetData.getContent().size()!=0)
                a=++a;
            if (globalSearchGetData.getDesigner()!=null&&globalSearchGetData.getDesigner().size()!=0)
                a=++a;
            if (globalSearchGetData.getProducts()!=null&&globalSearchGetData.getProducts().size()!=0)
                a=++a;
            if (a!=0)
            return a;
            return 1;*/
            if (type.size()==0)
                return 1;
            else return type.size();
        }
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (globalSearchGetData!=null)
        {
            if (position==0)
            {
                if ("movie".equals(type.get(0)))
                    return 1;
                if ("celeb".equals(type.get(0)))
                    return 2;
                if ("content".equals(type.get(0)))
                    return 3;
                if ("product".equals(type.get(0)))
                    return 4;
                if ("designer".equals(type.get(0)))
                    return 5;
                if ("brand".equals(type.get(0)))
                    return 6;
            }
            else if (position==1)
            {
                if ("movie".equals(type.get(1)))
                    return 1;
                if ("celeb".equals(type.get(1)))
                    return 2;
                if ("content".equals(type.get(1)))
                    return 3;
                if ("product".equals(type.get(1)))
                    return 4;
                if ("designer".equals(type.get(1)))
                    return 5;
                if ("brand".equals(type.get(1)))
                    return 6;
            }
            else if (position==2)
            {
                if ("movie".equals(type.get(2)))
                    return 1;
                if ("celeb".equals(type.get(2)))
                    return 2;
                if ("content".equals(type.get(2)))
                    return 3;
                if ("product".equals(type.get(2)))
                    return 4;
                if ("designer".equals(type.get(2)))
                    return 5;
                if ("brand".equals(type.get(2)))
                    return 6;
            }
            else if (position==3)
            {
                if ("movie".equals(type.get(3)))
                    return 1;
                if ("celeb".equals(type.get(3)))
                    return 2;
                if ("content".equals(type.get(3)))
                    return 3;
                if ("product".equals(type.get(3)))
                    return 4;
                if ("designer".equals(type.get(3)))
                    return 5;
                if ("brand".equals(type.get(3)))
                    return 6;
            }
            else if (position==4)
            {
                if ("movie".equals(type.get(4)))
                    return 1;
                if ("celeb".equals(type.get(4)))
                    return 2;
                if ("content".equals(type.get(4)))
                    return 3;
                if ("product".equals(type.get(4)))
                    return 4;
                if ("designer".equals(type.get(4)))
                    return 5;
                if ("brand".equals(type.get(4)))
                    return 6;
            }
            else if (position==5)
            {
                if ("movie".equals(type.get(5)))
                    return 1;
                if ("celeb".equals(type.get(5)))
                    return 2;
                if ("content".equals(type.get(5)))
                    return 3;
                if ("product".equals(type.get(5)))
                    return 4;
                if ("designer".equals(type.get(5)))
                    return 5;
                if ("brand".equals(type.get(5)))
                    return 6;
            }
            else if (position==6)
            {
                if ("movie".equals(type.get(6)))
                    return 1;
                if ("celeb".equals(type.get(6)))
                    return 2;
                if ("content".equals(type.get(6)))
                    return 3;
                if ("product".equals(type.get(6)))
                    return 4;
                if ("designer".equals(type.get(6)))
                    return 5;
                if ("brand".equals(type.get(6)))
                    return 6;
            }
        }
        return 0;
        /*if (globalSearchGetData!=null)
        {
            if ((globalSearchGetData.getMovie()!=null&&globalSearchGetData.getMovie().size()!=0))
            {
                if(position==0)
                return 1;
            }
            if ((globalSearchGetData.getCeleb()!=null&&globalSearchGetData.getCeleb().size()!=0))
            {
                if (position==0||position==1)
                return 2;
            }
            if ((globalSearchGetData.getBrand()!=null&&globalSearchGetData.getBrand().size()!=0)&&brand==false)
            {
                brand=true;
                return 3;
            }
            if ((globalSearchGetData.getContent()!=null&&globalSearchGetData.getContent().size()!=0)&&content==false)
            {
                content=true;
                return 4;
            }
            if ((globalSearchGetData.getDesigner()!=null&&globalSearchGetData.getDesigner().size()!=0)&&designer==false)
            {
                designer=true;
                return 5;
            }
            if ((globalSearchGetData.getProducts()!=null&&globalSearchGetData.getProducts().size()!=0)&&product==false)
            {
                product=true;
                return 6;
            }
            else {
                return 0;
            }
        }
        return 0;*/
    }

    public class ViewHolder0 extends RecyclerView.ViewHolder
    {
        public ViewHolder0(View itemView) {
            super(itemView);
        }
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView searchview_recycleritem_topname;/*,searchview_recycleritem_firstitem,
                searchview_recycleritem_secitem,searchview_recycleritem_seemore;*/
        RecyclerView recyclerView;
        public ViewHolder1(View itemView) {
            super(itemView);
            searchview_recycleritem_topname=(TextView)itemView.findViewById(R.id.searchview_recycleritem_topname);
            recyclerView=(RecyclerView)itemView.findViewById(R.id.searchitem_recyclerview);
            /*searchview_recycleritem_firstitem=(TextView)itemView.findViewById(R.id.searchview_recycleritem_firstitem);
            searchview_recycleritem_secitem=(TextView)itemView.findViewById(R.id.searchview_recycleritem_secitem);
            searchview_recycleritem_seemore=(TextView)itemView.findViewById(R.id.searchview_recycleritem_seemore);
            searchview_recycleritem_seemore.setOnClickListener(this);
            searchview_recycleritem_secitem.setOnClickListener(this);
            searchview_recycleritem_firstitem.setOnClickListener(this);*/
        }

        @Override
        public void onClick(View view) {
            /*if (view.getId()==R.id.searchview_recycleritem_firstitem)
            {
                searchViewToFrag.test(globalSearchGetData.getMovie().get(0).getSlug(),new MovieFragment(),1,userId,
                        globalSearchGetData.getMovie().get(0).getId());
            }
            else if (view.getId()==R.id.searchview_recycleritem_secitem)
            {
                searchViewToFrag.test(globalSearchGetData.getMovie().get(1).getSlug(),new MovieFragment(),1,userId,
                        globalSearchGetData.getMovie().get(1).getId());
            }
            else if (view.getId()==R.id.searchview_recycleritem_seemore)
            {

            }*/
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView searchview_recycleritem_topname,searchview_recycleritem_firstitem,
                searchview_recycleritem_secitem,searchview_recycleritem_seemore;
        RecyclerView recyclerView;
        public ViewHolder2(View itemView) {
            super(itemView);
            searchview_recycleritem_topname=(TextView)itemView.findViewById(R.id.searchview_recycleritem_topname);
            recyclerView=(RecyclerView)itemView.findViewById(R.id.searchitem_recyclerview);
            /*searchview_recycleritem_firstitem=(TextView)itemView.findViewById(R.id.searchview_recycleritem_firstitem);
            searchview_recycleritem_secitem=(TextView)itemView.findViewById(R.id.searchview_recycleritem_secitem);
            searchview_recycleritem_seemore=(TextView)itemView.findViewById(R.id.searchview_recycleritem_seemore);
            searchview_recycleritem_seemore.setOnClickListener(this);
            searchview_recycleritem_secitem.setOnClickListener(this);
            searchview_recycleritem_firstitem.setOnClickListener(this);*/
        }

        @Override
        public void onClick(View view) {
            /*if (view.getId()==R.id.searchview_recycleritem_firstitem)
            {
                searchViewToFrag.test(globalSearchGetData.getCeleb().get(0).getSlug(),new CelebrityFragment(),2,userId,
                        globalSearchGetData.getCeleb().get(0).getId());
            }
            else if (view.getId()==R.id.searchview_recycleritem_secitem)
            {
                searchViewToFrag.test(globalSearchGetData.getCeleb().get(1).getSlug(),new CelebrityFragment(),2,userId,
                        globalSearchGetData.getCeleb().get(1).getId());
            }
            else if (view.getId()==R.id.searchview_recycleritem_seemore)
            {

            }*/
        }
    }

    public class ViewHolder3 extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView searchview_recycleritem_topname,searchview_recycleritem_firstitem,
                searchview_recycleritem_secitem,searchview_recycleritem_seemore;
        RecyclerView recyclerView;
        public ViewHolder3(View itemView) {
            super(itemView);
            searchview_recycleritem_topname=(TextView)itemView.findViewById(R.id.searchview_recycleritem_topname);
            recyclerView=(RecyclerView)itemView.findViewById(R.id.searchitem_recyclerview);
            /*searchview_recycleritem_firstitem=(TextView)itemView.findViewById(R.id.searchview_recycleritem_firstitem);
            searchview_recycleritem_secitem=(TextView)itemView.findViewById(R.id.searchview_recycleritem_secitem);
            searchview_recycleritem_seemore=(TextView)itemView.findViewById(R.id.searchview_recycleritem_seemore);
            searchview_recycleritem_seemore.setOnClickListener(this);
            searchview_recycleritem_secitem.setOnClickListener(this);
            searchview_recycleritem_firstitem.setOnClickListener(this);*/
        }

        @Override
        public void onClick(View view) {
            /*if (view.getId()==R.id.searchview_recycleritem_firstitem)
            {
                searchViewToFrag.newsCardOnClick("",
                        "",
                        "",
                        globalSearchGetData.getContent().get(0).getProfilePic(),
                        globalSearchGetData.getContent().get(0).getName(),
                        globalSearchGetData.getContent().get(0).getName(),
                        new NewsOnClickFragment(),
                        globalSearchGetData.getContent().get(0).getContentType(),
                        userId,
                        globalSearchGetData.getContent().get(0).getId());
            }
            else if (view.getId()==R.id.searchview_recycleritem_secitem)
            {
                searchViewToFrag.newsCardOnClick("",
                        "",
                        "",
                        globalSearchGetData.getContent().get(1).getProfilePic(),
                        globalSearchGetData.getContent().get(1).getName(),
                        globalSearchGetData.getContent().get(1).getName(),
                        new NewsOnClickFragment(),
                        globalSearchGetData.getContent().get(1).getContentType(),
                        userId,
                        globalSearchGetData.getContent().get(1).getId());
            }
            else if (view.getId()==R.id.searchview_recycleritem_seemore)
            {

            }*/
        }
    }

    public class ViewHolder4 extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView searchview_recycleritem_topname,searchview_recycleritem_firstitem,
                searchview_recycleritem_secitem,searchview_recycleritem_seemore;
        RecyclerView recyclerView;
        public ViewHolder4(View itemView) {
            super(itemView);
            searchview_recycleritem_topname=(TextView)itemView.findViewById(R.id.searchview_recycleritem_topname);
            recyclerView=(RecyclerView)itemView.findViewById(R.id.searchitem_recyclerview);
            /*searchview_recycleritem_firstitem=(TextView)itemView.findViewById(R.id.searchview_recycleritem_firstitem);
            searchview_recycleritem_secitem=(TextView)itemView.findViewById(R.id.searchview_recycleritem_secitem);
            searchview_recycleritem_seemore=(TextView)itemView.findViewById(R.id.searchview_recycleritem_seemore);
            searchview_recycleritem_seemore.setOnClickListener(this);
            searchview_recycleritem_secitem.setOnClickListener(this);
            searchview_recycleritem_firstitem.setOnClickListener(this);*/
        }

        @Override
        public void onClick(View view) {

        }
    }

    public class ViewHolder5 extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView searchview_recycleritem_topname,searchview_recycleritem_firstitem,
                searchview_recycleritem_secitem,searchview_recycleritem_seemore;
        RecyclerView recyclerView;
        public ViewHolder5(View itemView) {
            super(itemView);
            searchview_recycleritem_topname=(TextView)itemView.findViewById(R.id.searchview_recycleritem_topname);
            recyclerView=(RecyclerView)itemView.findViewById(R.id.searchitem_recyclerview);
            /*searchview_recycleritem_firstitem=(TextView)itemView.findViewById(R.id.searchview_recycleritem_firstitem);
            searchview_recycleritem_secitem=(TextView)itemView.findViewById(R.id.searchview_recycleritem_secitem);
            searchview_recycleritem_seemore=(TextView)itemView.findViewById(R.id.searchview_recycleritem_seemore);
            searchview_recycleritem_seemore.setOnClickListener(this);
            searchview_recycleritem_secitem.setOnClickListener(this);
            searchview_recycleritem_firstitem.setOnClickListener(this);*/
        }

        @Override
        public void onClick(View view) {

        }
    }

    public class ViewHolder6 extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView searchview_recycleritem_topname,searchview_recycleritem_firstitem,
                searchview_recycleritem_secitem,searchview_recycleritem_seemore;
        RecyclerView recyclerView;
        public ViewHolder6(View itemView) {
            super(itemView);
            searchview_recycleritem_topname=(TextView)itemView.findViewById(R.id.searchview_recycleritem_topname);
            recyclerView=(RecyclerView)itemView.findViewById(R.id.searchitem_recyclerview);
            /*searchview_recycleritem_firstitem=(TextView)itemView.findViewById(R.id.searchview_recycleritem_firstitem);
            searchview_recycleritem_secitem=(TextView)itemView.findViewById(R.id.searchview_recycleritem_secitem);
            searchview_recycleritem_seemore=(TextView)itemView.findViewById(R.id.searchview_recycleritem_seemore);
            searchview_recycleritem_seemore.setOnClickListener(this);
            searchview_recycleritem_secitem.setOnClickListener(this);
            searchview_recycleritem_firstitem.setOnClickListener(this);*/
        }

        @Override
        public void onClick(View view) {

        }
    }
}
