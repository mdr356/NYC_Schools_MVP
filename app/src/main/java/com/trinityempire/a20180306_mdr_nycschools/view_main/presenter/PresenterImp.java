package com.trinityempire.a20180306_mdr_nycschools.view_main.presenter;

import com.trinityempire.a20180306_mdr_nycschools.logs.SchoolsLog;
import com.trinityempire.a20180306_mdr_nycschools.view_main.model.GetItemsIteratorModel;
import com.trinityempire.a20180306_mdr_nycschools.view_main.model.Schools;
import com.trinityempire.a20180306_mdr_nycschools.view_main.FragSchoolView;

import java.util.List;

/**
 * Created by disciplemarc on 3/6/18.
 */

public class PresenterImp implements Presenter, GetItemsIteratorModel.OnFinishListener {
    private FragSchoolView fragSchoolView;
    private GetItemsIteratorModel getItemsIteratorModel;
    private int startVal;
    private int offsetVal;

    public PresenterImp(FragSchoolView fragSchoolView, GetItemsIteratorModel getItemsIteratorModel, int startVal, int offsetVal) {
        SchoolsLog.d("PresenterImp() constructor called");
        this.fragSchoolView = fragSchoolView;
        this.getItemsIteratorModel = getItemsIteratorModel;
        this.startVal = startVal;
        this.offsetVal = offsetVal;
    }
    @Override
    public void onFindSchools () {
        SchoolsLog.d("onFind() constructor called");

        if (fragSchoolView != null) {
            fragSchoolView.showProgress();
        }
        getItemsIteratorModel.getItems(this, startVal, offsetVal);
    }

    @Override
    public void onFinishListener(List<Schools> items) {
        SchoolsLog.d("getItemsIteratorModel() is called");

        if (fragSchoolView !=null) {
            fragSchoolView.setItems(items);
            fragSchoolView.hideProgress();
        }
    }
}
