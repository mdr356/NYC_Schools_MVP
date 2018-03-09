package com.trinityempire.a20180306_mdr_nycschools.view_favorite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.trinityempire.a20180306_mdr_nycschools.R;
import com.trinityempire.a20180306_mdr_nycschools.adapter.AdapterFragSchool;
import com.trinityempire.a20180306_mdr_nycschools.adapter.ItemClickListener;
import com.trinityempire.a20180306_mdr_nycschools.adapter.LongClickListener;
import com.trinityempire.a20180306_mdr_nycschools.adapter.OnBottomReachedListener;
import com.trinityempire.a20180306_mdr_nycschools.database.DatabaseHandler;
import com.trinityempire.a20180306_mdr_nycschools.logs.SchoolsLog;
import com.trinityempire.a20180306_mdr_nycschools.view_favorite.model.GetFavitemsIteratorModelImpl;
import com.trinityempire.a20180306_mdr_nycschools.view_main.model.Schools;
import com.trinityempire.a20180306_mdr_nycschools.view_favorite.presenter.PresenterFav;
import com.trinityempire.a20180306_mdr_nycschools.view_favorite.presenter.PresenterFavImpl;
import com.trinityempire.a20180306_mdr_nycschools.view_details.SchoolsDetailsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by disciplemarc on 3/6/18.
 */

public class FavoriteActivity extends AppCompatActivity implements FavView {

    private List<Schools> list = new ArrayList<>();
    private ProgressBar progressBar2;
    private AdapterFragSchool mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        SchoolsLog.d("onCreate() Favorite view is called.");
        setTitle(R.string.favorite_title);

        initView();
        PresenterFav presenterFav = new PresenterFavImpl(this, new GetFavitemsIteratorModelImpl(),this);
        presenterFav.onFindFav();
    }

    private void initView() {
        SchoolsLog.d("initView() is called inside Favorite view");
        SchoolsLog.d("initView() is called to initialize the items in favorite view");

        progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView2);
        mAdapter = new AdapterFragSchool(list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                SchoolsLog.d("selected: "+list.get(position).getSchool_name());
                SchoolsLog.d("Navigating to "+getClass().getName());

                Intent intent = new Intent(FavoriteActivity.this,SchoolsDetailsActivity.class);
                intent.putExtra("details","details");
                intent.putExtra("SCHOOL_OBJ",list.get(position));
                startActivity(intent);

            }
        });

        mAdapter.setOnBottomReachedListener(new OnBottomReachedListener() {
            @Override
            public void onBottomReached(int position) {

            }
        });
        mAdapter.setOnLongClickListener(new LongClickListener() {
            @Override
            public void LongClickListener(int position) {
                DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                boolean isDeleted = false;
                try {
                    isDeleted = db.deleteData(list.get(position).getSchool_name());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                db.close();
                if (isDeleted) {
                    mAdapter.notifyDataSetChanged();
                    Toast.makeText(FavoriteActivity.this, "Deleted from list", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(FavoriteActivity.this, "Error deleting data", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
    @Override
    public void showProgress() {
        progressBar2.setVisibility(View.VISIBLE);
    }

    @Override
    public void setItems(List<Schools> items) {
        SchoolsLog.d("setItems() in favorite recyclervieqw");

        list.addAll(items); // add to the list
        mAdapter.notifyDataSetChanged();
        SchoolsLog.d("notify adapter that data change happened");

    }

    @Override
    public void hideProgress() {
        progressBar2.setVisibility(View.GONE);
    }
}
