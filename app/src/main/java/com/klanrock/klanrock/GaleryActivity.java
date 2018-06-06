package com.klanrock.klanrock;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GaleryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GaleryAdapter adapter;
    private List<Galery> galeryList;
    private Toolbar toolbar;
    private ProgressDialog pd;
    final String URL = ServerUrl.URL+"galery/get_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galery);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        initCollapsingToolbar();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        galeryList = new ArrayList<>();
        adapter = new GaleryAdapter(this, galeryList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

//        prepareAlbums();
        request_data();

        try {
            Glide.with(this).load(R.drawable.pemandangan).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);
        final int a = toolbar.getDrawingCacheBackgroundColor();

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    toolbar.setBackgroundColor(getResources().getColor(R.color.colorEbony));

                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    toolbar.setBackgroundColor(a);
                    isShow = false;
                }
            }
        });
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    public void request_data(){
        pd = new ProgressDialog(this);
        pd.setMessage("Loading...");
        pd.show();
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest strReq = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.hide();
                        try{
                            JSONObject jsObj = new JSONObject(response);
                            JSONArray galery = jsObj.getJSONArray("galery");
                            for (int i=0;i<galery.length();i++){
                                JSONObject g = galery.getJSONObject(i);
                                String name = g.getString("nama_galery");
                                String id = g.getString("id");
                                String url_gambar = g.getString("url_gambar");
                                String keterangan = g.getString("keterangan");
                                String jumlah_foto = g.getString("jumlah_foto");
                                Galery galeryku = new Galery(name,url_gambar,"See all",id,jumlah_foto,null);
                                galeryList.add(galeryku);
                            }
                            adapter.notifyDataSetChanged();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }

                }
        );
        strReq.setRetryPolicy(new DefaultRetryPolicy(0,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(strReq);

    }
}
