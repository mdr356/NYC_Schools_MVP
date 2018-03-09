package com.trinityempire.a20180306_mdr_nycschools.view_main;

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
import com.trinityempire.a20180306_mdr_nycschools.adapter.AdapterFragSchool;
import com.trinityempire.a20180306_mdr_nycschools.adapter.ItemClickListener;
import com.trinityempire.a20180306_mdr_nycschools.adapter.OnBottomReachedListener;
import com.trinityempire.a20180306_mdr_nycschools.view_main.model.GetItemsIteratorModelImp;
import com.trinityempire.a20180306_mdr_nycschools.view_main.presenter.Presenter;
import com.trinityempire.a20180306_mdr_nycschools.view_main.presenter.PresenterImp;
import com.trinityempire.a20180306_mdr_nycschools.logs.SchoolsLog;
import com.trinityempire.a20180306_mdr_nycschools.view_main.model.Schools;
import com.trinityempire.a20180306_mdr_nycschools.view_details.SchoolsDetailsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by disciplemarc on 3/5/18.
 */

public class FragmentSchools extends Fragment implements View.OnClickListener, FragSchoolView {

    private List<Schools> list = new ArrayList<>();
    AdapterFragSchool mAdapter;
    private int startVal = 10;
    private int offSetVal = 20;
    private ProgressBar progressBar1;

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        SchoolsLog.d("onCreateView() is called");
        View mView = inflater.inflate(R.layout.frag_schools, container, false);

        initView(mView);
        loadData();

        return mView;
    }


    private void initView(View view) {
        SchoolsLog.d("initView() is called to initialize the items in view");

        progressBar1 = (ProgressBar) view.findViewById(R.id.progressBar1);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        mAdapter = new AdapterFragSchool(list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnBottomReachedListener(new OnBottomReachedListener() {
            @Override
            public void onBottomReached(int position) {
                SchoolsLog.d("setOnBottomReachedListener() is called loading more data");
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
    private void loadData() {
        SchoolsLog.d("loadData() is called calling the presenter for data");

        Presenter presenter = new PresenterImp(this, new GetItemsIteratorModelImp(), startVal,offSetVal);
        presenter.onFindSchools();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void showProgress() {
        SchoolsLog.d("showProgress()");
        progressBar1.setVisibility(View.VISIBLE);
    }

    @Override
    public void setItems(List<Schools> items) {
        SchoolsLog.d("setItems()");

        list.addAll(items); // add to the list
        mAdapter.notifyDataSetChanged();
        SchoolsLog.d("notify adapter that data change happened");

    }

    @Override
    public void hideProgress() {
        SchoolsLog.d("hideProgress()");
        progressBar1.setVisibility(View.GONE);
    }
}
