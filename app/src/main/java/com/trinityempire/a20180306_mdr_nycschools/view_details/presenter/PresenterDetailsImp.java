package com.trinityempire.a20180306_mdr_nycschools.view_details.presenter;

import android.content.Context;

import com.trinityempire.a20180306_mdr_nycschools.view_details.model.GetSATItemsIteratorModel;
import com.trinityempire.a20180306_mdr_nycschools.view_details.SchoolDetailsView;
import com.trinityempire.a20180306_mdr_nycschools.logs.SchoolsLog;
import com.trinityempire.a20180306_mdr_nycschools.view_main.model.Schools;

import java.util.HashMap;

/**
 * Created by disciplemarc on 3/7/18.
 */

public class PresenterDetailsImp implements PresenterDetails, GetSATItemsIteratorModel.OnFinishListener {

    private SchoolDetailsView schoolDetailsView;
    private GetSATItemsIteratorModel getSATItemsIteratorModel;
    public PresenterDetailsImp(SchoolDetailsView schoolDetailsView,
                               GetSATItemsIteratorModel getSATItemsIteratorModel, String school_name) {
        SchoolsLog.d("PresenterDetailsImp is called");
        this.schoolDetailsView = schoolDetailsView;
        this.getSATItemsIteratorModel = getSATItemsIteratorModel;
    }
    @Override
    public void onFindSchoolSAT(String dbn) {
        SchoolsLog.d("onFindSchoolSAT() is called");
        if (schoolDetailsView != null) {
            schoolDetailsView.showProgress();
        }
        getSATItemsIteratorModel.getItems(this,dbn);
    }

    @Override
    public void onSetupDataHash(Schools data) {
        SchoolsLog.d("onSetupDataHash() called, passing data to model");
        getSATItemsIteratorModel.setUpItems(this,data);
    }

    @Override
    public void favOn(Context context, String school_name) {
        SchoolsLog.d("favOn() called, passing data to model");

        getSATItemsIteratorModel.getIfFavOnItem(this,context,school_name);
    }
    @Override
    public void onFinishListener(String item) {
        SchoolsLog.d("onFinishListener() is called school");
        if (schoolDetailsView != null) {
            schoolDetailsView.setItems(item);
            schoolDetailsView.hideProgress();
        }
    }

    @Override
    public void onFinishListener(HashMap<String, String> item) {
        SchoolsLog.d("onFinishListener() is called passing hashmap to detials view");

        if (schoolDetailsView != null) {
            schoolDetailsView.setItems(item);
        }
    }

    @Override
    public void onFinisheListener(Boolean isFavOn) {
        SchoolsLog.d("onFinishListener() is called passing boolean for favorite button to detials view");

        if (schoolDetailsView != null) {
            schoolDetailsView.setUpFavOn(isFavOn);
        }
    }


}
