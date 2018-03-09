package com.trinityempire.a20180306_mdr_nycschools.view_search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.trinityempire.a20180306_mdr_nycschools.R;
import com.trinityempire.a20180306_mdr_nycschools.MainBaseActivity;
import com.trinityempire.a20180306_mdr_nycschools.adapter.AdapterFragSchool;
import com.trinityempire.a20180306_mdr_nycschools.adapter.ItemClickListener;
import com.trinityempire.a20180306_mdr_nycschools.adapter.OnBottomReachedListener;
import com.trinityempire.a20180306_mdr_nycschools.logs.SchoolsLog;
import com.trinityempire.a20180306_mdr_nycschools.view_details.SchoolsDetailsActivity;
import com.trinityempire.a20180306_mdr_nycschools.view_main.model.Schools;
import com.trinityempire.a20180306_mdr_nycschools.view_search.model.GetSearchItemsIteratorModelImp;
import com.trinityempire.a20180306_mdr_nycschools.view_search.presenter.PresenterSearch;
import com.trinityempire.a20180306_mdr_nycschools.view_search.presenter.PresenterSearchImp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by disciplemarc on 3/9/18.
 */

public class FragmentSearch extends Fragment implements SearchView{

    private List<Schools> list = new ArrayList<>();
    private AdapterFragSchool mAdapter;
    private ProgressBar progressBar;
    private int startVal = 10;
    private int offSetVal = 20;

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        SchoolsLog.d("onCreateView() is called SearchView");
        View mView = inflater.inflate(R.layout.frag_search_view, container, false);

        initView(mView);
        loadData();

        return mView;
    }
    private void loadData() {
        SchoolsLog.d("loadData() is called calling the presentersearch for data SearchView");

        PresenterSearch presenter = new PresenterSearchImp(this,
                new GetSearchItemsIteratorModelImp(), MainBaseActivity.searchQuery, startVal,offSetVal);
        presenter.onFindSchoolsLocation();

    }

    private void initView(View view) {
        SchoolsLog.d("initView() is called to initialize the items in SearchView ");

        progressBar = (ProgressBar) view.findViewById(R.id.progressBarSearch);

        RecyclerView recyclerView = view.findViewById(R.id.recycleViewSearch);
        mAdapter = new AdapterFragSchool(list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnBottomReachedListener(new OnBottomReachedListener() {
            @Override
            public void onBottomReached(int position) {
                SchoolsLog.d("setOnBottomReachedListener() is called loading more data for SearchView");
                startVal = startVal + offSetVal;
                offSetVal = startVal + 20;

                loadData();
            }

        });
        mAdapter.setOnItemClickListener(new ItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                SchoolsLog.d("selected: "+list.get(position).getSchool_name());
                SchoolsLog.d("Navigating to "+getClass().getName());

                Intent intent = new Intent(getActivity(),SchoolsDetailsActivity.class);
                intent.putExtra("details","details");
                intent.putExtra("SCHOOL_OBJ",list.get(position));
                startActivity(intent);

            }
        });
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void setSearchItems(List<Schools> items) {
        SchoolsLog.d("setSearchItems() is called for SearchView");
        list.addAll(items); // add to the list
        mAdapter.notifyDataSetChanged();
        SchoolsLog.d("notify adapter that data change happened");
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }
}
